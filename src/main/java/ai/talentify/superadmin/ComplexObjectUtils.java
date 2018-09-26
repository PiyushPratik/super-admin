package ai.talentify.superadmin;

import org.json.JSONObject;

public class ComplexObjectUtils {
	JSONObject complexObject = new JSONObject();
	
	public ComplexObjectUtils(String object) {
		JSONObject complexObject = new JSONObject(object);
		String kamini = complexObject.getString("statusMSG");
		this.complexObject = new JSONObject(kamini);
	
		//System.out.println("complexObject->"+ object.toString());
	}

	public String getName() {
		return  (this.complexObject.getJSONObject("studentProfile")).get("name").toString();
	}
	
	public String getEmail() {
		return  (this.complexObject.getJSONObject("studentProfile")).get("email").toString();
		
	}
}
