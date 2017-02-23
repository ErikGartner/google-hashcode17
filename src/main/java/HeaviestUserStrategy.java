import java.util.*;

/**
 * Created by erik on 2017-02-23.
 */
public class HeaviestUserStrategy extends Strategy{


    public void apply(Map<Integer, Cache> cacheMap, List<Request> requests, ArrayList<Video> videos, List<Endpoint> endpoints, int maxSize) {

        for(Cache cache: cacheMap.values()){

            int size = 0;


            // Score each endpoint
            Map<Endpoint,Integer> bestEndpoints = new HashMap<Endpoint, Integer>();
            for(Endpoint e: cache.endpoints) {
                int score = 0;
                for(Request r: e.requests){
                    score += r.no * e.latency - e.cacheLatency.get(cache);
                }
                bestEndpoints.put(e, score);
            }

            // Sort by endpoint score
            LinkedList<Map.Entry<Endpoint, Integer>> sortedEndpoints = new LinkedList<Map.Entry<Endpoint, Integer>>(bestEndpoints.entrySet());

            while(sortedEndpoints.size() > 0){

                // Get the best endpoint
                Endpoint bestEndpoint = sortedEndpoints.removeLast().getKey();

                Map<Video, Integer> videoUsage = new HashMap<Video,Integer>();
                for(Request r: bestEndpoint.requests) {
                    if(!videoUsage.containsKey(r.video)){
                        videoUsage.put(r.video, 0);
                    }
                    int score = videoUsage.get(r.video);
                    score += r.no;
                }

                LinkedList<Map.Entry<Video, Integer>> sortedVideos = new LinkedList<Map.Entry<Video, Integer>>(videoUsage.entrySet());
                Collections.sort(sortedVideos, new ValueSorter());

                boolean nextCache = false;
                while(size < maxSize) {
                    if(sortedVideos.size() == 0){
                        break;
                    }
                    Video v = sortedVideos.removeLast().getKey();
                    if(size + v.size< maxSize){
                        cache.cacheVideo(v);
                        size += v.size;
                    }else{
                        nextCache = true;
                        break;
                    }
                }

                if(nextCache){
                    break;
                }
            }

        }


    }
}

class ValueSorter<T> implements Comparator<Map.Entry<T, Integer>> {

    public int compare(Map.Entry<T, Integer> o1, Map.Entry<T, Integer> o2) {
        return o1.getValue() - o2.getValue();
    }
}