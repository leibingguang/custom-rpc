package lougao.serial.impl;

import lougao.constants.SerialType;
import lougao.serial.ISerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * java序列化
 *
 * @author lou_gao
 * @description:
 * @since 2022/7/9 10:05 PM
 */
public class JavaSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {

        try {
            ObjectInputStream byteArrayInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return (T) byteArrayInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte serialType() {
        return SerialType.JAVA_SERIAL.getCode();
    }
}
