package RunnerClass;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Assignment.Change_Password.PasswordVerification;
import ExcelUtils.ExcelUtils;

public class RunnerClass {
	public static String specialCharacters = "!@#$&*";// Special characters list
	public static String newPassword;
	public static String projectPath = System.getProperty("user.dir");//get current project directory
	public static String filePath = projectPath + "\\\\src\\\\main\\\\java\\\\DataSheet\\\\ChangePassword.xlsx";// excel file for testcases

	
	public static boolean mainChangePasswordflag = false, passwordStrengthFlag = false, repeatedCharCheckflag = false,
			passwordRulesflag = false, specialCharflag = false, passwordnumericBalance = false,passwordPercentageMatchflag=false;
	public static int newPasswordLength;
	public static String oldPassword;

	
@BeforeSuite	

public void AssignData() throws Exception {
	ExcelUtils.setExcelFile(filePath);
	int RowNum=ExcelUtils.getRowContains("SC_001", 0, "Scenario");
	oldPassword=ExcelUtils.getCellData(RowNum,2,"Scenario");
	newPassword=ExcelUtils.getCellData(RowNum,3,"Scenario");
	newPasswordLength = newPassword.length();
 
		
}	
	
@Test(description = "Validate Change Password function with two parameters (newPassword & oldPassword) should match with all password validation rules.")

public void SC_001() throws Exception {
try{
	mainChangePasswordflag=PasswordVerification.ChangePassword(oldPassword, newPassword);
	
	
	if(mainChangePasswordflag==true){
		ExcelUtils.setCellData("Pass",1,4,"Scenario",filePath);
	}else{
		ExcelUtils.setCellData("Fail",1,4,"Scenario",filePath);
	}
		
	System.out.println("Change Password Result --"+mainChangePasswordflag);
}catch(Exception e){
	System.out.println("Something went wrong-"+e);
}

}

@AfterSuite
public void close() {
	try{
	Runtime.getRuntime().exec("E:\\Project\\git project\\Change_Password\\src\\main\\java\\DataSheet\\ChangePassword.xlsx");  
	System.out.println("close");
	}catch(Exception e){
		System.out.println("Exception to open excel file-"+filePath+e);
	}
	}
}
