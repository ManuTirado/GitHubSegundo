import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class DAL {
    private static SessionFactory sessionFactory = null;

    public DAL () {
        try {
            setUp();
        } catch (Exception e) {
            System.err.println("Error al hacer el setup al hibernate");
            throw new RuntimeException(e);
        }
    }

    /**
     * Inserta un nuevo contacto en la BBDD Agenda y devuelve su id al generarse
     * @param nombre Nombre del contacto
     * @param tfno Teléfono del contacto
     * @return id del contacto en la agenda
     */
    public int guardar(String nombre, String tfno) {
        AgendaEntity persona = new AgendaEntity(nombre, tfno);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(persona);
        transaction.commit();
        System.out.println(id);
        sessionFactory.close();
        return id;
    }

    /**
     * Lee todos los contactos de la agenda y los devuelve en una lista
     * @return lista con todos los contactos
     */
    public List<AgendaEntity> leerTodos() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT p FROM AgendaEntity p");
        List<AgendaEntity> contactos  = query.list();
        transaction.commit();
        session.close();
        return contactos;
    }

    /**
     * Lee al contacto cuyo id sea el pasado y lo devuelve como entidad, si no hay ninguno devuelve un objeto nulo
     * @param id id del contacto buscado
     * @return contacto encontrado, nulo si no se ha encontrado
     */
    public AgendaEntity leer(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AgendaEntity persona = session.get(AgendaEntity.class, id);
        if (persona == null) {
            System.out.println("El id introducido no corresponde a ningún contacto");
        }
        transaction.commit();
        session.close();
        return persona;
    }

    /**
     * Actualiza un contacto de la agenda buscándolo por su id
     * @param id id del contacto que queremos actualizar
     * @param nombre nuevo nombre del contacto
     * @param tfno nuevo teléfono del contacto
     */
    public void actualizar(int id,String nombre, String tfno) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AgendaEntity persona = session.get(AgendaEntity.class,id);
        if (persona != null) {
            persona.setNombre(nombre);
            persona.setTfno(tfno);
            session.update(persona);
        } else {
            System.out.println("El id introducido no corresponde a ningún contacto");
        }
        transaction.commit();
        session.close();
    }

    /**
     * Borra el contacto cuyo id coincida con el pasado por parámetro
     * @param id id del contacto a borrar
     */
    public void borrar(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AgendaEntity contacto = session.get(AgendaEntity.class, id);
        if (contacto != null) {
            session.delete(contacto);
        } else {
            System.out.println("El id introducido no corresponde a ningún contacto");
        }
        transaction.commit();
        session.close();
    }

    /**
     * Realiza en setup del hibernate
     * @throws Exception
     */
    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }
}
