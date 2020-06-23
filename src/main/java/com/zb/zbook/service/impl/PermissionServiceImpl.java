package com.zb.zbook.service.impl;

import com.zb.zbook.entity.Permission;
import com.zb.zbook.mapper.PermissionMapper;
import com.zb.zbook.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zb
 * @since 2020-06-23
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
