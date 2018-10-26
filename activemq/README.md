# ActiveMq

异步、解耦、流量削峰


* 使用SpringBoot练习ActiveMq消息中间件

1、首先是ActiveMQ的安装启动http://localhost:8161,用户密码都是admin

2、其次是代码层面的生产者发送消息

3、消费者监听获取消息


### 实践过程遇到的问题
* 启动项目时会出现bean的注解失败

扫描的路径出现问题，默认SpringBoot的扫描路径是@SpringBootApplication注解的入口类所在的目录，解决方法一个可以将你的代码移到此包下。另一个是主动设置SpringBoot的扫描路径@SpringBootApplication(scanBasePackages="com.example")


