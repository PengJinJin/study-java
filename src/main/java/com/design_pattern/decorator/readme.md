##装饰模式

#####定义：在不改变现有对象结构的情况下，动态的给该对象增加一些职责。它属于对象结构型模式

#####缺点：增加许多子类，不可过度使用

主要角色
1. 抽象构件（Component）：定义一个抽象接口以规范准备接收附加责任的对象
2. 具体构件（Concrete Component）：实现抽象构件，通过装饰角色为其添加一些职责
3. 抽象装饰（Decorator）：继承抽象构件，并包含具体构件实例，可以通过其子类扩展具体构件的功能
4. 具体装饰（Concrete Decorator）：实现抽象装饰的方法，并给具体构件对象添加附加的责任

![装饰模式结构图](http://c.biancheng.net/uploads/allimg/181115/3-1Q115142115M2.gif "装饰模式结构图")