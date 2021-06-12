# 一个简单的，未完成的云工厂工程

一个简单的，未完成的云工厂工程

as a summer term training work

## 为什么是未完成

- [x] 用户管理
- [x] 用户注册/登录
- [x] 产品管理
- [x] 设备管理
- [x] 工厂管理
- [ ] 订单管理（未完）
- [ ] 设备类型管理（未完）
- [ ] 产品类别管理（未完）
- [ ] 订单排单管理（未完）

## 技术选型

- 界面（前端）：Java swing
- 逻辑（后端）：Java
- 数据持久化：Java文件读写

~~所以直接说是个纯 Java 工程不就完事了😂~~

### 开发时使用的环境

- JDK 13 
- Eclipse 2019-12
- Window Builder 插件 （可有可无，不过确实要写 Java GUI 还是建议装）
- Amateras UML 类图生成插件 （可有可无）

### 关于架构

这点是我最想吐槽的，个人看来，其实这个工程只是采用了分层架构，而且对于一个普通的 GUI 且数据只是保存在文件里面的工程来说，controller，service，dao的三层架构都略显冗余，~~但最要命的是，实训验收的老师似乎把这个架构当成MVC架构了~~，私以为，只有 frames 包里面的一些 Java swing 组件才是 MVC 的体现。不过这只是个人观点，或者我是站在严格的 MVC 架构观点来看待这个工程的吧。

## 启动！

启动 `com.cloudfactory.frames` 包下面的 `Login.java` 即可

超级管理员的用户名和密码都是 `admin`

然后在用户管理里面就可以看到其它的用户名和密码了

## 参考

- [菜鸟教程_单例模式](https://www.runoob.com/design-pattern/singleton-pattern.html)

- [菜鸟教程_工厂模式](https://www.runoob.com/design-pattern/factory-pattern.html)

- [JTable添加按钮效果和事件效果](https://blog.csdn.net/u012067254/article/details/16891797/)

- [绿调线面图标](https://www.iconfont.cn/collections/detail?cid=18219)

