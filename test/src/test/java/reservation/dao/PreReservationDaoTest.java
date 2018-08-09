package reservation.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reservation.vo.CheckResponseVO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PreReservationDaoTest {
    @Autowired
    PreReservationDao preReservationDao;
    //@Test
    public void selectPreReservationMaster() {
        Map<String,Object> masterData = preReservationDao.selectPreReservationMaster(1);
        System.out.println(masterData);
    }

    //@Test
    public void selectPreReservationJoinMember() {
        Map<String,Object> masterData = preReservationDao.selectPreReservationJoinMember(1,1111);
        System.out.println(masterData);
    }

    @Test
    public void insertPreReservation() {

        DateTime nowTime = DateTime.now();
        DateTimeFormatter yearlyFmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:SS");

        int seq  = preReservationDao.insertPreReservation(33333,1, yearlyFmt.print(nowTime)  );

        System.out.println(seq);
    }

    @Test
    public void selectPreReservationCount() {
        String Count= preReservationDao.selectPreReservationCount(1);
        System.out.println(Count);
    }

    @Test
    public void selectPreReservationMyRankType1() {
        CheckResponseVO checkResponseVO = preReservationDao.selectPreReservationMyRankType1(1,11119);
        System.out.println(checkResponseVO.getRank1());
    }

    @Test
    public void selectPreReservationMyRankType2() {
        Map<String,Object> data = preReservationDao.selectPreReservationMyRankType2(1,1111);

        System.out.println(MapUtils.getString(data,"rank1"));
    }
}