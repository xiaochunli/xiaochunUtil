package org.xiaochun.common.util.file.test;
import org.junit.Test;
import org.xiaochun.common.util.file.FileReplace;

public class FileReplaceTest {
	@Test
	public void replaceTest(){
		FileReplace replace = new FileReplace("D:/java/spring_git_dir/IPTID/服务端/固城湖/code_new", "\n", "\r\n");
		replace.doReplace();
	}
}
