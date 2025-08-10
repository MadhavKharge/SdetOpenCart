package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//Data provider 1
	@DataProvider(name="loginData")
	public String[][] getData() throws IOException {

		String path = "./testData/loginData (7).xlsx";

		ExcelUtility xlutile = new ExcelUtility(path); //taking xl file from testData

		int totalrows = xlutile.getRowCount("Sheet1");//creating object for xlUtility
		int totalcols = xlutile.getCellCount("Sheet1", 1);
		
		String[][] logindata = new String[totalrows][totalcols];//created for two dimensional array which can stored data

		for(int i=1; i<=totalrows;i++) {//1  //read the data from excel sheet storing in 2d array
			
			for(int j=0;j<totalcols;j++) { //0
				
				logindata[i-1][j]=xlutile.getCellData("Sheet1", i, j);//1,0
			}
		}
		
		return logindata; //returning 2D array
	
	}
	
	//Data provider 2
	
	//Data provider 3
	
	//Data provider 4
	

}
