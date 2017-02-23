import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by RTG on 2017-02-23.
 */
public class RandomStrategy extends Strategy{
    public void apply(Map<Integer, Cache> cacheMap, List<Request> requests, ArrayList<Video> videos, List<Endpoint> endpoints, int maxSize) {
        int allocatedMem = 0;
        for(Cache c : cacheMap.values()){
            for(int i = 0; i < 100; i++) {
                Video v = videos.get((int) Math.random() * videos.size());
                if(allocatedMem < maxSize && (v.size + allocatedMem) < maxSize) {
                    c.cacheVideo(v);
                }
            }
        }
    }
}
