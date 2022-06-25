package root.spring;

public class Person {

	private Car car;

	public void setCar(Car car) {
		this.car = car;
	}
}

class Car {


	private Car(CarBuilder builder) {
	}

	public static class CarBuilder {

		private String make;
		private int year;

		public static CarBuilder options() {
			return new Car.CarBuilder();
		}

		public Car factory() {
			return new Car(this);
		}

		public CarBuilder build() {
			return this;
		}

		public void setMake(String make) {
			this.make = make;
		}

		public void setYear(int year) {
			this.year = year;
		}
	}

}