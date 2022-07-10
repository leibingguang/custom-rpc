package lougao.spring.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/10 3:07 PM
 */
@Data
@ConfigurationProperties(prefix = "custom.rpc")
public class RpcServerProperties {
    /**
     * 服务器端口
     */
    private int serverPort;
}
