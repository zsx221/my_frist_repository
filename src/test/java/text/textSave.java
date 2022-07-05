package text;
import domain.Customer;
import org.junit.Test;
import utils.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class textSave {
/*
*测试jpa的保存
案例:保存一个客户到数据库中
零
Jpa的操作步骤
*
1加载配置文件创建工厂(实体管理器工厂 )对象
2.通过实体管理器工厂获取实体管理器
3.获取事务对象，开启事务
4.完成增删改查操作
5.提交事务(回滚事务)
6.释放资源
率*/

    @Test
    public  void textSave(){
        /*
        //1、加载配置文件创建工厂，（实体管理器工厂）对象
        EntityManagerFactory myfactroy = Persistence.createEntityManagerFactory("myjpa");
        //2、通过实体管理器工厂获取实体管理器
        EntityManager em=myfactroy.createEntityManager();
        //3、获取事务对象，开启事务
        */
        EntityManager em= JpaUtils.getEntityManager();
        EntityTransaction tx= em.getTransaction();
        tx.begin();//开启事务
        //4、完成crud
        Customer customer=new Customer();
        customer.setCustName("xhd");
        customer.setCustAddress("深圳市2");
        //保存
        em.persist(customer);//保存操作
        tx.commit();//提交事务
        em.close();//释放资源
    //    myfactroy.close();   不关闭工厂，提供一个公共的工厂
    }
    /* 根据id查客户*/
    /*
    根据id查询客户
    使用find方法查询:
        1.查询的对象就是当前客户对象本身
        2.在调用find方法的时候，就会发送sq1语句查询数据库
    */

    @Test
    public void findbyid1(){
        //1、通过工具类获取EntityManager
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        //2、开启事务
        tx.begin();
        //3、增删改查
        /*       根据id查客户
                   find查客户
                        class ：查询数据结果需要包装的实体类类型的字节码
                        id：查询的主键的取值
         */
        Customer customer = em.find(Customer.class, 1l);
        //4、提交事务
        tx.commit();
     //  System.out.println(customer);
        //5、释放资源
        em.close();
    }
    /* 根据id查客户
    * 根据id查询客户
            *getReference方法
            *   1.获取的对象是一一个动态代理对象
            *    2.调用getReference方法不会立即发送sql语句查询数据库
            *本当调用查询结果对象的时候，才会发送查询的sq1语句:
            * 什么时候用，什么时候发送sql语句查询数据库
            延迟加载(懒加载)
            *得到的是一个动态代理对象
            *什么时候用，什么使用才会查询
    *
    * */
    @Test
    public void findbyid2_ference(){
        //1、通过工具类获取EntityManager
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        //2、开启事务
        tx.begin();
        //3、增删改查
        /*       根据id查客户
                   find查客户
                        class ：查询数据结果需要包装的实体类类型的字节码
                        id：查询的主键的取值
         */
        Customer customer = em.getReference(Customer.class, 1l);
        //4、提交事务
        System.out.println(customer);
        tx.commit();
       // System.out.println(customer);
        //5、释放资源
        em.close();
    }
    @Test
    public void removebyid(){
        //1、通过工具类获取EntityManager
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        //2、开启事务
        tx.begin();
        //3、增删改查
        /*       根据id查客户
                   find查客户
                        class ：查询数据结果需要包装的实体类类型的字节码
                        id：查询的主键的取值
                  然后把查出来的对象放到remove里面，完成操作
         */
        Customer customer = em.getReference(Customer.class, 1l);
        //4、提交事务
        em.remove(customer);
        System.out.println(customer);
        tx.commit();
        // System.out.println(customer);
        //5、释放资源
        em.close();
    }
    @Test
    public void updatebyid(){
        //1、通过工具类获取EntityManager
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        //2、开启事务
        tx.begin();
        //3、增删改查
        /*       根据id查客户
                   find查客户
                        class ：查询数据结果需要包装的实体类类型的字节码
                        id：查询的主键的取值
        */
        Customer customer = em.getReference(Customer.class, 1l);
        //然后修改数据
        customer.setCustAddress("湖南衡阳");
        //进行更新操作
        em.merge(customer);
        //4、提交事务
        tx.commit();
        //5、释放资源
        em.close();
    }
}
