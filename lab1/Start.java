import java.util.*;
import java.io.*;

/**
 * Created with VIM.
 * User: flevix
*/

public class Start {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int m = in.nextInt();
        int n = in.nextInt();
        int[] zero = new int[n];
        int[] one = new int[n];
        char[] ch = new char[n];
        for (int i = 0; i < n; i++) {
            zero[i] = in.nextInt() - 1;
            one[i] = in.nextInt() - 1;
            ch[i] = in.getFirstChar();
        }
        String s = in.next();
        int[][] table = new int[2][n];
        int layer = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(s.length() - 1);
            table[layer][i] = (ch[zero[i]] == c || ch[one[i]] == c)
                                ? 1 : 0;
        }
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(s.length() - i - 1);
            layer ^= 1;
            for (int j = 0; j < n; j++) {
                table[layer][j] = 
                        ((ch[zero[j]] == c && table[layer ^ 1][zero[j]] == 1) ||
                        (ch[one[j]] == c && table[layer ^ 1][one[j]] == 1))
                        ? 1 : 0;
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (table[layer][i] == 1) {
                count += 1;
            }
        }
        out.print(count);
        for (int i = 0; i < n; i++) {
            if (table[layer][i] == 1) {
                out.print(" " + (i + 1));
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("start.in"));
            out = new PrintWriter(new File("start.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Start().run();
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

        char getFirstChar() {
            return st.nextToken().charAt(0);
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
