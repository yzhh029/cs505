package cs505ex3;

public class Stat {
	public int insertSuccess = 0;
	public int insertFail = 0;
	public int removeSuccess = 0;
	public int removeFail = 0;
	public int containSuccess = 0;
	public int containFail = 0;
	public long start;
	public long end;
	
	public String toString() {
		return new String("insert  success: " + insertSuccess + " failed: " + insertFail + "\n"
				    	+ "remove  success: " + removeSuccess + " failed: " + removeFail + "\n"
				    	+ "contain success: " + containSuccess + " failed: " + containFail);
	}
	
	public int getAllOps() {
		return insertSuccess + insertFail + removeSuccess + removeFail + containSuccess + containFail;
	}
		
}
