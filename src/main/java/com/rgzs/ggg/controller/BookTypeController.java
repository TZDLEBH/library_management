package com.rgzs.ggg.controller;


import com.rgzs.ggg.entity.vo.BookTypeAddVo;
import com.rgzs.ggg.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 高靖奇
 * @since 2022-08-25
 */
@Api("图书分类模块")
@RequestMapping("/book-type")
public interface BookTypeController {

    /**
     * 新增图书分类
     * @param bookTypeAddVos
     * @param session
     * @return
     */
    @ApiOperation("新增图书分类")
    @GetMapping("/addbooktype")
    CommonResult addBookType(@RequestBody List<BookTypeAddVo> bookTypeAddVos, HttpSession session);

    /**
     * 查找所有图书分类
     * @return
     */
    @ApiOperation("查找所有图书分类")
    @GetMapping("/selectallbooktype")
    CommonResult selectAllBookType();

    /**
     * 删除图书分类
     * @param typeId
     * @param session
     * @return
     */
    @ApiOperation("删除图书分类")
    @GetMapping("/deletebooktype")
    CommonResult deleteBookType(@RequestParam String typeId,HttpSession session);
}

