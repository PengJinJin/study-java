##代理模式

#####定义：给某个对象提供一个代理类来控制对这个对象对访问，访问对象不能直接访问目标对象，需要使用代理类作为中介

#####缺点：请求速度变慢，增肌系统复杂度

主要角色
1. 抽象主题（Subject）：通过接口或抽象类声明真实主题和代理对象实现的业务方法
2. 真实主题（RealSubject）：实现了抽象主题中的具体业务，是代理类需要代理的真实对象
3. 代理类（Proxy）：提供了与真实主题相同的接口，内部有对真实主题的引用，可以访问、控制真实主题的功能

######静态代理缺点：每个代理类都需要实现抽象主题类，Subject增加方法代理类也需要改动
######动态代理实现：需要定义一个事件管理器实现InvocationHandler接口并重写invoke方法、调用java.lang.reflect.Proxy.newProxyInstance(当前使用到的appClassloader, 实现接口, 事件处理器对象)生成实例对象

![代理模式的结构图](http://c.biancheng.net/uploads/allimg/181115/3-1Q115093011523.gif "代理模式的结构图")