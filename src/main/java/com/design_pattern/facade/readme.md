##外观模式

#####定义：一种通过为多个复杂的子系统提供一个一致的接口，使这些子系统更加容易被访问。对外提供一个统一接口，而不担心如何实现的具体细节，大大降低应用程序的复杂性，也提高了可维护性。

#####缺点：不能很好的限制客户使用子系统类；新增的子系统类可能需要修改外观类或客户端类源码，违背了"开闭原则"

主要角色
1. 外观角色（Facade）：为多个子系统提供一个对外的接口
2. 子系统角色（Sub System）：实现系统的部分功能，客户可以通过外观角色访问
3. 客户角色（Client）：通过外观角色访问各个子系统的功能

![外观模式模式结构图](http://c.biancheng.net/uploads/allimg/181115/3-1Q115152143509.gif "外观模式模式结构图")