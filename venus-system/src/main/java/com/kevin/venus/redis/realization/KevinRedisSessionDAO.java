package com.kevin.venus.redis.realization;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class KevinRedisSessionDAO extends EnterpriseCacheSessionDAO {

    // session 在redis过期时间是30分钟30*60
//    private static int expireTime = 1800;
	private static int expireTime = 1;

    private static String prefix = "weiyou-shiro-session:";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
//    protected Serializable doCreate(Session session) {
//        Serializable sessionId = generateSessionId(session);
//        assignSessionId(session, sessionId);
//        storeSession(sessionId, session);
//        return sessionId;
//    }
    // 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        redisTemplate.opsForValue().set(prefix + sessionId.toString(), session);
        return sessionId;
    }

    // 获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = (Session) redisTemplate.opsForValue().get(prefix + sessionId.toString());
        }
        return session;
    }

    // 更新session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        String key = prefix + session.getId().toString();
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, session);
        }
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    // 删除session
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        redisTemplate.delete(prefix + session.getId().toString());
    }

}
