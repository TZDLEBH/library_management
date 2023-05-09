package com.rgzs.ggg.controller.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rgzs.ggg.controller.BooksController;
import com.rgzs.ggg.entity.BookLog;
import com.rgzs.ggg.entity.BookType;
import com.rgzs.ggg.entity.Books;
import com.rgzs.ggg.entity.User;
import com.rgzs.ggg.entity.vo.*;
import com.rgzs.ggg.enums.BookStatusEnum;
import com.rgzs.ggg.enums.DeleteEnum;
import com.rgzs.ggg.enums.PermissionEnum;
import com.rgzs.ggg.service.*;
import com.rgzs.ggg.utils.CommonResult;
import com.rgzs.ggg.utils.NumberUtils;
import com.rgzs.ggg.utils.OssUtils;
import com.rgzs.ggg.utils.ReturnConstant;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UnknownFormatConversionException;
import java.util.stream.Collectors;

import static com.rgzs.ggg.utils.Const.CURRENT_USER;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 高靖奇
 * @since 2022-08-24
 */

@Slf4j
@RestController
public class BooksControllerImpl implements BooksController {

    @Resource
    private BooksService booksService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private BookTypeService bookTypeService;
    @Resource
    private NumberUtils numberUtils;
    @Resource
    private OssUtils ossUtils;
    @Resource
    private UserService userService;
    @Resource
    private BookLogService bookLogService;

