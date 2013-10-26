import java.util.*;
import java.io.*;

/**
 * Created with VIM.
 * User: flevix
*/

public class Continuous {
    FastScanner in;
    PrintWriter out;

    double[] gauss(double[][] mtr, double[] ans) {
        int n = ans.length;
        int m = mtr[0].length;
        for (int i = 0; i < m; i++) {
            int nextNonZero = i;
            for (int j = i; j < n; j++) {
                if (Double.compare(mtr[j][i], 0) != 0) {
                    nextNonZero = j;
                    break;
                }
            }
            {
                double[] tmp = mtr[i];
                mtr[i] = mtr[nextNonZero];
                mtr[nextNonZero] = tmp;
                double t = ans[i];
                ans[i] = ans[nextNonZero];
                ans[nextNonZero] = t;
            }
            if (Double.compare(mtr[i][i], 0) == 0) {
                continue;
            }
            for (int j = 0; j < m; j++) {
                if (i == j) continue;
                for (int k = 0; k < n; k++) {
                    if (i == k) continue;
                    mtr[k][j] -= mtr[k][i] / mtr[i][i] * mtr[i][j];
                }
            }
            for (int j = 0; j < n; j++) {
                if (i != j) ans[j] -= mtr[j][i] / mtr[i][i] * ans[i];
            }
            for (int j = 0; j < n; j++) {
                if (i != j) mtr[j][i] = 0;
            }
        }
        for (int i = 0; i < m; i++) {
            if (Double.compare(mtr[i][i], 0) == 0) {
                ans[i] = 0;
            } else {
                ans[i] /= mtr[i][i];
            }
        }
        return ans;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] zero = new int[n];
        int[] one = new int[n];
        for (int i = 0; i < n; i++) {
            zero[i] = in.nextInt() - 1;
            one[i] = in.nextInt() - 1;
        }
        double[][] matrix = new double[2 * n][2 * n];
        double[] ans = new double[n * 2];
        for (int i = 0; i < m; i++) {
            int currState = 0;
            int length = in.nextInt();
            char[] currIn = in.next().toCharArray();
            double[] currPath = new double[2 * n];
            for (int j = 0; j < length; j++) {
                double currOut = in.nextDouble();
                currPath[2 * currState + (currIn[j] - '0')] += 1D / length;
                currState = (currIn[j] == '0') ? zero[currState]
                                                : one[currState];
                //matrix update
                for (int z = 0; z < 2 * n; z++) {
                    for (int k = 0; k < 2 * n; k++) {
                        matrix[z][k] += currPath[z] * currPath[k] * length;
                        matrix[k][z] += currPath[z] * currPath[k] * length;
                    }
                    ans[z] += 2 * currPath[z] * currOut;
                }
            }
        }
        ans = gauss(matrix, ans);
        for (int i = 0; i < ans.length / 2; i++) {
            out.println(ans[2 * i] + " " + ans[2 * i + 1]);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("continuous.in"));
            out = new PrintWriter(new File("continuous.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Continuous().run();
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
