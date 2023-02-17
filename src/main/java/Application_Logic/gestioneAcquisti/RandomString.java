package Application_Logic.gestioneAcquisti;


import java.security.SecureRandom;
import java.math.BigInteger;

public class RandomString {
    private SecureRandom random = new SecureRandom();

    public String nextString() {
        String result = new BigInteger(130, random).toString(32).toUpperCase().substring(0, 16);
        int length = result.length();
        for (int i = 4; i < length; i += 5) {
            result = result.substring(0, i) + "-" + result.substring(i);
        }
        return result;
    }
}

