package com.zb.zbook.mapper;

import com.zb.zbook.entity.Permission;
import com.zb.zbook.entity.Role;
import com.zb.zbook.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zb
 * @since 2020-06-23
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from Role where id in (select role_id from user_role where user_id = #{userId})")
    List<Role> selectRoleByUserId(@Param("userId") int userId);

    @Select("select * from permission where id in (select permission_id from role_permission where role_id = #{roleId})")
    List<Permission> selectPermissionIdByRoleId(@Param("roleId") int roleId);

}
