package reservation.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PrereservationServiceTest {
    @Autowired
    private PrereservationService prereservationService;
    @Test
    public void enroll() {
        prereservationService.enroll(1,1111);
    }

//    @Test
//    public void count() {
//        prereservationService.count(1);
//    }

    @Test
    public void check() {
        prereservationService.check(1,1111);
    }
}