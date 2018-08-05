package reservation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reservation.service.PrereservationService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/prereservation")
public class PrereservationController {

    @Autowired
    private PrereservationService prereservationService;
    /**
     * user_id 가 preserv_id 사전예약에 참여하는 api
     * **/
    @RequestMapping(value = "/enroll/{preserv_id}/{user_id}")
    public Object enroll(HttpServletRequest httpServletRequest, @PathVariable int preserv_id, @PathVariable long user_id){
        return prereservationService.enroll(preserv_id, user_id);
    }

    /**
     *  preserv_id 에 참여한 총 카운트 조회 api
     *  **/
    @RequestMapping(value = "/count/{preserv_id}")
    public Object count(HttpServletRequest httpServletRequest, @PathVariable int preserv_id){
        return prereservationService.count(preserv_id);
    }
    /**
    * user_id 가 preserv_id 사전예약에 참여했는지
    **/
    @RequestMapping(value = "/check/{preserv_id}/{user_id}")
    public Object check(HttpServletRequest httpServletRequest, @PathVariable int preserv_id, @PathVariable long user_id){
        return prereservationService.check(preserv_id, user_id);
    }

}
