package org.xiaochun.common.util.file.test;
import org.junit.Test;
import org.xiaochun.common.util.file.FileReplace;

public class FileReplaceTest {
	@Test
	public void replaceTest(){
		FileReplace replace = new FileReplace("D:/java/spring_git_dir/IPTID/服务端/固城湖/code_new", "(\r\n|\r|\n|\n\r)", "\r\n");
		//FileReplace replace = new FileReplace("C:\\Users\\Administrator\\Desktop\\呵呵.txt", "a", "x");
		//FileReplace replace = new FileReplace("C:\\Users\\Administrator\\Desktop\\呵呵.txt", "(\r\n|\r|\n|\n\r)", "\r\n");
		replace.doReplace();
	}
}
