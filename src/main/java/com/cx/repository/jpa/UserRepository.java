package com.cx.repository.jpa;

import com.cx.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Date: Created on 2018/3/5
 * @Version: 1.0
 */
@CacheConfig(cacheNames = "users")//设置全局value = "users"
public interface UserRepository extends JpaRepository<User, String> {

    @Cacheable(key = "T(String).valueOf('user.'+#p0)")
    User findById(String id);

//    @Cacheable(key = "#p0")//会导致脏数据
    User findByName(String name);

    @CachePut(key = "T(String).valueOf('user.'+#p0.id)")
    User save(User user);

    //此方法不会清空上方两方法的缓存，因此要将users的缓存全部清空
    @CacheEvict(key = "T(String).valueOf('user.'+#p0)")
    void delete(String id);
}
