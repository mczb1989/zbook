package com.zb.zbook.service.impl;

import com.zb.zbook.entity.Permission;
import com.zb.zbook.entity.Role;
import com.zb.zbook.entity.User;
import com.zb.zbook.mapper.UserMapper;
import com.zb.zbook.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zb
 * @since 2020-06-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User validateUser(String username, String password) {

        List<User> list = this.lambdaQuery().eq(User::getUsername, username)
                .eq(User::getPassword, password)
                .list();

        return CollectionUtils.isEmpty(list)? null: list.get(0);
    }

    @Override
    public User findRoleAndPermissions(User user) {
        List<Role> roleList = this.baseMapper.selectRoleByUserId(user.getId());
        user.setRoleList(roleList);

        List<Permission> permissions = new ArrayList<>();
        for (Role role: roleList) {
            List<Permission> permissionlist = this.baseMapper.selectPermissionIdByRoleId(role.getId());
            role.setPermissionList(permissionlist);
            permissions.addAll(permissionlist);
        }
        user.setPermissionList(permissions);
        return user;
    }
}
