package p08;

import com.practice.springboot.p08.SampleWebJspApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 * Created by liaowenqiang on 2016/9/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes=SampleWebJspApplication.class)
public class RedisTests  {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        String testKey="wenQiangKey.test01";
        String testStr="redis test";
        stringRedisTemplate.opsForValue().set(testKey,testStr);
        Assert.isTrue(testStr.equals(stringRedisTemplate.opsForValue().get(testKey)));
    }
}
