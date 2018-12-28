package com.lsj.settlement.Service;

import org.springframework.data.redis.core.Cursor;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * class_name: RedisService
 * package: com.lsj.settlement.Service
 * describe: TODO
 * @author liusijia
 * @Date 2018/11/22
**/

public interface RedisService extends Serializable {

	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final Serializable... keys);

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final Serializable pattern);

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final Serializable key);

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final Serializable key);

	/**
	 * read cache
	 * 
	 * @param key
	 * @return
	 */
	public Object getStr(final Serializable key);

	/**
	 * add into canche
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setStr(final Serializable key, Object value);

	/**
	 * add into cache with expiration time
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setStr(final Serializable key, Object value, Long expireTime);

	/**
	 * read Set cache
	 * 
	 * @param key
	 * @return
	 */
	public Object getSet(final Serializable key);

	/**
	 * add into canche
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setSet(final Serializable key, Object value);

	/**
	 * check the value is one member or not.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean isSetMemeber(final Serializable key, Object value);

	/**
	 * add into cache with expiration time
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setSet(final Serializable key, Object value, Long expireTime);

	public Cursor<Object> getSetScanCursor(final Serializable key);

	/**
	 * read cache
	 * 
	 * @param key
	 * @return
	 */
	public List<Object> getList(final Serializable key);

	/**
	 * read cache
	 * 
	 * @param key
	 * @return
	 */
	public List<Object> getRangeList(final Serializable key, Long start, Long end);

	public boolean removeListItem(final Serializable key, Long count, Object value);

	/**
	 * getListSize:(Description). <br/>
	 *
	 * @param key
	 * @return
	 */
	public Long getListSize(final Serializable key);

	/**
	 * add into canche
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setList(final Serializable key, Object value);

	/**
	 * add into cache with expiration time
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setList(final Serializable key, Object value, Long expireTime);

	/**
	 * setListAll:(Description). <br/>
	 *
	 * @param key
	 * @param values
	 * @return
	 */
	public boolean setListAll(final Serializable key, Collection<Object> values);

	/**
	 * setListAll:(Description). <br/>
	 *
	 * @param key
	 * @param values
	 * @param expireTime
	 * @return
	 */
	public boolean setListAll(final Serializable key, Collection<Object> values, Long expireTime);

	/**
	 * read Set cache
	 * 
	 * @param key
	 * @return
	 */
	public Map<Object, Object> getHashSet(final Serializable key);

	/**
	 * getHashSetValue:(Description). <br/>
	 *
	 * @param key
	 * @param mapKey
	 * @return
	 */
	public Object getHashSetValue(final Serializable key, final Object mapKey);
	

	/**
	 * getFSMStateSetValue:Special for FSMState. <br/>
	 *
	 * @param key
	 * @param mapKey
	 * @return
	 */
//	public FSMState getFSMStateSetValue(final Serializable key, final Integer mapKey);

	/**
	 * read Set cache
	 * 
	 * @param key
	 * @return
	 */
	public Long getHashSetSize(final Serializable key);

	/**
	 * getHashScanCursor:(Description). <br/>
	 *
	 * @param key
	 * @return
	 */
	public Cursor<Map.Entry<Object, Object>> getHashScanCursor(final Serializable key);

	/**
	 * add into canche
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setHashSet(final Serializable key, Object mapKey, Object mapvalue);

	/**
	 * setFSMStateSet: Special for FSMState. <br/>
	 *
	 * @param key
	 * @param mapKey
	 * @param mapvalue
	 * @return
	 */
//	public boolean setFSMStateSet(final Serializable key, Integer mapKey, FSMState mapvalue);
	
	/**
	 * commitLastFSMStateBatch:(Description). <br/>
	 *
	 */
	public void commitLastFSMStateBatch();

	/**
	 * add into canche
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean removeFromHashSet(final Serializable key, Object mapKey);

	/**
	 * add into cache with expiration time
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setHashSet(final Serializable key, Object mapKey, Object mapvalue, Long expireTime);
}
