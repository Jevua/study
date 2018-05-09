# Jenkins
1、怎么启动Jenkins？

　　step1：进入到Jenkins的war包所在的目录。

　　　　如果是win7及以上版本，直接打开Jenkins的war包所在的目录，在地址栏敲cmd，回车。

　　　　上述结果和进入cmd后，用cd命令进入期望目录  是一样的。

　　step2：java -jar jenkins.war(调用里面的这个war包，如果你的war包名字不是Jenkins.war，请用你的war包名字，不可生搬硬套)

2、启动Jenkins服务

net start jenkins  （注：如果Jenkins曾经启动过，启动服务不需要进入到某个目录）

3、停止Jenkins服务

net stop jenkins

注：Jenkins的关闭和启动都可以通过关闭和启动服务来进行。

4、登录地址

http://localhost:8080/
