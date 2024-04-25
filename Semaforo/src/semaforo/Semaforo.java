package semaforo;

import java.util.LinkedList;
import java.util.Queue;

public class Semaforo {
    private int permits;
    private Queue<Thread> queue;

    public Semaforo(int permits) {
        this.permits = permits;
        this.queue = new LinkedList<>();
    }

    public synchronized void acquire() throws InterruptedException {
        while (permits <= 0) {
            queue.add(Thread.currentThread());
            wait();
        }
        permits--;
        queue.remove(Thread.currentThread());
    }

    public synchronized void release() {
        if (!queue.isEmpty()) {
            permits++;
            notify();
        } else {
            permits++;
        }
    }
}
