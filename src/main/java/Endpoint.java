import java.util.List;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by erik on 2017-02-23.
 */
public class Endpoint {

    public final int latency;
    public final Map<Cache, Integer> cacheLatency;
    public LinkedList<Request> requests;

    public Endpoint(int latency, Map<Cache, Integer> cacheLatency){
        this.cacheLatency = cacheLatency;
        this.latency = latency;
        requests = new LinkedList<Request>();
    }

    public void addRequest(Request r){
        requests.add(r);
    }
}
