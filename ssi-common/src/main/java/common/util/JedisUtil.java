/** 
 * <pre>项目名称:ssi-common-01 
 * 文件名称:JedisUtil.java 
 * 包名:common.util 
 * 创建日期:2017年7月28日上午10:26:55 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package common.util;

import redis.clients.jedis.Jedis;

/** 
 * <pre>项目名称：ssi-common-01    
 * 类名称：JedisUtil    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年7月28日 上午10:26:55    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年7月28日 上午10:26:55    
 * 修改备注：       
 * @version </pre>    
 */
public class JedisUtil {
	
	private static Jedis jedis = null;
	
	private static Jedis getJedis() {
		if (null == jedis) {
			synchronized (JedisUtil.class) {
				if (null == jedis) {
					jedis = new Jedis("192.168.1.177", 6479);
					jedis.auth("123456");
				}
			}
		}
		return jedis;
	}
	
	/**
	 * <pre>setString(这里用一句话描述这个方法的作用)   
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年7月28日 上午10:30:36    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年7月28日 上午10:30:36    
	 * 修改备注： 
	 * @param k
	 * @param v
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
	 * 创建人：陈教授 chenjh123@gmail.com    
	 * 创建时间：2017年7月28日 上午10:30:40    
	 * 修改人：陈教授 chenjh123@gmail.com     
	 * 修改时间：2017年7月28日 上午10:30:40    
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
