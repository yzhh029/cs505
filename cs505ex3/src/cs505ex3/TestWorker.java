package cs505ex3;

import java.util.Random;

public class TestWorker extends Thread {
	
	private IntSet intSet;
	private Random random;
	private final int insertLimit;
	private final int removeLimit;
	private final int valueRange;
	private volatile boolean running;
	private Stat stat;
	
	public TestWorker(IntSet s, int updateRatio, int _valueRange) {
		intSet = s;
		insertLimit = updateRatio / 2;
		removeLimit = updateRatio;
		valueRange = _valueRange;
		random = new Random();
		running = true;
		stat = new Stat();
	}
	
	

	public Stat getStat() {
		return stat;
	}

	@Override
	public void run() {
		stat.start = System.currentTimeMillis();
		int op, value;
		boolean ret;
		while (running) {
			op = random.nextInt(100);
			value = random.nextInt(valueRange);
			//System.out.println(Thread.currentThread().getName() + " run ");
			if (op < insertLimit) {
				ret = intSet.insert(value);
				if (ret) {
					++stat.insertSuccess;
				} else {
					++stat.insertFail;
				}
				//System.out.println(Thread.currentThread().getName() + " insert " + value + " " + ret);
			} else if ( op < removeLimit) {
				ret = intSet.remove(value);
				if (ret) {
					++stat.removeSuccess;
				} else {
					++stat.removeFail;
				}
				//System.out.println(Thread.currentThread().getName() + " remove " + value + " " + ret);
			} else {
				ret = intSet.contain(value); 
				if (ret) {
					++stat.containSuccess;
				} else {
					++stat.containFail;
				}
				//System.out.println(Thread.currentThread().getName() + " contains " + value + " " + ret);
			}
			//System.out.println(Thread.currentThread().getName() + " ");
		}
		//System.out.println(Thread.currentThread().getName() + " exit");
		stat.end = System.currentTimeMillis();
		System.out.println(Thread.currentThread().getName() + " " + (stat.end - stat.start) + "ms\n" + stat.toString());
	}
	
	public void stopRunning() {
		running = false;
	}
}
