public class PolynomialCounter {

    public static double count(String p, double x) throws Exception{

        p = p.replaceAll(" +", "");
        p = p.replaceAll("-", "+-");
        String[] monomials = p.split("\\+");

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
