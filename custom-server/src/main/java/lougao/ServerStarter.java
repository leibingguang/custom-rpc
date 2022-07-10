package lougao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/10 12:00 AM
 */
@ComponentScan(basePackages = {"lougao.service", "lougao.spring"})
@SpringBootApplication
public class ServerStarter {
    public static void main(String[] args) {
        SpringApplication.run(ServerStarter.class, args);
    }

}
