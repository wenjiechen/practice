//hash string
//it's not a conventional hash function. See <<effective java>> -- override hashCode
public class HashFunction {
  private static int arraySize = 97; // prime

  public static int hashFunc(String key) {
    int hashval = 0;
    for (char c : key.toCharArray()) {
      int letter = c - 'a';
      hashval = (hashval << 5 + letter) % arraySize;
    }
    return hashval;
  }
}
