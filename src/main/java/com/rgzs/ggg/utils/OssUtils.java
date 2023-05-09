package com.rgzs.ggg.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */
@Component
public final class OssUtils {

    //读取配置文件内容
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String accessKeyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;
    @Resource
    private NumberUtils numberUtils;


    /**
     * 上传文件
     * @param multipartFile
     * @return
     */
    public String uploadOssLocalFile(MultipartFile multipartFile) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 获取上传文件输入流
            InputStream inputStream = multipartFile.getInputStream();
            //获取当前日期
            String datePath = simpleDateFormat.format(new Date());
            //随机生成六位数
            String number = numberUtils.getSixRandomNumber();
            System.out.println("number = " + number);
            //拼接成唯一文件名
            String fileneme = multipartFile.getOriginalFilename();
            System.out.println("fileneme = " + fileneme);
            String ext = fileneme.substring(fileneme.lastIndexOf("."));
            String newFileName = datePath + number + ext;
            System.out.println("newFileName = " + newFileName);
            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, newFileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //返回上传文件路径
            String url = "https://" + bucketName + "." + endpoint + "/" + newFileName;
            System.out.println("url = " + url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
