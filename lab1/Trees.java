import java.util.*;
import java.io.*;

/**
 * Created with VIM.
 * User: flevix
 */

public class Trees {
    FastScanner in;
    PrintWriter out;
    int[] zero;
    int[] one;
    int[] stat;
    int[] value;
    int[] id;
    int size = 0;
    Map<Integer, Integer> path = new HashMap<Integer, Integer>();

    void dfs(int v) {
        if (stat[v] == 1) {
            if (path.containsKey(value[v])) {
                int tr = path.get(value[v]);
                int u = (tr == 0) ? zero[v] : one[v];
                if (stat[u] == 0) {
                    value[v] = value[u];
                    zero[v] = -1;
                    one[v] = -1;
                    stat[v] = 0;
                    id[v] = ++size;
                    return;
                } else {
                    stat[v] = 1;
                    zero[v] = zero[u];
                    one[v] = one[u];
                    value[v] = value[u];
                    dfs(v);
                    return;
                }
            }
            id[v] = ++size;
            path.put(value[v], 0);
            dfs(zero[v]);
            path.put(value[v], 1);
            dfs(one[v]);
            path.remove(value[v]);
        } else {
            id[v] = ++size;
        }
    }

    void print(int v) {
        if (stat[v] == 0) {
            out.println("leaf " + value[v]);
        } else {
            out.println("choice " + value[v] + " " + (id[zero[v]])
                                + " " + (id[one[v]]));
            print(zero[v]);
            print(one[v]);
        }
    }

    void solve() throws IOException {
        int k = in.nextInt();
        zero = new int[k];
        one = new int[k];
        stat = new int[k];
        value = new int[k];
        id = new int[k];
        for (int i = 0; i < k; i++) {
            String s = in.next();
            if (s.charAt(0) == 'c') {
                stat[i] = 1;
                value[i] = in.nextInt();//p
                zero[i] = in.nextInt() - 1;//f
                one[i] = in.nextInt() - 1;//t
            } else {
                //stat[i] = 0;
                value[i] = in.nextInt();//a
            }
            id[i] = i;
        }
        dfs(0);
        out.println(size);
        print(0);
    }

    void run() {
        try {
            in = new FastScanner(new File("trees.in"));
            out = new PrintWriter(new File("trees.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Trees().run();
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
