package lougao.serial;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 10:04 PM
 */
public interface ISerializer {
    /**
     * 对象序列化
     *
     * @param obj
     * @return
     */
    <T> byte[] serialize(T obj);

    /**
     * 反序列化
     *
     * @param bytes
     * @param clazz
     * @return
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);

    /**
     * 序列化类型
     *
     * @return
     * @see lougao.constants.SerialType
     */
    byte serialType();

}
