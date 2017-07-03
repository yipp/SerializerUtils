package org.yunet.serializer.protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * Created by CL-PC202 on 2017/7/3.
 */
public class Utils {
    public Utils() {
    }
    /**序列化*/
    public  static <T> byte[] serializer(T t){
        Schema schema = RuntimeSchema.getSchema(t.getClass());
        return ProtobufIOUtil.toByteArray(t,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }
    /**反序列化*/
    public  static <T> T deserializer(byte[] bytes,Class<T> clazz){
        T obj = null;
        try {
            obj = clazz.newInstance();
            Schema schema = RuntimeSchema.getSchema(obj.getClass());
            ProtostuffIOUtil.mergeFrom(bytes,obj,schema);
        }catch (Exception e){

        }
        return obj;
    }
}
