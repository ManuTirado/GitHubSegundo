public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new HilosDurmientes("Hilo" + (i+1)).start();
        }
    }


}