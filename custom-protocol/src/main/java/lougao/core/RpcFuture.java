package lougao.core;

import io.netty.util.concurrent.Promise;
import lombok.Data;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 11:33 PM
 */
@Data
public class RpcFuture<T> {
    private Promise<T> promise;

    public RpcFuture(Promise<T> promise) {
        this.promise = promise;
    }
}
