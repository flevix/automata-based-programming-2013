import java.io.*;
import java.text.DecimalFormat;
import java.util.BitSet;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 03.10.13
 * Time: 2:31
 * <p/>
 * >:3
 * RAWR I'M A LION!
 * JESUS CHRIST IT'S A LION GET IN THE CAR!
 */

public class Mutation {
    FastScanner in;
    PrintWriter out;

    String calculateProbability(int n, Double denominator, String s, String t) throws IOException {
        BitSet bitSet = new BitSet(n);
        for (int i = 0; i < s.length(); i++) {
            bitSet.set(i, s.charAt(i) == t.charAt(i));
        }
        double numerator = Math.pow(n - 1, bitSet.cardinality());
        return new DecimalFormat("##.#########").format(numerator / denominator);
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        double denominator = Math.pow(n, n);
        for (int i = 0; i < m; i++) {
            out.println(calculateProbability(n, denominator, in.next(), in.next()));
        }
    }

    public void run() {
        try {
            Locale.setDefault(Locale.ENGLISH);
            in = new FastScanner(new File("mutation.in"));
            out = new PrintWriter(new File("mutation.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        char nextChar() {
            return st.nextToken().charAt(0);
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(String[] arg) throws IOException {
        new Mutation().run();
    }
}
