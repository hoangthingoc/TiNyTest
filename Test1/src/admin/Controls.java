package admin;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

//import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class Controls {
	
	public static final Grid[] gridConfig;
	public static final User[] users;
	public static final App appConfig;

	final static String FILE_NAME = System.getProperty("user.dir") + "\\src\\Admin\\Configs.csv";
//
	// load file from resource
	// static File file = new
	// File(Controls.class.getResource("Configs.csv").getFile());

	static File file = new File(FILE_NAME);

	static {
		// read from file
		try (Reader reader = new FileReader(file)) {
			CsvMapper mapper = new CsvMapper();
			CsvSchema schema = CsvSchema.emptySchema().withHeader(); // use first row as header; otherwise defaults are
																		// fine
			MappingIterator<Configs> mi = mapper.readerFor(Configs.class).with(schema).readValues(file);

			if (mi.hasNext()) {
				// System.out.println(mi.next());
				Configs configs = mi.next();

				gridConfig = new Grid[] { new Grid() {
					{
						useGrid = Boolean.parseBoolean(configs.useGrid);
						address = configs.gridAddress;
					}
				} };

				appConfig = new App() {
					{
						appBaseUrl = configs.appBaseUrl;
					}
				};

			

				users = new User[] { new User() {
					{
						username = configs.appUsername;
						password = configs.appPassword;
//						usernameStaff = configs.appUserNameStaff;
//						passwordStaff = configs.appPasswordStaff;
					
					}
				} };

			} else {				
				gridConfig = new Grid[] { new Grid() {
					{
						useGrid = false;
						address = "http://dev.lampart.com.vn/_staff:4444/wd/hub";
					}
				} };

				appConfig = new App() {
					{
						appBaseUrl = "http://dev.lampart.com.vn/_staff";
					}
				};

				

				users = new User[] { new User() {
					{
						username = "0981601789";
						password = "abc@123";
//						usernameStaff = "wakkainc";
//						passwordStaff = "wakkainc";
					}
				} };

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}