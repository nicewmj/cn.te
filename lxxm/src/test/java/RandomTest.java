import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		// 1Ãë = 1000ºÁÃë = 1000x1000Î¢Ãë = 1000x1000x1000ÄÉÃë
		for (int i = 0; i < 10; i++) {
			System.out.println(System.nanoTime());
		}
		System.out.println();
		
		Random random;
		
		for (int i = 0; i < 5; i++) {
			random = new Random(System.nanoTime()); // seed
			System.out.println(random.nextInt());
		}
	}

}
