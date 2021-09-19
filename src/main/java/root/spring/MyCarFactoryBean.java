package root.spring;

public class MyCarFactoryBean implements FactoryBean<Car> {
	private String make;
	private int year;

	@Override
	public Car getObject() throws Exception {
		Car.CarBuilder cb = Car.CarBuilder.options().build();
		cb.setYear(year);
		cb.setMake(make);
		return cb.factory();
	}

	@Override
	public Class<Car> getObjectType() {
		return Car.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setYear(int year) {
		this.year = year;
	}
}

