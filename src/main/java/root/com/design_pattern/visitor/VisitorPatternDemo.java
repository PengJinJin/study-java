package root.com.design_pattern.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * CEO和CTO开始评定员工一年的工作绩效，员工分为工程师和经理，
 * CTO关注工程师的代码量、经理的新产品数量；
 * CEO关注的是工程师的KPI和经理的KPI以及新产品数量。
 * 由于CEO和CTO对于不同员工的关注点是不一样的，这就需要对不同员工类型进行不同的处理。访问者模式此时可以派上用场了。
 */
public class VisitorPatternDemo {
	public static void main(String[] args) {
		BusinessReport br = new BusinessReport();
		System.out.println("==========CEO=========");
		br.showReport(new CEOVisitor());
		System.out.println("==========CTO=========");
		br.showReport(new CTOVisitor());
	}
}

// 抽象元素
abstract class Staff {
	public String name;
	public int kpi;

	public Staff(String name) {
		this.name = name;
		this.kpi = new Random().nextInt(10);
	}

	// 核心方法，接受Visitor的访问
	protected abstract void accept(Visitor visitor);
}

// 具体元素1:工程师
class Engineer extends Staff {
	public Engineer(String name) {
		super(name);
	}

	@Override
	protected void accept(Visitor visitor) {
		visitor.visit(this);
	}

	// 工程师一年的代码数量
	public int getCodeLines() {
		return new Random().nextInt(10 * 10000);
	}
}

// 具体元素2:产品经理
class Manager extends Staff {

	public Manager(String name) {
		super(name);
	}

	@Override
	protected void accept(Visitor visitor) {
		visitor.visit(this);
	}

	// 一年做的产品数量
	public int getProducts() {
		return new Random().nextInt(10);
	}
}

// 抽象访问者
interface Visitor {
	void visit(Engineer e);

	void visit(Manager m);

}

// CEO:具体访问者
class CEOVisitor implements Visitor {
	@Override
	public void visit(Engineer e) {
		System.out.println("Engineer: " + e.name + ", KPI: " + e.kpi);
	}

	@Override
	public void visit(Manager m) {
		System.out.println("Manager: " + m.name + ", KPI: " + m.kpi
						+ ", new products count: " + m.getProducts());
	}
}

// CTO:具体访问者
class CTOVisitor implements Visitor {

	@Override
	public void visit(Engineer e) {
		System.out.println("Engineer: " + e.name + ",Code Lines: " + e.getCodeLines());
	}

	@Override
	public void visit(Manager m) {
		System.out.println("Manager: " + m.name + ", product count:" + m.getProducts());
	}
}


// 对象结构
class BusinessReport {
	private List<Staff> list = new ArrayList<>();

	public BusinessReport() {
		list.add(new Manager("Manager-A"));
		list.add(new Manager("Manager-B"));
		list.add(new Manager("Manager-C"));

		list.add(new Engineer("Engineer-A"));
		list.add(new Engineer("Engineer-B"));

		list.add(new Manager("Manager-D"));
	}

	/**
	 * 为访问者展示报表
	 *
	 * @param visitor 访问者，如CEO、CTO
	 */
	public void showReport(Visitor visitor) {
		for (Staff s : list) {
			s.accept(visitor);
		}
	}
}
