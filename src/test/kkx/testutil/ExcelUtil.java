package kkx.testutil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ExcelUtil {
	public static Workbook workbook(String fileName) throws IOException {
		File file = new File(fileName);
		InputStream in = new FileInputStream(file);
		if (fileName.endsWith(".xls")) {
			return new HSSFWorkbook(in);
		} else if (fileName.endsWith(".xlsx")) {
			return new XSSFWorkbook(in);
		} else {
			throw new RuntimeException("只支持.xls和.xlsx格式");
		}
	}

	public static Object[][] readExcel(String fileName, String sheetName) {
		try {
			Workbook workbook = workbook(fileName);
			Sheet sheet = workbook.getSheet(sheetName);
			int rows = sheet.getLastRowNum() - sheet.getFirstRowNum();
			Object[][] result = new Object[rows][];
			for (int i = 1; i <= rows; i++) {
				Row row = sheet.getRow(i);
				int cellsNum = row.getLastCellNum() - row.getFirstCellNum();
				String[] cell = new String[cellsNum - 1];
				for (int j = 0; j < cellsNum; j++) {
					Cell cells = row.getCell(j);
					cell[j] = cells.getStringCellValue();
				}
				result[i] = cell;
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	//public static void main(String[] args) {
	//	Object[][] objects = readExcel();
	//	System.out.println(Arrays.deepToString(objects));
	//}

}
