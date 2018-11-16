import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialCounter {

    public static double count(String line, double x) throws Exception{

        String patternString = "-?((([\\d]+|([\\d]+.[\\d]+))+x\\^[\\d]+)|([\\+-]{1}))+";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(line);
        if(!matcher.matches())
            throw new Exception("Wrong input 1");

        String p = line;

        p = p.replaceAll(" +", "");
        p = p.replaceAll("-", "+-");
        LinkedList<String> monomials = new LinkedList<>(Arrays.asList(p.split("\\+")));

        if(line.charAt(0) == '-')
            monomials.removeFirst();

        double result = 0;

        for(String n : monomials) {
            String[] temp = n.split("x\\^");
            if(temp.length != 2)
                throw new Exception("Wrong input");

            result += Double.parseDouble(temp[0]) * Math.pow(x, Integer.parseInt(temp[1]));
        }
        return result;
    }

}
