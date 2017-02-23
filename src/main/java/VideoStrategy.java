import java.util.*;

/**
 * Created by erik on 2017-02-23.
 */
public class VideoStrategy extends Strategy {
    public void apply(Map<Integer, Cache> cacheMap, List<Request> requests, ArrayList<Video> videos, List<Endpoint> endpoints, int maxSize) {
        LinkedList<Video> sortedVideo = new LinkedList<Video>(videos);
        Collections.sort(sortedVideo, new VideoComp());

        for(Video v: sortedVideo){

            LinkedList<Cache> availCache = new LinkedList<Cache>(cacheMap.values());
            Collections.sort(availCache, new CacheComp());

            while(availCache.size()> 0){
                Cache c = availCache.removeFirst();
                c.cacheVideo(v);
                availCache.remove(c);
                for(Endpoint e: c.endpoints){
                    availCache.removeAll(e.cacheGains.keySet());
                }
            }

        }
    }
}

class VideoComp implements Comparator<Video> {

    public int compare(Video o1, Video o2) {
        int v1 =0 , v2 = 0;
        for(Request r: o1.requests)
            v1 += r.no;
        for(Request r: o2.requests)
            v2 += r.no;
        return  v2 - v1;
    }
}

class CacheComp implements Comparator<Cache> {

    public int compare(Cache o1, Cache o2) {

        return  o1.endpoints.size() - o2.endpoints.size();
    }
}