1、结合PageHelper分页的插件自己做了个简单的分页
        访问http://www.wenqiang.com:8080/queryAllByPage?pageNum=1&pageSize=2
        http://blog.csdn.net/isea533/article/details/23831273
2、同时整合了log4j 和log4jdbc框架，实现sql的输出
    加入引用：
    <dependency>
                <groupId>com.googlecode.log4jdbc</groupId>
                <artifactId>log4jdbc</artifactId>
                <version>1.2</version>
    </dependency>
    数据连接做处理
    spring.datasource.url=jdbc:log4jdbc:mysql://192.168.8.195:3306/liao_test
    spring.datasource.driver-class-name=net.sf.log4jdbc.DriverSpy

    http://ju.outofmemory.cn/entry/219285
3、做了环境切换的测试。
参考 http://m.blog.csdn.net/article/details?id=52013537
