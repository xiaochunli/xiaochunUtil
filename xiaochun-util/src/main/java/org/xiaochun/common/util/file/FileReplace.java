package org.xiaochun.common.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileReplace {
	private String path;
	private String oldWords;
	private String newWords;
	private static Logger logger = LogManager.getLogger(FileReplace.class);
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOldWords() {
		return oldWords;
	}
	public void setOldWords(String oldWords) {
		this.oldWords = oldWords;
	}
	public String getNewWords() {
		return newWords;
	}
	public void setNewWords(String newWords) {
		this.newWords = newWords;
	}
	
	public FileReplace(String path,String oldWords,String newWords){
		this.path = path;
		this.oldWords = oldWords;
		this.newWords = newWords;
	}
	
	private void doReplacePath(String path){
		File pathFile = new File(path);
		doReplacePath(pathFile);
	}
	
	private void doReplacePath(File pathFile){
		logger.info("开始替换 [" + pathFile.getAbsolutePath() + "]");
		if(! pathFile.exists()){
			logger.info("文件不存在 [" + pathFile.getAbsolutePath() + "]");
			return;
		}
		if(pathFile.isFile()){
			this.doReplaceFile(pathFile);
		}else if(pathFile.isDirectory()){
			File[] files = pathFile.listFiles();
			if(files != null && files.length > 0){
				for(File cFile : files){
					this.doReplacePath(cFile);
				}
			}
		}
		logger.info("替换结束 [" + pathFile.getAbsolutePath() + "]");
	}
	
	public void doReplace(){
		this.doReplacePath(this.path);
	}
	
	public void doReplaceFile(File file){
		if(file == null || ! file.exists()){
			return;
		}
		InputStream inps = null;
		Writer writer = null;
		try {
			inps = new FileInputStream(file);
			StringBuilder sb = new StringBuilder();
			byte[] b = new byte[2048];
			String s = null;
			while(inps.read(b) != -1){
				s = new String(b).replace(this.oldWords, this.newWords);
				sb.append(s);
				s = null;
			}
			
			//write back
			writer = new FileWriter(file, false);
			writer.write(sb.toString());
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(inps != null){
				try {
					inps.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

