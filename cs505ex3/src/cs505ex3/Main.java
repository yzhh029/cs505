package cs505ex3;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		Options opt = new Options(args);
		
		//IntSet iset = new CoarseIntSet(opt.getInitSize());
		IntSet iset = IntSetFactory.getIntSet(opt.getSetType(), opt.getInitSize());

		ArrayList<TestWorker> workers = new ArrayList<>();
		Stat stat = new Stat();
		
		for (int i = 0; i < opt.getNumTh(); ++i)
		{
			workers.add(new TestWorker(iset, opt.getUpdateRatio(), opt.getInitSize() * 2));
		}
		
		stat.start = System.currentTimeMillis();
		for (TestWorker worker : workers) {			
			worker.start();
		}
		
		try {
			Thread.sleep(opt.getRunTime());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (TestWorker worker : workers) {
			worker.stopRunning();
		}
		
		stat.end = System.currentTimeMillis();
		
		for (TestWorker worker : workers) {
			try {
				worker.join();
				stat.insertSuccess += worker.getStat().insertSuccess;
				stat.insertFail += worker.getStat().insertFail;
				stat.removeSuccess += worker.getStat().removeSuccess;
				stat.removeFail += worker.getStat().removeFail;
				stat.containSuccess += worker.getStat().containSuccess;
				stat.containFail += worker.getStat().containFail;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		System.out.println("Total: " + (stat.end - stat.start) + "ms\n" + stat.toString());

        //System.out.println(stat.getAllOps());
        //System.out.println(stat.end - stat.start);
		System.out.println(stat.getAllOps() / ((stat.end - stat.start) / 1000) + " ops/second");
		//System.out.println("set size: " + ((CoarseIntSet)iset).getSize() + " " + (opt.getInitSize() + stat.insertSuccess - stat.removeSuccess));
	}

}
