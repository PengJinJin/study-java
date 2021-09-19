package root.com.design_pattern.proxy.sub2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 事件管理器
 */
public class DynamicProxy implements InvocationHandler {

	private Object object;

	public DynamicProxy(Object object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		preRequest();
		method.invoke(object, args);
		postRequest();
		return null;
	}

	public void preRequest() {
		System.out.println("preRequest");
	}

	public void postRequest() {
		System.out.println("postRequest");
	}

}
