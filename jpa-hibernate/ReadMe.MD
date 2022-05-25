#### Hibernate
1. maven 依赖
   - 注意安装的Mysql的版本， 驱动版本， 以及在使用 dialect 配置 方言的时候， 选择的INNODB的版本要匹配
   ``` xml
    <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-entitymanager</artifactId>
            <version>5.4.32.Final</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.25</version>
    </dependency>
   ```
2. hibernate.cfg.xml 的配置
    ``` xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE hibernate-configuration PUBLIC
            "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
            "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
    <hibernate-configuration>
        <!-- 配置数据库连接 connection -->
        <session-factory>
            <!-- 数据库驱动 -->
            <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
            <!-- 数据库地址 localhost可以写作 jdbc:mysql:/// -->
            <property name="connection.url">jdbc:mysql://localhost:3306/jpa</property>
            <!-- 数据库用户名 -->
            <property name="connection.username">jpa</property>
            <!-- 数据库用户的密码 -->
            <property name="connection.password">jpa</property>
    
            <!-- 数据库方言 MySQL -->
            <property name="dialect">org.hibernate.dialect.MySQL8Dialect</property>
    
            <!-- 连接池 C3P0 -->
            <!--        <property name="hibernate.connection.provider_class">org.hibernate.connection.C3P0ConnectionProvider</property>-->
    
            <!-- 格式化输出生成的SQL语句 -->
            <property name="hibernate.show_sql">true</property>
            <property name="hibernate.format_sql">true</property>
            <!-- hibernate根据映射关系自动建表
                默认: 不会创建表
                create: 没有表就创建，有表就删除重建。
                create-drop: 没有表就创建，有表就删除重建，使用完自动删表。
                update: 没有表就创建表，否则使用现有的表。
                validate: 不会创建表
             -->
            <property name="hbm2ddl.auto">update</property>
            <!-- 加载映射文件 -->
            <mapping class="sizhe.chen.pojo.Customer"></mapping>
        </session-factory>
    </hibernate-configuration>
    ```
3. hibernate 实现增删改查
   * 查询
        1. 在使用find 查询的时候， 不是延迟加载
        2. 在使用load 查询的时候， 进行延迟加载
    * 更新删除
        1. 在进行更新删除的时候，都要追加key ，否在不执行 
    ``` Java
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
    ```
4. JPQL 与HQL ，一个是JPA的规范，一个是 hibernate 的规范，其中HQL 更强大， JPQL 不支持Insert
    ``` java 
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
    ```