package com.java.containers;

import com.java.util.CollectionData;
import com.java.util.RandomGenerator;

import java.util.ArrayList;
import java.util.HashSet;

public class CollectionDataGeneration {

	public static void main(String[] args) {
		System.out.println(new ArrayList<>(
				CollectionData.list(
						new RandomGenerator.String(9), 10
				)
		));

		System.out.println(new HashSet<>(
				new CollectionData<>(
						new RandomGenerator.Integer(), 10
				)
		));
	}

}
