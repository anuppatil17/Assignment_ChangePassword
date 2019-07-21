package Assignment.Change_Password;

import java.util.ArrayList;
import java.util.List;



import ExcelUtils.ExcelUtils;
import RunnerClass.RunnerClass;

public class PasswordVerification {

	public static boolean ChangePassword(String oldPassword, String newPassword) throws Exception {
		System.out.println("welcome");
		try {
			RunnerClass.passwordStrengthFlag = passwordPercentageMatch();
			if (RunnerClass.passwordPercentageMatchflag = true) {

				System.out.println("New Password String should < 80 % of Old Password String");
				ExcelUtils.setCellData("Pass", 1, 2, "TestCases", RunnerClass.filePath);

			} else {
				System.out.println("New Password > 80% of Old Password character");
				ExcelUtils.setCellData("Fail", 1, 2, "TestCases", RunnerClass.filePath);
			}

			if (oldPassword.contains(RunnerClass.oldPassword)) {

				System.out.println("old password matching");

				RunnerClass.passwordStrengthFlag = passwordStrength();// 18
																		// Alpha
																		// numeric
																		// character
				RunnerClass.specialCharflag = specialCharCheck();// list of
																	// special
																	// chars
																	// !@#$&*

				if (RunnerClass.passwordStrengthFlag == true) {

					System.out.println("At least 18 alphanumeric characters");

					ExcelUtils.setCellData("Pass", 2, 2, "TestCases", RunnerClass.filePath);
				} else {
					ExcelUtils.setCellData("Fail", 2, 2, "TestCases", RunnerClass.filePath);
					System.out.println("***********************************");

				}

				RunnerClass.passwordRulesflag = passwordRules();
				RunnerClass.specialCharflag = specialCharCheck();
				if (RunnerClass.passwordRulesflag == true && RunnerClass.specialCharflag == true) {
					System.out.println("At least 1 Upper case, 1 lower case ,least 1 numeric, 1 special character");

					ExcelUtils.setCellData("Pass", 3, 2, "TestCases", RunnerClass.filePath);
				} else {
					ExcelUtils.setCellData("Fail", 3, 2, "TestCases", RunnerClass.filePath);
					System.out.println("***********************************");

				}

				RunnerClass.repeatedCharCheckflag = repeatedCharCheck();
				if (RunnerClass.repeatedCharCheckflag == true) {
					System.out.println("Password should not contains duplicate repeat characters more than 4");

					ExcelUtils.setCellData("Pass", 4, 2, "TestCases", RunnerClass.filePath);
				} else {
					ExcelUtils.setCellData("Fail", 4, 2, "TestCases", RunnerClass.filePath);
					System.out.println("***********************************");

				}

				RunnerClass.specialCharflag = specialCharCheck();
				if (RunnerClass.specialCharflag == true) {

					System.out.println(
							"New Password should contains list of special chars !@#$&* and not contains more than 4 special characters");
					ExcelUtils.setCellData("Pass", 5, 2, "TestCases", RunnerClass.filePath);

				} else {
					ExcelUtils.setCellData("Fail", 5, 2, "TestCases", RunnerClass.filePath);
					System.out.println("***********************************");

				}

				RunnerClass.passwordnumericBalance = numericbalanceChar();
				if (RunnerClass.passwordnumericBalance == true) {
					ExcelUtils.setCellData("Pass", 6, 2, "TestCases", RunnerClass.filePath);
					System.out.println("50 % of password should not be a number");

				} else {
					ExcelUtils.setCellData("Fail", 6, 2, "TestCases", RunnerClass.filePath);
					System.out.println("***********************************");

				}

			}

			if (RunnerClass.repeatedCharCheckflag == true && RunnerClass.specialCharflag == true
					&& RunnerClass.passwordnumericBalance == true && RunnerClass.passwordPercentageMatchflag == true
					&& RunnerClass.passwordStrengthFlag == true && RunnerClass.specialCharflag == true
					&& RunnerClass.passwordRulesflag == true && RunnerClass.specialCharflag == true) {

				RunnerClass.mainChangePasswordflag = true;

			} else {
				RunnerClass.mainChangePasswordflag = false;
			}
		} catch (Exception e) {
			System.out.println("Something went wrong-" + e); // TODO: handle
																// exception
		}

		return RunnerClass.mainChangePasswordflag;
	}

	public static boolean passwordStrength() {

		///////////////////////// 18 Alpha numeric character //////////////////
		try {
			int newPasswordLength = RunnerClass.newPassword.length();

			if (newPasswordLength > 18) {
				System.out.println("valid length password");
				RunnerClass.passwordStrengthFlag = true;
			} else {
				System.out.println("length Failed");
				RunnerClass.passwordStrengthFlag = false;
			}
		} catch (Exception e) {
			System.out.println("Exception in password strength");// TODO: handle
																	// exception
		}
		return RunnerClass.passwordStrengthFlag;
	}

	public static boolean repeatedCharCheck() {
		List<String> list = new ArrayList<String>();
		////////////////// Repeated 4 Character not allowed//////////////
		try {
			int RepeactedCharCount = 1;
			int newPasswordLength = RunnerClass.newPassword.length();

			for (int i = 0; i < newPasswordLength; i++) {
				for (int j = i + 1; j < newPasswordLength; j++) {

					if (RunnerClass.newPassword.charAt(i) == RunnerClass.newPassword.charAt(j)) {

						list.add(Character.toString(RunnerClass.newPassword.charAt(i)));

						RepeactedCharCount++;

						System.out.println(RunnerClass.newPassword.charAt(i) + "" + RunnerClass.newPassword.charAt(j)
								+ "" + RepeactedCharCount);

						if (RepeactedCharCount >= 5) {

							System.out.println("more than Four repeated char --" + RepeactedCharCount);

						}
					}

				}

				break;
			}

			if (RepeactedCharCount >= 5) {

				System.out.println("fail repeated char" + list.size() + "" + list.toString());
				RunnerClass.repeatedCharCheckflag = false;

			} else {
				System.out.println("pass repeated char" + list.size() + "" + list.toString());
				RunnerClass.repeatedCharCheckflag = true;
			}
		} catch (Exception e) {
			System.out.println("Exception in repeatedCharCheck-" + e);// TODO:
																		// handle
																		// exception
		}

		return RunnerClass.repeatedCharCheckflag;

	}

