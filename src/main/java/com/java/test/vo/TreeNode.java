package com.java.test.vo;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	private String id;
	private String pid;
	private String name;
	private Integer level;
	private List<TreeNode> children;
	private String rootId;
	private boolean isLeaf;

	public TreeNode() {
	}

	public TreeNode(String id, String pid, String name) {
		this.id = id;
		this.pid = pid;
		this.name = name;
	}

	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public static List<TreeNode> getData() {
		List<TreeNode> list = new ArrayList<>();
		list.add(new TreeNode("1", "0", "客户组1"));
		list.add(new TreeNode("2", "1", "客户组1_1"));
		list.add(new TreeNode("3", "1", "客户组1_2"));
		list.add(new TreeNode("4", "2", "客户组1_1_1"));
		list.add(new TreeNode("5", "4", "客户组1_1_1_1"));
		list.add(new TreeNode("6", "0", "客户组2"));
		return list;
	}

}
