
public class StringTest {

	public static void main(String[] args) {
		StringBuffer str = new StringBuffer();
		
		for (int i = 0; i < 100000000; i++) {
			str.append(i); // 0123456789101112...100000100001100002
		}
		
		System.out.println("OVER");
	}

}
