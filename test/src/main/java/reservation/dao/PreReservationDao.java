package reservation.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.joda.time.DateTime;

@Mapper
public interface PreReservationDao {
    @Insert(value = {
            "insert into preservation_join (user_id, preserv_id, seq, reg_time) " +
            "select #{user_id},#{preserv_id},  max(seq)+1 , #{reg_time}"+
            "from preservation_join;"
            })
    void insertPreReservation(@Param("user_id") long user_id, @Param("preserv_id") int preserv_id,@Param("reg_time") DateTime reg_time);

    @Results({
            @Result(property = "reservationCount",column ="countn" )
    })
    @Options(statementType = StatementType.CALLABLE)
    @Select(value = {
            "select count(seq) as countn from preservation_join where preserv_id = #{preserv_id};"
    })
    String selectPreReservationCount(@Param("preserv_id") int preserv_id);



//    @Select(value = "{call dbo.MSP_SELECT_NONLICENCE_SONG_BY_LID( #{sid, mode=IN},#{lid, mode=IN})}")
//    @Options(statementType = StatementType.CALLABLE)
//    Map<String, Object> insertPreReservation(@Param("use") int sid, @Param("lid") String lid);
}
