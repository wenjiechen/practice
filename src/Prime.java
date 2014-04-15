import java.util.ArrayList;

public class Prime {
  static ArrayList<Long> primes = new ArrayList<Long>();
  static {
    primes.add((long) 2);
  }

  public void getPrime(long min, long max) {
    for (long i = min; i <= max; ++i) {
      if (isPrime2(i)) {
        primes.add(i);
      }
    }
  }

  private boolean isPrime(long n) {
    for (int j = 0; j < primes.size(); j++) {
      long prime = primes.get(j);
      if (prime * prime > n)
        break;
      if (n % prime == 0)
        return false;
    }
    return true;
  }

  /**
   * determine if n is a prime
   * 
   * @param n
   * @return
   */
  private boolean isPrime2(long n) {
    for (long i = 2; i * i <= n; ++i) {
      if (n % i == 0)
        return false;
    }
    return true;
  }

  public static void main(String[] a) {
    Prime p = new Prime();
    p.getPrime(3, 200);
    System.out.println(primes);
  }
}
