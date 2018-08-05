package reservation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        return checkResponseVO;

    }
}
