import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 03.10.13
 * Time: 20:06
 */

public class Crossover {
    FastScanner in;
    PrintWriter out;

    int check(int n, String s, String a1, String a2) {
        int flips = 0;
        for (int i = 0; i < n; i++) {
            if (flips % 2 == 0) {
                if (s.charAt(i) != a1.charAt(i)) {
                    flips += 1;
                    if (s.charAt(i) != a2.charAt(i)) {
                        return n + 1;
                    }
                }
            } else {
                if (s.charAt(i) != a2.charAt(i)) {
                    flips += 1;
                    if (s.charAt(i) != a1.charAt(i)) {
                        return n + 1;
                    }
                }
            }
        }
        return flips;
    }

    int checkFlips(int n, String s, String a1, String a2) {
        return Math.min(check(n, s, a1, a2), check(n, s, a2, a1));
    }

    public void solve() throws IOException {
        int m = in.nextInt();
        int n = in.nextInt();
        String[] set = new String[m];
        for (int i = 0; i < m; i++) {
            set[i] = in.next();
        }
        String s = in.next();
        boolean onePoint = false;
        boolean twoPoint = false;
        boolean uniform = false;
        for (int i = 0; i < m && !(onePoint && twoPoint && uniform); i++) {
            for (int j = 0; j < m && !(onePoint && twoPoint && uniform); j++) {
                int flips = checkFlips(n, s, set[i], set[j]);
                onePoint |= (flips < 2 && flips <= n);
                twoPoint |= (flips < 3 && flips <= n);
                uniform |= (flips <= n);
            }
        }
        out.println(onePoint ? "YES" : "NO");
        out.println(twoPoint ? "YES" : "NO");
        out.print(uniform ? "YES" : "NO");
    }

    public void run() {
        try {
            Locale.setDefault(Locale.ENGLISH);
            in = new FastScanner(new File("crossover.in"));
            out = new PrintWriter(new File("crossover.out"));

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
        new Crossover().run();
    }
}
