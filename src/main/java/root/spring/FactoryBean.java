package root.spring;

public interface FactoryBean<T> {

	T getObject() throws Exception;

	Class<T> getObjectType();

	boolean isSingleton();

}
