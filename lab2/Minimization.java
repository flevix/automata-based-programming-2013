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
    int n;

    double query(int dimension, double coordinate) {
        for (int i = 0; i < n; i++) {
            out.print(i != dimension ? "0.0 " : coordinate + " ");
        }
        out.println();
        out.flush();
        return in.nextDouble();
    }

    double ternarySearch() {
        query(0,1.0);
        return 0.0;
    }

    public void solve() throws IOException {
        n = in.nextInt();
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
