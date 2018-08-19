package com.ls.demo.service.sys;

import com.ls.demo.entity.sys.Menu;
import com.ls.demo.entity.sys.User;
import com.ls.demo.jpaDao.sys.MenuRep;
import com.ls.demo.jpaDao.sys.UserRep;
import com.ls.demo.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {

    @Autowired
    private UserRep userRep;

    @Autowired
    private MenuRep menuRep;

    @Autowired
    private RedisTemplate redisTemplate;

    public String doLogin(User user) {
        Example<User> example = Example.of(user);
        Optional<User> one = userRep.findOne(example);
        if (one.isPresent()) {
            //获取depts,menus
            String token = IdUtils.getUuid();
            HashSet<Menu> menus = menuRep.findAllByUsername(user.getUsername());
            BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(token);
            boundHashOperations.put("user", one.get());
            boundHashOperations.put("menus", menus);
            boundHashOperations.expire(30, TimeUnit.MINUTES);
            return token;
        }
        return null;
    }

    public void logout(String token) {
        redisTemplate.delete(token);
    }

    public void indexData(String token, Map<String, Object> map) {
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(token);
        map.put("user", boundHashOperations.get("user"));
        map.put("menus", boundHashOperations.get("menus"));
    }
}
