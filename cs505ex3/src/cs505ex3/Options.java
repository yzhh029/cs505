package cs505ex3;

import java.util.Arrays;

public class Options {
	
	private String setType;
	private int numTh;
	private int updateRatio;
	private int initSize;
	private int runTime;
	private final String[] setTypeList = {"coarse", "hoh", "optimistic"};
	
	public Options(String[] args) {
		if (args.length != 10) {
			printUsage(args);
			System.exit(0);
		} else {
			int i = 0;
			String arg;
			while (i < args.length && args[i].startsWith("-")) {
				arg = args[i++];
				if (arg.equalsIgnoreCase("-t")) {
					numTh = Integer.parseInt(args[i++]);
					System.out.println("Number of Thread: " + numTh);
				} else if (arg.equalsIgnoreCase("-u")) {
					updateRatio = Integer.parseInt(args[i++]);
					System.out.println("Update ratios: " + updateRatio);
				} else if (arg.equalsIgnoreCase("-i")) {
					initSize = Integer.parseInt(args[i++]);
					System.out.println("Initial list size: " + initSize);
				} else if (arg.equalsIgnoreCase("-d")) {
					runTime = Integer.parseInt(args[i++]);
					System.out.println("Duration: " + runTime + " ms");
				} else if (arg.equalsIgnoreCase("-b")) {
					setType = args[i++];
					if (!Arrays.asList(setTypeList).contains(setType)) {
						System.out.println("Wrong set type: " + setType);
						System.exit(0);
					}
					System.out.println("Locking scheme: " + setType);
				} else {
					System.out.println("Wrong parameter: " + arg);
					System.exit(0);
				}
			}
		}
	}
	
	private void printUsage(String[] args) {
		System.out.println("Parameter list:");
		System.out.println("    -t NUM_OF_THREADS");
		System.out.println("    -u UPDATE_RATIOS [0 - 100]");
		System.out.println("    -i INIT_SIZE");
		System.out.println("    -d DURATION (ms)");
		System.out.println("    -b SET_TYPE [coarse, hoh, optimistic]");
		System.out.println("Example:");
		System.out.println("-t 8 -d 3000 -u 100 -i 10000 -b coarse");
		System.out.println("Your input:");
		System.out.println(Arrays.toString(args));
		
	}

	public String getSetType() {
		return setType;
	}

	public int getNumTh() {
		return numTh;
	}

	public int getUpdateRatio() {
		return updateRatio;
	}

	public int getInitSize() {
		return initSize;
	}

	public int getRunTime() {
		return runTime;
	}
	
	
}
