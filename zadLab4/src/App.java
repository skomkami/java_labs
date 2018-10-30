import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static int getInt(){
        String text = null;
        try{
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);
            text = bfr.readLine();
        }catch(IOException e){e.printStackTrace();}
        int n = Integer.parseInt(text);
        return n;
    }

    public static String getString(){
        String text = null;
        try{
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);
            text = bfr.readLine();
        }catch(IOException e){e.printStackTrace();}
        return text;
    }
}
