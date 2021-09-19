package root.com.java.enumerated;


import java.util.EnumMap;

import static root.com.java.enumerated.E09_PostOffice2.MailHandler.*;
import static root.com.java.util.Print.print;

public class E09_PostOffice2 {
	static EnumMap<MailHandler, E09_Command> em = new EnumMap<>(MailHandler.class);

	static {
		em.put(GENERAL_DELIVERY, m -> {
			switch (m.generalDelivery) {
				case YES:
					print("Using general delivery for " + m);
					return true;
				default:
					return false;
			}
		});
		em.put(MACHINE_SCAN, m -> {
			switch (m.scannability) {
				case UNSANNABLE:
					return false;
				default:
					switch (m.address) {
						case INCORRECT:
							return false;
						default:
							print("Delivering " + m + " automatically");
							return true;
					}
			}
		});
		em.put(VISUAL_INSPECTION, m -> {
			switch (m.readability) {
				case ILLEGIBLE:
					return false;
				default:
					switch (m.address) {
						case INCORRECT:
							return false;
						default:
							print("Delivering " + m + " normally");
							return true;
					}
			}
		});
		em.put(RETURN_TO_SENDER, m -> {
			switch (m.returnAddress) {
				case MISSING:
					return false;
				default:
					print("Returning " + m + " to sender");
					return true;
			}
		});
	}

	enum MailHandler {
		GENERAL_DELIVERY, MACHINE_SCAN, VISUAL_INSPECTION, RETURN_TO_SENDER;
	}

	static void handle(Mail m) {
		for (E09_Command cmd : em.values()) {
			if (cmd.handle(m)) {
				return;
			}
			print(m + " is a dead letter.");
		}
	}

	public static void main(String[] args) {
		for (Mail mail : Mail.generator(10)) {
			print(mail.details());
			handle(mail);
			print("*****");
		}
	}
}

interface E09_Command {
	boolean handle(Mail m);
}

