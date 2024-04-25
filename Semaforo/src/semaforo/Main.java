package semaforo;

public class Main {
    public static void main(String[] args) {
        Semaforo semaforo = new Semaforo(1);

        Thread t1 = new Thread(new Worker(semaforo));
        Thread t2 = new Thread(new Worker(semaforo));
        Thread t3 = new Thread(new Worker(semaforo));
        Thread t4 = new Thread(new Worker(semaforo));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    static class Worker implements Runnable {
        private final Semaforo semaforo;

        Worker(Semaforo semaforo) {
            this.semaforo = semaforo;
        }

        @Override
        public void run() {
            try {
            	semaforo.acquire();
                System.out.println(Thread.currentThread().getName() + " Adquiriu permissão");

                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " Liberou permissão.");
                semaforo.release();

            }
        }
    }
}

