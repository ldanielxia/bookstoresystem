package com.team15.commonmybatis.model;

import com.team15.commonmybatis.model.Book;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class BookFilesModelAndView {
    public Book book;
     List<MultipartFile> fileList;
     Integer [] pictureIds;
}
