import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by RTG on 2017-02-23.
 */
public class SmallAndUsedStrategy extends Strategy {

    public void apply(Map<Integer, Cache> cacheMap, List<Request> requests, ArrayList<Video> videos, List<Endpoint> endpoints, int i) {

        for(Cache c: cacheMap.values()){
            int index = 0;
           // videos.sort();
            int myAllocatedMemory = 0;
            while(myAllocatedMemory > i){
                c.videos.add(videos.get(index));
                index++;
            }
        }
    }
}
