package com.zb.zbook.service;

import com.zb.zbook.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zb
 * @since 2020-06-23
 */
public interface IUserService extends IService<User> {

    User validateUser(String username, String password);

    User findRoleAndPermissions(User user);
}
