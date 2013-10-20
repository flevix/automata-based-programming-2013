import java.util.*;
import java.io.*;

/**
 * Created with VIM.
 * User: flevix
*/

public class Minimization {
    FastScanner in;
    PrintWriter out;

    final static int REQUESTS = 998;
    final static int SEGMENTS = 40;
    final static int ITERATIONS = REQUESTS / SEGMENTS;

    double ternarySearch() {
        return 0.0;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        double[] points = new double[n];
        for (int i = 0; i < n; i++) {
            points[i] = ternarySearch();
        }
        for (int i = 0; i < n - 1; i++) {
            out.print(points[i] + " ");
        }
        out.println(points[n - 1]);
        out.flush();
        out.println(in.nextDouble());
        out.flush();
    }

    public void run() {
        try {
            in = new FastScanner();
            out = new PrintWriter(System.out);

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Minimization().run();
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
