import java.io.*;
import java.util.StringTokenizer;
import java.lang.Math;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Scs {

    private Set<String> getInputSet(final FastScanner in) {
        Set<String> strings = new HashSet<>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            strings.add(in.next());
        }
        return strings;
    }

    private List<String> getUniqueList(final Set<String> data) {
        Set<String> strings = new HashSet<>();
        for (String a : data) {
            boolean ok = true;
            for (String b : data) {
                if (b.contains(a) && !b.equals(a)) {
                    ok = false;
                }
            }
            if (ok) {
                strings.add(a);
            }
        }
        return new ArrayList<String>(strings);
    }

    private int[][] getOverlaps(final int n, final List<String> data) {
        int[][] overlaps = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    overlaps[i][j] = overlap(data.get(i), data.get(j));
                }
            }
        }
        return overlaps;
    }

    private void solve(final FastScanner in, final PrintWriter out) {
        List<String> list = getUniqueList(getInputSet(in));
        System.out.println(Arrays.toString(list.toArray()));//-
        final int n = list.size();
        int[][] overlaps = getOverlaps(n, list);
        for (int[] a : overlaps)//-
            System.out.println(Arrays.toString(a));//-
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
