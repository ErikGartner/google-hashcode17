import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by erik on 2017-02-23.
 */
public class HeaviestUserStrategy extends Strategy{


    public void apply(Map<Integer, Cache> cacheMap, List<Request> requests, ArrayList<Video> videos, List<Endpoint> endpoints, int i) {

        for(Cache cache: cacheMap.values()){

            Map<Endpoint, Integer> usage = new HashMap<Endpoint, Integer>();
            for(Endpoint e: cache.endpoints) {
                if(!usage.containsKey(e)){
                    usage.put(e, 0);
                }



            }

        }


    }
}
