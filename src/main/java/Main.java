import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by erik on 2017-02-23.
 */
public class Main {

    public static void main(String args[]) throws FileNotFoundException {

        /* READ from file using Kattio */
        Kattio io = new Kattio(new FileInputStream(new File(args[0])), new FileOutputStream(args[0] + ".out"));

        int v = io.getInt();
        int e = io.getInt();
        int r = io.getInt();
        int c = io.getInt();
        int size = io.getInt();

        ArrayList<Video> videos = new ArrayList<Video>(v);
        for(int i = 0; i < v; i++){
            videos.add(i, new Video(i, io.getInt()));
        }

        List<Endpoint> endpoints= new ArrayList<Endpoint>(e);
        Map<Integer, Cache> cacheMap = new HashMap<Integer,Cache>(c);
        for(int i = 0; i < e; i++){

            int latency = io.getInt();
            int caches = io.getInt();

            Map<Cache, Integer> latencyMap = new HashMap<Cache, Integer>();

            for(int j = 0; j < caches; j++){
                int cacheId = io.getInt();
                int cacheLatency = io.getInt();
                Cache cache = cacheMap.get(cacheId);
                if(cache == null){
                    cache = new Cache(size, cacheId);
                    cacheMap.put(cacheId, cache);
                }
                latencyMap.put(cache, cacheLatency);
            }

            Endpoint endpoint = new Endpoint(latency, latencyMap);
            endpoints.add(endpoint);
            for(Cache cache: latencyMap.keySet()){
                cache.addEndpoint(endpoint);
            }
        }

        List<Request> requests = new ArrayList<Request>(r);
        for(int i = 0; i < r; i++){

            int id = io.getInt();
            int endpoint = io.getInt();
            int no = io.getInt();
            Request newRequest = new Request(videos.get(id), endpoints.get(endpoint), no);
            endpoints.get(endpoint).addRequest(newRequest);
            videos.get(id).addRequest(newRequest);
            requests.add(newRequest);
        }

        HeaviestUserStrategy hus = new HeaviestUserStrategy();
        SmallAndUsedStrategy sus = new SmallAndUsedStrategy();
        BigRequestsStrategy bus = new BigRequestsStrategy();

        // always last
        //hus.apply(cacheMap, requests, videos, endpoints, size/4);
        //sus.apply(cacheMap, requests, videos, endpoints, size/10);
        bus.apply(cacheMap, requests, videos, endpoints, size);


        StringBuilder sb = new StringBuilder();
        sb.append(cacheMap.size());
        for(int newCacheId: cacheMap.keySet()){
            sb.append("\n");
            cacheMap.get(newCacheId).toString(sb);
        }

        io.print(sb.toString());

        // Close and flush IO.
        io.close();
    }


}
