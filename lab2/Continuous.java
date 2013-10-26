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
        int n = in.nextInt();
        int m = in.nextInt();
        int[] zero = new int[n];
        int[] one = new int[n];
        for (int i = 0; i < n; i++) {
            zero[i] = in.nextInt() - 1;
            one[i] = in.nextInt() - 1;
        }
        for (int i = 0; i < m; i++) {
            int currState = 0;
            int length = in.nextInt();
            char[] currIn = in.next().toCharArray();
            for (int j = 0; j < length; j++) {
                double currOut = in.nextDouble();

                currState = (currIn[j] == '0') ? zero[currState]
                                                : one[currState];
            }
        }

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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
