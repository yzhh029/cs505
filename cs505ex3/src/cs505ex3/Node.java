package cs505ex3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node {
	
	public int value;
	public Node next;
	
	static public boolean withLock = false;
	private Lock lock = null;
	
	public Node() {
		next = null;
		if (Node.withLock)
			lock = new ReentrantLock();
		value = Integer.MIN_VALUE;
	}
	
	public Node(int v) {
		this();
		this.value = v;
	}

	public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
}
