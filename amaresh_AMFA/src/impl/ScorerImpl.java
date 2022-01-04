//$Id$
package impl;

import java.util.HashMap;
import java.util.Map;

import com.zlabs.aics.adaptivemfa.Scorer;

public class ScorerImpl implements Scorer{

	@Override
	public int getN() {
		// TODO Auto-generated method stub
		return 30;
	}

	@Override
	public int getBrowserChangeScore() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getBrowserVersionChangeScore() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getOSChangeScore() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getDeviceChangeScore() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getSignInMethodScore() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getProvidedCountryChangeScore() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getProvidedDeviceChangeScore() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Map<Long, Integer> getLatLongScore() {
		Map<Long, Integer> map = new HashMap<Long, Integer>();
	    map.put(10000l, 100);
	    map.put(1000l, 80);
	    map.put(100l, 60);
	    map.put(10l, 40);
	    map.put(1l, 20);
	    return map;
	}

	@Override
	public int getCountryChangeScore() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getStateChangeScore() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRegionChangeScore() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getCityChangeScore() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getHourOfTheDayChangeScore() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getDayOfTheWeekChangeScore() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getPlaceChangeScore() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Map<Integer, Integer> getDistanceScore() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	    map.put(10000, 100);
	    map.put(1000, 80);
	    map.put(100, 60);
	    map.put(10, 40);
	    map.put(1, 20);
	    return map;
	}

}
