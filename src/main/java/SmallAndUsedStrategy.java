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

            Video v = videos.get(0);
            while(myAllocatedMemory + v.size <= maxSize){
                if(c.cacheVideo(v)) {
                    myAllocatedMemory += v.size;
                }
                index++;
                if(index >= videos.size()){
                    break;
                }
                v = videos.get(index);
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

