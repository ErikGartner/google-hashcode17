/**
 * Created by erik on 2017-02-23.
 */
import java.util.LinkedList;
public class Video {

    public final int size;
    public final int id;
    public LinkedList<Request> requests;

    public Video(int id, int size){
        this.size = size;
        this.id = id;
        requests = new LinkedList<Request>();
    }

    public void addRequest(Request r){
        requests.add(r);
    }
}
