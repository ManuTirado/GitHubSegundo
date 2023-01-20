import EntidadesHibernate.Factura;
import EntidadesHibernate.Mesa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DAL_Hibernate {
    private static SessionFactory sessionFactory = null;
    private static Session session;

    public DAL_Hibernate() {
        try {
            setUp();
            session = sessionFactory.openSession();
        } catch (Exception e) {
            System.err.println("Error al hacer el setup al hibernate");
            throw new RuntimeException(e);
        }
    }

    //region Mesa
    public void insertarMesa(Mesa mesa) {
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(mesa);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Algo no salió bien, se ha hecho un rollback");
            transaction.rollback();
        }
    }

    public Mesa leerMesa(int idMesa) {
        Mesa mesa = null;

        try {
            mesa = session.load(Mesa.class, idMesa);
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer la mesa");
        } finally {
            return mesa;
        }
    }
    //endregion


    //region Factura
    public void insertarFactura(Factura factura) {
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(factura);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Algo no salió bien, se ha hecho un rollback");
            transaction.rollback();
        }
    }
    //endregion


    //region Pedido

    //endregion


    //region Productos

    //endregion


    public void prueba() {
        /*
        Transaction transaction = session.beginTransaction();
        try {

            transaction.commit();
        } catch () {
            System.err.println("Algo no salió bien, se ha hecho un rollback");
            transaction.rollback();
        }
         */
    }

    /**
     * Realiza el setup del hibernate
     *
     * @throws Exception
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
