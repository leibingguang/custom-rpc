package lougao.serial;

import lougao.constants.SerialType;
import lougao.serial.impl.JSONSerializer;
import lougao.serial.impl.JavaSerializer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 10:15 PM
 */
public class SerializerManager {
    public static final ConcurrentHashMap<Byte, ISerializer> serializerMap = new ConcurrentHashMap<>();

    static {
        serializerMap.put(SerialType.JAVA_SERIAL.getCode(), new JavaSerializer());
        serializerMap.put(SerialType.JSON_SERIAL.getCode(), new JSONSerializer());
    }

    public static ISerializer getSerializer(Byte serialType) {
        if (serialType == null) {
            return serializerMap.get(SerialType.JAVA_SERIAL.getCode());
        }
        return serializerMap.get(serialType);
    }
}
