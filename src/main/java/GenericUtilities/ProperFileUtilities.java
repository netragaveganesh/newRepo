package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ProperFileUtilities {

	/**
	 * This method is used to fetch the data from property file user need to enter the input as key 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String fetchDataFromPropFile(String key) throws IOException {
		FileInputStream fis= new FileInputStream("./src/test/resources/VtigerCommonData.properties");
		
		Properties p= new Properties();
		p.load(fis);
		
		String data=p.getProperty(key);
		return data;
	}
	
	/**
	 * This method is used to add/edit/update the property file, 
	 * we have to give input as key,value, and a message text
	 * @param key
	 * @param value
	 * @param messege
	 * @throws IOException
	 */
	public void writeBackDataToPropFile(String key, String value, String messege) throws IOException {
		FileInputStream fis= new FileInputStream("./src/test/resources/VtigerCommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		p.put(key, value);
		
		FileOutputStream fos= new FileOutputStream("./src/test/resources/VtigerCommonData.properties");
		p.store(fos, messege);
	}
}
