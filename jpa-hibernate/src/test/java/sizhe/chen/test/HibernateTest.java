package sizhe.chen.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;
import sizhe.chen.pojo.Customer;

import javax.xml.crypto.dsig.TransformService;

/**
 * @Author: sizhe.chen
 * @Date: Create in 11:01 下午 2022/5/24
 * @Description:
 * @Modified:
 * @Version:
 */

public class HibernateTest {

    private SessionFactory sessionFactory;
    @Before
    public void init(){
        StandardServiceRegistry registry = new  StandardServiceRegistryBuilder()
                .configure("/hibernate.cfg.xml").build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
    @Test
    public void testC(){
        try(Session session = sessionFactory.openSession() ) {
            Transaction transaction = session.beginTransaction();
            Customer customer = new Customer();

            customer.setCustName("clark");
            customer.setCustAddress("dalian");
            session.save(customer);
            transaction.commit();
        }


    }


    @Test
    public void testRByIdFind(){
        try (Session session = sessionFactory.openSession()){
           // Transaction transaction = session.beginTransaction();
            System.out.println(session.find(Customer.class, 1L));

        }
    }
    @Test
    public void testRByIdLoad(){
        try (Session session = sessionFactory.openSession()){
            // Transaction transaction = session.beginTransaction();
            Customer customer = session.load(Customer.class, 1L);
            System.out.println("===========");
            System.out.println(customer);

        }
    }

    @Test
    public void testU(){
        try (Session session = sessionFactory.openSession()){
             Transaction transaction = session.beginTransaction();
            Customer customer = new Customer();
            customer.setCustId(1L);  //带有iD 是更新，没有带id 是新规
            customer.setCustName("clark");
            customer.setCustAddress("shenzhen");
            session.saveOrUpdate(customer);

            transaction.commit();

        }
    }

    @Test
    public void testD(){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Customer customer = new Customer();
             customer.setCustId(1L);  //带有iD 是更新，没有带id 是新规
            customer.setCustName("clark");
            customer.setCustAddress("shenzhen");
            session.remove(customer);

            transaction.commit();

        }
    }

    @Test
    public void testRByHQL(){
        try (Session session = sessionFactory.openSession()){
            // Transaction transaction = session.beginTransaction();
            //1. 查询全部
            String hql = " FROM Customer";
            System.out.println(session.createQuery(hql).getResultList());
            //2. 具名方式
            hql = " FROM Customer where custName=:test";
            System.out.println(session.createQuery(hql).setParameter("test","clark").getResultList());
            //3. ?查询
            hql = " FROM Customer where custName=?0";
            System.out.println(session.createQuery(hql).setParameter(0,"clark").getResultList());

        }
    }

}
