import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		// 1�� = 1000���� = 1000x1000΢�� = 1000x1000x1000����
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
