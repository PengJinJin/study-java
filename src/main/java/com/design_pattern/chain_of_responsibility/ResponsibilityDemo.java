package com.design_pattern.chain_of_responsibility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 现实中，请假的OA申请，请假天数如果是半天到1天，可能直接主管批准即可；
 * 如果是1到3天的假期，需要部门经理批准；
 * 如果是3天到30天，则需要总经理审批；
 * 大于30天，正常不会批准。
 */
public class ResponsibilityDemo {
	public static void main(String[] args) {
		LeaveRequest request = LeaveRequest.builder().leaveDays(31).name("John").build();

		AbstractLeaveHandler handler_1 = new DirectLeaderLeaveHandler("Handler_1");
		AbstractLeaveHandler handler_2 = new DeptManagerLeaveHandler("Handler_2");
		AbstractLeaveHandler handler_3 = new GManagerLeaveHandler("Handler_3");

		handler_1.setNextHandler(handler_2);
		handler_2.setNextHandler(handler_3);
		handler_3.setNextHandler(null);

		handler_1.handlerRequest(request);
	}
}

// 客户类角色
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class LeaveRequest {
	// 请假天数
	private int leaveDays;
	// 姓名
	private String name;
}

// 抽象处理者
abstract class AbstractLeaveHandler {
	/**
	 * 直接主管审批处理的请假天数
	 */
	protected int MIN = 1;
	/**
	 * 部门经理处理的请假天数
	 */
	protected int MIDDLE = 3;
	/**
	 * 总经理处理的请假天数
	 */
	protected int MAX = 30;

	/**
	 * 领导名称
	 */
	protected String handlerName;

	/**
	 * 下一个处理节点（即更高级别的领导）
	 */
	protected AbstractLeaveHandler nextHandler;

	public AbstractLeaveHandler(String handlerName) {
		this.handlerName = handlerName;
	}

	public void setNextHandler(AbstractLeaveHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	protected abstract void handlerRequest(LeaveRequest request);

}

// 具体处理角色(直接主管)
class DirectLeaderLeaveHandler extends AbstractLeaveHandler {

	public DirectLeaderLeaveHandler(String handlerName) {
		super(handlerName);
	}

	@Override
	protected void handlerRequest(LeaveRequest request) {
		if (request.getLeaveDays() <= MIN) {
			System.out.println(">>DirectLeaderLeaveHandler: " + handlerName + ". end...");
			return;
		}
		if (null != nextHandler) {
			nextHandler.handlerRequest(request);
		} else {
			System.out.println("You are rejected.");
		}
	}

}

// 具体处理角色(部门主管)
class DeptManagerLeaveHandler extends AbstractLeaveHandler {

	public DeptManagerLeaveHandler(String handlerName) {
		super(handlerName);
	}

	@Override
	protected void handlerRequest(LeaveRequest request) {
		if (request.getLeaveDays() > MIN && request.getLeaveDays() <= MIDDLE) {
			System.out.println(">>DeptManagerLeaveHandler: " + handlerName + ". end...");
			return;
		}
		if (null != nextHandler) {
			nextHandler.handlerRequest(request);
		} else {
			System.out.println("You are rejected.");
		}
	}

}

// 具体处理角色(总经理处理)
class GManagerLeaveHandler extends AbstractLeaveHandler {

	public GManagerLeaveHandler(String handlerName) {
		super(handlerName);
	}

	@Override
	protected void handlerRequest(LeaveRequest request) {
		if (request.getLeaveDays() > MIDDLE && request.getLeaveDays() <= MAX) {
			System.out.println(">>GManagerLeaveHandler: " + handlerName + ". end...");
		} else {
			System.out.println("It's too long, You are rejected.");
		}
	}

}
