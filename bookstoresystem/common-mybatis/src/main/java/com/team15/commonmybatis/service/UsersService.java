package com.team15.commonmybatis.service;

import com.team15.commonmybatis.model.BookExtend;
import com.team15.commonmybatis.model.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author lixia
 * @since 2023-01-23
 */
public interface UsersService extends IService<Users> {
    Users exitsUser(Users usr);
    boolean updatePassword(Users usr);

}
