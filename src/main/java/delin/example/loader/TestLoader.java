package delin.example.loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import delin.example.loader.plugins.Test;
import delin.util.ClassFileUtil;
import delin.util.FileUtil;

public class TestLoader {

	public static void main(String[] args) {
		loadPlugin();
	}
	
	public static void loadPlugin() {
		try {
			ClassLoader loader=ClassFileUtil.getURLClassLoader("E:\\STSWorkSpace\\frame\\src\\main\\java\\delin\\example\\loader");
			JarFile jf=ClassFileUtil.importJar("E:\\STSWorkSpace\\frame\\src\\main\\java\\delin\\example\\loader\\plugins\\plugin.jar");
			Enumeration<JarEntry> urls=jf.entries();
			HashMap<String,String> map=new HashMap<>();
			while(urls.hasMoreElements()) {
				JarEntry entry=urls.nextElement();
				String name=entry.getName();
				System.out.println(name);
				if (name.endsWith(".class")) {
					map.put(name.substring(name.lastIndexOf("/")+1, name.lastIndexOf(".")), ClassFileUtil.filePathToAllClassName(name));
				}
			}
			System.out.println(map);
			
			map.entrySet().stream().filter(e->!e.getKey().equals("Test")).forEach(entry->{
				System.out.println(entry);
				try {
					Test test=(Test)loader.loadClass(entry.getValue()).getConstructor().newInstance();
					test.test();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException
						| ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void loadJar() {
		try {
			//ClassFileUtil 测试 1
			JarFile jf=ClassFileUtil.importJar("E:\\STSWorkSpace\\frame\\src\\main\\java\\delin\\example\\loader\\testclass\\my.jar");
			Enumeration<JarEntry> entries=jf.entries();
			while(entries.hasMoreElements()) {
				JarEntry entry=entries.nextElement();
				String entryName=entry.getName();
				//META-INF/MANIFEST.MF
				//delin/example/loader/TestLoader.class
				//delin/example/loader/testclass/Test.class
				System.out.println(entryName);
				//获得输入某个文件的流
				InputStream in=jf.getInputStream(entry);
			}
			//ClassFileUtil 测试二
			ClassFileUtil.loadJar("E:\\STSWorkSpace\\frame\\src\\main\\java\\delin\\example\\loader\\testclass\\my.jar", (classPath,loader)->{
				System.out.println(classPath);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loadCurrentProjectClass() {
		ArrayList<File> files=new ArrayList<>();
		String pack="delin/example/loader";
		Enumeration<URL> dirs;
		
		try {
			ClassLoader loader=ClassFileUtil.getURLClassLoader("E:\\STSWorkSpace\\frame\\src\\main\\java\\delin\\example\\loader");
			dirs=Thread.currentThread().getContextClassLoader().getResources(pack);
			while(dirs.hasMoreElements()) {
				URL url=dirs.nextElement();
				String file=FileUtil.urlToFilePath(url, "UTF-8");
				FileUtil.listAllFiles(file, f->f.getName().endsWith(".class"), files,true);
				files.stream().map(File::getName).map(FileUtil::removeExt).forEach(System.out::println);
			}
		} catch (IOException | IllegalArgumentException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
