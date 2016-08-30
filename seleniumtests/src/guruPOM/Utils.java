package guruPOM;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utils {
	//opening an excel file
	String Filepath = "C:\\Users\\Samatha\\Workspace\\Logindata.xlsx";
	XSSFWorkbook workbook = null;
	XSSFSheet worksheet = null;
	
	public XSSFSheet Utils() throws IOException{
		workbook = new XSSFWorkbook(Filepath);
		worksheet = workbook.getSheetAt(0);
		return worksheet;
	}
	
	
}
