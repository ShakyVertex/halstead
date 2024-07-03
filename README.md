# halstead租赁系统

启动虚拟机server01，启动移动端后台和管理端后台

`systemctl start lease-app`

`systemctl start lease-admin`

启动虚拟机server02，检查nginx是否正常启动

`systemctl status nginx`

由于本机以及完成了域名映射，我们可以直接访问以下url

移动端 http://lease.atguigu.com

管理端 http://admin.lease.atguigu.com

----

Linux系统使用、虚拟机配置、MobaXterm远程SSH访问、NAT内网配置

MyBatispuls、Minio、Redis连接、Knife4j案例项目

最终会部署2台虚拟机，本机使用NAT对项目进行连接，静态的前端内容和Nginx服务会部署在一台linux虚拟机上，而后端的Spring项目（移动端后台和管理类端后台）会部署在另一台虚拟机。



该项目为单体项目，个人认为最大的难点是该项目的后台涉及到的数据库SQL查询逻辑十分的复杂，

由于其数据表结构具有相当庞大的关联系统，每个租赁单位涉及到非常多的attribute，所以在写Mybatisplus自定义mapper时，需要配置复杂的query语句以及应用resultMap来映射复杂的返回结果。



本项目中同样使用了**ThreadLocal**存储用户信息，此处给出详细的解释：

由于加入了JWT自定义拦截器，所以系统的所有操作都会涉及到一次JWT校验功能，同时在本项目中用户的信息是整合到JWT的Payload当中并使用HS256算法加密，所以从JWT中就可以提取出用户的UserId。

在项目中controller中有部分接口会返回用户的信息给前端，那如果直接使用JWT解密操作就会很low，因为这样的话就会在拦截器解密一次，然后再次在controller中解密一次，是对系统资源的浪费。所以对于所有的需要调用用户信息的接口，统一在拦截器部分的JWT校验结束后，就将从JWT解密出来的用户信息以键值对的形式存储在ThreadLocal当中，如果项目中有调用用户信息的接口，则直接从ThreadLocal当中读取信息。



# 简历准备

技术栈：（Vue+H5移动端）双前端，SpringBoot+MyBatisPlus，MySQL+Redis+MINIO

* 灵活应用MybatisPlus嵌套查询和嵌套结果映射来满足复杂的多表查询和分页查询需求
* 注册自定义ConverterFactory映射前端请求，实现项目中的全部枚举类的自动构造
* 配置@Schedule定时任务功能，实现定时检查租约状态功能以及数据库的主动刷新
* 应用MD5算法+EasyCaptcha验证码+JWT自定义拦截器方案实现登录校验与操作授权
* 接入阿里云短信服务，实现移动端验证码登录功能，并设置Key过期时间与限制发送频率
* 调用@Async异步支持，实现异步后台保存用户浏览记录以减少接口响应时间
* 使用延迟删除+主动更新的缓存优化方案，将核心业务接口的平均响应时间从39ms降低至5ms
* 使用Nginx作为静态资源http服务器，部署后端服务的反向代理和负载均衡

