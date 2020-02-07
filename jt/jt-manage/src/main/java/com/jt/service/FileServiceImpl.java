package com.jt.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.vo.ImageVO;

@Service
@PropertySource("classpath:/properties/jtimage.properties")
public class FileServiceImpl implements FileService {
	
	@Value("${image.localDirPath}")
	private String localDirPath;
	
	@Value("${image.urlPath}")
	private String urlPath;

	@Override
	public ImageVO uploadFile(MultipartFile uploadFile) {
		//定义imageVO对象
		ImageVO imageVO = null;
		//获取上传文件的文件名称
		String fileName = uploadFile.getOriginalFilename();
		//将所有文件名称统一成小写
		fileName = fileName.toLowerCase();
		
		if (!fileName.matches("^.+\\.(jpg|gif|png)$")) {
			return imageVO.fail();
		}
		
		try {
			
		BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
		
		int width = bufferedImage.getWidth();
		
		int height = bufferedImage.getHeight();
		
		if (width==0 || height==0) {
			return imageVO.fail();
		}
		
		String dataPath = new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
		
		String fileLocalPath = localDirPath+dataPath;
		
		File fileDir = new File(fileLocalPath);
		
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		
		String uuid = UUID.randomUUID().toString();
		int index = fileName.lastIndexOf(".");
		String type = fileName.substring(index);
		String uuidName = uuid+type;
		
		String reallocalName = fileLocalPath+uuidName;
		
		
			uploadFile.transferTo(new File(reallocalName));
			String url = urlPath+dataPath+uuidName;
			imageVO = ImageVO.success(url, width, height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imageVO;
	}

}
