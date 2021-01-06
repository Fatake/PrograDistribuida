public class Contenedor implements Runnable{
    private boolean consumidor;
    private int id;

    private static int tarta = 0;
    private static int c = 1;
    private static Object lock = new Object();

    public Contenedor(boolean consumidor){
        this.consumidor = consumidor;
        if (consumidor) {
            this.id = c;
            c++;
        }
    }

    public Contenedor(int i, int j) {
	}

	@Override
    public void run(){
        while (true) {
            if (consumidor) {
                consumir();
            } else { // Productor
                produce();
            }
        }
    }

    private void consumir(){
        synchronized (lock){
            if (tarta > 0) {
                tarta --;
                System.out.println("[B"+id+"] Descargando el contenedor "+tarta);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else { 
                lock.notifyAll();
                try {
                    lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void produce(){
        synchronized (lock){
            if (tarta == 0) {
                tarta = 10;
                System.out.println("[C] LLenando el contenedor");
                lock.notifyAll();
            }
            try {
                lock.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Donde el hilo[0] es productor
     * los otros 10 hilos son consumidores
     * @param args
     */
    public static void main(String[] args) {
        int numHilos = 11;
        Thread[] hilos = new Thread[numHilos];

        for (int i = 0; i < hilos.length; i++) {
            Runnable runnable = null;
            if (i != 0) {
                runnable = new Contenedor(true);
            } else {
                runnable = new Contenedor(false);
            }
            hilos[i] = new Thread(runnable);
            hilos[i].start();
        }

        for (int i = 0; i < hilos.length; i++) {
            try{
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[*] End Program");
    }

	public boolean descargarUnaPieza() {
		return false;
	}

	public String getPiezas() {
		return null;
	}
}