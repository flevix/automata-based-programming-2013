import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class FastaStatistics {

    int statistic(ArrayList<Integer> sizes, final double mean,
                                            final int full_length) {
        return full_length;
    }

    private void solve(FastScanner in, PrintWriter out) {
        ArrayList<Integer> sizes = new ArrayList<>();
        String line = in.nextLine();

        int current_length = 0;
        int full_length = 0;
        while ((line = in.nextLine()) != null) {
            if (line.charAt(0) == '>') {
                sizes.add(current_length);
                full_length += current_length;
                current_length = 0;
            } else {
                current_length += line.length();
            }
        }
        sizes.add(current_length);
        full_length += current_length;
        
        out.println(statistic(sizes, 0.5D, full_length));
        out.println(statistic(sizes, 0.9D, full_length));
    }

    public void run(String fileName) {
        try {
            FastScanner in = new FastScanner(new File(fileName + ".fasta"));
            PrintWriter out = new PrintWriter(new File(fileName + ".out"));
 
            solve(in, out);
 
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        new FastaStatistics().run("fasta-statistics");
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
    
    String nextLine() {
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
        }
        return s;
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
