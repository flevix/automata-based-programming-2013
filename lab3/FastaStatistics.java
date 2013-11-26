import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class FastaStatistics {

    int statistic(final ArrayList<Integer> lengths, final double mean,
                                                  final int full_length) {
        final double required_mean = full_length * mean;
        double curr_mean = 0;
        int length = lengths.get(lengths.size() - 1);
        for (int i = 0; i < lengths.size(); i++) {
            curr_mean += lengths.get(i);
            if (curr_mean >= required_mean) {
                length = lengths.get(i);
                break;
            }
        }
        return length;
    }

    private void solve(final FastScanner in, final PrintWriter out) {
        ArrayList<Integer> lengths = new ArrayList<>();
        String line = in.nextLine();

        int current_length = 0;
        int full_length = 0;
        while ((line = in.nextLine()) != null) {
            if (line.charAt(0) == '>') {
                lengths.add(current_length);
                full_length += current_length;
                current_length = 0;
            } else {
                current_length += line.length();
            }
        }
        lengths.add(current_length);
        full_length += current_length;

        Collections.sort(lengths, Collections.reverseOrder());

        out.println(statistic(lengths, 0.5D, full_length));
        out.println(statistic(lengths, 0.9D, full_length));
        out.println(lengths.get(lengths.size() - 1));
        out.println(lengths.get(0));
        out.println(((double) full_length / lengths.size()));
    }

    public void run(final String fileName) {
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
