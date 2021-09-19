package root.com.java.enumerated;

import root.com.java.util.Print;

import java.util.EnumSet;

import static root.com.java.util.Print.print;

public class EnumSets {

	public static void main(String[] args) {
		EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
		points.add(AlarmPoints.BATHROOM);
		Print.print(points);
		points.addAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));
		Print.print(points);
		points = EnumSet.allOf(AlarmPoints.class);
		points.removeAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));
		Print.print(points);
		points.removeAll(EnumSet.range(AlarmPoints.OFFICE1, AlarmPoints.OFFICE4));
		Print.print(points);
		points = EnumSet.complementOf(points);
		Print.print(points);
	}

}
