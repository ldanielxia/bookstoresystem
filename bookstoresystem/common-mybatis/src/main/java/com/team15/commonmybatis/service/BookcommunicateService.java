package com.team15.commonmybatis.service;

import com.team15.commonmybatis.model.Bookcommunicate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team15.commonmybatis.model.BookcommunicateExtend;

import java.util.List;

/**
 * @author lixia
 * @since 2023-02-03
 */
public interface BookcommunicateService extends IService<Bookcommunicate> {
    List<BookcommunicateExtend> bookcommunicateByBookID(int ID);
}
