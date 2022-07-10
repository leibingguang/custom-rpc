package lougao.web;

import lougao.IUserService;
import lougao.annotation.CustomReference;
import lougao.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/10 5:27 PM
 */
@RestController
public class TestController {
    @CustomReference
    private IUserService userService;

    @GetMapping("/hello")
    public User saveUser() {
        return userService.saveUser(new User("lou_gao", 18));
    }

}
