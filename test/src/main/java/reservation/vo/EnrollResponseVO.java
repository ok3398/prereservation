package reservation.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
public class EnrollResponseVO extends CommonResponseVO{
    @JsonProperty("seq")
    private long seq;
    @JsonProperty("regTime")
    private String regTime;
}
