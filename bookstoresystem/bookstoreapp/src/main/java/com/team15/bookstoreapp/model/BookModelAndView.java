package com.team15.bookstoreapp.model;

import com.team15.commonmybatis.model.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BookModelAndView {
    public Book book;
    public List<Bookimage> bookimageList;
    public Users user;
    public List<BookcommunicateExtend> bookcommunicateExtends;
    public Bookfavorites bookfavorites;
    public Orders orders;

}
