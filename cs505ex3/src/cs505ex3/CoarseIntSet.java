package cs505ex3;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CoarseIntSet implements IntSet {

	private Node head;
	private Lock lock;
	private Random rand;
	
	public CoarseIntSet() {
		Node.withLock = false;
		head = new Node();
		lock = new ReentrantLock();
		rand = new Random();
	}
	
	public CoarseIntSet(int initSize) {
		this();
		
		Node curr = head;
		int range = initSize * 2;
		for (int i = 0; i < initSize; ++i) {
			Node temp = new Node(rand.nextInt(range));
			curr.next = temp;
			curr = temp;
		}
	}
	
	@Override
	public boolean insert(int x) {
		
		lock.lock();
		
		try {
			Node pred = head;
			Node curr = head.next;
			
			while (curr != null && curr.value <= x) {
				if (curr.value == x) {
					return false;
				}
				pred = curr;
				curr = curr.next;
			}
			
			Node temp = new Node(x);
			
			temp.next = curr;
			pred.next = temp;
			
			return true; 
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean remove(int x) {
		
		lock.lock();
		
		try {
			if (isEmpty())
				return false;
			
			Node pred = head;
			Node curr = head.next;
			
			while (curr != null && curr.value != x) {
				pred = curr;
				curr = curr.next;
			}
			
			if (curr == null || curr.value != x) 
				return false;
			else if (curr.value == x) {
				pred.next = curr.next;
				curr.next = null;
				return true;
			} else {
				return false;
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean contain(int x) {
		lock.lock();
		
		try {
		
			if (isEmpty())
				return false;
			
			Node curr = head.next;
			
			while (curr != null && curr.value != x) {
				curr = curr.next;
			}
			
			if (curr == null || curr.value != x) 
				return false;
			else if (curr.value == x) {
				return true;
			} else
				return false;
		} finally {
			lock.unlock();
		}
		
	}
	
	private boolean isEmpty() {
		return head.next == null;
	}

}
