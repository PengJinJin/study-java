package com.design_pattern.interpreter;

public class InterpreterPatternDemo {

	public static void main(String[] args) {
		Expression isMale = getMaleExpression();

		Expression isMarriedWoman = getMarriedWomanExpression();

		System.out.println("John is male? >>>" + isMale.interpreter("John"));
		System.out.println("Julie is a married women? >>>" + isMarriedWoman.interpreter("Married Julie"));
	}

	/**
	 * 规则：Robert 和 John 是男性
	 */
	public static Expression getMaleExpression() {
		Expression robert = new TerminalExpression("Robert");
		Expression john = new TerminalExpression("John");

		return new OrExpression(robert, john);
	}

	/**
	 * 规则：Julie 是一个已婚的女性
	 */
	public static Expression getMarriedWomanExpression() {
		Expression julie = new TerminalExpression("Julie");
		Expression married = new TerminalExpression("Married");

		return new AndExpression(julie, married);
	}

}

interface Expression {
	boolean interpreter(String context);
}

class TerminalExpression implements Expression {
	private String data;

	public TerminalExpression(String data) {
		this.data = data;
	}

	@Override
	public boolean interpreter(String context) {
		return context.contains(data);
	}
}

class OrExpression implements Expression {

	private Expression e1;
	private Expression e2;

	public OrExpression(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public boolean interpreter(String context) {
		return e1.interpreter(context) || e2.interpreter(context);
	}
}

class AndExpression implements Expression {

	private Expression e1;
	private Expression e2;

	public AndExpression(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public boolean interpreter(String context) {
		return e1.interpreter(context) && e2.interpreter(context);
	}
}
