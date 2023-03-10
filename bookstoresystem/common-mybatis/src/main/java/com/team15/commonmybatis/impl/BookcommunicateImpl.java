
package com.team15.commonmybatis.impl;
import com.team15.commonmybatis.model.Bookcommunicate;
import com.team15.commonmybatis.mapper.BookcommunicateMapper;
import com.team15.commonmybatis.model.BookcommunicateExtend;
import com.team15.commonmybatis.service.BookcommunicateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lixia
 * @since 2023-02-03
 */
@Service
public class BookcommunicateImpl extends ServiceImpl<BookcommunicateMapper, Bookcommunicate> implements BookcommunicateService {
    @Autowired
    BookcommunicateMapper bookcommunicateMapper;
    @Override
    public List<BookcommunicateExtend> bookcommunicateByBookID(int ID) {
        return bookcommunicateMapper.bookcommunicateByBookID(ID);
    }
}
