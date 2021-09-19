package root.com.java.enumerated;


import root.com.java.util.Print;

import static root.com.java.util.Print.print;

enum LikeClasses {

	WINKEN {
		void behavior() {
			Print.print("Behavior1");
		}
	},
	BLINKEN {
		void behavior() {
			Print.print("Behavior2");
		}
	},
	NOD {
		void behavior() {
			Print.print("Behavior3");
		}
	};

	abstract void behavior();
}

public class NotClasses {

	// void  f1(LikeClasses.WINKEN instance) {	}

	public static void main(String[] args) {

	}

}
