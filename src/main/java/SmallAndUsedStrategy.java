import java.util.*;

/**
 * Created by RTG on 2017-02-23.
 */
public class SmallAndUsedStrategy extends Strategy {

    int gainz = 0;

    public void apply(Map<Integer, Cache> cacheMap, List<Request> requests, ArrayList<Video> videos, List<Endpoint> endpoints, int maxSize) {

        Collections.sort(videos, new VideoSizeComparator());

        for(Cache c: cacheMap.values()){

            int myAllocatedMemory = 0;
            int index = 0;


            Map<Video, Double> videoUsage = new HashMap<Video,Double>();
            for(Endpoint endpoint: c.endpoints){
                for(Request r: endpoint.requests) {
                    if(!videoUsage.containsKey(r.video)){
                        videoUsage.put(r.video, 1.0 / r.video.size);
                    }
                    double score = videoUsage.get(r.video);
                    score += r.no*(r.endpoint.latency - r.endpoint.cacheLatency.get(c));
                }
            }

            LinkedList<Map.Entry<Video, Double>> sortedVideos = new LinkedList<Map.Entry<Video, Double>>(videoUsage.entrySet());
            Collections.sort(sortedVideos, new DValueSorter());


            Video v = sortedVideos.removeLast().getKey();
            while(myAllocatedMemory + v.size <= maxSize){
                if(c.cacheVideo(v)) {
                    myAllocatedMemory += v.size;
                }
                index++;
                if(index >= sortedVideos.size()){
                    break;
                }
                v = sortedVideos.removeLast().getKey();
            }

            for (Request r : requests){
                if(c.videos.contains(r.video) && c.endpoints.contains(r.endpoint)){
                    gainz += r.no*(r.endpoint.latency - r.endpoint.cacheLatency.get(c));
                }
            }
        }
    }

    public String toString(){
        return "Latency saved =" + gainz;
    }
}

