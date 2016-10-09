主要功能如下：
=====
1、数据库
======

    Druid数据库连接池，监控数据库访问性能，详细统计SQL的执行性能，这对于线上分析数据库访问性能有帮助。 数据库密码加密。

2、持久层
======

    mybatis持久化，PageHelper分页。Transtraction注解Jta事务。

3、MVC
======

    基于spring mvc注解。Exception统一管理。
    spring security权限管理。
    aop日志记录。

4、调度
======

    Spring task, 可以查询已经注册的任务。立即执行一次任务。

5、缓存和Session
===========

    注解redis缓存数据，Spring-session和redis实现分布式session同步(建议按功能模块划分系统)。


6、日志
===========

    logback打印日志，业务日志和调试日志分开打印。同时基于时间和文件大小分割日志文件。


9、项目构建
===========

    mybatis generator生成mybatis映射文件。 
    请先安装eclipse  mybatis－generator插件。

10、其它
===========

>####说明：启动项目前请安装Redis，并启动服务，系统中均使用默认配置。

打war包：（dev、test、prod）指定配置文件
mvn clean package -Dmaven.test.skip=true -P test