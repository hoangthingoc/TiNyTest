package pageCommon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CSVRead {
	private static final char DEFAULT_QUOTE = '"';

	public CSVRead() {
	}

	// HashMap<TCID, HashMap<colName, String>>
	public List<HashMap<String, HashMap<String, String>>> csvReadData(String csvFileName, String id) {
		List<HashMap<String, HashMap<String, String>>> lstMapData = new ArrayList<HashMap<String, HashMap<String, String>>>();
		HashMap<String, HashMap<String, String>> hsMapData = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> data = new HashMap<String, String>();
		String sFilePath = csvFileName;
		File file = new File(sFilePath);
		/*
		 * Scanne scanner = new Scanner(new File(file)); while (scanner.hasNext()) {
		 * List<String> line = parseLine(scanner.nextLine());
		 * System.out.println("Country [id= " + line.get(0) + ", code= " + line.get(1) +
		 * " , name=" + line.get(2) + "]"); } scanner.close();
		 */
		if (!file.exists()) {
			System.out.println("File is not exists by " + csvFileName);
			return lstMapData;
		}
		try {
			BufferedReader bufRdr = new BufferedReader(new FileReader(file));
			try {
				String line = null;
				Boolean blnFirst = false;
//				Boolean blnDefault = false;

				String lstCols[] = null;

				while ((line = bufRdr.readLine()) != null) {
					hsMapData = new HashMap<String, HashMap<String, String>>();
					String fields[] = line.split(",");
					String sKeyTCId = fields[0];
					if (sKeyTCId.substring(0, 1).toCharArray()[0] == DEFAULT_QUOTE) {
						sKeyTCId = sKeyTCId.substring(1, sKeyTCId.length());
					}
					if (sKeyTCId.substring(sKeyTCId.length() - 1, sKeyTCId.length())
							.toCharArray()[0] == DEFAULT_QUOTE) {
						sKeyTCId = sKeyTCId.substring(0, sKeyTCId.length()-1);
					}
					if (!blnFirst) {
						lstCols = fields;
						blnFirst = true;
					} 	
					else {
						data = new HashMap<String, String>();
						for (int i = 1; i < fields.length; i++) {
							String sKeyValue = lstCols[i];
							String sValue = fields[i];
							if (sKeyValue.substring(0, 1).toCharArray()[0] == DEFAULT_QUOTE) {
								sKeyValue = sKeyValue.substring(1, sKeyValue.length());
							}
							if (sKeyValue.substring(sKeyValue.length() - 1, sKeyValue.length())
									.toCharArray()[0] == DEFAULT_QUOTE) {
								sKeyValue = sKeyValue.substring(0, sKeyValue.length()-1);
							}
							if (sValue.substring(0, 1).toCharArray()[0] == DEFAULT_QUOTE) {
								sValue = sValue.substring(1, sValue.length());
							}
							if (sValue.substring(sValue.length() - 1, sValue.length()).toCharArray()[0] == DEFAULT_QUOTE) {
								sValue = sValue.substring(0, sValue.length()-1);
							}

							data.put(sKeyValue, sValue);
						}
//						if(!blnDefault ){
//							sKeyTCId = Constant.KeysValueData.VALUE_DEFAULT_KEY;
//							hsMapData.put(sKeyTCId, data);
//							blnDefault = true;
//						}
//						else {
						if(sKeyTCId.trim().toLowerCase().equals(id.trim().toLowerCase()))
							hsMapData.put(sKeyTCId, data);
						//}
					}
					if(!hsMapData.isEmpty() && sKeyTCId.trim().toLowerCase().equals(id.trim().toLowerCase())) {
						lstMapData.add(hsMapData);
					}
					
				}

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				bufRdr.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lstMapData;
	}
}
