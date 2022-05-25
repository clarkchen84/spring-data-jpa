### JPA 
1. JPA 是一套上层接口规范， 他有Hebernate 和OpenJpa 等实现。
2. JPA 是有约定大于配置的
3. JPA的实现
    1. 创建META-INF 文件夹
    2. 创建文件 `persistence.xml`
      ``` xml 
      <?xml version="1.0" encoding="UTF-8"?>
      <persistence version="2.0" xmlns="http://java.sun.com/xml/ns/persistence">
          <!-- name="持久化单元命名"  transaction-type="本地事务(RESOURCE_LOCAL)/JTA(分布式事物 )" -->
          <persistence-unit name="hibernateJPA" transaction-type="RESOURCE_LOCAL">
              <!-- 供应商 -->
              <provider>org.hibernate.ejb.HibernatePersistence</provider>
              <!--        ORM POJO 类-->
              <class>sizhe.chen.pojo.Customer</class>
      
              <properties>
                  <!-- 参数：数据库驱动名、地址、用户、密码、方言、显示执行SQL语句 -->
                  <property name="javax.persistence.jdbc.user" value="jpa"/>
                  <property name="javax.persistence.jdbc.password" value="jpa"/>
                  <property name="javax.persistence.jdbc. url" value="jdbc:mysql://127.0.0.1:3306/jpa"/>
                  <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
                  <property name="hibernate.hbm2ddl.auto" value="update"/>
                  <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
                  <property name="hibernate.show_sql" value="true"/>
      
                  <!-- 其他设置 -->
                  <property name="minPoolSize" value="5"/>
                  <property name="initialPoolSize" value="10"/>
                  <property name="idleConnectionTestPeriod" value="120"/>
                  <property name="acquireIncrement" value="10"/>
                  <property name="checkoutTimeout" value="3600"/>
                  <property name="numHelperThreads" value="4"/>
                  <property name="maxStatements" value="400"/>
                  <property name="maxStatementsPerConnection" value="20"/>
                  <property name="maxIdleTime" value="180"/>
                  <property name="acquireRetryAttempts" value="30"/>
                  <property name="maxPoolSize" value="200"/>
      
              </properties>
          </persistence-unit>
      </persistence>
      ```
4. jpa 的使用方法
   1. 创建`EntityManagerFactory`
   ``` java
    private EntityManagerFactory factory;
    @Before
    public  void init(){
        //1. 需要创建EntityManagementFactory
        factory= Persistence.createEntityManagerFactory("hibernateJPA");// 参数为persistence.xml 中 持久化单元的名称
    }

   ```
   2. 使用JPA 进行创建操作
   ``` java
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
   ```
   3. 立即查询
   ``` Java
    @Test
    public void testRFInd(){
        EntityManager manager = factory.createEntityManager();
        System.out.println(manager.find(Customer.class, 2L));
    }
   ```
   4. 延迟查询
   ``` java 
       @Test
    public void testRGetReference(){
        EntityManager manager = factory.createEntityManager();
        Customer customer = manager.getReference(Customer.class, 2L);
        System.out.println("========");
        System.out.println(customer);
    }
   ```
   5. 修改， 实体bean中追加key
   ``` java
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

   ```
   6. JPQL
   ``` java
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
   ```
   7. 删除, 不能删除游离态的对象，必须先查询，然后才能删除
   ``` java 
    @Test
    public void testD(){
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        manager.remove(manager.find(Customer.class,5l));
        transaction.commit();

    }
   ```