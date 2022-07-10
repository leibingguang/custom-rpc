package lougao.serial.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lougao.constants.SerialType;
import lougao.serial.ISerializer;

/**
 * json序列化
 *
 * @author lou_gao
 * @description:
 * @since 2022/7/9 10:07 PM
 */
public class JSONSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return JSONObject.parseObject(bytes, clazz);
    }

    @Override
    public byte serialType() {
        return SerialType.JSON_SERIAL.getCode();
    }
}
