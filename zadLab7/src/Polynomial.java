import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {

    public static HashMap<Integer, Double> getFromString(String pol) throws Exception{
        String patternString = "-?((([\\d]+|([\\d]+.[\\d]+))+x\\^[\\d]+)|([\\+-]{1}))+";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(pol);
        if(!matcher.matches())
            throw new Exception("Wrong input");

        String p = pol;

        p = p.replaceAll(" +", "");
        p = p.replaceAll("-", "+-");
        LinkedList<String> monomials = new LinkedList<>(Arrays.asList(p.split("\\+")));

        if(pol.charAt(0) == '-')
            monomials.removeFirst();

        HashMap<Integer, Double> polynomial = new HashMap<>();

        for(String n : monomials) {
            String[] temp = n.split("x\\^");
            if (temp.length != 2)
                throw new Exception("Wrong input");

            polynomial.put(Integer.parseInt(temp[1]), Double.parseDouble(temp[0]));
        }

        return polynomial;
    }

    public static double count(HashMap<Integer, Double> pol, double x){

        double result = 0;

        Iterator it = pol.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();

            result += (Double)pair.getValue() * (Double)Math.pow(x, (Integer)pair.getKey());
        }

        return result;
    }

}
