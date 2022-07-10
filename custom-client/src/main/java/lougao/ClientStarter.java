package lougao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/10 5:31 PM
 */
@ComponentScan(basePackages = {"lougao.spring.reference", "lougao.web", "lougao.annotation"})
@SpringBootApplication
public class ClientStarter {
    public static void main(String[] args) {
        SpringApplication.run(ClientStarter.class, args);
    }
}
