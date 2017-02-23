import java.util.*;

/**
 * Created by erik on 2017-02-23.
 */
public class BigRequestsStrategy extends Strategy{

    public void apply(Map<Integer, Cache> cacheMap, List<Request> requests, ArrayList<Video> videos, List<Endpoint> endpoints, int maxSize) {

        Collections.sort(requests, new BigRequestsComparator());
        for(Request r: requests) {

            if(r.endpoint.isCached(r.video)){
                continue;
            }

            LinkedList<Map.Entry<Cache, Integer>> sortedCaches = new LinkedList<Map.Entry<Cache, Integer>>(r.endpoint.cacheGains.entrySet());
            Collections.sort(sortedCaches, new ValueSorter());

            boolean success = false;
            for(Map.Entry<Cache, Integer> mec: sortedCaches){
                if(mec.getKey().cacheVideo(r.video)){
                    success = true;
                    break;
                }
            }

        }

    }
}

class BigRequestsComparator implements Comparator<Request>{

    public int compare(Request o1, Request o2) {
        return o1.no * o1.endpoint.maxLatencyGain - o2.no * o2.endpoint.maxLatencyGain;
    }
}