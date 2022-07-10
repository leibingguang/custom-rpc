package lougao;

import lougao.annotation.CustomRpcService;
import lougao.bean.User;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 11:57 PM
 */
public interface IUserService {

    User saveUser(User user);
}
