import java.util.*;

/**
 * Created by erik on 2017-02-23.
 */
public class HeaviestUserStrategy extends Strategy{


    public void apply(Map<Integer, Cache> cacheMap, List<Request> requests, ArrayList<Video> videos, List<Endpoint> endpoints, int maxSize) {

        for(Cache cache: cacheMap.values()){

            int bestScore = 0;
            Endpoint bestEndpoint = null;
            for(Endpoint e: cache.endpoints) {
                int score = 0;
                for(Request r: e.requests){
                    score += r.no * e.latency - e.cacheLatency.get(cache);
                }

                if(score > bestScore){
                    bestEndpoint = e;
                }
            }

            if(bestEndpoint == null){
                return;
            }

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

            int size = 0;
            while(size < maxSize) {
                Video v = sortedVideos.removeLast().getKey();
                if(size + v.size< maxSize){
                    cache.cacheVideo(v);
                }else{
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