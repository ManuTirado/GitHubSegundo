import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;

public class    DAL_Hibernate {
    private static SessionFactory sessionFactory = null;
    private static Session session;

    //region Constructor
    public DAL_Hibernate() {
        try {
            setUp();
            session = sessionFactory.openSession();
        } catch (Exception e) {
            System.err.println("Error al hacer el setup al hibernate");
            throw new RuntimeException(e);
        }
    }
    //endregion

    /**
     * Lee el registro de la BBDD con el id pasado por parámetro, de no existir devuelve
     * el objeto a null y muestra un mensaje de error
     *
     * @param id id objeto deseada
     * @return Objeto deseado o objeto a null si no existe
     */
    public <T> T leer(int id, Class<T> clase) {
        T entidad = (T) clase;
        entidad = null;
        try {
            entidad = session.get(clase, id);
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer la mesa");
        }
        return entidad;
    }

    /**
     * Lee todos los registros de la BBDD y los devuelve en un arraylist con estos
     *
     * @return arraylist con los registros existentes
     */
    public <T> ArrayList<T> leerTodosRegistros(Class<T> clase) {
        ArrayList<T> registros = new ArrayList<>();
        try {
            registros = (ArrayList<T>) session.createQuery("FROM " + clase.getName() + " p", clase).list();
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer todos los registros");
        }
        return registros;
    }

    /**
     * Inserta el objeto en la BBDD
     *
     * @param objeto Objeto a insertar
     */
    public void insertar(Object objeto) {
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(objeto);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Algo no salió bien, se ha hecho un rollback");
            transaction.rollback();
        }
    }


    public <T> ArrayList<T> leerNamedQuery(String namedQuery) {
        return (ArrayList<T>) session.getNamedQuery(namedQuery).getResultList();
    }

    public <T> ArrayList<T> leerNamedQuery(String namedQuery, String param, String valor) {
        return (ArrayList<T>) session.getNamedQuery(namedQuery).setParameter(param, valor).getResultList();
    }


    /**
     * Realiza el setup del hibernate
     *
     * @throws Exception Error en el setup
     */
    private void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
