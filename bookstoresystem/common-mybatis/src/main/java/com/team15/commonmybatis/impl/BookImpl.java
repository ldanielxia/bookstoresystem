package com.team15.commonmybatis.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team15.commonmybatis.mapper.BookimageMapper;
import com.team15.commonmybatis.model.*;
import com.team15.commonmybatis.mapper.BookMapper;
import com.team15.commonmybatis.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnails;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author lixia
 * @since 2023-01-26
 */
@Service
public class BookImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BookimageMapper bookimageMapper;

    @Override
    public List<BookExtend> top10List() {
        List<BookExtend> list  = bookMapper.top10List();
        return list;
    }

    @Override
    public List<BookExtend> searchBook(Wrapper wrapper) {
        return bookMapper.searchBook(wrapper);
    }

    @Override
    public List<BookExtend> favoriteBook(Wrapper wrapper) {
        return bookMapper.favoriteBook(wrapper);
    }

    @Override
    public List<BookExtend> myUsedBook(Wrapper wrapper) {
        return bookMapper.myUsedBook(wrapper);
    }

    @Override
    public List<BookExtend> myOrdersBook(Wrapper wrapper) {
        return bookMapper.myOrdersBook(wrapper);
    }

    @Override
    public void addBook(BookFilesModelAndView bookFilesModelAndView) {
        Book book=bookFilesModelAndView.book;
        try {
            if (book.getId() + 1 != 0) {
                //update
                bookMapper.updateById(book);
                //delete all book images
                Integer[] ids=bookFilesModelAndView.getPictureIds();
                ArrayList arr=new ArrayList();
                QueryWrapper<Bookimage> queryWrapper=new QueryWrapper<>();
                queryWrapper.lambda().eq(Bookimage::getBookId,book.getId());
                List<Bookimage> mybookimageList=bookimageMapper.selectList(queryWrapper);
                if(mybookimageList.size()>0) {
                    for (int i = 0; i < mybookimageList.size(); i++) {
                        boolean flag=true;
                        for(int j=0;j<ids.length;j++){
                           if(mybookimageList.get(i).getId()==ids[j]){
                                   flag=false;
                                   break;
                           }
                        }
                        if(flag)
                           arr.add(mybookimageList.get(i));
                    }
                }
                for(int i=0;i<arr.size();i++){
                    bookimageMapper.deleteById(((Bookimage) arr.get(i)).getId());
                }
                int iCount=mybookimageList.size()-arr.size();
                //add book images
                List<Bookimage> bookimageList = new ArrayList<>();
                Bookimage bookimage = null;

                if(bookFilesModelAndView.getFileList().size()>0) {
                    int i=0;
                    for(MultipartFile file :  bookFilesModelAndView.getFileList()) {
                        if (file.getSize() == 0) {
                            continue;
                        }
                        String filePath = "d://web//img" ;
                        String fileName = thumbnail(file, filePath, book.getId().toString());
                        bookimage=new Bookimage();
                        bookimage.setImageUrl("img/"+fileName);
                        bookimage.setCreateDT(LocalDateTime.now());
                        bookimage.setBookId(book.getId());
                        bookimage.setFileName(file.getName());
                        if(iCount<=0){
                           if(i==0){
                            bookimage.setIsMain(1);
                           }else{
                            bookimage.setIsMain(0);
                           }
                        }else{
                            bookimage.setIsMain(0);
                        }

                        bookimageMapper.insert(bookimage);
                        i++;
                    }
                }
            } else {
                //add new object
                book.setId(null);
                LocalDateTime dateTime = LocalDateTime.now();
                book.setCeateDT(dateTime);
                // svae mybatis-plus 'insert return the primarykey
                bookMapper.insert(book);
                List<Bookimage> bookimageList = new ArrayList<>();
                Bookimage bookimage = null;
                if(bookFilesModelAndView.getFileList().size()>0) {
                    int i=0;
                    for(MultipartFile file :  bookFilesModelAndView.getFileList()) {
                        if (file.getSize() == 0) {
                            continue;
                        }
                        String filePath = "d://web//img" ;
                        String fileName = thumbnail(file, filePath, book.getId().toString());
                        bookimage=new Bookimage();
                        bookimage.setImageUrl("img/"+fileName);
                        bookimage.setCreateDT(LocalDateTime.now());
                        bookimage.setBookId(book.getId());
                        bookimage.setFileName(file.getName());
                        if(i==0){
                            bookimage.setIsMain(1);
                        }else{
                            bookimage.setIsMain(0);
                        }
                        bookimageMapper.insert(bookimage);
                        i++;
                    }
                }
            }
            //update
        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }
    }

    public static String thumbnail(MultipartFile multipartFile, String path, String bookID) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String originalFilename = multipartFile.getOriginalFilename();

        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fileName =bookID+ getStrUUID() + getFileExtension(multipartFile);
        try {
            String ff=path+ File.separator + fileName;
            Thumbnails.of(multipartFile.getInputStream()).scale(0.3f)
                    .outputQuality(0.15f)
                    .outputFormat(ext)
                    .toFile(ff);
        } catch (IOException e) {
            System.out.print(e.getMessage());
            throw  e;
        }
        return fileName;
    }

    private static String getFileExtension(MultipartFile cFile) {
        String originalFileName = cFile.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }
    public static String getStrUUID(){
        Random random = new Random();
        int end = random.nextInt(999);
        String endStr =String.format("%03d", end);
        return "A"+UUID.randomUUID().toString().replace("-", "")+endStr;
    }
}
