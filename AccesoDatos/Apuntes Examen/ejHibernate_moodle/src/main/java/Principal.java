import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    private static Accesobd instancia;

    public static void main(String[] args) throws Exception {
        instancia=new Accesobd();
        //ejemplo1a1();
        //ejemplo1a1bi();
        ejemplo1amuchos();
    }

    private static void ejemplo1amuchos() throws Exception {
        Alumnado alumnado=new Alumnado(9, "Rosa", "Díaz", "Del Toro");
        List<Email> email=new ArrayList<>();
        email.add(new Email(3, "rosa@yahoo.com",alumnado));
        email.add(new Email(2, "rosa@hotmail.com",alumnado));
        email.add(new Email(1, "rosa@gmail.com",alumnado));

        alumnado.setEmails(email);
        guardar(alumnado);
    }

    private static void ejemplo1a1bi() throws Exception {
        Direccion direccion1=new Direccion(3, "Calle de la sarten", 23, "Manises", "Valencia");
        Alumnado alumnado1=new Alumnado(3, "Sergio", "Mateo", "Ramis");
        alumnado1.setDireccion(direccion1);
        direccion1.setAlumnado(alumnado1);
        Direccion direccion2=new Direccion(4, "Calle Luis lamarca", 45, "Torrente", "Valencia");
        Alumnado alumnado2=new Alumnado(4, "Paco", "Moreno", "Díaz");
        alumnado2.setDireccion(direccion2);
        direccion2.setAlumnado(alumnado2);
        guardar(alumnado1);
        guardar(direccion2);
    }

    private static void ejemplo1a1() throws Exception {
        Direccion direccion=new Direccion(1, "Plaza del ayuntamiento", 8, "Xativa", "Valencia");
        Alumnado alumnado=new Alumnado(1, "Juan", "Perez", "García");
        alumnado.setDireccion(direccion);
        guardar(alumnado);
    }

    private static void guardar(Object cosa) throws Exception {
        instancia.abrir();
        int id = (int) instancia.guardar(cosa);
        System.out.println(id);
        instancia.cerrar();
    }


}
