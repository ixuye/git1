/** 
 * <pre>项目名称:ssi-common-01 
 * 文件名称:JedisUtil.java 
 * 包名:common.util 
 * 创建日期:2017年7月28日上午10:26:55 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package util;

import redis.clients.jedis.Jedis;

/**
 * 
 * <pre>项目名称：ssi-util    
 * 类名称：JedisUtil    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月28日 下午4:21:45    
 * 修改人：徐叶    
 * 修改时间：2017年7月28日 下午4:21:45    
 * 修改备注：       
 * @version </pre>
 */
public class JedisUtil {
	
	private static Jedis jedis = null;
	
	private static Jedis getJedis() {
		if (null == jedis) {
			synchronized (JedisUtil.class) {
				if (null == jedis) {
					jedis = new Jedis("192.168.217.128", 6379);
					jedis.auth("123456");
				}
			}
		}
		return jedis;
	}
	
	/**
	 * <pre>setString(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午4:21:52    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午4:21:52    
	 * 修改备注： 
	 * @param k
	 * @param v
	 * @param seconds
	 * @return</pre>
	 */
	public static String setString(String k, String v, int seconds) {
		Jedis jedis = getJedis();
		String s = jedis.set(k, v);
		if (0 <= seconds) {
			jedis.expire(k, seconds);
		}
		return s;
	}
	
	/**
	 * <pre>getString(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午4:22:01    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午4:22:01    
	 * 修改备注： 
	 * @param k
	 * @return</pre>
	 */
	public static String getString(String k) {
		Jedis jedis = getJedis();
		String v = jedis.get(k);
		return v;
	}

}
