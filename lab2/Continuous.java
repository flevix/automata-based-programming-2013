import java.util.*;
import java.io.*;

/**
 * Created with VIM.
 * User: flevix
*/

public class Continuous {
    FastScanner in;
    PrintWriter out;

    double[] gauss(double[][] mtx, double[] ans) {
        return new double[1];
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
