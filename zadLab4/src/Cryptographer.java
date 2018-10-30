import java.io.*;

public class Cryptographer {
    public static void cryptfile(File fileIn, File fileOut, Algorithm algo) throws IOException {
        FileInputStream fstream = new FileInputStream(fileIn);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        String[] splitedLine;

        PrintWriter pw = new PrintWriter(new FileOutputStream(fileOut),true);

       while( (strLine = br.readLine()) != null)
        {
            splitedLine = strLine.split(" ");

            for(String s : splitedLine)
                pw.append(algo.crypt(s)+" ");

            pw.append("\n");
        }

        pw.close();
        br.close();
    }

    public static void decryptfile(File fileIn, File fileOut, Algorithm algo) throws IOException {
        FileInputStream fstream = new FileInputStream(fileIn);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        String[] splitedLine;

        PrintWriter pw = new PrintWriter(new FileOutputStream(fileOut),true);

        while( (strLine = br.readLine()) != null)
        {
            splitedLine = strLine.split(" ");

            for(String s : splitedLine)
                pw.append(algo.decrypt(s)+" ");

            pw.append("\n");
        }

        pw.close();
        br.close();
    }


}
