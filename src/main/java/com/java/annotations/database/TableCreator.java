package com.java.annotations.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {

	public static void main(String[] args) throws ClassNotFoundException {
		if (args.length < 1) {
			System.out.println("arguments: annotated classes");
			System.exit(0);
		}
		for (String className : args) {
			// 类名获取class
			Class<?> cl = Class.forName(className);
			DBTable dbTable = cl.getAnnotation(DBTable.class);
			if (dbTable == null) {
				System.out.println("No DBTable annotations in class " + className);
				continue;
			}
			String tableName = dbTable.name();
			// if the name is empty, use the class name
			if (tableName.length() < 1) {
				tableName = cl.getName().toUpperCase();
			}
			List<String> columnDefs = new ArrayList<>();
			// 获取类的字段
			for (Field field : cl.getDeclaredFields()) {
				String columnName;
				Annotation[] anns = field.getDeclaredAnnotations();
				if (anns.length < 1) {
					// No a db table column
					continue;
				}
				if (anns[0] instanceof SQLInteger) {
					SQLInteger sInt = (SQLInteger) anns[0];
					// use field name if name not specified
					if (sInt.name().length() < 1) {
						columnName = field.getName().toUpperCase();
					} else {
						columnName = sInt.name();
					}
					columnDefs.add(columnName + " INT" + getConstrains(sInt.constraints()));
				}
				if (anns[0] instanceof SQLString) {
					SQLString sString = (SQLString) anns[0];
					if (sString.name().length() < 1) {
						columnName = field.getName().toUpperCase();
					} else {
						columnName = sString.name();
					}
					columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstrains(sString.constraints()));
				}
				StringBuilder builder = new StringBuilder("CREATE TABLE " + tableName + "(");
				builder.append("\n	").append(String.join(",\n\t", columnDefs)).append("\n)");
				// Remove trailing comma
				String tableCreate = builder.substring(0, builder.length() - 1) + ");";
				System.out.println("Table Creation SQL for " + className + " is: \n" + tableCreate);
			}
		}
	}

	private static String getConstrains(Constraints con) {
		String constraints = "";
		if (!con.allowNull()) {
			constraints += " NOT NULL";
		}
		if (con.primaryKey()) {
			constraints += " PRIMARY KEY";
		}
		if (con.unique()) {
			constraints += " UNIQUE";
		}
		return constraints;
	}

}
