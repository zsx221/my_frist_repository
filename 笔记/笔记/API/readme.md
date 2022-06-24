关于API，这里提一下jpa是什么？jpa与hibernate究竟是什么关系？
一、认识JPA
1、JPA是什么？
全称java Persistence API，通过jdk 5.0注解或者XML描述对象-关系表的映射关系，并将运行期的实体对象持久化到数据库中
简而言之：用于对象持久化的API，使得应用程序以统一的方式访问持久层

2、那么JPA与Hibernate究竟是什么关系呢？
JPA是一种ORM规范，而Hibernate是JPA的一种实现，mybatis也是JPA的一种实现
我们可以类比的理解为JDBC技术和JDBC驱动的关系或者类比的理解为

3.JPA我们主要学习什么呢？
JPA包括三个方面的技术：
1、ORM映射元数据，支持XML和JDK注解两种元数据的形式
2、JPA的API
3.查询语言：JPQL
JPA和Hibernate是同一个人写的

二、JPA的基本使用
1、JPA的常用的几个注解
1)@Entity：修饰实体类，指明该类将映射到指定的数据表，例如Customer类默认的数据表名为customer
2)@Table：当实体类与映射的数据表名不同名时需要使用@Table注解，该注解与@Entity注解并列使用，使用其name属性
指明数据库的表名
3)@id：标识该属性为主键，一般标注该属性的getter方法上面
4、@GeneratedValue：标注主键的生成策略，通过其 strategy 属性。通常与 @Id 注解一起使用。默认情况下 JPA 会自动选择一个最适合底层数据库的主键生成策略
MySQL 默认为 AUTO
5、@Column：一般用于 getter 方法上。其 name 属性用来指明此属性在数据表中对应的列名；
unique 属性指明是否为唯一约束；nullable 属性用来指明是否可以为空
false 为不能为空；length 属性指明此列的长度。
