package com.rgzs.ggg.controller;


import com.rgzs.ggg.entity.vo.BookAddVo;
import com.rgzs.ggg.entity.vo.BookDeleteVo;
import com.rgzs.ggg.entity.vo.BookSelectVo;
import com.rgzs.ggg.entity.vo.BookUpdataVo;
import com.rgzs.ggg.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 高靖奇
 * @since 2022-08-25
 */
@Api("图书模块")
@RequestMapping("/books")
public interface BooksController {

    /**
     * 新增图书信息
     * @param bookAddVo
     * @param multipartFile
     * @param session
     * @return
     */
    @ApiOperation("新增图书信息")
    @PostMapping("/addbook")
    CommonResult addBook(BookAddVo bookAddVo, MultipartFile multipartFile,HttpSession session);

    /**
     * 删除图书信息
     * @param bookDeleteVos
     * @param session
     * @return
     */
    @ApiOperation("删除图书信息")
    @PostMapping("/deletebook")
    CommonResult deleteBook(@RequestBody List<BookDeleteVo> bookDeleteVos, HttpSession session);

    /**
     * 查找图书信息
     * @param bookSelectVo
     * @return
     */
    @ApiOperation("查找图书信息")
    @PostMapping("/selectbook")
    CommonResult selectBook(@RequestBody(required = false)BookSelectVo bookSelectVo);

    /**
     * 更新图书信息
     * @param bookUpdataVo
     * @param session
     * @return
     */
    @ApiOperation("更新用户信息")
    @PostMapping("/updatebook")
    CommonResult updataBook(@RequestBody BookUpdataVo bookUpdataVo, HttpSession session);

    /**
     * 借书
     * @param bookId
     * @param session
     * @return
     */
    @ApiOperation("借书")
    @GetMapping("/borrowbook")
    CommonResult borrowBook(@RequestParam String bookId,HttpSession session);

    /**
     * 还书
     * @param bookId
     * @param session
     * @return
     */
    @ApiOperation("还书")
    @GetMapping("/returnbook")
    CommonResult returnbook(@RequestParam String bookId,HttpSession session);

}