	public static boolean specialCharCheck() {
		///////////////////////// 1 Special Char////////////////////////
		try {
			int SpecialCharCount = 0;
			for (int i = 0; i < RunnerClass.newPassword.length(); i++) {

				if (RunnerClass.specialCharacters.contains(Character.toString(RunnerClass.newPassword.charAt(i)))) {
					System.out.println("Special Character pass" + RunnerClass.newPassword.charAt(i));
					RunnerClass.specialCharflag = true;
					SpecialCharCount++;
					System.out.println("count for special--" + SpecialCharCount);

					if (SpecialCharCount < 5) {

						System.out.println("Special Char Present" + RunnerClass.specialCharacters);
						RunnerClass.specialCharflag = true;
					} else {
						System.out
								.println("Fail due to more than 4 special character in password--" + SpecialCharCount);
						RunnerClass.specialCharflag = false;
						// Fail

					}
				}

			}
		} catch (Exception e) {
			System.out.println("Exception in specialCharCheck-" + e);// TODO:
																		// handle
																		// exception
		}
		return RunnerClass.specialCharflag;
	}

	public static boolean numericbalanceChar() {
		try {
			int passwordSize = RunnerClass.newPassword.length();
			int count = 0;
			int passwordReminder = 0;

			int newlength = RunnerClass.newPassword.length();

			double percentage = 50;

			float passwordperSize = (float) ((percentage / 100) * passwordSize);
			int newpasswordSizeRound = Math.round(passwordperSize);
			System.out.println("----" + newpasswordSizeRound);
			for (int i = 0; i < passwordSize; i++) {

				passwordReminder = passwordSize / 2;

				char ch1 = RunnerClass.newPassword.charAt(i);
				if (ch1 >= '0' && ch1 <= '9') {
					count++;

				}

			}
			if (newpasswordSizeRound <= count) {
				System.out.println("Password contains more Numbers 50% than characters...." + count);
				RunnerClass.passwordnumericBalance = false;
			} else {
				RunnerClass.passwordnumericBalance = true;
				System.out.println("Password contains less than 50 % numeric characters");
			}
		} catch (Exception e) {
			System.out.println("Exception in balanceChar ");// TODO: handle
															// exception
		}
		return RunnerClass.passwordnumericBalance;
	}

	public static boolean passwordRules() {
		try {
			int lowercaseCount = 0, uppercaseCount = 0, numericCount = 0;

			for (int i = 0; i < RunnerClass.newPasswordLength; i++) {

				char stringChar = RunnerClass.newPassword.charAt(i);

				////////////////////// lower case
				////////////////////// ////////////////////////////////
				if (stringChar >= 'a' && stringChar <= 'z') {

					lowercaseCount++;

				}

				/////////////////////// Upper case
				/////////////////////// ///////////////////////////////
				if (stringChar >= 'A' && stringChar <= 'Z') {

					uppercaseCount++;

				}
				//////////////////////// 1 Numeric //////////////////////////
				if (stringChar >= '0' && stringChar <= '9') {

					numericCount++;

				}
			}

			if (lowercaseCount >= 1 && uppercaseCount >= 1 && numericCount >= 1) {
				System.out.println("passwordRules Passed");
				RunnerClass.passwordRulesflag = true;
			} else {
				RunnerClass.passwordRulesflag = false;
				System.out.println("passwordRules fail");
			}
		} catch (Exception e) {
			System.out.println("Exception in passwordRules " + e);// TODO:
																	// handle
																	// exception
		}
		return RunnerClass.passwordRulesflag;
	}

	public static boolean passwordPercentageMatch() {

		int oldlength = RunnerClass.oldPassword.length();
		int newlength = RunnerClass.newPassword.length();
		System.out.println(oldlength + "" + newlength);
		double percentage = 80;

		float oldperSize = (float) ((percentage / 100) * oldlength);
		int oldperSizeRound = Math.round(oldperSize);
		// OldPassword Percentage calculation

		float newperSize = (float) ((percentage / 100) * newlength);
		int newperSizeRound = Math.round(newperSize);
		// NewPassword Percentage calculation

		System.out.println(oldlength + "" + newlength);

		StringBuffer oldPasswordsb = new StringBuffer(RunnerClass.oldPassword);
		System.out.println(oldPasswordsb.substring(0, oldperSizeRound));

		StringBuffer newPasswordsb = new StringBuffer(RunnerClass.newPassword);

		System.out.println(newPasswordsb.substring(0, newperSizeRound));

		if (oldPasswordsb.substring(0, oldperSizeRound).equals(newPasswordsb.substring(0, newperSizeRound))) {

			System.out.println("80 % string matching with old password");
			RunnerClass.passwordPercentageMatchflag = false;

		} else {

			RunnerClass.passwordPercentageMatchflag = true;
			System.out.println("Pass - newPassword < 80% with oldPassword matching ");

		}
		return RunnerClass.passwordPercentageMatchflag;

	}

}
