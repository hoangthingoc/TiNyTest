package pageCommon;

import java.util.Random;

public class Constant {

	public final class Commons {
		public static final String URL = "https://staging.tinyserver.info/engage/signup";
		public static final String OBJECT_NOT_PRESENT = "The object is not exist";
		public static final String OBJECT_PRESENT = "The object is exist in this page";
		public static final String CHECKING_DATA_FILE = "Please recheck data file in 'src/data folder'.";
		
		public static final boolean DTRUE = true;
		public static final boolean DFALSE = false;
	}


	public final class Accounts {
		public static final String ID_LOGIN = "ngocht.hcmus@gmail.com";
		public static final String PASS_LOGIN = "Ngoc@000";
	}

//	public final class InputDatas {
//		public static final String ASSIGN_SCHEDULE_FOR_MANAGER = "Preprod, Manager";
//		public static final String ASSIGN_SCHEDULE_FOR_STAFF = "Preprod, Staff";
//	}

	public final class TestSuiteUrl {
		public static final String LOGIN = "Login.xlsx"; //File name of checklist or Testsuite

	}

	public final class KeysValueData {
		public static final String VALUE_DEFAULT_KEY = "default";
		public static final String URL_KEY = "url";
		public static final String USERNAME_KEY = "userName";
		public static final String PASSWORD_KEY = "password";
		
		public static final String FIRSTNAME1_KEY = "firstName1";
		public static final String FIRSTNAME2_KEY = "firstName2";
		public static final String FIRSTNAME3_KEY = "firstName3";
		
		public static final String LASTNAME1_KEY = "lastName1";
		public static final String LASTNAME2_KEY = "lastName2";
		public static final String LASTNAME3_KEY = "lastName3";
		
		public static final String EMAIL1_KEY = "email1";
		public static final String EMAIL2_KEY = "email2";
		public static final String EMAIL3_KEY = "email3";
		
		public static final String MESSAGE1_KEY = "message1";
	}
	
	public static int generatingRandomString() { 
		Random randomGenerator = new Random();  
		return randomGenerator.nextInt(1000);  
	}

	public final class Mail {
//		public static final String MAIL_ACCOUNT = "thingoc@gmail.com";
//		public static final String MAIL_FORWARD_ACCOUNT = "thingoc@gmail.com";
//		public static final String MAIL_PASSWORD = "ngoc@123";
//		public static final String MAIL_URL = "https://mail.google.com";
	}
}

