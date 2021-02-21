package com.my.mealsys.utils;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Iterator;

/**
 * base64转图片
 */
public class Base64Utils {

    /**
     * 根据base64判断图片类型
     * @param base64ImgData
     * @return
     */
    public static String checkImageBase64Format(String base64ImgData) {
        String[] imgTypes = {"jpg","png","jpeg"};
        try {
            byte[] bytes=new BASE64Decoder().decodeBuffer(base64ImgData);
            //不带类似data:image/jpg;base64,前缀的解析
            ImageInputStream imageInputstream = new MemoryCacheImageInputStream(new ByteArrayInputStream(bytes));
            //不使用磁盘缓存
            ImageIO.setUseCache(false);
            Iterator<ImageReader> it = ImageIO.getImageReaders(imageInputstream);
            if (it.hasNext()) {
                ImageReader imageReader = it.next();
                // 设置解码器的输入流
                imageReader.setInput(imageInputstream, true, true);
                // 图像文件格式后缀
                String imgType = imageReader.getFormatName().trim().toLowerCase();
                for (String type : imgTypes) {
                    if (type.equalsIgnoreCase(imgType)) {
                        return imgType;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }



    public static void convertBase64DataToImage(String base64ImgData, String filePath) throws IOException {
        Files.write(Paths.get(filePath), Base64.getDecoder().decode(base64ImgData), StandardOpenOption.CREATE);
    }
}
