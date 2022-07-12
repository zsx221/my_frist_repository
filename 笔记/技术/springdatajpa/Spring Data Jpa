SpringDataJpa第二天
orm思想，hibernate, JPA的相关操作
* SpringDataJpa
第一springDataJpa的概述
I
第二springDataJpa的入门门操作
案例:客户的基本CRUD
  i.搭建环境
    创建工程导入坐标
    配置spring的配置文件
    编写实体类(Customer) ，使用jpa注解配置映射关系
  ii .编写-一个符合springDataJpa的dao层接口
                符合SpringDataJpa的dao层接口规范
                1.需要继承两个接口(JpaRepository, JpaSpecificationExecutor)
                2.需要提供响应的泛型
     JpaRepository<操作的实体类类型，实体类中主键属性的类型>
      * 封装了基本的CRUD操作
     JpaSpecificationExecutor<操作的实体类类型>
      * 封装了复杂操作
    
第三springDataJpa 的运行过程和原理剖析
   (例子：findone)(问题：jdk动态代理不是很理解)
    1、使用接口继承JpaRepository,和JpaSpecificationExecutor
    2、在使用接口的一些API的时候，会自动使用JDK的动态代理，从而实现InvocationHandler了，JdkDynamicAopProxy的invoke创建一个代理对象
    3、从而获取一个JpaRepository的实现类SimpleJpaRepository，再使用其中的findone的方法
    回顾
    i. springDatajpa, jpa规范，hibernate三者之间的关系
        code-- > springDatajpa --> jpa规范的API --> hibernate
    ii.符合springDataJpa规范的dao层接口的编写规则
        1.需要实现两个接口(JpaRepository, JapSpecificationExecutor )
        2.提供响应的泛型
    iii.运行过程
        *动态代理的方式:动态代理对象
iiii.查询
    JpaSpecificationExecutor方法列表
      T findOne (Specification<T> spec); //查询单个对象
      List<T> findAll (Specification<T> spec); //查询列表
      //查询全部，分页
      //pageable:分页参数
      //返回值:分页pageBean (page:是springdatajpa提供的 )
      Page<T> findAll (Specification<T> spec, Pageable pageable) ; !
      //查询列表
      //sort:排序参数
      List<T> findAll (Specification<T> spec, Sort sort);
      long count (Specification<T> spec) ;//统计查询
      * specification :查询条件
      自定义我们自己的Specification实现类
      实现
        //root:查询的根对象(查询的任何属性都可以从根对象中获取)
        //CriteriaQuery:顶层查询对象，自定义查询方式(了解: 一般不用)
        //criteriaBuilder:查询的构造器，封装了很多的查询条佣

          Predicate toPredicate (Rogt<T> root, criteriaQuery<?> query, criteriaBuilder cb);

第四复杂查询
    
