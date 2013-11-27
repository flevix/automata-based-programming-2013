import java.io.*;
import java.util.StringTokenizer;
import java.lang.Math;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Scs {

    private class MinMaxPair {
        private int min;
        private int max;
        
        MinMaxPair(final int min, final int max) {
            setValues(min, max);
        }

        public void setValues(final int min, final int max) {
            this.min = min;
            this.max = max;
        }
        
        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        @Override
        public String toString() {
            return min + " " + max;
        }
    }

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

    private int[][] getDpArray(final int n, final int[][] overlaps) {
        final int maxMask = 1 << n;
        int[][] dp = new int[maxMask][n];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MIN_VALUE);
        }
        Arrays.fill(dp[0], 0);
        for (int mask = 1; mask < maxMask; mask++) {
            for (int k = 0; k < n; k++) {
                if ((mask & (1 << k)) != 0) {
                    continue;
                }
                for (int i = 0; i < n; i++) {
                    if ((mask ^ (1 << i)) <= mask) {
                        dp[mask][k] = Math.max(
                                        dp[mask][k],
                                        overlaps[k][i] + dp[mask ^ (1 << i)][i]);
                    }
                }
            }
        }
        return dp;
    }

    private MinMaxPair getMinMaxValues(final int n, final int[][] dp) {
        int mask = (1 << n) - 1;
        MinMaxPair p = new MinMaxPair(0, dp[mask - 1][0]);
        for (int i = 1; i < n; i++) {
            int value = dp[mask - (1 << i)][i];
            if (value > p.getMax()) {
                p.setValues(i, value);
            }
        }
        return p;
    }

    private void solve(final FastScanner in, final PrintWriter out) {
        List<String> list = getUniqueList(getInputSet(in));
        System.out.println(Arrays.toString(list.toArray()));//-
        final int n = list.size();
        int[][] overlaps = getOverlaps(n, list);
        for (int[] a : overlaps)//-
            System.out.println(Arrays.toString(a));//-
        int[][] dp = getDpArray(n, overlaps);
        for (int[] d : dp)//-
            System.out.println(Arrays.toString(d));//-
        MinMaxPair minMax = getMinMaxValues(n, dp);
        System.out.println(minMax);//-
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
