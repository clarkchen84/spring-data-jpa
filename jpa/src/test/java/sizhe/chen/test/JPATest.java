package sizhe.chen.test;

import org.junit.Before;
import org.junit.Test;
import sizhe.chen.pojo.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Author: sizhe.chen
 * @Date: Create in 11:18 下午 2022/5/25
 * @Description:
 * @Modified:
 * @Version:
 */

public class JPATest {
    private EntityManagerFactory factory;
    @Before
    public  void init(){
        //1. 需要创建EntityManagementFactory
        factory= Persistence.createEntityManagerFactory("hibernateJPA");// 参数为persistence.xml 中 持久化单元的名称
    }

    @Test
    public void testC(){
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustName("张三");
        customer.setCustAddress("杭州");
        manager.persist(customer);
        transaction.commit();

    }
    @Test
    public void testU(){
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
       customer.setCustId(5L);
        customer.setCustName("李四");
        customer.setCustAddress("杭州");
        manager.merge(customer);
        transaction.commit();

    }
    @Test
    public void testD(){
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        manager.remove(manager.find(Customer.class,5l));
        transaction.commit();

    }

    @Test
    public void testRFInd(){
        EntityManager manager = factory.createEntityManager();
        System.out.println(manager.find(Customer.class, 2L));
    }

    @Test
    public void testRGetReference(){
        EntityManager manager = factory.createEntityManager();
        Customer customer = manager.getReference(Customer.class, 2L);
        System.out.println("========");
        System.out.println(customer);
    }
    @Test
    public void testRJQL(){
        EntityManager manager = factory.createEntityManager();
        String jql = "FROM Customer where custName=:name";
        System.out.println(manager.createQuery(jql, Customer.class).setParameter("name", "李四").getResultList());
        jql = "FROM Customer ";
        System.out.println(manager.createQuery(jql, Customer.class).getResultList());
        jql = "FROM Customer where  custName = ?1";
        System.out.println(manager.createQuery(jql, Customer.class).setParameter(1, "张三").getResultList());
    }
}
