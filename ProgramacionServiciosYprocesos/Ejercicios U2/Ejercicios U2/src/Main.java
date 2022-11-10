public class Main {
    public static void main(String[] args) {
        Thread h = Thread.currentThread();
        System.out.println(h.getName());
        System.out.println(h.getState());
        System.out.println(h.getId());
        System.out.println("PRIORIDADES:");
        System.out.println("PRIORIDAD MÍNIMA: " + Thread.MIN_PRIORITY);
        System.out.println("PRIORIDAD MÁXIMA: " + Thread.MAX_PRIORITY);
        System.out.println("PRIORIDAD POR DEFECTO: " + Thread.NORM_PRIORITY);
        System.out.println(h.getPriority());
        System.out.println(h.isDaemon());

        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int octeto1 = (int) (Math.random() * 255);
            int octeto2 = (int) (Math.random() * 255);
            int octeto3 = (int) (Math.random() * 255);
            int octeto4 = (int) (Math.random() * 255);

            System.out.println(octeto1 + "." + octeto2 + "." + octeto3 + "." + octeto4);
        }
    }
}