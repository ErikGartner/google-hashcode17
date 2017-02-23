import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by erik on 2017-02-23.
 */
public class Cache {

    public double usedMemory = 0;
    public Set<Video> videos = new HashSet<Video>();
    public List<Endpoint> endpoints = new ArrayList<Endpoint>();
    public final int size;

    public Cache(int size){
        this.size = size;
    }

    public double cacheVideo(Video newVideo) {

        videos.add(newVideo);
        int totalSize = 0;
        for (Video v : videos) {
            totalSize = totalSize + v.size;
        }

        usedMemory = totalSize / size;
        return usedMemory;
    }

    public void addEndpoint(Endpoint e){
        endpoints.add(e);
    }
}
