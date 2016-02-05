package cs505ex3;

public class Main {

	public static void main(String[] args) {
		
		Options opt = new Options(args);
		
		IntSet iset = new CoarseIntSet(10);
		
		for (int i = 0; i < 10; ++i) {
			iset.insert(i);
			if (iset.contain(i)) {
				System.out.println(i + "in");
			}
		}
		
		for (int i = 0; i < 10; ++i) {
			iset.remove(i);
		}
		System.out.println("hello");
	}

}
