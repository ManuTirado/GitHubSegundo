import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;

public class DAL_Hibernate {
    private SessionFactory sf;
    private Session sesion;
    private Transaction transaction;
    protected void setUp() {
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
    public void abrir() {
        setUp();
        sesion=sf.openSession();
        transaction = sesion.beginTransaction();
    }
    public void cerrar(){
        try {
            transaction.commit();
        }catch(Exception e){
            transaction.rollback();
        }
        sf.close();
    }

    /**
     * Lee el registro de la BBDD con el id pasado por parámetro, de no existir devuelve
     * el objeto a null y muestra un mensaje de error
     * @param id id objeto deseada
     * @return Objeto deseado o objeto a null si no existe
     */
    public <T> T leer(int id, Class<T> clase) {
        T entidad = (T) clase;
        entidad = null;
        try {
            entidad = sesion.get(clase, id);
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer la mesa");
        }
        return entidad;
    }
    /**
     * Lee todos los registros de la BBDD y los devuelve en un arraylist con estos
     * @return arraylist con los registros existentes
     */
    public <T>ArrayList<T> leerTodosRegistros(Class<T> clase) {
        ArrayList<T> registros = new ArrayList<>();
        try {
            registros = (ArrayList<T>) sesion.createQuery("SELECT p FROM "+clase.getName()+" p",clase).list();
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer los registros");
        }
        return registros;

    }

    /**
     * Inserta el objeto en la BBDD
     * @param objeto Objeto a insertar
     */
    public void insertar(Object objeto) {
        try {
            sesion.persist(objeto);
        } catch (Exception e) {
            System.err.println("Algo no salió bien al insertar");
        }
    }

    /**
     * Actualiza el objeto en la BBDD
     * @param objeto Objeto a actualziar
     */
    public void actualziar(Object objeto) {
        try {
            sesion.update(objeto);
        } catch (Exception e) {
            System.err.println("Algo no salió bien al actualizar");
        }
    }

    public <T> ArrayList<T> leerNamedQuery(String namedQuery) {
        return (ArrayList<T>) sesion.getNamedQuery(namedQuery).getResultList();
    }

    /**
     * Borra el modelo pasado de la BBDD
     * @param objeto objeto a borrar
     */
    public void borrar(Object objeto) {
        try {
            sesion.remove(objeto);
        } catch (Exception e) {
            System.err.println("Algo no salió bien");
        }
    }
}
