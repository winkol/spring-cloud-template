# springCloud projects
/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 *            佛曰:
 *                   写字楼里写字间，写字间里程序员；
 *                   程序人员写程序，又拿程序换酒钱。
 *                   酒醒只在网上坐，酒醉还来网下眠；
 *                   酒醉酒醒日复日，网上网下年复年。
 *                   但愿老死电脑间，不愿鞠躬老板前；
 *                   奔驰宝马贵者趣，公交自行程序员。
 *                   别人笑我忒疯癫，我笑自己命太贱；
 *                   不见满街漂亮妹，哪个归得程序员？
 */


# cloud-config-client:7003
    测试config动态刷新
# cloud-config-client2:7003
    测试config动态刷新
# cloud-config-server:7001
    config配置中心
# cloud-eureka:9000
    注册中心
# cloud-zuul:80
    网关
# spring-boot-rabbitmq:7013
    mq
    Springboot 整合RabbitMq ，用心看完这一篇就够了
    https://blog.csdn.net/qq_35387940/article/details/100514134
    SpringBoot整合RabbitMQ之 典型应用场景实战一
    https://blog.csdn.net/u013871100/article/details/82982235
# spring-boot-timing:7008
    定时任务
# spring-cloud-feign:7004
    feign服务调用
# spring-cloud-hystrix:7005
    熔断（仪表盘）
# spring-cloud-hystrix-client:7006
    熔断使用
# spring-cloud-turebine:7007
    集群监控
# spring-cloud-zipkin:7012
    链路追踪
# spring-boot-junit5:7009
    单元测试
    1、请求参数校验
    2、后台请求
    3、请求，响应拦截（LogAspectConfig）
    4、springboot之多任务并行+线程池处理
    5、普通类获取bean工具类（SpringContextUtil）
    6、...
# spring-boot-threadqueue:7010
    ThreadPoolExecutor线程池+Queue缓冲队列实现高并发中进行下单业务
    1、Java并发编程：线程池的使用
    https://www.cnblogs.com/dolphin0520/p/3932921.html
    https://www.jianshu.com/p/13bf60545410?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
    
    2、Java多线程-线程池ThreadPoolExecutor构造方法和规则
    https://blog.csdn.net/qq_25806863/article/details/71126867#commentBox
  
    3、并发框架disruptor（高性能内存Queue）
    https://blog.csdn.net/fenglongmiao/article/details/79203788
    https://blog.csdn.net/xsh5324/article/details/84595717
    架构师入门笔记八 并发框架Disruptor场景应用
    https://blog.csdn.net/xsh5324/article/details/84595717
# spring-boot-apollo:7011
    阿波罗  远程配置管理工程  
    服务启动：E:\ld_work\workspaces\ideaIU\apollo\apollo-build-scripts
    demo.sh start
    
    springboot-监听apollo配置
    https://blog.csdn.net/qq_31289187/article/details/84346529
    https://blog.csdn.net/fenglongmiao/article/details/81460213
# spring-boot-mybatis:7012
    springboot+mybatis框架  (springboot+jpa)
# spring-boot-druid:7013
    springboot+druid框架
    1、集成jpa/mybatis
    2、连接串加密druid(扩展sharding)
    3、请求响应拦截/异常拦截
    4、分页插件PageHelper
    5、SHA256加密算法(SHA256Util)
    6、RestTemplate配置(RestTemplateConfig)
    7、文件上传FTP文件服务器
        SpringBoot整合自定义FTP文件连接池：https://cloud.tencent.com/developer/article/1432737
        springboot集成ftp: https://cloud.tencent.com/developer/article/1432735
        java操作ftp: https://cloud.tencent.com/developer/article/1140399
        win10中搭建并配置ftp服务器的方法: https://blog.csdn.net/baidu_38760069/article/details/88798825
    8、图片和PDF文件添加水印
        Java给图片和PDF文件添加水印(图片水印和文字水印)：https://www.cnblogs.com/qlqwjy/p/9326468.html
        java添加多个水印：https://www.cnblogs.com/SimonHu1993/p/9399752.html
    9、...
    
# spring-boot-ocr:7014
    1、图片提取
    2、图片/文字合识别
    
# spring-boot-encrypt:7015
    1、Java 实现对称加密
        DES（Data Encryption Standard）数据加密标准
          1 JDK 的 DES 实现、2 Bouncy Castle 的 DES 实现
        3DES（Triple DES）三重数据加密算法
          1 JDK 的 3DES 实现、2 Bouncy Castle 的 3DES 实现
        AES（Advanced Encryption Standard）高级加密标准
          1 JDK 的 AES 实现、2 Bouncy Castle 的 AES 实现
        PBE（Password Based Encryption）基于口令加密
          1 JDK 的 PBE 实现、2 Bouncy Castle 的 PBE 实现
        ......

# spring-boot-sentinel:7016
    Sentinel 以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性
    本地启动：java -Dserver.port=8080 -jar sentinel-dashboard-1.7.0.jar
    
    本地访问sentinel控制台：http://10.10.2.139:8080,用户和密码都是sentinel
    Springboot2（49）集成sentinel：
        https://blog.csdn.net/cowbin2012/article/details/90769370
    springBoot整合Sentinel实现降级限流熔断
        https://www.cnblogs.com/zjting/p/11406472.html

# 项目插件
    1、阿里巴巴Java开发规约IDEA插件安装及使用
        alibaba即可看到Alibaba Java Code Guidelines插件
        https://www.cnblogs.com/cnndevelop/p/7697920.html
    2、使用 FindBugs-IDEA 插件查找代码中潜在的 BUG
        https://www.jianshu.com/p/e6c264134602
    3、IDEA安装Sonarlint 代码扫描插件
        https://blog.csdn.net/weixin_42343424/article/details/84979830
    4、nginx 配置访问图片路径和静态页面
        https://www.jianshu.com/p/8e2df1ac3d83
    5、Java远程连接操作linux服务器，scp获取文件
        https://www.iteye.com/blog/aoyouzi-2091997
        java使用Jsch实现远程操作linux服务器进行文件上传、下载，删除和显示目录信息
        https://www.cnblogs.com/biehongli/p/9780652.html
