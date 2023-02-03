package com.team15.commonmybatis.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team15.commonmybatis.model.Users;
import com.team15.commonmybatis.mapper.UsersMapper;
import com.team15.commonmybatis.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**

 * @author lixia
 * @since 2023-01-23
 */
@Service
public class UsersImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public Users exitsUser(Users usr) {
        Users sysPerson=null;
        //valid
        // System.out.println(usr.getUserid());
        if(usr.getEmail()!=""&&usr.getPassword()!="") {
            QueryWrapper<Users> query1 = new QueryWrapper<>();
            query1.eq("email", usr.getEmail().trim());
            Users myUser = usersMapper.selectOne(query1);
            if (myUser != null) {
                //String secretPwd= AESUtil.Encrypt(lbLaborusr.getPwd().trim(),AESUtil.secretStr);
                String secretPwd =myUser.getPassword().trim();
                log.debug(secretPwd);
                if (usr.getPassword().trim().equals(secretPwd)) {
                    sysPerson=new Users();
                    //init user
                    sysPerson=myUser;
                }
            }
        }
        return sysPerson;
    }

    @Override
    public boolean updatePassword(Users usr) {
        boolean flag=false;
        LocalDateTime dt = LocalDateTime.now();
        if(usr.getEmail()!=""&&usr.getPassword()!="") {
            QueryWrapper<Users> query1 = new QueryWrapper<>();
            query1.eq("email", usr.getEmail().trim());
            Users myUser = usersMapper.selectOne(query1);
            if(myUser!=null) {
                //reset password
                String targetPwd = usr.getPassword();
                myUser.setPassword(targetPwd);
                int iResult = usersMapper.updateById(myUser);
                if (iResult > 0) {
                    flag=true;
                }
            }
        }
        return flag;
    }
}
