package cs505ex3;

public class IntSetFactory {

	public static IntSet getIntSet(String setType, int initSize) {
		if (setType.equalsIgnoreCase("COARSE")) {
			return new CoarseIntSet(initSize);
		} else if (setType.equalsIgnoreCase("HOH")) {
			return new HandOverHandIntSet(initSize);
		} else if (setType.equalsIgnoreCase("optimistic")) {
			return new OptimisticIntSet(initSize);
		} 
		return null;
	}
}
