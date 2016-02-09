package cs505ex3;

import java.util.Random;

public class HandOverHandIntSet implements IntSet {

    private Node head;
    //private Lock lock;
    private Random rand;
    private int size;

    public HandOverHandIntSet() {
        Node.withLock = true;
        head = new Node();
        rand = new Random();
        size = 0;
    }

    public HandOverHandIntSet(int initSize) {
        this();

        int range = initSize * 2;
        for (int i = 0; i < initSize; ++i) {
            while (!insert(rand.nextInt(range))) ;
        }
    }

	@Override
	public boolean insert(int x) {

        head.lock();

        Node pred = head;
        Node curr = head.next;

        try {

            if (curr != null)
                curr.lock();

            while (curr != null && curr.value < x) {
                pred.unlock();
                pred = curr;
                curr = curr.next;
                if (curr != null)
                    curr.lock();
            }

            if (curr != null && curr.value == x) {
                return false;
            }

            Node temp = new Node(x);

            temp.next = curr;
            pred.next = temp;
            //++size;
            return true;
        } finally {
            pred.unlock();
            if (curr != null)
                curr.unlock();
        }
	}

	@Override
	public boolean remove(int x) {
		head.lock();

        Node pred = head;
        Node curr = head.next;

        try {
            if (isEmpty()) {
                return false;
            }

            curr.lock();
            while (curr != null && curr.value < x) {
                pred.unlock();
                pred = curr;
                curr = curr.next;
                if (curr != null)
                    curr.lock();
            }

            if (curr == null)
                return false;
            else if (curr.value == x) {
                pred.next = curr.next;
                curr.next = null;
                //--size;
                return true;
            } else {
                return false;
            }
        } finally {
            pred.unlock();
            if (curr != null) {
                curr.unlock();
            }
        }
	}

	@Override
	public boolean contain(int x) {
		head.lock();
        Node pred = head;
        Node curr = pred.next;
        try {
            if (isEmpty())
                return false;

            curr.lock();
            while (curr != null && curr.value < x) {
                pred.unlock();
                pred = curr;
                curr = curr.next;
                if (curr != null)
                    curr.lock();
            }
            return curr != null && curr.value == x;
        } finally {
            pred.unlock();
            if (curr != null)
                curr.unlock();
        }
	}

    private boolean isEmpty() {
        return head.next == null;
    }

}
