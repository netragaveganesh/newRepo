package GenericUtilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileUtility {

	/**
	 * using this method we can fetch the data from json file of current project
	 * @param key
	 * @return String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public String fetchDataFromJSONTFileest(String key) throws FileNotFoundException, IOException, ParseException {
		JSONParser parse= new JSONParser();
		Object obj=parse.parse(new FileReader("./src/test/resources/data.json"));
		JSONObject jo=(JSONObject)obj;		
		String data=jo.get(key).toString();
		return data;
	}
}
