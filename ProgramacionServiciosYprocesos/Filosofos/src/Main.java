public class Main {
    public static void main(String[] args) {
        Mesa mesa = new Mesa(5);    // Mesa, igual para todos los filósofos
        for (int i = 0; i < 5; i++) {
            Filosofo filosofo = new Filosofo(mesa, i);
            filosofo.start();
        }
    }

    /*

    - Debe tenerse especial cuidado para que la solución implementada elimine toda posibilidad de interbloqueo.
    Establece para ello una prioridad entre los filósofos para evitar el interbloqueo. ¿Se consigue de esta forma?
    En caso negativo, ¿qué has hecho para evitar el interbloqueo? Documenta todas estas respuestas en el código.
        Yo no he conseguido nada estableciendo las prioridades entre los filósofos; sin embargo, como he conseguido que no
        haya interbloqueo es mediente el uso de métodos synchronized, de esta forma garantizo que solo un filósofo pueda
        coger o soltar los palillos y no se solapen.

    - ¿Sería posible, con la solución que has implementado, que algún filósofo no consiguiera comer nunca, por improbable que fuera? Si es así,
    explica el escenario en que esto sucedería. Explícalo añadiendo capturas de una depuración multihilo para ver el comportamiento de los filósofos.
        No, ya que todos los filósofos comienzan pensando, y después de un tiempo aleatorio comienzan a comer si ambos palillos están libres,
        no cogen un palillo y esperan a que el otro quede libre, de esa manera podría acabar en un interbloqueo.

     */
}