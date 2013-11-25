import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map;

public class CountEntries {

    private void solve(FastScanner in, PrintWriter out) {
        int k = in.nextInt();
        int n = in.nextInt();

        Map<String, Integer> set = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int j = 0; j <= s.length() - k; j++) {
                String cur = s.substring(j, j + k);
                if (set.containsKey(cur)) {
                    Integer count = set.get(cur);
                    set.put(cur, count + 1);
                } else {
                    set.put(cur, 1);
                }
            }
        }
        for (Map.Entry<String, Integer> entry : set.entrySet()) {
            out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void run(String fileName) {
        try {
            FastScanner in = new FastScanner(new File(fileName + ".in"));
            PrintWriter out = new PrintWriter(new File(fileName + ".out"));
 
            solve(in, out);
 
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        new CountEntries().run("count-entries");
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
