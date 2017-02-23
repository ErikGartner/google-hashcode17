import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by erik on 2017-02-23.
 */
public class Endpoint {

    public int latency;
    public Map<Cache, Integer> cacheLatency;
    public int maxLatencyGain = 0;
    public Map<Cache, Integer> cacheGains = new HashMap<Cache, Integer>();
    public LinkedList<Request> requests;

    public Endpoint(int latency, Map<Cache, Integer> cacheLatency){
        this.cacheLatency = cacheLatency;
        this.latency = latency;
        requests = new LinkedList<Request>();
        for(Cache c: cacheLatency.keySet()){
            cacheGains.put(c, latency - cacheLatency.get(c));
            this.maxLatencyGain = Math.max(latency - cacheLatency.get(c), this.maxLatencyGain);
        }

    }

    public void addRequest(Request r){
        requests.add(r);
    }

    public boolean isCached(Video video){
        for(Cache ca : cacheLatency.keySet()){
            if(ca.videos.contains(video)){
                return true;
            }
        }
        return false;
    }

}
