import java.util.*;

/**
 * Created by RTG on 2017-02-23.
 */
public class SmallAndUsedStrategy extends Strategy {

    int gainz = 0;

    public void apply(Map<Integer, Cache> cacheMap, List<Request> requests, ArrayList<Video> videos, List<Endpoint> endpoints, int i) {
        for(Cache c: cacheMap.values()){
            int index = 0;
            Collections.sort(videos);
            int myAllocatedMemory = 0;
            while(myAllocatedMemory < i){
                c.cacheVideo((videos.get(index)));
                index++;
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
