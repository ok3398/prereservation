package reservation.vo;

import lombok.Data;

@Data
public class EnrollVO {
    private long userId;
    private int preservId;
    private String regTime;
    private int result;
}
