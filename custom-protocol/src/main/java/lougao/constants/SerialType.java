package lougao.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 9:43 PM
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SerialType {
    JAVA_SERIAL((byte) 1),
    JSON_SERIAL((byte) 2);
    private byte code;

    public static SerialType getSerialType(byte code) {
        return Arrays.stream(SerialType.values()).filter(e -> Objects.equals(e.getCode(), code)).findFirst().orElse(null);
    }

}
