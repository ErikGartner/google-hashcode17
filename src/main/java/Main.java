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
                    cache = new Cache(size);
                    cacheMap.put(cacheId, cache);
                }
                latencyMap.put(cache, cacheLatency);
            }

            endpoints.add(new Endpoint(latency, latencyMap));
        }

        List<Request> requests = new ArrayList<Request>(r);
        for(int i = 0; i < r; i++){

            int id = io.getInt();
            int endpoint = io.getInt();
            int no = io.getInt();

            requests.add(new Request(videos.get(id), endpoints.get(endpoint), no));
        }

        HeaviestUserStrategy hus = new HeaviestUserStrategy();
        hus.apply(cacheMap, requests, videos, endpoints, size / 5);

        // Close and flush IO.
        io.close();
    }


}
