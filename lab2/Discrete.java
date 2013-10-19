import java.util.*;
import java.io.*;

/**
 * Created with VIM.
 * User: flevix
*/

public class Discrete {
    FastScanner in;
    PrintWriter out;

    final static int ALPHABET_SIZE = 26;
    final static int EVENT_COUNT = 2;
    int[] zero, one;
    double[][][] penalty;

    void countPenalty(String sIn, String sOut) {
        assert (sIn.length() == sOut.length()) : "Incorrect test";
        double lenPenalty = 1 / (double) sIn.length();
        int currState = 0;
        for (int i = 0; i < sIn.length(); i++) {
            int event = (sIn.charAt(i) == '0') ? 0 : 1;
            char effect = sOut.charAt(i);
            penalty[event][currState][effect - 'a'] += lenPenalty;
            currState = (event == 0) ? zero[currState] : one[currState];
        }
    }

    void dump(int n) {
        Formatter fmt;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < EVENT_COUNT; j++) {
                for (int c = 0; c < ALPHABET_SIZE; c++) {
                    if (penalty[j][i][c] != 0) {
                        fmt = new Formatter();
                        fmt.format("%.3f:%c ", penalty[j][i][c], c + 'a');
                        System.out.print(fmt);
                        fmt.close();
                    }
                }
                System.out.println();
            }
            System.out.println("--");
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        zero = new int[n];
        one = new int[n];
        penalty = new double[EVENT_COUNT][n][ALPHABET_SIZE];
        for (int i = 0; i < n; i++) {
            zero[i] = in.nextInt() - 1;
            one[i] = in.nextInt() - 1;
        }
        for (int i = 0; i < m; i++) {
            int len = in.nextInt();
            String sIn = in.next();
            String sOut = in.next();
            countPenalty(sIn, sOut);
        }
        //dump(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < EVENT_COUNT; j++) {
                double maxPenalty = Double.MIN_VALUE;
                int ch = ALPHABET_SIZE - 1; //if no effect for this event
                for (int c = 0; c < ALPHABET_SIZE; c++) {
                    if (maxPenalty < penalty[j][i][c]) {
                        maxPenalty = penalty[j][i][c];
                        ch = c;
                    }
                }
                out.print((char) (ch + 'a') + " ");
            }
            out.println();
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("discrete.in"));
            out = new PrintWriter(new File("discrete.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Discrete().run();
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
