package com.zb.zbook.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.zb.zbook.entity.User;
import com.zb.zbook.mapper.UserMapper;
import com.zb.zbook.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zb
 * @since 2020-06-23
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @Resource
    private UserMapper userMapper;

    @RequestMapping("/user")
    @ResponseBody
    public User getUserById(int id){
        User user = userService.getById(id);
        return user;
    }

    @RequestMapping("/userlist")
    @RequiresPermissions("user:list")
    public String getUserList(Model model, PageInfo pageInfo){

        int pageNum  = (pageInfo.getPageNum() == 0)? 1 : pageInfo.getPageNum();
        int pageSize  = (pageInfo.getPageSize() == 0)? 10 : pageInfo.getPageSize();
        IPage<User> iPage = userService.page(new Page<>(pageNum, pageSize));

        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(iPage.getTotal());
        pageInfo.setStartRow(pageSize * (pageNum - 1));
        pageInfo.setPages((int)(iPage.getTotal()/(int)pageSize) + 1);

        model.addAttribute("users", iPage.getRecords());
        model.addAttribute("pageInfo", pageInfo);
        return "userlist";
    }

    @RequestMapping("/userdelete")
    @RequiresPermissions("user:delete")
    public String userdelete(int id){
        userService.removeById(id);
        return "redirect:/userlist";
    }

    @RequestMapping("/useredit")
    public String useredit(int id, Model model){
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "useredit";
    }

    @RequestMapping(value = "/userupdateoradd", method = RequestMethod.POST)
    public String userUpdateOrAdd(User user){
        userService.saveOrUpdate(user);
        return "redirect:/userlist";
    }
}
