# halstead租赁系统

## MINIO

S3-API: http://192.168.10.101:9000  http://192.168.122.1:9000  http://127.0.0.1:9000
RootUser: `minioadmin`
RootPass: `minioadmin`

Console: http://192.168.10.101:43391 http://192.168.122.1:43391 http://127.0.0.1:43391
RootUser: `minioadmin`
RootPass: `minioadmin`

MINIO_ROOT_USER=`minioadmin`
MINIO_ROOT_PASSWORD=`minioadmin`
MINIO_VOLUMES=`/data`
MINIO_OPTS=`"--console-address :9001"`



## MySQL : Server01

username: `root`

password: `Lyqlah5577`$$



## Redis

启动客户端`redis-cli -h 127.0.0.1 -p 6379`

127.0.0.1是默认的系统自身，6379是redis默认的端口号



# 阿里云

AccessKeyID: **LTAI5tAGpZPZKmx7xgP45faL**

AccessKeySecret: **Q019KaFbv49zUUncBXTmrxgh3VJRjx**



# 学习总结

Linux系统使用、虚拟机配置、MobaXterm远程SSH访问、NAT内网配置

MyBatispuls、Minio、Redis连接、Knife4j案例项目



该项目为单体项目，个人认为最大的难点是该项目的后台涉及到的数据库SQL查询逻辑十分的复杂，

由于其数据表结构具有相当庞大的关联系统，每个租赁单位涉及到非常多的attribute，所以在写Mybatisplus自定义mapper时，需要配置复杂的query语句以及应用resultMap来映射复杂的返回结果



本项目中同样使用了**ThreadLocal**存储用户信息，此处给出详细的解释：

由于加入了JWT自定义拦截器，所以系统的所有操作都会涉及到一次JWT校验功能，同时在本项目中用户的信息是整合到JWT的Payload当中并使用HS256算法加密，所以从JWT中就可以提取出用户的UserId。

在项目中controller中有部分接口会返回用户的信息给前端，那如果直接使用JWT解密操作就会很low，因为这样的话就会在拦截器解密一次，然后再次在controller中解密一次，是对系统资源的浪费。所以对于所有的需要调用用户信息的接口，统一在拦截器部分的JWT校验结束后，就将从JWT解密出来的用户信息以键值对的形式存储在ThreadLocal当中，如果项目中有调用用户信息的接口，则直接从ThreadLocal当中读取信息。



# 简历准备

技术栈：（Vue+移动端）双前端，SpringBoot+MyBatisPlus，MySQL+Redis+MINIO

* 灵活应用MybatisPlus嵌套查询和嵌套结果映射来满足复杂的多表查询和分页查询需求

* 注册自定义ConverterFactory映射前端请求，实现项目中的全部枚举类的自动构造

* 配置SpringBoot定时任务功能，实现定时检查租约状态功能以及数据库的主动刷新

* 应用MD5算法+EasyCaptcha验证码+JWT自定义拦截器方案实现登录校验与操作授权
* 接入阿里云短信服务，实现移动端验证码登录功能，并设置Key过期时间与限制发送频率
* 调用SpringBoot异步支持，实现异步后台保存用户浏览记录以减少接口响应时间
* 接入高德地图的地理/逆地理编码API，实现结构化地址与经纬度之间相互转化

