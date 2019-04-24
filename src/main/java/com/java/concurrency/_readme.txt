多线程
1.Thread与Runnable
  Thread实现Runnable;Runnable可以避免java中的单继承的限制,因为自定义Thread必须继承Thread;异常都只能在内部消化;
  在非常简单的情况下可以直接extends Thread
2.各种方法
  Thread.yield();线程让步,表示这是其他线程执行任务的大好时机,但是自己也会执行.对于任何重要的控制或在调整应用时都不能依赖yield().
  Thread.sleep();新写法:TimeUnit.MILLISECONDS.sleep(2000);休眠.
  优先级:倾向于让优先权最高的线程执行,但不是绝对,也就是说优先权并不会导致死锁;试图操作线程优先级通常是一种错误
  	可以使用getPriority读取现有线程的优先级,并且任何时刻都可以使用setPriority来修改它
  	volatile关键字:如果volatile变量被修改,使得线程每次获取volatile变量都是最新的
  后台线程(守护线程):setDaemon(),指在程序运行的过程中在后台提供一种通用服务的线程,这种线程并不是程序中不可或缺的部分.
  	普通线程称为用户线程,只完成用户自己想要完成的任务,不提供公共服务;有时我们需要编写一段能够提供公共服务的程序,以保证所有用户针对该线程的请求都有响应.
  	非后台线程全部结束表示程序终止,那么所有的后台程序也会被杀死.
  	想将创建的线程声明为后台线程 ，必须在启动前将setDaemon()设置为true.
  	如果一个线程时后台线程, 那么它创建的任何子类也自动设置为后台线程.
  	后台线程在不执行finally字句的情况下就会终止其run()方法.
	后台线程(daemon)
	　　|——定义：指在程序运行的时候在后台提供一种通用服务的线程，并且这种线程并不属于程序中不可或缺的部分
	　　|       |——所有的“非后台线程”结束时，程序也就终止了，同时会杀死进程中所有后台线程：main就是一个非后台线程
	　　|——声明并试用后台线程
	　　|       |——传统方式：通过声明线程，操作线程来定义后台线程
	　　|       |         |——Thread daemon = new Thread(new TaskDaemon());//将任务交给线程也叫声明线程
	　　|       |         |——	daemon.setDaemon(true);//将线程设置为后台线程
	　　|       |         |——daemon.start();//启动后台线程
	　　|       |——由executor调用线程工厂：通过编写定制的ThreadFactory，可以定制由Executor创建的线程的属性
	　　|      　　　　 |——1.实现ThreadFactory接口
	　　|      　　　　 |——2.重写newThread方法
	　　|     　　　　  |——	public Thread newThread(Runnable r){
	　　|     　　　　  |——	Thread t = new Thread(r);
	　　|      　　　　 |——	t.setDaemon(true);
	　　|      　　　　 |——	return t;
	　　|      　　　　 |——	}
	　　|      　　　　 |——3.将定制的TaskDaemonFactory传递给Executor，它将用此来生成对应的线程，并操纵他们
	　　|      　　　　 |——	每个静态的ExecutorService创建方法都被重载为接受一个ThreadFactory对象，该对象将被用来创建新的线程
	　　|      　　　　 |——	ExecutorService exec = Executors.newCachedThreadPool(new TaskDaemonFactory());
	　　|      　　　　 |——4.将任务传递给exec，它会帮你执行线程操作
	　　|      　　　　 |——	exec.execute(new TaskDaemonFromFactory());
  t.join();加入一个线程,某个线程在另一个线程t上调用t.join(),此线程被挂起,直到目标线程t结束才恢复(即t.isAlive()返回为false),在调用线程上调用interrupt()方法可以中断
3.线程池
  // newCachedThreadPool为每个任务都创建一个线程;创建与所需数量相同的线程,在回收旧线程时停止创建新的线程(最常用)
  // FixedThreadPool(count)创建有限的线程集执行任务;预先执行代价高昂的线程分配,可以节约创建线程的开销(常用)
  // SingleThreadPool很像线程数量为1的FixedThreadPool,适用于长期存活的任务,它是按照顺序执行的,如果多个任务将会排队
  ExecutorService exec = Executors.newCachedThreadPool();
  exec.execute(new Thread());// 执行任务
  // exec.shutdown()用于有序关闭过程
  // JDK声明:关闭后,执行程序最终将终止,此时没有任务正在执行,没有任务正在等待执行,也没有任何新任务可以提交
  exec.shutdown();

  3.1 Callable<T>可以从任务中产生返回值
    返回值从call()方法获取,且必须使用ExecutorService.submit()调用;可以抛出异常
    submit()方法产生Future<V>对象,isDone()方法表示Future是否已经完成;当任务完成有一个结果,可以调用get()获取,如果不调用isDone()直接调用get(),get()将会阻塞,直至结果准备就绪
4.异常
  线程的异常并不会被catch到,会向上抛出;需要自定义异常实现Thread.UncaughtExceptionHandler;
5.同步
  synchronized与Lock：前者代码比较少，后者代码多，需要显示new、lock、unlock，但是可以在finally里维护正确的状态（一般在需要解决特殊问题使用）
6.中断(Thread.interrupt())
  interrupt()方法可以中断对sleep()的调用,但不可以中断早已试图获取synchronized锁或试图执行I/O操作的线程,这意味着I/O具有锁住多线程程序的潜在可能性(Interrupting.java)
  	其中一个解决办法是:关闭发生阻塞的底层资源,即executor.shutdownNow(),(CloseResource.java)
  	还有一个办法是:使用nio,被阻塞的nio通道会自动的响应中断.(NIOInterruption.java)

