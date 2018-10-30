public class Polibiusz implements Algorithm{
    public String crypt(String word) {
        String crypted = "";
        StringBuilder temp = new StringBuilder(word);

        for(int i=0; i<word.length(); ++i) {
            if(temp.charAt(i)>=65 && temp.charAt(i)<=90)
                temp.setCharAt(i, (char)(temp.charAt(i)-'A'));
            else if(temp.charAt(i)>=97 && temp.charAt(i)<=122)
                temp.setCharAt(i, (char)(temp.charAt(i)-'a'));

            if(temp.charAt(i)>8 && temp.charAt(i)<=25)
                temp.setCharAt(i, (char)(temp.charAt(i)-1));

            if(temp.charAt(i)>=0 && temp.charAt(i)<=25)
                crypted += Integer.toString(temp.charAt(i)/5+1) + Integer.toString(temp.charAt(i)%5+1);
            else
                crypted += Character.toString(temp.charAt(i));
        }

        return crypted;
    }

    public String decrypt(String word) {
        String decrypted = "";
        int temp;

        for(int i=0; i<word.length()-1; i+=2) {
            temp = (Character.getNumericValue(word.charAt(i))-1)*5 + Character.getNumericValue(word.charAt(i+1))-1;
            if(temp<0 || temp>25){
                decrypted += word.charAt(i);
                --i;
            }else {
                if (temp > 8) ++temp;
                decrypted += Character.toString((char) (temp + 'a'));
            }
        }

        return decrypted;
    }
}
