package root.com.design_pattern.mediator;

public class MediatorDemo {

	public static void main(String[] args) {
		Alarm alarm = new Alarm();
		CoffeePot coffeePot = new CoffeePot();
		Calender calender = new Calender();
		Sprinkler sprinkler = new Sprinkler();
		Mediator mediator = new ConcreteMediator(alarm, coffeePot, calender, sprinkler);
		// 闹钟事件到达，调用中介者就可以操作相关对象
		calender.onEvent(mediator);
	}

}

// 抽象中介者
interface Mediator {
	void doEvent(String eventType);
}

// 具体中介者
class ConcreteMediator implements Mediator {

	private Alarm alarm;
	private CoffeePot coffeePot;
	private Calender calender;
	private Sprinkler sprinkler;

	public ConcreteMediator(Alarm alarm, CoffeePot coffeePot, Calender calender, Sprinkler sprinkler) {
		this.alarm = alarm;
		this.coffeePot = coffeePot;
		this.calender = calender;
		this.sprinkler = sprinkler;
	}

	@Override
	public void doEvent(String eventType) {
		switch (eventType) {
			case "alarm":
				doAlarmEvent();
				break;
			case "coffeePot":
				doCoffeePotEvent();
				break;
			case "calender":
				doCalenderEvent();
				break;
			default:
				doSprinklerEvent();
		}
	}

	public void doAlarmEvent() {
		alarm.doAlarm();
		coffeePot.doCoffeePot();
		calender.doCalender();
		sprinkler.doSprinkler();
	}

	public void doCoffeePotEvent() {
		coffeePot.doCoffeePot();
	}

	public void doCalenderEvent() {
		calender.doCalender();
	}

	public void doSprinklerEvent() {
		sprinkler.doSprinkler();
	}
}

// 抽象同事
interface Colleague {
	void onEvent(Mediator m);
}

// 具体同事1
class Alarm implements Colleague {
	@Override
	public void onEvent(Mediator m) {
		m.doEvent("alarm");
	}

	public void doAlarm() {
		System.out.println("doAlarm()");
	}
}

// 具体同事2
class CoffeePot implements Colleague {

	@Override
	public void onEvent(Mediator m) {
		m.doEvent("coffeePot");
	}

	public void doCoffeePot() {
		System.out.println("doCoffeePot()");
	}
}

// 具体同事3
class Calender implements Colleague {
	@Override
	public void onEvent(Mediator m) {
		m.doEvent("calender");
	}

	public void doCalender() {
		System.out.println("doCalender()");
	}
}

// 具体同事4
class Sprinkler implements Colleague {

	@Override
	public void onEvent(Mediator m) {
		m.doEvent("sprinkler");
	}

	public void doSprinkler() {
		System.out.println("doSprinkler()");
	}
}


