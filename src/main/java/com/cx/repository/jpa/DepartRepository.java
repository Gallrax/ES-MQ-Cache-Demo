package com.cx.repository.jpa;

import com.cx.entity.Depart;
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
@CacheConfig(cacheNames = "departs")//设置全局value = "users"
public interface DepartRepository extends JpaRepository<Depart, String> {

    @Cacheable(key = "T(String).valueOf('depart.'+#p0)")
    Depart findById(String id);

    //@Cacheable(key = "#p0")//会导致脏数据
    Depart findByName(String name);

    @CachePut(key = "T(String).valueOf('depart.'+#p0.id)")
    Depart save(Depart depart);

    //此方法不会清空上方两方法的缓存，因此要将users的缓存全部清空
    @CacheEvict(key = "T(String).valueOf('depart.'+#p0)")
    void delete(String id);
}
