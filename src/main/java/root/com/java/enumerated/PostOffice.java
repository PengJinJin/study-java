package root.com.java.enumerated;

import root.com.java.util.Print;

import java.util.Iterator;

import static root.com.java.util.Print.print;

public class PostOffice {

	enum MailHandler {
		GENERAL_DELIVERY {
			boolean handle(Mail m) {
				switch (m.generalDelivery) {
					case YES:
						Print.print("Using general delivery for " + m);
						return true;
					default:
						return false;
				}
			}
		},
		MACHINE_SCAN {
			boolean handle(Mail m) {
				switch (m.scannability) {
					case UNSANNABLE:
						return false;
					default:
						switch (m.address) {
							case INCORRECT:
								return false;
							default:
								Print.print("Delivering " + m + " automatically");
								return true;
						}
				}
			}
		},
		VISUAL_INSPECTION {
			boolean handle(Mail m) {
				switch (m.readability) {
					case ILLEGIBLE:
						return false;
					default:
						switch (m.address) {
							case INCORRECT:
								return false;
							default:
								Print.print("Delivering " + m + " normally");
								return true;
						}
				}
			}
		},
		RETURN_TO_SENDER {
			boolean handle(Mail m) {
				switch (m.returnAddress) {
					case MISSING:
						return false;
					default:
						Print.print("Returning " + m + " to sender");
						return true;
				}
			}
		};

		abstract boolean handle(Mail m);
	}

	static void handle(Mail m) {
		for (MailHandler handler : MailHandler.values()) {
			if (handler.handle(m)) {
				return;
			}
			Print.print(m + " is a dead letter");
		}
	}

	public static void main(String[] args) {
		for (Mail mail : Mail.generator(10)) {
			Print.print(mail.details());
			handle(mail);
			Print.print("*****");
		}
	}

}

class Mail {
	enum GeneralDelivery {YES, NO1, NO2, NO3, NO4, NO5}

	enum Scannability {UNSANNABLE, YES1, YES2, YES3, YES4}

	enum Readability {ILLEGIBLE, YES1, YES2, YES3, YES4}

	enum Address {INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6}

	enum ReturnAddress {MISSING, OK1, OK2, OK3, OK4, OK5}

	GeneralDelivery generalDelivery;
	Scannability scannability;
	Readability readability;
	Address address;
	ReturnAddress returnAddress;

	static int counter = 0;

	long id = counter++;

	@Override
	public String toString() {
		return "Mail " + id;
	}

	public String details() {
		return toString() +
				", General Delivery: " + generalDelivery +
				", Address Scanability: " + scannability +
				", Address Readability: " + readability +
				", Address Address: " + address +
				", Return address: " + returnAddress;
	}

	// Generate test Mail
	public static Mail randomMail() {
		Mail m = new Mail();
		m.generalDelivery = Enums.random(GeneralDelivery.values());
		m.scannability = Enums.random(Scannability.values());
		m.readability = Enums.random(Readability.values());
		m.address = Enums.random(Address.values());
		m.returnAddress = Enums.random(ReturnAddress.values());
		return m;
	}

	public static Iterable<Mail> generator(final int count) {
		return new Iterable<Mail>() {
			int n = count;

			@Override
			public Iterator<Mail> iterator() {
				return new Iterator<Mail>() {
					@Override
					public boolean hasNext() {
						return n-- > 0;
					}

					@Override
					public Mail next() {
						return randomMail();
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}

}
