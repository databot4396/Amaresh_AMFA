//$Id$
package impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.zlabs.aics.adaptivemfa.AMFAData;
import com.zlabs.aics.adaptivemfa.AMFAUser;
import com.zlabs.aics.adaptivemfa.DBScanModel;
import com.zlabs.aics.adaptivemfa.factors.SignInMethod;
import com.zlabs.aics.adaptivemfa.factors.UserAgent;
import com.zlabs.aics.adaptivemfa.factors.UserSignInTime;

import access.MainClass;

public class AMFAUserImpl implements AMFAUser{

	@Override
	public List<AMFAData> getUserData(String zuid) {
		if(MainClass.auditmap==null || MainClass.auditmap.isEmpty()) {
			MainClass.initData();
		}
		List<JSONObject> list = MainClass.auditmap.get(zuid);
		List<AMFAData> outputList = new ArrayList<AMFAData>();
		for(JSONObject obj :list) {
			String Zuid = obj.getString("Zuid");
			JSONObject ipinfo = obj.getJSONObject("ipinfo");
			double latitude = Double.parseDouble(ipinfo.getString("LATITUDE"));
			double longitude = Double.parseDouble(ipinfo.getString("LONGITUDE"));
			String providedCountry = ipinfo.getString("COUNTRY_NAME");
			String providedDevice = obj.getString("UADeviceName");
			String country = ipinfo.getString("COUNTRY_CODE");
			String city = ipinfo.getString("CITY");
			String region = ipinfo.getString("REGION");
			String state = region;
			String place = ipinfo.getString("COUNTRY_CODE");
			String signInMethod = SignInMethod.PRIMARY_EMAIL_SIGNIN.getSignInMethod();
			String signInTime =  new UserSignInTime(obj.getLong("AuditedTime")).getEncoded(); 
			UserAgent ua = new UserAgent(obj.getString("UserAgent"));
			AMFAData data = new AMFAData(Zuid, latitude, longitude, providedCountry, providedDevice, country, state, city, region, place, signInMethod, signInTime, ua.getBrowser(), ua.getOs(), ua.getBrowserVersion(), ua.getDevice(), obj.getLong("AuditedTime"));
			outputList.add(data);
		}
		return outputList;
	}

	@Override
	public DBScanModel loadModel(String zuid) {
		return null;
	}

	@Override
	public void saveModel(String zuid, DBScanModel arg1) {
		
	}

	@Override
	public void saveUserData(String zuid, AMFAData data) {
		
	}

}
