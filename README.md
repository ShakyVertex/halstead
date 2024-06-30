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



# 学习总结

Linux系统使用、虚拟机配置、MobaXterm远程SSH访问、NAT内网配置

MyBatispuls、Minio、Redis连接、Knife4j案例项目



该项目为单体项目，个人认为最大的特点是该项目的后台涉及到的数据库SQL查询逻辑十分的复杂，

由于其数据表结构具有相当庞大的关联系统，每个租赁单位涉及到非常多的attribute，所以在写Mybatispuls对应的mapper文件时，需要配置复杂的query语句以及应用resultMap来映射复杂的返回结果



# 简历准备

技术栈：（Vue+移动端）双前端，SpringBoot+MyBatisPlus，MySQL+Redis+MINIO

项目亮点：

配置MybatisPlus的逻辑删除功能与自动填充功能，并在序列化时忽略相应字段

注册自定义ConverterFactory映射前端请求，实现项目中的全部枚举类的自动构造

应用SpringMVC全局异常处理功能，统一处理所有异常使代码更易维护

接入高德地图的地理/逆地理编码API，实现结构化地址与经纬度之间相互转化的功能

配置SpringBoot定时任务功能，实现定时检查租约状态功能以及数据库的主动刷新
