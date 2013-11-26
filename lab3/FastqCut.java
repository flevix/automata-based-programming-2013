import java.util.StringTokenizer;
import java.io.*;

public class FastqCut {

    private void solve(FastScanner in, PrintWriter out) {
        int q = in.nextInt() + 33;
        in.nextLine();
        String seqname;
        while ((seqname = in.nextLine()) != null) {
            String seq = in.nextLine();
            String seqname2 = in.nextLine();
            String qual = in.nextLine();
            int pos = qual.length();
            for (int i = 0; i < qual.length(); i++) {
                if (qual.charAt(i) < q) {
                    pos = i;
                    break;
                }
            }
            out.print(seqname + '\n'
                    + seq.substring(0, pos) + '\n'
                    + seqname2 + '\n'
                    + qual.substring(0, pos) + '\n');
        }
    }

    public void run(String fileName) {
        try {
            FastScanner in = new FastScanner(new File(fileName + ".fastq"));
            PrintWriter out = new PrintWriter(new File(fileName + ".out"));
 
            solve(in, out);
 
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        new FastqCut().run("fastqcut");
    }

    private class FastScanner {
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
                s = "";
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
}

