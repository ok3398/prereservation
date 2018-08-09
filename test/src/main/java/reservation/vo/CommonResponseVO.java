package reservation.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommonResponseVO {
    @JsonProperty("errorCode")
    private String  errorCode;
    @JsonProperty("errorMsg")
    private String errorMsg;

    @JsonProperty("userId")
    private long userId;
    @JsonProperty("preservId")
    private int preservId;
}
