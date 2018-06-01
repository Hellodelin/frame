package delin.frame.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FileUtil {
	/**
	 * 获取文件夹下满足filter条件的所有文件
	 * @param basic 搜索的根文件夹
	 * @param filter 文件需满足的条件
	 * @param result 接受文件结果
	 * @param deep 是否深度遍历全部子文件夹
	 * @throws IOException basic参数不是文件夹或basic路径不存在
	 */
	public static void listAllFiles(String basic,Predicate<File> filter,List<File> result,boolean deep) throws IOException{
		File root=new File(basic);
		if (!root.exists()||!root.isDirectory()) {
			throw new IOException("please input correct file path");
		}
		
		File[] childs=root.listFiles(f->filter.test(f)||(deep&&f.isDirectory()));
		
		Arrays.stream(childs).forEach(child->{
			if (child.isDirectory()) {
				try {
					listAllFiles(child.getAbsolutePath(),filter,result,deep);
				} catch (IOException e) {}
			}else {
				result.add(child);
			}
		});
	}
	/**
	 * 把file:开头的URL转化成文件路径
	 * @param url 
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String urlToFilePath(URL url,String charset) throws UnsupportedEncodingException {
		return URLDecoder.decode(url.getFile(), charset);
	}
	
	public static String removeExt(String pathOrName) {
		return pathOrName.substring(0, pathOrName.lastIndexOf('.'));
	}
}
