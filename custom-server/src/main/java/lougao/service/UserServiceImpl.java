package lougao.service;

import lougao.IUserService;
import lougao.annotation.CustomRpcService;
import lougao.bean.User;
import org.springframework.stereotype.Component;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/10 12:00 AM
 */
@CustomRpcService
public class UserServiceImpl implements IUserService {
    @Override
    public User saveUser(User user) {
        user.setAge(1111);
        return user;
    }
}
