package reservation.service;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reservation.dao.PreReservationDao;
import reservation.vo.CheckResponseVO;
import reservation.vo.CountResponseVO;
import reservation.vo.EnrollResponseVO;
import reservation.vo.EnrollVO;

import java.util.Map;

@Slf4j
@Service
public class PrereservationService {
    @Autowired
    private PreReservationDao preReservationDao;

    public EnrollResponseVO enroll(int preserv_id, long user_id){

        DateTime nowTime = DateTime.now();
        DateTimeFormatter yearlyFmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:SS");

        EnrollResponseVO enrollResponseVO = new EnrollResponseVO();
        enrollResponseVO.setUserId(user_id);
        enrollResponseVO.setPreservId(preserv_id);
        enrollResponseVO.setRegTime(yearlyFmt.print(nowTime));

        Map<String,Object> masterData = preReservationDao.selectPreReservationMaster(preserv_id);

        if (masterData == null){
            enrollResponseVO.setErrorCode("-1");
            enrollResponseVO.setErrorMsg("사전 예약 정보가 없습니다.");
            return enrollResponseVO;
        }

        if (MapUtils.getString(masterData,"start_time").compareTo(yearlyFmt.print(nowTime) ) > 0) {
            enrollResponseVO.setErrorCode("-1");
            enrollResponseVO.setErrorMsg("사전 예약 기간이 아닙니다.");
            return enrollResponseVO;
        }

        if (MapUtils.getString(masterData,"end_time").compareTo(yearlyFmt.print(nowTime) ) < 0) {
            enrollResponseVO.setErrorCode("-1");
            enrollResponseVO.setErrorMsg("사전 예약 기간이 종료되었습니다.");
            return enrollResponseVO;
        }

        try {
            EnrollVO enrollVO = new EnrollVO();
            enrollVO.setPreservId(preserv_id);
            enrollVO.setUserId(user_id);
            enrollVO.setRegTime(yearlyFmt.print(nowTime));

            preReservationDao.insertPreReservation(enrollVO);
          enrollResponseVO.setSeq(String.valueOf(enrollVO.getResult()));
        }catch(Exception e){
            enrollResponseVO.setErrorCode("-2");
            enrollResponseVO.setErrorMsg("중복/오류");
            return enrollResponseVO;
        }

        enrollResponseVO.setErrorCode("0");
        enrollResponseVO.setErrorMsg("성공");


        return enrollResponseVO;

    }

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