    @Override
    public CommonResult addBook(BookAddVo bookAddVo, MultipartFile multipartFile, HttpSession session) {
        boolean flag = permissionService.getPermissions((String) session.getAttribute(CURRENT_USER), PermissionEnum.ADDBOOK.getCode());
        if (flag){
            if (!StringUtils.isEmpty(bookAddVo)){
                Books books = new Books();
                //属性对拷
                BeanUtils.copyProperties(bookAddVo,books);
                //设置id
                books.setBookId(numberUtils.getSixteenRandomNumber()+"book");
                //设置分类
                QueryWrapper<BookType> bookTypeQueryWrapper = new QueryWrapper<>();
                bookTypeQueryWrapper.eq("book_type_name",bookAddVo.getBookTypeName()).eq("logic_deleted", DeleteEnum.EXIST.getCode());
                BookType bookType = bookTypeService.getOne(bookTypeQueryWrapper);
                books.setBookTypeId(bookType.getBookTypeId());
                //处理图片
                String url = ossUtils.uploadOssLocalFile(multipartFile);
                books.setBookPic(url);
                //处理时间
                Date addTime = new Date();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = format.format(addTime);
                books.setGmtCreated(date);

                boolean one = booksService.save(books);
                if (one){
                    return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200);
                }
                return CommonResult.error(ReturnConstant.UPDATE_BOOK_INFO_ERROR_CODE,ReturnConstant.UPDATE_BOOK_INFO_ERROR_MESSAGE);
            }
            return CommonResult.error(ReturnConstant.HTTP_RES_CODE_500,ReturnConstant.HTTP_RES_CODE_500_VALUE);
        }
        return CommonResult.error(ReturnConstant.NO_PERMISSION_CODE,ReturnConstant.NO_PERMISSION_MESSAGE);
    }

    @Override
    public CommonResult deleteBook(List<BookDeleteVo> bookDeleteVos, HttpSession session) {
        boolean flag = permissionService.getPermissions((String) session.getAttribute(CURRENT_USER),PermissionEnum.DELETEBOOK.getCode());
        if (flag){
            bookDeleteVos.stream().map(bookDeleteVo -> {
                Books books = new Books();
                books.setBookId(bookDeleteVo.getBookId());
                books.setLogicDeleted(DeleteEnum.DISAPEAR.getCode());
                //处理时间
                Date addTime = new Date();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = format.format(addTime);
                books.setGmtModified(date);

                booksService.removeById(books);
                return null;
            }).collect(Collectors.toList());
            return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200);
        }
        return CommonResult.error(ReturnConstant.NO_PERMISSION_CODE,ReturnConstant.NO_PERMISSION_MESSAGE);
    }

    @Override
    public CommonResult selectBook(BookSelectVo bookSelectVo) {
        QueryWrapper<Books> booksQueryWrapper = new QueryWrapper<>();
        booksQueryWrapper.eq("logic_deleted", DeleteEnum.EXIST.getCode());
        //图书id
        if (!StringUtils.isEmpty(bookSelectVo.getBookId())){
            booksQueryWrapper.and(wrapper->{
                wrapper.eq("book_id",bookSelectVo.getBookId());
            });
        }
        //图书名称
        if (!StringUtils.isEmpty(bookSelectVo.getBookPress())){
            booksQueryWrapper.and(wrapper->{
                wrapper.eq("book_press",bookSelectVo.getBookPress());
            });
        }
        //出版社
        if (!StringUtils.isEmpty(bookSelectVo.getBookName())){
            booksQueryWrapper.and(wrapper->{
                wrapper.eq("book_name",bookSelectVo.getBookName());
            });
        }
        //分类
        if (!StringUtils.isEmpty(bookSelectVo.getBookTypeName())){
            //通过分类名称查到分类id
            QueryWrapper<BookType> bookTypeQueryWrapper = new QueryWrapper<>();
            bookTypeQueryWrapper.eq("book_type_name",bookSelectVo.getBookTypeName()).eq("logic_deleted", DeleteEnum.EXIST.getCode());
            BookType bookType = bookTypeService.getOne(bookTypeQueryWrapper);
            String bookTypeId = bookType.getBookTypeId();

            booksQueryWrapper.and(wrapper->{
                wrapper.eq("book_type_id",bookTypeId);
            });
        }
        //价格区间
        if (!StringUtils.isEmpty(bookSelectVo.getHighPrice())){
            booksQueryWrapper.and(wrapper->{
                wrapper.le("book_price",bookSelectVo.getHighPrice());
            });
        }
        if (!StringUtils.isEmpty(bookSelectVo.getLowPrice())){
            booksQueryWrapper.and(wrapper->{
                wrapper.ge("book_price",bookSelectVo.getLowPrice());
            });
        }
        //图书状态
        if (!StringUtils.isEmpty(bookSelectVo.getBookStatus())){
            Integer code = BookStatusEnum.getcode(bookSelectVo.getBookStatus());
            booksQueryWrapper.and(wrapper->{
                wrapper.eq("book_status",code);
            });
        }

        List<Books> books = booksService.list(booksQueryWrapper);
        if (!StringUtils.isEmpty(books)){
            List<BooksInfoVo> booksInfoVoList = books.stream().map(book->{
                BooksInfoVo booksInfoVo = new BooksInfoVo();
                BeanUtils.copyProperties(book,booksInfoVo);
                //处理分类
                QueryWrapper<BookType> bookTypeQueryWrapper = new QueryWrapper<>();
                bookTypeQueryWrapper.eq("book_type_id",book.getBookTypeId());
                BookType bookType = bookTypeService.getOne(bookTypeQueryWrapper);
                booksInfoVo.setBookTypeName(bookType.getBookTypeName());
                //处理图书状态
                String massage = BookStatusEnum.getMassage(book.getBookStatus());
                booksInfoVo.setStatus(massage);

                return booksInfoVo;
            }).collect(Collectors.toList());

            return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200).put("BookInfo",booksInfoVoList);
        }
        return CommonResult.error(ReturnConstant.HTTP_RES_CODE_500,ReturnConstant.HTTP_RES_CODE_500_VALUE);
    }

    @Override
    public CommonResult updataBook(BookUpdataVo bookUpdataVo, HttpSession session) {
        boolean flag = permissionService.getPermissions((String) session.getAttribute(CURRENT_USER),PermissionEnum.UPDATABOOK.getCode());
        if (flag){
            Books books = new Books();
            BeanUtils.copyProperties(bookUpdataVo,books);
           /* if (!StringUtils.isEmpty(bookUpdataVo.getBookPrice())){
                books.setBookPrice(bookUpdataVo.getBookPrice());
            }
            if (!StringUtils.isEmpty(bookUpdataVo.getBookName())){
                books.setBookName(bookUpdataVo.getBookName());
            }
            if (!StringUtils.isEmpty(bookUpdataVo.getBookPress())){
                books.setBookPress(bookUpdataVo.getBookPress());
            }
            if (!StringUtils.isEmpty(bookUpdataVo.getBookTypeName())){
                //处理分类
                QueryWrapper<BookType> bookTypeQueryWrapper = new QueryWrapper<>();
                bookTypeQueryWrapper.eq("book_type_name",bookUpdataVo.getBookTypeName());
                BookType bookType = bookTypeService.getOne(bookTypeQueryWrapper);
                books.setBookTypeId(bookType.getBookTypeId());
            }
            books.setBookId(bookUpdataVo.getBookId());*/
            //处理时间
            Date addTime = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(addTime);
            books.setGmtModified(date);

            boolean one = booksService.updateById(books);
            if (one){
                return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200);
            }
            return CommonResult.error(ReturnConstant.HTTP_RES_CODE_500,ReturnConstant.HTTP_RES_CODE_500_VALUE);
        }
        return CommonResult.error(ReturnConstant.NO_PERMISSION_CODE,ReturnConstant.NO_PERMISSION_MESSAGE);
    }

    @Override
    public CommonResult borrowBook(String bookId, HttpSession session) {
        boolean flag = permissionService.getPermissions((String) session.getAttribute(CURRENT_USER),PermissionEnum.BORROWBOOK.getCode());
        if (flag){
            //通过书号查图书
            QueryWrapper<Books> booksQueryWrapper = new QueryWrapper<>();
            booksQueryWrapper.eq("book_id",bookId);
            Books book = booksService.getOne(booksQueryWrapper);
            //通过token查借书人信息
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            System.out.println(session.getAttribute(CURRENT_USER));
            userQueryWrapper.eq("token",session.getAttribute(CURRENT_USER));
            User user = userService.getOne(userQueryWrapper);
            //属性对拷
            BookLog bookLog = new BookLog();
            BeanUtils.copyProperties(book,bookLog);
            BeanUtils.copyProperties(user,bookLog);
            bookLog.setBookStatus(BookStatusEnum.DISAPEAR.getCode());
            //处理时间
            Date addTime = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(addTime);
            bookLog.setBorrowCreated(date);

            boolean one = bookLogService.save(bookLog);

            //修改图书状态
            book.setBookStatus(BookStatusEnum.DISAPEAR.getCode());
            //处理时间
            book.setGmtModified(date);
            boolean two = booksService.updateById(book);

            if (one && two){
                return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200).put("book_log",bookLog);
            }
            return CommonResult.error(ReturnConstant.HTTP_RES_CODE_500,ReturnConstant.HTTP_RES_CODE_500_VALUE);
        }
        return CommonResult.error(ReturnConstant.NO_PERMISSION_CODE,ReturnConstant.NO_PERMISSION_MESSAGE);
    }

    @Override
    public CommonResult returnbook(String bookId, HttpSession session) {
        boolean flag = permissionService.getPermissions((String) session.getAttribute(CURRENT_USER),PermissionEnum.RETURNBOOK.getCode());
        if (flag){
            //通过书号在book-log中查借书记录
            UpdateWrapper<BookLog> bookLogUpdateWrapper = new UpdateWrapper<>();
            bookLogUpdateWrapper.eq("book_id",bookId);
            BookLog bookLog = new BookLog();
            bookLog.setBookStatus(BookStatusEnum.EXIST.getCode());

            //处理时间
            Date addTime = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(addTime);
            bookLog.setBorrowEnd(date);

            boolean one = bookLogService.update(bookLog,bookLogUpdateWrapper);

            //通过书号改图书信息
            Books books = new Books();
            books.setBookId(bookId);

            books.setBookStatus(BookStatusEnum.EXIST.getCode());
            boolean two = booksService.updateById(books);

            if (one && two){
                return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200).put("book_log",bookLog);
            }
            return CommonResult.error(ReturnConstant.HTTP_RES_CODE_500,ReturnConstant.HTTP_RES_CODE_500_VALUE);
        }
        return CommonResult.error(ReturnConstant.NO_PERMISSION_CODE,ReturnConstant.NO_PERMISSION_MESSAGE);
    }
}

