import java.util.*;

/**
 * Created by erik on 2017-02-23.
 */
public class BigRequestsStrategy extends Strategy{

    public void apply(Map<Integer, Cache> cacheMap, List<Request> requests, ArrayList<Video> videos, List<Endpoint> endpoints, int maxSize) {

        Collections.sort(requests, new BigRequestsComparator());

        for(Request r: requests) {

         //   LinkedList<Map.Entry<Cache, Integer>> bestCache = new LinkedList<Map.Entry<Video, Integer>>(r.endpoint.cacheGains.entrySet());
            //Collections.sort(sortedVideos, new ValueSorter());
        }

    }
}

class BigRequestsComparator implements Comparator<Request>{

    public int compare(Request o1, Request o2) {
        return o1.no - o2.no;
    }
}