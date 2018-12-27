
public class MathTest {
	
	public static void main(String[] args) {
		System.out.println(Math.floor(101.0 / 20));
		System.out.println(Math.round(101.0 / 20));
		int i = Math.floorDiv(101, 20);
		System.out.println(i);
		int maxPage = (int) Math.ceil(101.0 / 20);
		System.out.println(maxPage);
	}

}
