import java.math.BigInteger;

public class Basic {
    public static void main(String[] args) {
        isPrime(3);
        isCoPrime(new BigInteger("4"), new BigInteger("8"));

        totient(new BigInteger("8"));
    }

    public static Boolean isPrime(int x) {

        if (x <= 1) {
            System.out.println(x + " is not a prime number");
            return false;
        }

        int divisor = 2;
        boolean stat = false;

        while (divisor < x/2) {
            if (x % divisor == 0) {
                stat = true;
                break;
            }
            divisor++;
        }
        if (stat == true) {
            System.out.println(x + " is not a prime number");
            return false;
        } else {
            System.out.println(x + " is a prime number");
            return true;
        }
    }

    public static Boolean isCoPrime(BigInteger x, BigInteger y) {
        BigInteger c = x.gcd(y);

        if (c.intValue() == 1) {
            System.out.println(x.intValue() + " and " + y.intValue() + " are Co-Prime");
            return true;
        } else {
            System.out.println(x.intValue() + " and " + y.intValue() + " are NOT Co-Prime");
            return false;
        }
    }

    public static int totient(BigInteger x) {
        int tot = 0;
        for (int i = 1; i < x.intValue(); i++) {
            if (isCoPrime(x, BigInteger.valueOf(i))) {
                tot++;
            }
        }
        System.out.println("Totient Value of " + x + " = " + tot);
        return tot;
    }
        
        
}
