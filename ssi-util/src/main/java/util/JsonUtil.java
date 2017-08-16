
package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * <pre>项目名称：ssi-util    
 * 类名称：JsonUtil    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月28日 下午4:22:50    
 * 修改人：徐叶    
 * 修改时间：2017年7月28日 下午4:22:50    
 * 修改备注：       
 * @version </pre>
 */
public class JsonUtil {
	
	private static Gson gson = new Gson();
	
	/**
	 * <pre>toJsonString(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午4:22:56    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午4:22:56    
	 * 修改备注： 
	 * @param obj
	 * @return</pre>
	 */
	public static String toJsonString(Object obj) {
		if (null == obj) {
			throw new NullPointerException();
		}
		String json = gson.toJson(obj);
		return json;
	}
	
	/**
	 * <pre>fromJson(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午4:23:04    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午4:23:04    
	 * 修改备注： 
	 * @param json
	 * @param t
	 * @return</pre>
	 */
	public static <T> T fromJson(String json, Class<T> t) {
		if (null == json) {
			throw new NullPointerException();
		}
		T obj = gson.fromJson(json, new TypeToken<T>(){}.getType());
		return obj;
	}

}
