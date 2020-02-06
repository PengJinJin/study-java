package com.design_pattern.command;

/**
 * 调用者Invoker里面有命令Command
 * Command里面有实现者Receiver
 * Invoker实际上通过Command调用的Receiver
 */
public class CommandPattern {

	public static void main(String[] args) {
		Command cmd = new ConcreteCommand();
		Invoker invoker = new Invoker(cmd);
		System.out.println("main------");
		invoker.call();
	}
}

//抽象命令
interface Command {
	void execute();
}

// 具体命令
class ConcreteCommand implements Command {
	private Receiver receiver;

	public ConcreteCommand() {
		receiver = new Receiver();
	}

	@Override
	public void execute() {
		receiver.action();
	}
}

// 实现者/接收者
class Receiver {
	public void action() {
		System.out.println(">>>Receiver.action()~~");
	}
}

//调用者
class Invoker {
	private Command command;

	public void call() {
		System.out.println(">>Invoker.call() command...");
		command.execute();
	}

	public Invoker(Command command) {
		this.command = command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
}
