package lougao.core;

import io.netty.util.concurrent.Promise;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 11:31 PM
 */
public class RequestHolder<T> {
    public static final AtomicLong REQUEST_ID  =new AtomicLong();
    public static final ConcurrentHashMap<Long, RpcFuture> REQUEST_MAP = new ConcurrentHashMap<>();
}
