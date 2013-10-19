import java.util.*;
import java.io.*;

/**
 * Created with VIM.
 * User: flevix
*/

public class Continuous {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
    }

    public void run() {
        try {
            in = new FastScanner(new File("continuous.in"));
            out = new PrintWriter(new File("continuous.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Continuous().run();
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
