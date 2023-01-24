import EntidadesHibernate.Factura;
import EntidadesHibernate.Mesa;
import EntidadesHibernate.Pedido;
import EntidadesHibernate.Productos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;

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

    //region Métodos Mesa
    public Mesa leerMesa(int idMesa) {
        Mesa mesa = null;
        try {
            mesa = session.get(Mesa.class, idMesa);
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer la mesa");
        }
        return mesa;

    }

    public ArrayList<Mesa> leerTodasMesas() {
        ArrayList<Mesa> mesas = new ArrayList<>();
        try {
            mesas = (ArrayList<Mesa>) session.createQuery("SELECT p FROM Mesa p",Mesa.class).list();
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer las mesas");
        }
        return mesas;

    }

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

    public void borrarMesa(Mesa mesa) {
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(mesa);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Algo no salió bien, se ha hecho un rollback");
            transaction.rollback();
        }
    }
    //endregion


    //region Métodos Factura
    public Factura leerFactura(int idFactura) {
        Factura factura = null;
        try {
            factura = session.get(Factura.class, idFactura);
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer la factura");
        }
            return factura;
    }

    public ArrayList<Factura> leerTodasFacturas() {
        ArrayList<Factura> facturas = new ArrayList<>();
        try {
            facturas = (ArrayList<Factura>) session.createQuery("SELECT p FROM Factura p", Factura.class).list();
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer las mesas");
        }
            return facturas;
    }

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

    public void borrarFactura(Factura factura) {
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(factura);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Algo no salió bien, se ha hecho un rollback");
            transaction.rollback();
        }
    }
    //endregion


    //region Métodos Pedido
    public Pedido leerPedido(int idPedido) {
        Pedido pedido = null;
        try {
            pedido = session.get(Pedido.class, idPedido);
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer el pedido");
        }
            return pedido;
    }

    public ArrayList<Pedido> leerTodosPedidos() {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            pedidos = (ArrayList<Pedido>) session.createQuery("SELECT p FROM Pedido p", Pedido.class).list();
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer las mesas");
        }
            return pedidos;
    }

    public void insertarPedido(Pedido pedido) {
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(pedido);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Algo no salió bien, se ha hecho un rollback");
            transaction.rollback();
        }
    }

    public void borrarPedido(Pedido pedido) {
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(pedido);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Algo no salió bien, se ha hecho un rollback");
            transaction.rollback();
        }
    }
    //endregion


    //region Métodos Productos
    public Productos leerProducto(int idProducto) {
        Productos producto = null;
        try {
            producto = session.get(Productos.class, idProducto);
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer el producto");
        }
            return producto;
    }

    public ArrayList<Productos> leerTodosProductos() {
        ArrayList<Productos> productos = new ArrayList<>();
        try {
            productos = (ArrayList<Productos>) session.createQuery("SELECT p FROM Productos p", Productos.class).list();
        } catch (Exception e) {
            System.err.println("Algo no salió bien al leer las mesas");
        }
            return productos;
    }

    public void insertarProducto(Productos producto) {
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(producto);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Algo no salió bien, se ha hecho un rollback");
            transaction.rollback();
        }
    }

    public void borrarProducto(Productos producto) {
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(producto);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Algo no salió bien, se ha hecho un rollback");
            transaction.rollback();
        }
    }
    //endregion

    /**
     * Realiza el setup del hibernate
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
