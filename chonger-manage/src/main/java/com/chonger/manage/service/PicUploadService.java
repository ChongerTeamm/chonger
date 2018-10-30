package com.chonger.manage.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chonger.common.vo.PicUploadResult;

@Service
public class PicUploadService {
	
	public PicUploadResult upload(MultipartFile uploadFile) {
		/*
		 * 1 获取文件名称
		 * 2 判断合法 后缀jpg,png,gif
		 * 3 木马的try catch
		 * 4 生成路径:dir=/image/yyyy/MM/dd/
		 * 5 生成url访问地址:http://image.chonger.com+dir
		 * 6 图片重命名:currentTime+3位随机数
		 * 7存储图片,返回数据
		 */
		PicUploadResult result=new PicUploadResult();
		
		//文件名称的后缀判断合法
		String fileOldName=uploadFile.getOriginalFilename();
		//后缀 .jpg
		String extName=fileOldName.substring(fileOldName.lastIndexOf("."));
		if(!extName.matches("^.(jpg|png|gif)$")){
			result.setError(1);
			return result;
		}
		try{//利用java的bufferedImage对象保存流,
			//如果是图片数据,有宽高,非图片数据,没有宽高的数据
			BufferedImage bufImage = ImageIO.read(uploadFile.getInputStream());
			result.setHeight(bufImage.getHeight()+"");//执行成功没异常,说明数据有高的数据
			result.setWidth(bufImage.getWidth()+"");
			
			//生成公用路径 dir /image/2018/10/21/
			String dir="/image/"+new SimpleDateFormat("yyyy/MM/dd").
					format(new Date())+"/";
			//磁盘路径
			String path="D:/chonger-upload/"+dir;
			//url路径
			String urlPath="http://image.chonger.com/"+dir;
			//fiel存盘,生成磁盘的文件夹结构
			File _dir=new File(path);
			if(!_dir.exists()){//文件夹结构不存在磁盘需要输出
				_dir.mkdirs();
			}
			//重命名文件名称
			String fileName=System.currentTimeMillis()+""
			+RandomUtils.nextInt(100, 999)+extName;
			
			//拼接url访问地址
			//http://image.chonger.com/image/2018/10/21/128937612973.jpg
			result.setUrl(urlPath+fileName);
			//输出文件到目录
			//d:/chonger-UPLOAD/image/2018/10/21/128937612973.jpg
			uploadFile.transferTo(new File(path+fileName));
			return result;
		}catch(Exception e){
			e.printStackTrace();
			result.setError(1);
			return result;
		}
	}

}
