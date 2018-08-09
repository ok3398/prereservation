package reservation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import reservation.config.cache.MyCacheConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Import(MyCacheConfig.class)
public class MyCacheTest {
    @Autowired
    private CacheManager cacheManager;

    @Test
    @Cacheable
    public void cacheTest() {
            Cache cache = cacheManager.getCache("countCachet");
        cache.put("counttest","12");

            System.out.println("cache : " + cache.get("countKey").get());

    }
}
