import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AccessBBDD {

    private SessionFactory sf;
    private Session session;
    private Transaction transaction;

    public void abrir() {
        setUp();
        session = sf.openSession();
        transaction = session.beginTransaction();
    }

    private void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sf = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public void cerrar() {
        try {
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public Object guardar(Object cosa) {
        return session.save(cosa);
    }
}
