package reservation.service;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reservation.dao.PreReservationDao;
import reservation.vo.CheckResponseVO;
import reservation.vo.CountResponseVO;
import reservation.vo.EnrollResponseVO;

@Slf4j
@Service
public class PrereservationService {
    @Autowired
    private PreReservationDao preReservationDao;

    public EnrollResponseVO enroll(int preserv_id, long user_id){
        EnrollResponseVO enrollResponseVO = new EnrollResponseVO();


        DateTime nowTime = DateTime.now();
        DateTimeFormatter yearlyFmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:SS");


        preReservationDao.insertPreReservation(user_id,preserv_id, yearlyFmt.print(nowTime) );



        return enrollResponseVO;

    }

    @Cacheable(cacheNames = "countCache", key="#{preserv_id}")
    public CountResponseVO count(int preserv_id){
        CountResponseVO countResponseVO = new CountResponseVO();
        String count = preReservationDao.selectPreReservationCount(preserv_id);

        countResponseVO.setPreservId(preserv_id);
        countResponseVO.setReservationCount(count);

        return countResponseVO;

    }

    public CheckResponseVO check(int preserv_id, long user_id){
        CheckResponseVO checkResponseVO  = new CheckResponseVO();
        preReservationDao.selectPreReservationMyRankType1(preserv_id,user_id);
        return checkResponseVO;

    }
}
