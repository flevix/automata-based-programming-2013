import java.io.*;
import java.util.StringTokenizer;
import java.lang.Math;

public class Scs {

    private void solve(final FastScanner in, final PrintWriter out) {
    }
    
    private static int overlap(final String s1, final String s2) {
        final int len = Math.min(s1.length(), s2.length());
        final int lenStr1 = s1.length();
        int overlap = 0;
        for (int i = 1; i <= len; i++) {
            String suffix = s1.substring(lenStr1 - i, lenStr1);
            String prefix = s2.substring(0, i);
            if (suffix.equals(prefix)) {
                overlap = i;
            }
        }
        return overlap;
    }

    public void run(final String fileName) {
        try {
            final FastScanner in = new FastScanner(new File(fileName + ".in"));
            final PrintWriter out = new PrintWriter(new File(fileName + ".out"));
 
            solve(in, out);
 
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        new Scs().run("scs");
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
