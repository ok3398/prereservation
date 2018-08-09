package reservation.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CountResponseVO {

    @JsonProperty("reservationCount")
    private String reservationCount;
    @JsonProperty("preservId")
    private int preservId = 0;
}
