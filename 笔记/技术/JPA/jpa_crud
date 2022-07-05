案例：客户的相关操作(crud)
客户：就是一家公司
客户表：
  jpa操作的操作步骤
    1、加载配置文件创建实体管理器工厂
      persistent：静态方法（根据持久化单元名称创建实体管理器工厂）
          createEntityManagerFactory(持久化单元名称)
          作用：创建实体类管理器工厂
     2、根据实体类管理器工厂，创建实体类管理器
          EntityManagerFactory:获取EntityManager对象
            *内部维护了数据库信息
              维护了缓存信息
               维护了所有的实体类管理器对象
            *在创建EntityManagerFactory的创建过程比较浪费资源
         特点：线程安全的对象
          多个线程访问一个对象(EntityManagerFactory)，不会造成资源冲突或者线程安全问题
          *如何解决EntityManagerFactory的创建过程浪费资源（耗时）的问题？
          思路：创建一个公共的EntityManagerFactory对象
          *静态代码块创建EntityManagerFactory
     3、创建事务对象，开启事务
          EntityManager对象：实体类管理器
             beginTransaction :创建事务对象
             presist :保存
             merge：更新
             remove :删除
             find/getRefrence :根据id查询（两者还是有区别的，find在语句之后直接执行，
             而getRefrence是懒加载，被使用了才会加载并执行）
      4、完成crud
      5、提交事务
      6、释放资源
  i.搭建环境的过程
    1.创建maven的工程导入坐标        
    2.需要配置jpa的核心配置文件
    3.编写客户的实体类
    4.配置实体类和表，类中属性和表中字段的映射关系
    5.保存客户到数据库中           （出现了一些问题，存到数据库的中文变成了？的编码错误）
  ii.完成基本CRUD案例
    persist：保存
    merge：更新
    remove：删除
    find/getReference：查
  iii.jpql查询---java持久查询语言
    sql：查询的是表和表中的字段
    jpql：查询的是实体类和类中的属性 
    *jpql和sql语句的语法相似
    1、查询全部
    2、分页查询
    3、统计查询
    4、条件查询
    5、排序
    
