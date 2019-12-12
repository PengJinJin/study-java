package com.design_pattern.proxy.sub2;

import com.design_pattern.proxy.sub1.RealSubject;
import com.design_pattern.proxy.sub1.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试
 */
public class Test {
	public static void main(String[] args) {
		Subject subject = new RealSubject();

		InvocationHandler handler = new DynamicProxy(subject);

		ClassLoader classLoader = subject.getClass().getClassLoader();

		Subject subject1 = (Subject) Proxy.newProxyInstance(classLoader,
						new Class[]{Subject.class}, handler);
		subject1.request();
	}
}
