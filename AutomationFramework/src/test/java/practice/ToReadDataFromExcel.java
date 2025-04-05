package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\DDT.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		//Call methods
		String lastname = wb.getSheet("Contacts").getRow(1).getCell(2).toString();
		System.out.println(lastname);
	}
}
