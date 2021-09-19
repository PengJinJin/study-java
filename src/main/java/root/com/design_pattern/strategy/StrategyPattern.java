package root.com.design_pattern.strategy;

/**
 * 这个例子：
 * 现实生活中我们到商场买东西的时候，
 * 卖场往往根据不同的客户制定不同的报价策略，
 * 比如针对新客户不打折扣，针对老客户打9折，针对VIP客户打8折...
 * 使用策略模式而不是if……else
 */
public class StrategyPattern {
	public static void main(String[] args) {
		//1.创建老客户的报价策略
		IQuoteStrategy oldQuoteStrategy = new OldCustomerConcrete();

		//2.创建报价上下文对象，并设置具体的报价策略
		QuoteContext quoteContext = new QuoteContext(oldQuoteStrategy);

		double price = quoteContext.getPrice(100.0D);
		System.out.println(price);

	}
}

// 抽象策略类
interface IQuoteStrategy {

	// 获取折后价的价格方法
	Double getPrice(Double originalPrice);

}

// 具体策略：老客户
class OldCustomerConcrete implements IQuoteStrategy {

	@Override
	public Double getPrice(Double originalPrice) {
		System.out.println("恭喜！老客户享有9折优惠！");
		return originalPrice * 0.9;
	}
}

// 具体策略：VIP
class VIPCustomerConcrete implements IQuoteStrategy {

	@Override
	public Double getPrice(Double originalPrice) {
		System.out.println("恭喜！VIP客户享有8折优惠！");
		return originalPrice * 0.8;
	}
}

// 环境类
class QuoteContext {

	private IQuoteStrategy strategy;

	public QuoteContext(IQuoteStrategy strategy) {
		this.strategy = strategy;
	}

	//回调具体报价策略的方法
	public Double getPrice(Double originalPrice) {
		return strategy.getPrice(originalPrice);
	}

}
