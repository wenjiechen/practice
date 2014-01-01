
public class Observice {

	public static int gcd(int x, int y){
		if ( y ==0 )
				return x;
		else
			return gcd(y,x%y);
	}
	
	public static int gcd2(int x, int y){
		int z = 0;
		while(y!=0){
			z = y;
			y =  x%z;
			x = z;
		}
		return z;
	}

	public static void main (String... args){
		System.out.println(gcd(15,5));
		System.out.println(gcd2(15,5));

	}
}
