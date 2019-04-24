package com.java.enumerated;

import java.util.EnumSet;

import static com.java.enumerated.AlarmPoints.*;
import static com.java.util.Print.print;

public class EnumSets {

	public static void main(String[] args) {
		EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
		points.add(BATHROOM);
		print(points);
		points.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
		print(points);
		points = EnumSet.allOf(AlarmPoints.class);
		points.removeAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
		print(points);
		points.removeAll(EnumSet.range(OFFICE1, OFFICE4));
		print(points);
		points = EnumSet.complementOf(points);
		print(points);
	}

}
