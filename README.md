### 疯狂的程序员群：186305789
### 个人兴趣网站，[zerochl接码平台](https://xinghai.party)
### 个人兴趣网站，[猿指](https://blog.xinghai.party)
# Springboot Server端框架
实现功能，主被动API请求、腾讯IM集成、API json接收与返回值加密、二维消息队列、数据库队列异步写入、redis注解与java代码缓存、mongodb注解与java代码查询、聚合查询、拦截器、log4f分割、延迟事件管理、多线程与线程池管理等等，基本符合一个正常后台服务该有的东西。
# 涉及的第三方
Retrofit、Rxjava等。
# 关于MongoDB JPA
能用的JPA支持本项目基本都有了，之前还想自己写数据库指定字段更新的JPA，发现要修改的内容太多，最后还是使用Java方式完成了需求，包括复杂的聚合查询也是通过Java方式来实现的，JPA还是做做日常的查询工作吧。
# 关于延迟事件的管理
springboot提供了schedule来定时触发任务，但是如果需要类似Android的post就不行，所以我自己实现了一个管理类，可以达到Android post的功能，支持添加与删除延时任务，至于递归循环执行定时任务按照已经提供的post方法要实现非常简单。
# 关于redis
redis也使用了注解，同时实现了一个redis的管理类，用于批量删除redis，因为写接口的过程一般都会出现插入一条数据删除多种缓存的操作，所以直接java代码处理会比注解更简洁一些。
# 独创功能
* 二维消息队列，以空间换时间，提供消息优先级设置，同时腾讯IM免费版本只有100的并发，合理使用消息队列仍然可以支持偶尔的更高并发
* 数据库队列异步写入，当某个时间段写入操作非常频繁，且不注重一致性时可以使用此方式提高并发，原理类似与数据库主从
* API json接收与返回值加解密（这个是自己写的注解与实现）
# 具体文档请参考description.docx
