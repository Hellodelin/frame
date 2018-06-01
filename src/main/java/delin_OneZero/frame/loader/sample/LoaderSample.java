package delin_OneZero.frame.loader.sample;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import delin.util.FileUtil;

public class LoaderSample {
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		String path = "D:\\Users\\李德林\\Desktop\\my.jar";
		try {
			@SuppressWarnings("resource")
			JarFile jarFile = new JarFile(path);
			@SuppressWarnings("resource")
			ClassLoader loader = new URLClassLoader(new URL[] { new URL("file:" + path) });
			Enumeration<JarEntry> entries = jarFile.entries();
			@SuppressWarnings("rawtypes")
			HashMap<String,Class> map=new HashMap<>();
			while (entries.hasMoreElements()) {
				JarEntry jarEntry = (JarEntry) entries.nextElement();
				String name = jarEntry.getName();
				System.out.println(name);
				if (name.endsWith(".class")) {
					String classPath=name.replace("/", ".").substring(0, name.lastIndexOf("."));
					map.put(classPath, loader.loadClass(classPath));
					if (name.contains("String")) {
						((delin_OneZero.frame.loader.sample.test.String)map.get(classPath).getConstructor().newInstance()).test();;
					}
				}
				InputStream input=jarFile.getInputStream(jarEntry);
				int available;
				byte[] bytes=new byte[1024];
				while((available=input.read(bytes))>0) {
					System.out.println(new String(bytes,0,available));
				}
			}
			
		} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File("E:\\STSWorkSpace\\frame\\src\\main\\java\\delin_OneZero\\frame\\loader\\sample\\test");
		URL url = null;
		try {
			url = file.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("resource")
		URLClassLoader loader = new URLClassLoader(new URL[] { url });
		try {
			delin_OneZero.frame.loader.sample.test.String str = (delin_OneZero.frame.loader.sample.test.String) loader
					.loadClass("delin_OneZero.frame.loader.sample.test.String").getConstructor().newInstance();
			str.test();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(System.getProperty("java.class.path"));
		
	}
}
