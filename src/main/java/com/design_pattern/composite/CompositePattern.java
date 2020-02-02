package com.design_pattern.composite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个仿文件系统的demo，
 * 把文件和目录看成是同一类节点Component，
 * 然后用实现类来区分不同，
 * 在实现类中定义各自实现的内容
 */
public class CompositePattern {

	public static void main(String[] args) {
		String path = "/Users/pengjin/git_projects/study-java/src/main/java/com/design_pattern";
		Component c = new Composite(path);
		createTree(c);
		c.display();
	}

	public static void createTree(Component c) {
		File file = new File(c.name);
		File[] f = file.listFiles();
		for (File fi : f) {
			if (fi.isFile()) {
				Leaf leaf = new Leaf(fi.getAbsolutePath());
				c.addNode(leaf);
			} else if (fi.isDirectory()) {
				Composite cs = new Composite(fi.getAbsolutePath());
				c.addNode(cs);
				createTree(cs);
			}
		}
	}

}

// 抽象构件角色（不必区分是文件还是文件夹）
abstract class Component {
	protected String name;

	public Component(String name) {
		this.name = name;
	}

	// 新增节点：文件节点无此方法，目录节点重写此方法
	public void addNode(Component c) {
		throw new RuntimeException("Invalid exception");
	}

	// 文件和目录都需要实现这个方法
	abstract void display();

}

// 树叶构件角色(没有子节点)
class Leaf extends Component {

	public Leaf(String name) {
		super(name);
	}

	@Override
	void display() {
		System.out.println(name);
	}
}

// 树枝构件角色
class Composite extends Component {

	List<Component> list = new ArrayList<>();

	public Composite(String name) {
		super(name);
	}

	@Override
	public void addNode(Component c) {
		list.add(c);
	}

	@Override
	void display() {
		System.out.println(name);
		for (Component c : list) {
			c.display();
		}
	}
}