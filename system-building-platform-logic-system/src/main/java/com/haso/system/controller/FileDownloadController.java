package com.haso.system.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haso.common.exception.AppException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FileDownloadController {

	@GetMapping("/system/download")
	public void testDownload(HttpServletResponse response, @RequestParam(name="filePath") String filePath) {
		// 待下载文件名
		String[] pathArray = filePath.split("\\\\");
		String fileName = pathArray[pathArray.length - 1];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		byte[] buff = new byte[1024];
		// 创建缓冲输入流
		BufferedInputStream bis = null;
		OutputStream outputStream = null;

		try {
			outputStream = response.getOutputStream();

			// 这个路径为待下载文件的路径
			bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
			int position = bis.read(buff);

			// 通过while循环写入到指定了的文件夹中
			while (position != -1) {
				outputStream.write(buff, 0, position);
				outputStream.flush();
				
				position = bis.read(buff);
			}
			
		} catch (IOException e) {
			log.error("下载失败", e);
			throw new AppException("下载失败", e);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
