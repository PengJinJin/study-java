package root.com.java.io;

import root.com.java.util.Print;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import static root.com.java.util.Print.print;

public class RecoverCADState {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("StoreCADState.out"));
		List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>) in.readObject();
		Line.deserializeStaticState(in);
		List<Shape> shapes = (List<Shape>) in.readObject();
		Print.print(shapes);
	}

}
