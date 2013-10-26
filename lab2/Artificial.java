/**
 * Created Vim
 * User: flevix
 */
 
import java.io.*;
import java.util.*;
 
public class Artificial {
 
    FastScanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
        final String[][] ans = {
                {"3 2 L M", "3 3 R M", "2 4 R M", "2 1 M M"}
              , {"1 1 L M"}
              , {"2 3 L M", "3 2 R M", "1 2 M R"}
              , {"2 1 M M", "3 4 M M", "1 1 R L", "1 4 L M"}
              , {"3 2 M M", "1 1 R M", "2 3 M L"}
              , {"2 2 R M", "3 4 M M", "3 5 L M", "3 5 M M", "1 2 M M"}
              , {"2 4 R M", "2 3 L M", "2 4 M M", "1 3 M M"}
              , {"2 3 M M", "3 3 L M", "4 3 M M", "5 2 R M", "2 1 L M"}
              , {"2 3 M M", "4 3 R M", "5 6 M M", "6 6 L M", "1 2 R M", "5 1 L M"}
              , {"2 3 L M", "4 4 M M", "2 4 R M", "5 6 R M", "1 1 L M", "1 4 R M"}
        };
        int n = in.nextInt() - 1;
        for (int i = 0; i < ans[n].length; i++) {
            out.println(ans[n][i]);
        }
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("artificial.in"));
            out = new PrintWriter(new File("artificial.out"));
 
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
 
        int nextInt() {
            return Integer.parseInt(next());
        }
    }
 
    public static void main(String[] arg) {
        new Artificial().run();
    }
}
