package text;

import org.junit.Test;
import utils.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class jpqlText {
    /**
     * 查询全部
     * jqpl. from cn. itcast .domain.Cus tomer
     * sql: SELECT FROM cst. customer
     *
     */
    @Test
    public void testFindA11() {
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        //2.开启事务
        tx.begin();
        //3.查询全部
        String d="from domain.Customer";
        Query query = em.createQuery(d);//创建query查询对象
        //发生查询，并封装结果集
        List list = query.getResultList();
        for(Object o:list){
            System.out.println(o);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }
    /*
    *   排序查询:倒序查询全部客户 (根据id倒序)
    *   sql: SELECT 率FROM cst_ customer ORDER BY cust_ id DESC
    *   jpql: from Customer order by custId desc
    * */
    @Test
    public void testFinddesc() {
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        //2.开启事务
        tx.begin();
        //3.查询全部
        String jpql="from domain.Customer order by custId desc";
        Query query = em.createQuery(jpql);//创建query查询对象
        //发生查询，并封装结果集
        List list = query.getResultList();
        for(Object o:list){
            System.out.println(o);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }
}
