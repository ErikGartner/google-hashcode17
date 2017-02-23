/**
 * Created by erik on 2017-02-23.
 */
public class Request {

    public final Video video;
    public final Endpoint endpoint;
    public final int no;

    public Request(Video video, Endpoint endpoint, int no) {

        this.video = video;
        this.endpoint = endpoint;
        this.no = no;
    }
}
