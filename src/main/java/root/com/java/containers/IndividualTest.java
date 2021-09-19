package root.com.java.containers;

import root.com.java.holding.MapOfList;
import root.com.java.typeinfo.pets.Pet;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class IndividualTest {

	public static void main(String[] args) {
		Set<Individual> pets = new TreeSet<>();
		for (List<? extends Pet> lp : MapOfList.petPeople.values()) {
			pets.addAll(lp);
		}
		System.out.println(pets);
	}

}
