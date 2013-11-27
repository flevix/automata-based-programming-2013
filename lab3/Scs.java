import java.io.*;
import java.util.StringTokenizer;
import java.lang.Math;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Scs {

    private class IndexValuePair {
        private int index;
        private int value;
        
        IndexValuePair(final int index, final int value) {
            setValues(index, value);
        }

        public void setValues(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
        
        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return index + " " + value;
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

    private IndexValuePair getIndexValue(final int n, final int[][] dp) {
        int mask = (1 << n) - 1;
        IndexValuePair p = new IndexValuePair(0, dp[mask - 1][0]);
        for (int i = 1; i < n; i++) {
            int value = dp[mask - (1 << i)][i];
            if (value > p.getValue()) {
                p.setValues(i, value);
            }
        }
        return p;
    }

    private String getSCS(final int n, final int index, final int value,
                            final int[][] overlaps, final int[][] dp,
                            List<String> data) {
        StringBuilder scs = new StringBuilder(data.get(index));
        final int mask = (1 << n) - 1;
        int lastIndex = index;
        int lastValue = value;
        int lastMask = mask - (1 << lastIndex);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int currMask = lastMask ^ (1 << j);
                if ((currMask <= lastMask) &&
                        (dp[currMask][j] + overlaps[lastIndex][j] == lastValue)) {
                    scs.append(data.get(j).substring(overlaps[lastIndex][j]));
                    lastIndex = j;
                    lastValue = dp[currMask][j];
                    lastMask = currMask;
                }
            }
        }
        return scs.toString();
    }

    private void solve(final FastScanner in, final PrintWriter out) {
        List<String> list = getUniqueList(getInputSet(in));
        final int n = list.size();
        int[][] overlaps = getOverlaps(n, list);
        int[][] dp = getDpArray(n, overlaps);
        IndexValuePair p = getIndexValue(n, dp);
        String ans = getSCS(n, p.getIndex(), p.getValue(),
                                overlaps, dp, list);
        out.println(ans);
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
