##适配器模式

#####定义：将一个类的接口转换成客户希望的另一个接口，使原本接口不兼容而不能一起工作的那些类能一起工作

######适配器模式分为类适配器（耦合度高，采用继承的方式实现）、对象适配器（采用组合的方式实现）和接口适配器（默认实现接口的方法）三种

#####缺点：对类适配器来说，更换适配器的实现过程比较复杂

主要角色
1. 目标接口（Target）：当前业务所期待的接口（或抽象类）
2. 适配者（Adaptee）：它是被访问和适配的现存组件库中的组件接口
3. 适配器（Adapter）：是一个转换器，通过继承或引用适配者对象，把适配者转换成目标接口，让客户按目标接口的格式访问适配者

![类适配器模式结构图](http://c.biancheng.net/uploads/allimg/181115/3-1Q1151045351c.gif "类适配器模式结构图")
--
![对象适配器模式结构图](http://c.biancheng.net/uploads/allimg/181115/3-1Q1151046105A.gif "对象适配器模式结构图")