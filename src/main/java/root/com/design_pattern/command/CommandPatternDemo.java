package root.com.design_pattern.command;

import java.util.ArrayList;
import java.util.List;

public class CommandPatternDemo {

	public static void main(String[] args) {
		Stock stock = new Stock();
		BuyStock bs = new BuyStock(stock);
		SellStock ss = new SellStock(stock);

		Broker broker = new Broker();
		broker.addOrder(bs);
		broker.addOrder(ss);

		broker.placeOrders();
	}

}

interface Order {
	void execute();
}

class BuyStock implements Order {

	private Stock stock;

	public BuyStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public void execute() {
		stock.buy();
	}
}

class SellStock implements Order {

	private Stock stock;

	public SellStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public void execute() {
		stock.sell();
	}
}

// 实现者/接收者
class Stock {

	private String name = "ABC";
	private int quantity = 10;

	public void buy() {
		System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] bought");
	}

	public void sell() {
		System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] sold");
	}

}

// 调用者/请求者
class Broker {

	private List<Order> list = new ArrayList<>();

	public void addOrder(Order order) {
		list.add(order);
	}

	public void placeOrders() {
		for (Order order : list) {
			order.execute();
		}
		list.clear();
	}

}
