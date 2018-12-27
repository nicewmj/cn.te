
public class RegExTest {
	
	public static void main(String[] args) {
		String regex = "^[A-Za-z][A-Za-z1-9_-]+$";
		String username = "liu_¡ıcs";
		boolean result = username.matches(regex);
		System.out.println(result);
	}

}
