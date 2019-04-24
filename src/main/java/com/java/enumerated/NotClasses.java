package com.java.enumerated;


import static com.java.util.Print.print;

enum LikeClasses {

	WINKEN {
		void behavior() {
			print("Behavior1");
		}
	},
	BLINKEN {
		void behavior() {
			print("Behavior2");
		}
	},
	NOD {
		void behavior() {
			print("Behavior3");
		}
	};

	abstract void behavior();
}

public class NotClasses {

	// void  f1(LikeClasses.WINKEN instance) {	}

	public static void main(String[] args) {

	}

}
