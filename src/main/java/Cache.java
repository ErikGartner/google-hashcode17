import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by erik on 2017-02-23.
 */
public class Cache {

    public int usedMemory = 0;
    public Set<Video> videos = new HashSet<Video>();
    public List<Endpoint> endpoints = new ArrayList<Endpoint>();
    public final int size;
    public final int id;

    public Cache(int size, int cacheId){
        this.size = size;
        this.id = cacheId;
    }

    public boolean cacheVideo(Video newVideo) {
        if(videos.contains(newVideo) || usedMemory + newVideo.size > size){
            return false;
        }
        videos.add(newVideo);
        usedMemory += newVideo.size;
        return true;
    }

    public void addEndpoint(Endpoint e){
        endpoints.add(e);
    }

    public StringBuilder toString(StringBuilder sb){
        sb.append(id);
        for(Video v: videos){
            sb.append(" " + v.id);
        }
        return sb;
    }
}
