import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by erik on 2017-02-23.
 */
public class Main {

    public static void main(String args[]) throws FileNotFoundException {

        /* READ from file using Kattio */
        Kattio io = new Kattio(new FileInputStream(new File(args[0])), new FileOutputStream(args[0] + ".out"));

        while (io.hasMoreTokens()) {
            io.getWord();
        }



        // Close and flush IO.
        io.close();
    }


}
