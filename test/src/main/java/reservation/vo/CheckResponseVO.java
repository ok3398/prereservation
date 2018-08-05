package reservation.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CheckResponseVO extends CommonResponseVO{
    @JsonProperty("seq")
    private long seq;
}
