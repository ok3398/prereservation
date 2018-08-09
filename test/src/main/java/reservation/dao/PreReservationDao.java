package reservation.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.joda.time.DateTime;
import reservation.vo.CheckResponseVO;

import java.util.Map;

@Mapper
public interface PreReservationDao {
    @Options(statementType = StatementType.CALLABLE)
    @Select(value = {
            "select count(seq) as countn from preservation_master where preserv_id = #{preserv_id};"
    })
    String selectPreReservationMaster(@Param("preserv_id") int preserv_id);


    @Insert(value = {
            "insert into preservation_join (user_id, preserv_id, seq, reg_time) " +
            "select #{user_id},#{preserv_id},  max(seq)+1 , #{reg_time}"+
            "from preservation_join where preserv_id = #{preserv_id};"
            })
    void insertPreReservation(@Param("user_id") long user_id, @Param("preserv_id") int preserv_id, @Param("reg_time") String reg_time);

    @Results({
            @Result(property = "reservationCount",column ="countn" )
    })
    @Options(statementType = StatementType.CALLABLE)
    @Select(value = {
            "select count(seq) as countn from preservation_join where preserv_id = #{preserv_id};"
    })
    String selectPreReservationCount(@Param("preserv_id") int preserv_id);


    /**
     * TYPE 1**/

    @Results({
            @Result(property = "seq", column = "seq"),
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "rank1", column = "rank1"),
    })
    @Options(statementType = StatementType.CALLABLE)
    @Select(value = {
            "select * from (" +
            "    select  user_id," +
            "            @curRank := @curRank + 1 as rank1" +
            "    from    preservation_join p, (select @curRank := 0) t1" +
            "    where   preserv_id =  #{preserv_id}" +
            "    order by  seq " +
            "    ) t2" +
            "where a.user_id = #{user_id};"
    })
    CheckResponseVO selectPreReservationMyRankType1(@Param("preserv_id") int preserv_id, @Param("user_id") long user_id);
    /**
     * TYPE 2**/
    @Options(statementType = StatementType.CALLABLE)
    @Select(value = {
            "select seq as rank1 from preservation_join where preserv_id =  #{preserv_id} and user_id = #{user_id};"
    })
    Map<String,Object> selectPreReservationMyRankType2(@Param("preserv_id") int preserv_id, @Param("user_id") long user_id);


}
