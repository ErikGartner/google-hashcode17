import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by erik on 2017-02-23.
 */
public abstract class Strategy {

    public abstract void apply(Map<Integer, Cache> cacheMap, List<Request> requests, ArrayList<Video> videos, List<Endpoint> endpoints, int maxSize);

}
