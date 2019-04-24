package com.java.enumerated;

import java.util.EnumSet;

import static com.java.util.Print.print;

/**
 * 此种方法名为: 定义常量相关的方法<br/>
 * 这种方式会比使用匿名内部类更加简洁, 高效<br/>
 * {@link EnumMaps}
 */
public class CarWash {

	public enum Cycle {
		UNDERBODY {
			void action() {
				print("Spraying the underbody");
			}
		},
		WHEELWASH {
			void action() {
				print("Washing the wheels");
			}
		},
		PREWASH {
			void action() {
				print("Loosening the dirt");
			}
		},
		BASIC {
			void action() {
				print("The basic wash");
			}
		},
		HOTWAX {
			void action() {
				print("Applying hot wax");
			}
		},
		RINSE {
			void action() {
				print("Rinsing");
			}
		},
		BLOWDRY {
			void action() {
				print("Blowing dry");
			}
		};

		abstract void action();

	}

	EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);

	public void add(Cycle cycle) {
		cycles.add(cycle);
	}

	public void washCar() {
		for (Cycle v : cycles) {
			v.action();
		}
	}

	@Override
	public String toString() {
		return cycles.toString();
	}

	public static void main(String[] args) {
		CarWash wash = new CarWash();
		print(wash);
		wash.washCar();
		// Order of addition is unimportant
		wash.add(Cycle.BLOWDRY);
		wash.add(Cycle.BLOWDRY);// Duplicates ignored
		wash.add(Cycle.RINSE);
		wash.add(Cycle.HOTWAX);
		print(wash);
		wash.washCar();
	}

}
