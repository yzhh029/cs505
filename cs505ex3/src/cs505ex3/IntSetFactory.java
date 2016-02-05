package cs505ex3;

public class IntSetFactory {

	public IntSet getIntSet(String setType, int initSize) {
		if (setType.equalsIgnoreCase("COARSE")) {
			return new CoarseIntSet(initSize);
		} else if (setType.equalsIgnoreCase("HOH")) {
			return new HandOverHandIntSet();
		} else if (setType.equalsIgnoreCase("optimistic")) {
			return new OptimisticIntSet();
		} 
		return null;
	}
}
