package trackshipment;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	public static String[][] readData() throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook("./exceldata/waybillno.xlsx");
		XSSFSheet sheetAt = wb.getSheetAt(0);
		int rowNum = sheetAt.getLastRowNum();
		int cellNum = sheetAt.getRow(0).getLastCellNum();
		String[][] data = new String[rowNum][cellNum];
		for (int i = 1; i <= rowNum; i++) {
			XSSFRow row = sheetAt.getRow(i);
			for (int j = 0; j < cellNum; j++) {
				String cellData = row.getCell(j).getStringCellValue();
				System.out.println(cellData);
				data[i - 1][j] = cellData;
			}
		}
		wb.close();
		return data;
	}
}
