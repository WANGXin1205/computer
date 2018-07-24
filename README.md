# computer

#### 项目介绍
我的电脑文件管理后端，我写着玩，可能会用到Java，Kotlin，Scala，Haskell；   
然后为了找工作，苦逼的我会写一些练习代码，比如多线程；   
其实我是很喜欢写算法的，以前用Matlab实现过一些算法，结果现在代码丢了>_<!;   
因为是新开工程，所以我写的很杂，其实我是想分成几个项目搞的。   

#### 软件语言
目前只有Java，其实我写了scala和kotlin，在其他工程。

#### 软件架构
springBoot和SSM 

#### 特别说明
我是小菜鸟，什么都不会，如果觉得我的代码写的不好，
可以拉分支改进，希望能看到牛逼闪闪的代码。

#### 特别感谢
农夫山泉 被带入坑了
农夫山泉开发三组，棒棒哒，全公司最棒~~低调低调

#### 更新日志
2018.6.29  1:24  
我决定将这个项目作为我第一个开源项目，之前觉得自己写的算法很牛逼，有很多私有
未开源的代码，现在想想，那些代码还是很牛逼。目前涉及视频和图书两个电脑管理模块，
用了Gson，排序算法写了冒泡，定义了二叉树，然后为了应付找工作面试，我写了多线程
的简单实现。
          
2018.6.30 00:00   
增加了数据库创建sql,增加了选择排序，插入排序，并给出排序算法的时间复杂度，
添加了两种实现多线程的简单方法。 

2018.7.1  
增加了线程的一些操作，增加了一个判断素数的低端方法和较高端方法。

2018.7.2   
多线程基本完毕

2018.7.5   
多线程基础探讨完毕，解决了依赖注入问题和事务回滚问题，
简要实验了其他方法的回滚，然后失败了。同样的使用网上给出的一般方法，也失败了。

2018.7.24
一晃7月又要过去了，讨论了spring的事务传播机制。
增加了hashtable，hashMap，concurrentHashMap的讨论。

#### 未来工作  
1.各种排序算法的实现     
2.我电脑数据的录入（不会更新代码结构，只是录入数据）  
3.素数判断的更高大上算法  

#### 困惑
Q1. @Resource 标签在jdk10下无法正常使用；  
A1 

Q2. 多线程依赖注入会报空指针？
A2. spring对多线程的支持不是很好，需要手动的去获取一些bean，才能够注入

Q3. 多线程的回滚怎么做？
A3. 网上有说是用callable去获取每个子线程的结果，如果有false就抛出异常回滚。
    但是我没有成功，我在子线程中做了回滚。同样子线程异常，主线程回滚，
    发现数据并不能回滚。

#### 参与贡献

1. Fork 本项目
2. 新建 develop_xxx 分支
3. 提交代码
4. 新建 Pull Request

码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
