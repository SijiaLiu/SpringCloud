package com.lsj.settlement.Service.impl;

import com.lsj.settlement.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * class_name: RedisServiceImpl
 * package: com.lsj.settlement.Service.impl
 * describe: TODO
 * @author liusijia
 * @Date 2018/11/22
**/

@Service
public class RedisServiceImpl implements RedisService {

	/**
	 * serialVersionUID:TODO Description.
	 */
	private static final long serialVersionUID = -2989094134101492276L;

	@Value("${redis.batch-size:1000}")
	private int COMMIT_BATCH_SIZE = 10000;

	@Autowired
	private RedisTemplate<Serializable, Object> redisTemplate;

//	private Map<String, Map<Integer, FSMState>> fsmStateCache;
//
//	public RedisServiceImpl() {
//		log.info("RedisServiceImpl is initialized [{}] ....", COMMIT_BATCH_SIZE);
//		fsmStateCache = new HashMap<String, Map<Integer, FSMState>>();
//	}

	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final Serializable... keys) {
		for (Serializable key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final Serializable pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final Serializable key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final Serializable key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * read cache
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Object getStr(final Serializable key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}

	/**
	 * add into canche
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setStr(final Serializable key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * add into cache with expiration time
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setStr(final Serializable key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * read Set cache
	 * 
	 * @param key
	 * @return
	 */
	public Object getSet(final Serializable key) {
		Object result = null;
		SetOperations<Serializable, Object> operations = redisTemplate.opsForSet();
		result = operations.members(key);
		return result;
	}

	/**
	 * add into canche
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setSet(final Serializable key, Object value) {
		boolean result = false;
		try {
			SetOperations<Serializable, Object> operations = redisTemplate.opsForSet();
			operations.add(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * add into cache with expiration time
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setSet(final Serializable key, Object value, Long expireTime) {
		boolean result = false;
		try {
			SetOperations<Serializable, Object> operations = redisTemplate.opsForSet();
			operations.add(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean isSetMemeber(Serializable key, Object value) {
		boolean result = false;
		try {
			SetOperations<Serializable, Object> operations = redisTemplate.opsForSet();
			result = operations.isMember(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Cursor<Object> getSetScanCursor(final Serializable key) {
		Cursor<Object> cursor = redisTemplate.opsForSet().scan(key, ScanOptions.NONE);
		return cursor;
	}

	/**
	 * read cache
	 * 
	 * @param key
	 * @return
	 */
	public List<Object> getList(final Serializable key) {
		List<Object> result = null;
		ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
		Long size = operations.size(key);
		result = operations.range(key, 0, size);
		return result;
	}

	/**
	 * read cache
	 * 
	 * @param key
	 * @return
	 */
	public List<Object> getRangeList(final Serializable key, Long start, Long end) {
		List<Object> result = null;
		ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
		Long size = operations.size(key);
		if (start > size) {
			return null;
		}
		result = operations.range(key, start, size > end ? end : size);
		return result;
	}

	public boolean removeListItem(final Serializable key, Long count, Object value) {
		ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
		operations.remove(key, count, value);
		return true;
	}

	/**
	 * getListSize:(Description). <br/>
	 *
	 * @param key
	 * @return
	 */
	public Long getListSize(final Serializable key) {
		ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
		return operations.size(key);
	}

	/**
	 * add into canche
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setList(final Serializable key, Object value) {
		boolean result = false;
		try {
			ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
			operations.leftPush(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * add into cache with expiration time
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setList(final Serializable key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
			operations.leftPush(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * setListAll:(Description). <br/>
	 *
	 * @param key
	 * @param values
	 * @return
	 */
	public boolean setListAll(final Serializable key, Collection<Object> values) {
		boolean result = false;
		try {
			ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
			operations.leftPushAll(key, values);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * setListAll:(Description). <br/>
	 *
	 * @param key
	 * @param values
	 * @param expireTime
	 * @return
	 */
	public boolean setListAll(final Serializable key, Collection<Object> values, Long expireTime) {
		boolean result = false;
		try {
			ListOperations<Serializable, Object> operations = redisTemplate.opsForList();
			operations.leftPushAll(key, values);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * read Set cache
	 * 
	 * @param key
	 * @return
	 */
	public Map<Object, Object> getHashSet(final Serializable key) {
		Map<Object, Object> result = null;
		HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
		result = operations.entries(key);
		return result;
	}

	/**
	 * getHashSetValue:(Description). <br/>
	 *
	 * @param key
	 * @param mapKey
	 * @return
	 */
	public Object getHashSetValue(final Serializable key, final Object mapKey) {
		Object result = null;
		HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
		result = operations.get(key, mapKey);
		return result;
	}

	/**
	 * read Set cache
	 * 
	 * @param key
	 * @return
	 */
	public Long getHashSetSize(final Serializable key) {
		HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
		return operations.size(key);
	}

	/**
	 * getHashScanCursor:(Description). <br/>
	 *
	 * @param key
	 * @return
	 */
	public Cursor<Map.Entry<Object, Object>> getHashScanCursor(final Serializable key) {
		// log.info("The cursor size is: {}", ScanOptions.NONE.getCount());
		HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
		Cursor<Map.Entry<Object, Object>> cursor = operations.scan(key,
				ScanOptions.scanOptions().count(operations.size(key)).build());
		return cursor;
	}

	/**
	 * add into canche
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setHashSet(final Serializable key, Object mapKey, Object mapvalue) {
		boolean result = false;
		try {
			HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
			operations.put(key, mapKey, mapvalue);
			result = true;
			// if(mapvalue instanceof gate.creole.gazetteer.FSMState)
			// log.info("Redis Save: [{}]->[{}]->[{}]", key, mapKey,
			// ((gate.creole.gazetteer.FSMState)mapvalue).getTransitionFunction().getItemsKeys());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * add into canche
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public boolean removeFromHashSet(final Serializable key, Object mapKey) {
		boolean result = false;
		try {
			HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
			operations.delete(key, mapKey);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * add into cache with expiration time
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setHashSet(final Serializable key, Object mapKey, Object mapvalue, Long expireTime) {
		boolean result = false;
		try {
			HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
			operations.put(key, mapKey, mapvalue);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void commitLastFSMStateBatch() {
		// TODO Auto-generated method stub
		
	}
}
