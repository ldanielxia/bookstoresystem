package com.team15.commonmybatis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookExtend extends Book {
    private  String imageUrl;
    private int isMain;
}
