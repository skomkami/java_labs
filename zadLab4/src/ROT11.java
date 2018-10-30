import java.io.File;

public class ROT11 implements Algorithm {
    @Override
    public String crypt(String word){
        String crypted = "";
        for(int i=0; i<word.length(); ++i) {
            if(word.charAt(i)>=65 && word.charAt(i)<=90)
                crypted += Character.toString((char)((word.charAt(i)-'A'+11)%26 + 'A'));
            else if(word.charAt(i)>=97 && word.charAt(i)<=122)
                crypted += Character.toString((char)((word.charAt(i)-'a'+11)%26 + 'a'));
            else
                crypted += Character.toString(word.charAt(i));
        }
        return crypted;
    }

    public String decrypt(String word) {
        String decrypted = "";
        for(int i=0; i<word.length(); ++i) {
            if(word.charAt(i)>=65 && word.charAt(i)<=90)
                decrypted += Character.toString((char)((word.charAt(i)-'A'-11+26)%26 + 'A'));
            else if(word.charAt(i)>=97 && word.charAt(i)<=122)
                decrypted += Character.toString((char)(((word.charAt(i)-'a')-11+26)%26 + 'a'));
            else
                decrypted += Character.toString(word.charAt(i));
        }
        return decrypted;
    }
}
