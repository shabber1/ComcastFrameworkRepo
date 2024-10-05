package practicedatadriven;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteBacktoExcel {

	public static void main(String[] args) throws Throwable {
		FileInputStream fis = new FileInputStream("src/test/resources/testscriptdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet("org");
		Row row=sh.getRow(6);
		Cell cell = row.createCell(4);
		cell.setCellValue("pass");
//		wb.createSheet("Sheet4").createRow(0).createCell(0).setCellValue("dghj");
		FileOutputStream fos=new FileOutputStream("src/test/resources/testscriptdata.xlsx");
		wb.write(fos);
		
		wb.close();
		System.err.println("--------program executed------------");
	}

}
