package reservation.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;
import reservation.vo.CheckResponseVO;
import reservation.vo.CountResponseVO;
import reservation.vo.EnrollResponseVO;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PrereservationServiceTest {
    @Autowired
    private PrereservationService prereservationService;
    @Test
    public void enroll() {


        EnrollResponseVO enrollResponseVO = prereservationService.enroll(1,1111);
        System.out.println(enrollResponseVO.getErrorCode());
        System.out.println(enrollResponseVO.getErrorMsg());
        System.out.println(enrollResponseVO.getSeq());
    }

    @Test
    public void count() {
        CountResponseVO countResponseVO = prereservationService.count(1);
        System.out.println(countResponseVO.getReservationCount());
    }

    @Test
    public void check() {

        CheckResponseVO checkResponseVO = prereservationService.check(1,1111);
        System.out.println(checkResponseVO.getRank1());
    }
}