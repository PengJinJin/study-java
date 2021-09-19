##备忘录模式

#####定义：在不破坏封装行的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便以后当需要时能将该对象恢复到原先保存的状态。
######该模式又叫快照模式

#####缺点：资源消耗比较大。如果要保存的内部状态信息过多或特别频繁，将会占用比较大的内存资源

主要角色
1. 发起人（Originator）：记录当前时刻的内部状态信息，提供创建备忘录和恢复备忘录数据的功能，实现其他业务功能，它可以访问备忘录里所有的信息
2. 备忘录（Memento）：负责存储发起人的内部状态，在需要的时候提供这些内部状态给发起人
3. 管理者（Caretaker）：对备忘录进行管理，提供保存与获取备忘录的功能，但其不能对备忘录对备忘录对内容进行访问与修改

![备忘录模式的结构图](http://c.biancheng.net/uploads/allimg/181119/3-1Q119130413927.gif "备忘录模式的结构图")