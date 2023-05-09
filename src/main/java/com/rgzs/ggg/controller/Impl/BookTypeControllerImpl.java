package com.rgzs.ggg.controller.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rgzs.ggg.controller.BookTypeController;
import com.rgzs.ggg.entity.BookType;
import com.rgzs.ggg.entity.vo.BookTypeAddVo;
import com.rgzs.ggg.entity.vo.BookTypeInfoVo;
import com.rgzs.ggg.enums.DeleteEnum;
import com.rgzs.ggg.enums.PermissionEnum;
import com.rgzs.ggg.service.BookTypeService;
import com.rgzs.ggg.service.PermissionService;
import com.rgzs.ggg.utils.CommonResult;
import com.rgzs.ggg.utils.NumberUtils;
import com.rgzs.ggg.utils.ReturnConstant;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
public class BookTypeControllerImpl implements BookTypeController {

    @Resource
    private BookTypeService bookTypeService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private NumberUtils numberUtils;

    @Override
    public CommonResult addBookType(List<BookTypeAddVo> bookTypeAddVos, HttpSession session) {
        boolean flag = permissionService.getPermissions((String) session.getAttribute(CURRENT_USER), PermissionEnum.ADDBOOKTYPE.getCode());
        if (flag){
            bookTypeAddVos.stream().map(bookTypeAddVo -> {
                //保证数据唯一性
                QueryWrapper<BookType> bookTypeQueryWrapper = new QueryWrapper<>();
                bookTypeQueryWrapper.eq("book_type_name",bookTypeAddVo.getBookTypeName()).eq("logic_deleted", DeleteEnum.EXIST.getCode());
                Integer count = bookTypeService.count(bookTypeQueryWrapper);
                if (count > 0){
                    return CommonResult.error(ReturnConstant.HAD_BOOK_TYPE_NAME_ERROR_CODE,ReturnConstant.HAD_BOOK_TYPE_NAME_ERROR_MESSAGE);
                }
                //添加图书分类
                BookType bookType = new BookType();
                bookType.setBookTypeName(bookTypeAddVo.getBookTypeName());
                //生成id
                String typeId = numberUtils.getSixteenRandomNumber()+"type";
                bookType.setBookTypeId(typeId);
                //处理时间
                Date addTime = new Date();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = format.format(addTime);
                bookType.setGmtCreated(date);

                bookTypeService.save(bookType);
                return  null;
            }).collect(Collectors.toList());

            return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200);
        }
        return CommonResult.error(ReturnConstant.NO_PERMISSION_CODE,ReturnConstant.NO_PERMISSION_MESSAGE);
    }

    @Override
    public CommonResult selectAllBookType() {
        QueryWrapper<BookType> bookTypeQueryWrapper = new QueryWrapper<>();
        bookTypeQueryWrapper.eq("logic_deleted", DeleteEnum.EXIST.getCode());

        List<BookType> bookTypes = bookTypeService.list(bookTypeQueryWrapper);

        List<BookTypeInfoVo> bookTypeInfoVos = bookTypes.stream().map(bookType -> {
            BookTypeInfoVo bookTypeInfoVo = new BookTypeInfoVo();
            //属性对拷
            BeanUtils.copyProperties(bookType,bookTypeInfoVo);

            return bookTypeInfoVo;
        }).collect(Collectors.toList());

        if (!StringUtils.isEmpty(bookTypeInfoVos)){
            return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200).put("bookTypeInfo",bookTypeInfoVos);
        }
        return CommonResult.error(ReturnConstant.HTTP_RES_CODE_500,ReturnConstant.HTTP_RES_CODE_500_VALUE);
    }

    @Override
    public CommonResult deleteBookType(String typeId, HttpSession session) {
        boolean flag = permissionService.getPermissions((String) session.getAttribute(CURRENT_USER),PermissionEnum.DELETEBOOKTYPE.getCode());
        if (flag){
            BookType bookType = new BookType();
            bookType.setBookTypeId(typeId);
            bookType.setLogicDeleted(DeleteEnum.DISAPEAR.getCode());
            //处理时间
            Date addTime = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(addTime);
            bookType.setGmtModified(date);

            boolean one = bookTypeService.updateById(bookType);
            if (one){
                return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200);
            }
            return CommonResult.error(ReturnConstant.HTTP_RES_CODE_500,ReturnConstant.HTTP_RES_CODE_500_VALUE);
        }
        return CommonResult.error(ReturnConstant.NO_PERMISSION_CODE,ReturnConstant.NO_PERMISSION_MESSAGE);
    }
}

