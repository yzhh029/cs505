package cs505ex3;

import java.util.Random;

public class OptimisticIntSet implements IntSet {

    private Node head;
    private Random rand;
    //private int size;

    public OptimisticIntSet() {
        Node.withLock = true;
        head = new Node();
        rand = new Random();
    }

    public OptimisticIntSet(int initSize) {
        this();

        int range = initSize * 2;
        for (int i = 0; i < initSize; ++i) {
            while (!insert(rand.nextInt(range))) ;
        }
    }

	@Override
	public boolean insert(int x) {
        while (true) {
            Node pred = head;
            Node curr = head.next;

            while (curr != null && curr.value < x) {
                pred = curr;
                curr = curr.next;
            }

            pred.lock();
            if (curr != null)
                curr.lock();

            try {
                if (validate(pred, curr)) {
                    if (curr != null && curr.value == x)
                        return false;
                    Node temp = new Node(x);
                    pred.next = temp;
                    temp.next = curr;
                    return true;
                }
            } finally {
                pred.unlock();
                if (curr != null)
                    curr.unlock();
            }
        }

	}

	@Override
	public boolean remove(int x) {
		while (true) {
            Node pred = head;
            Node curr = pred.next;

            if (curr == null)
                return false;

            while (curr.value < x) {
                pred = curr;
                curr = curr.next;
                if (curr == null)
                    return false;
            }

            pred.lock();
            curr.lock();

            try {
                if (validate(pred, curr)) {
                    if (curr.value == x) {
                        pred.next = curr.next;
                        return true;
                    } else
                        return false;
                }
            } finally {
                pred.unlock();
                curr.unlock();
            }
        }

	}

	@Override
	public boolean contain(int x) {
		while (true) {
            Node pred = head;
            Node curr = pred.next;

            while (curr != null && curr.value < x) {
                pred = curr;
                curr = curr.next;
            }

            pred.lock();
            if (curr != null) {
                curr.lock();
            }

            try {
                if (validate(pred, curr)) {
                    return curr != null && curr.value == x;
                }
            } finally {
                pred.unlock();
                if (curr != null)
                    curr.unlock();
            }
        }
	}

	private boolean validate(Node pred, Node curr) {
        Node node = head;

        while (node != null && node.value <= pred.value) {
            if (node == pred)
                return pred.next == curr;
            node = node.next;
        }
        return false;
    }

}
