package lougao.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 9:41 PM
 */
@Getter
@AllArgsConstructor
public enum ReqType {
    REQUEST((byte) 1),
    RESPONSE((byte) 2),
    HEART_BEAT((byte) 3);
    private byte code;

    public static ReqType getReqType(byte code) {
        return Arrays.stream(ReqType.values()).filter(e -> Objects.equals(e.getCode(), code)).findFirst().orElse(null);
    }


}
