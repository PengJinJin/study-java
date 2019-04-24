package com.java.test2;

import com.alibaba.fastjson.JSONArray;
import com.java.test.vo.TreeNode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class IfElseTest {

	public static void main(String[] args) {
		// test2(9);
		// test3(TreeNode.getData());
		List<TreeNode> buildListToTree = buildListToTree(TreeNode.getData());
		System.out.println(JSONArray.toJSONString(buildListToTree));
	}

	/*
	 * public static void test3(List<TreeNode> list) { if (list.size() == 1) {
	 * list.get(0).setChildrenNode(null); } else { test2(list.size() - 1); for
	 * (int i = 0; i < list.size(); i++) { TreeNode treeNode = list.get(i); if
	 * (treeNode.getLevel()) {
	 * 
	 * } } } }
	 */

	private static List<TreeNode> findChildren(TreeNode root, List<TreeNode> allTreeNodes) {
		List<TreeNode> children = new ArrayList<TreeNode>();

		for (TreeNode comparedOne : allTreeNodes) {
			if (comparedOne.getPid().equals(root.getId())) {
				// comparedOne.setParent(root);
				comparedOne.setLevel(root.getLevel() + 1);
				children.add(comparedOne);
			}
		}
		List<TreeNode> notChildren = (List<TreeNode>) CollectionUtils.subtract(allTreeNodes, children);
		for (TreeNode child : children) {
			List<TreeNode> tmpChildren = findChildren(child, notChildren);
			if (tmpChildren == null || tmpChildren.size() < 1) {
				child.setLeaf(true);
			} else {
				child.setLeaf(false);
			}
			child.setChildren(tmpChildren);
		}
		return children;
	}

	public static List<TreeNode> buildListToTree(List<TreeNode> dirs) {
		List<TreeNode> roots = findRoots(dirs);
		List<TreeNode> notRoots = (List<TreeNode>) CollectionUtils.subtract(dirs, roots);
		for (TreeNode root : roots) {
			root.setChildren(findChildren(root, notRoots));
		}
		return roots;
	}

	public static List<TreeNode> findRoots(List<TreeNode> allTreeNodes) {
		List<TreeNode> results = new ArrayList<TreeNode>();
		for (TreeNode node : allTreeNodes) {
			boolean isRoot = true;
			for (TreeNode comparedOne : allTreeNodes) {
				if (node.getPid().equals(comparedOne.getId())) {
					isRoot = false;
					break;
				}
			}
			if (isRoot) {
				node.setLevel(0);
				results.add(node);
				node.setRootId(node.getId());
			}
		}
		return results;
	}

	public static void test2(int i) {
		if (i == 1) {
			System.out.println("1*1=1 ");
		} else {
			test2(i - 1);
			for (int j = 1; j <= i; j++) {
				System.out.print(j + "*" + i + "=" + (j * i) + "\t");
			}
			System.out.println();
		}
	}

	public static void test1() {
		boolean examIsDone = true;
		int score = 65;
		if (examIsDone)
			if (score >= 50)
				System.out.println("A ,Excellent");
			else if (score >= 80)
				System.out.println("B ,Good");
			else if (score >= 70)
				System.out.println("C ,Middle");
			else if (score >= 60)
				System.out.println("D ,Pass");
			else
				System.out.println("E ,Fail");
		System.out.println("Done is Done");
	}

}
