package algorithms4edition;

public class Chapter1Fundations {

  public static int[][] MatrixMultply(int[][] a, int[][] b) {
    int[][] ret = new int[a.length][b[0].length];
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < b[0].length; j++) {
        ret[i][j] = 0;
        for (int k = 0; k < a[0].length; k++) {
          ret[i][j] += a[i][k] * b[k][j];
        }
      }
    }
    return ret;
  }

  public static void printMatrix(int[][] a) {
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[0].length; j++) {
        System.out.print(a[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[][] a = { { 1, 2, 3 }, { 4, 5, 6 } };
    int[][] b = { { 5, 6 }, { 7, 8 }, { 9, 10 } };
    int[][] c = MatrixMultply(a, b);
    System.out.println("matrix a ");
    printMatrix(a);
    System.out.println("matrix b ");
    printMatrix(b);
    System.out.println("matrix c ");
    printMatrix(c);

  }
}
