/**
 * 
 */
package superadmin;

import org.json.JSONObject;
import org.json.JSONTokener;

import ai.talentify.superadmin.ComplexObjectUtils;

/**
 * @author Vaibhav
 *
 */
public class VVTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String json = "{\"statusMSG\":\"{\\\"studentProfile\\\":{\\\"userRoles\\\":[\\\"SALES_MANAGER\\\"],\\\"name\\\":\\\"Anurag Gupta\\\",\\\"mobile\\\":5896745823,\\\"org_details\\\":[{\\\"id\\\":20,\\\"org_name\\\":\\\"Independence Day\\\"}],\\\"userType\\\":\\\"SALES_MANAGER\\\",\\\"id\\\":198126,\\\"profileImage\\\":\\\"https://business.talentify.in//users/198126/e1d9555f-53fc-4ae8-b2c9-a2c8fba9b6db.jpg\\\",\\\"email\\\":\\\"anurag_manager@istarindia.com\\\"},\\\"notifications\\\":[]}\",\"statusCode\":200}";
		JSONObject complexObject = new JSONObject(json);
		
		System.out.println(new JSONObject(complexObject.getString("statusMSG")).getJSONObject("studentProfile").getString("name"));
		ComplexObjectUtils utils = new ComplexObjectUtils(json);
		System.out.println(utils.getName());

	}

}
