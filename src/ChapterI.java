import java.lang.*;
import java.util.Arrays;

public class ChapterI {

  public static void sop(Object x) {
    System.out.println(x);
  }

  public boolean solu1(String str) {
    if (str.length() > 256)
      return false;

    boolean[] charExist = new boolean[256];
    for (char c : str.toCharArray()) {
      if (charExist[(int) c] == true)
        return false;
      else
        charExist[(int) c] = true;
    }

    return true;
  }

  public boolean solu1_2(String str) {
    // use bit operation to save space, and string only use lower case
    if (str.length() > 256)
      return false;
    int checker = 0;
    for (char c : str.toCharArray()) {
      int pos = c - 'a';
      if ((checker & (1 << pos)) != 0)
        return false;
      else
        checker |= (1 << pos);
    }
    return true;
  }

  // reverse a string
  public String solu2(String str) {
    StringBuilder sb = new StringBuilder();
    for (int i = str.length() - 1; i >= 0; --i) {
      sb.append(str.charAt(i));
    }

    str = sb.toString();
    return str;
  }

  public boolean solu3(String s1, String s2) {
    char[] ca1 = s1.toCharArray();
    char[] ca2 = s2.toCharArray();

    Arrays.sort(ca1);
    Arrays.sort(ca2);

    s1 = new String(ca1);
    s2 = new String(ca2);

    if (s1.equals(s2)) // can't use ca1.equals(ca2) to compare contents of
      // two char arrays
      return true;
    else
      return false;
  }

  public boolean solu3_2(String s1, String s2) {
    if (s1.length() != s2.length())
      return false;

    // assume characters set is ASCII
    int[] letters = new int[256];
    // String[] s = new String[100];
    for (char c : s1.toCharArray()) {
      letters[c]++;
    }

    for (char c : s2.toCharArray()) {
      if (--letters[c] < 0)
        return false;
    }

    return true;
  }

  public String solu4(char[] cstr, int length) {
    int spaceCount = 0;
    int newLength = 0;

    for (int i = 0; i < length; ++i) {
      if (cstr[i] == ' ')
        spaceCount++;
    }

    newLength = length + spaceCount * 2;
    cstr[newLength] = '\0';
    for (int i = newLength - 1, j = length - 1; j >= 0; j--) {
      if (cstr[j] == ' ') {
        cstr[i] = '0';
        cstr[i - 1] = '2';
        cstr[i - 2] = '%';
        i = i - 3;
      } else {
        cstr[i--] = cstr[j];
      }
    }

    return new String(cstr);
  }

  public String solu5(String s) {
    StringBuilder r = new StringBuilder();
    char last = s.charAt(0);
    int count = 1;
    for (int i = 1; i < s.length(); ++i) {
      if (s.charAt(i) == last) {
        ++count;
      } else {
        r.append(last);
        r.append(count);
        count = 1;
        last = s.charAt(i);
      }
    }
    r.append(last);
    r.append(count);
    String re = r.toString();
    if (re.length() > s.length())
      return s;
    else
      return re;
  }

  public void solu6(int[][] M, int n) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(M[i][j] + " ");
      }
      System.out.println();
    }
    for (int layer = 0; layer < n / 2; ++layer) {
      int first = layer;
      int last = n - 1 - layer;
      for (int i = first; i < last; ++i) {
        int temp_top = M[layer][i];
        // top
        M[layer][i] = M[last - i + first][first];
        // left
        M[last - i + first][first] = M[last][last - i + first];
        // bottom
        M[last][last - i + first] = M[i][last];
        // right
        M[i][last] = temp_top;
      }
    }
    sop("==============");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(M[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void sol7(int[][] M) {
    boolean[] row = new boolean[M.length];
    boolean[] column = new boolean[M[0].length];

    for (int i = 0; i < M.length; ++i) {
      for (int j = 0; j < M[0].length; ++j) {
        if (M[i][j] == 0) {
          row[i] = true;
          column[j] = true;
        }
        System.out.print(M[i][j] + " ");
      }
      System.out.println();
    }
    sop("==================");
    for (int i = 0; i < M.length; ++i) {
      for (int j = 0; j < M[0].length; ++j) {
        if (row[i] || column[j]) {
          M[i][j] = 0;
        }
      }
    }
    
    for (int i = 0; i < M.length; ++i) {
      for (int j = 0; j < M[0].length; ++j) {
        System.out.print(M[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    ChapterI c = new ChapterI();
    sop("===============solution 1==============");
    sop(c.solu1("asdflkj;werqoiu123!@#$%^"));
    sop(c.solu1("asdflkj;werqoiu123!@#$%^;"));
    sop(c.solu1_2("asdflkjwerqoiu"));
    sop(c.solu1_2("asdflkjwerqoiua"));
    sop("===============solution 2=============");
    sop(c.solu2("abcdefg"));
    sop("===============solution 3=============");
    sop(c.solu3("abcd", "bcda"));
    sop(c.solu3_2("abcd", "bcda"));
    sop("===============solution 4=============");
    char[] cstr = new char[100];
    cstr[0] = 'a';
    cstr[1] = ' ';
    cstr[2] = 'b';
    cstr[3] = ' ';
    sop(c.solu4(cstr, 4));
    sop("===============solution 5=============");
    sop(c.solu5("aaaaabbcddd"));
    sop(c.solu5("aabcccdddde"));
    int[][] ma = new int[][] { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 },
        { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 }, { 21, 22, 23, 24, 25 } };
    sop("===============solution 6=============");
    c.solu6(ma, 5);
    sop("===============solution 6=============");
    int[][] mb = new int[][] { { 1, 2, 3, 0, 5 }, { 6, 7, 8, 9, 10 },
        { 11, 12, 13, 14, 15 }, { 16, 0, 18, 19, 20 } };
    sol7(mb);
  }
}