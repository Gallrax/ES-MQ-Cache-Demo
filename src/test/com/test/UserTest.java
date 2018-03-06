package com.test;

import com.cx.entity.Depart;
import com.cx.entity.User;
import com.cx.repository.jpa.DepartRepository;
import com.cx.repository.jpa.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * @Description:
 * @Date: Created on 2018/3/5
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartRepository departRepository;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test01() {
        for (int i = 1; i < 10; i++) {
            String uuid = UUID.randomUUID().toString();
            User user = new User(uuid, "tom" + i, 1, "1381111111" + i, new Date());
            userRepository.save(user);
        }
    }

    @Test
    public void test02() {
        Page<User> users = userRepository.findAll(new PageRequest(2, 3));
        System.out.println(users.getContent());
        Page<User> temp = userRepository.findAll(new PageRequest(2, 3));
    }

    /**
     * 测试spring cache
     */
    @Test
    public void test03() {
        User tom1 = userRepository.findByName("tom1");
        User tom2 = userRepository.findByName("tom2");
        System.out.println(" 第一次tom1 : " + tom1);
        User tempTom = userRepository.findByName("tom1");
        System.out.println(" 第二次tom1 : " + tempTom);
        tom1.setSex(2);
        userRepository.save(tom1);
        System.out.println(" save user end ");
        User user = userRepository.findByName("tom1");
        System.out.println(" find from cache ? " + user.getSex());
        /*userRepository.delete(tom1.getId());
        //证明是否为清空所有
        User tempTom1 = userRepository.findByName("tom1");
        User tempTom2 = userRepository.findByName("tom2");
        System.out.println(" tempTom1 : " + tempTom1);
        System.out.println(" tempTom2 : " + tempTom2);*/
    }

    @Test
    public void test05() {
        User tom1 = userRepository.findByName("tom1");
        System.out.println("第一次查询 ： " + tom1);
        User tempTom1 = userRepository.findByName("tom1");
        System.out.println("第二次查询 ： " + tempTom1);
        tom1.setSex(2);
        User save = userRepository.save(tom1);
        System.out.println("修改之后 ： " + save.getSex());
        User updateTom1 = userRepository.findByName("tom1");
        System.out.println(" cache ? : " + updateTom1.getSex());
    }

    @Test
    public void test06() {
        User user1 = userRepository.findById("574a4f31-035a-4272-9c62-2f73d1b87fa8");
        System.out.println(" 第一次查询 : " + user1);
        User user2 = userRepository.findById("574a4f31-035a-4272-9c62-2f73d1b87fa8");
        System.out.println(" 第二次查询 : " + user2);
        user1.setSex(user1.getSex() + 1);
        User save = userRepository.save(user1);
        System.out.println(" 修改之后 : " + save);
        User user3 = userRepository.findById("574a4f31-035a-4272-9c62-2f73d1b87fa8");
        System.out.println(" 测试缓存 ： " + user3);
        User user4 = userRepository.findById("574a4f31-035a-4272-9c62-2f73d1b87fa8");
        System.out.println(" 测试缓存2 ： " + user4);
    }

    /**
     * 测试spring cache name定义是否有作用(制定name之后，key的规则一样。实际结果证明此种方式不靠谱，当id相同时则会抛出类型不匹配异常)
     */
    @Test
    public void test04() {
//        User user = userRepository.findById("574a4f31-035a-4272-9c62-2f73d1b87fa8");
//        System.out.println(user);
//        User tempUser = userRepository.findById("574a4f31-035a-4272-9c62-2f73d1b87fa8");
//        System.out.println(tempUser);
//        Depart depart1 = departRepository.findById("1");
//        System.out.println(depart1);
        Depart depart2 = departRepository.findById("574a4f31-035a-4272-9c62-2f73d1b87fa8");
        System.out.println(depart2);
        Depart tempDepart = departRepository.findById("574a4f31-035a-4272-9c62-2f73d1b87fa8");
        System.out.println(tempDepart);
    }

    @Test
    public void testTemp() {
        Runnable runnable = () -> System.out.println("-");
        new Thread(runnable).start();
        KeyGenerator keyGenerator = (o, m, params) -> params[0];
    }

}
