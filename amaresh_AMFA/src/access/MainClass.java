//$Id$
package access;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zlabs.aics.adaptivemfa.AMFAData;
import com.zlabs.aics.adaptivemfa.AMFATests;
import com.zlabs.aics.adaptivemfa.AMFAUser;
import com.zlabs.aics.adaptivemfa.AdaptiveMFAScorer;
import com.zlabs.aics.adaptivemfa.Scorer;
import com.zlabs.aics.adaptivemfa.factors.LocationFinder;
import com.zlabs.aics.adaptivemfa.factors.SignInMethod;
import impl.AMFAUserImpl;
import impl.ScorerImpl;

public class MainClass {
	public static Map<String, List<JSONObject>> auditmap = new HashMap<String, List<JSONObject>>();

	public static void main(String[] args) {
		initData();
		LocationFinder.init("/Users/amar-5055/git/amfa/resources/out.csv");
		AMFAUser user = new AMFAUserImpl();
		//UserAgent ua = new UserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");
		//AMFAData currentAudit = new AMFAData("639399239", 12.85, 80.06, "INDIA", "Mac", "INDIA", "TAMIL NADU", "KANCHIPURAM", "KANCHIPURAM", "", "SIGN_IN", "1640760841518", ua.getBrowser(), ua.getOs(), ua.getBrowserVersion(), ua.getDevice(), 1640760841518l);
		AMFAData currentAudit = new AMFAData("639399239", "Mac", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36",
				12.85, 80.06, "INDIA", 1640760841518l, SignInMethod.PRIMARY_EMAIL_SIGNIN.getSignInMethod());
		Scorer scorer = new ScorerImpl();
		AdaptiveMFAScorer a = new AdaptiveMFAScorer(user, currentAudit, scorer);
  		Map<AMFATests, Integer> mapOut = a.runTests();
		System.out.println(mapOut.toString());
	}
	
	public static void initData() {
		String filename = "/Users/amar-5055/git/Amaresh_AMFA/amaresh_AMFA/productpackage/audit.csv";
		File file =  null;
		BufferedReader br = null;
		try {
			file = new File(filename);
			br = new BufferedReader(new FileReader(file));
			if(br!=null) {
				String line = br.readLine();
				if(line!=null && !line.isEmpty()) {
					JSONObject mainObj = new JSONObject(line);
					JSONObject hitsObj = mainObj.optJSONObject("hits");
					JSONArray array = hitsObj.optJSONArray("hits");
					for(int i =0; i<array.length(); i++) {
						JSONObject sourceObj = array.getJSONObject(i);
						JSONObject auditObj = sourceObj.getJSONObject("_source");
						String zuid = auditObj.getString("Zuid");
						if(!auditmap.isEmpty() && auditmap.containsKey(zuid)) {
							List<JSONObject> list = auditmap.get(zuid);
							list.add(auditObj);
						auditmap.put(zuid, list);
						}else {
							List<JSONObject> list = new ArrayList<JSONObject>();
							list.add(auditObj);
							auditmap.put(zuid, list);
						}
					}
				}
			}
		}catch(Exception e) {
			System.out.print(e.getMessage()+"\n");
			e.printStackTrace();
		}finally {
			if(br!=null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		}
		
	}
}
