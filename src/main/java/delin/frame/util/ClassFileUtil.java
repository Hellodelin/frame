package delin.frame.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import delin.frame.util.processor.ClassProcessor;

public class ClassFileUtil {
	/**
	 * 加载Jar文件并processor方法进行处理
	 * 
	 * @param path
	 * @param processor
	 * @throws IOException
	 */
	public static void loadJar(String path, ClassProcessor processor) throws IOException {
		try {
			ClassLoader loader = new URLClassLoader(new URL[] { new URL("file:" + path) });
			JarFile jarFile = importJar(path);
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				JarEntry jarEntry = (JarEntry) entries.nextElement();
				String entry = jarEntry.getName();
				String classPath=filePathToAllClassName(entry);
				if (classPath!=null) {
					processor.process(classPath, loader);
				}
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 导入Jar文件
	 * 
	 * @param path
	 *            jar文件所在路径
	 * @return
	 * @throws IOException
	 */
	public static JarFile importJar(String path) throws IOException {
		JarFile jarFile;
		try {
			jarFile = new JarFile(path);
		} catch (IOException e) {
			throw e;
		}
		return jarFile;
	}

	/**
	 * 把class文件路径转换成可加载全类名
	 * 
	 * @param classPath
	 * @return
	 */
	public static String filePathToAllClassName(String classPath) {
		if (!classPath.endsWith(".class")) {
			return null;
		}

		return classPath.replace("/", ".").substring(0, classPath.lastIndexOf("."));
	}

	/**
	 * 根据给定仓库返回一个ClassLoader
	 * 
	 * @param respository
	 * @return
	 * @throws MalformedURLException
	 */
	public static URLClassLoader getURLClassLoader(String respository) throws MalformedURLException {
		File file = new File(respository);
		URL url = null;
		url = file.toURI().toURL();
		URLClassLoader loader = new URLClassLoader(new URL[] { url });
		return loader;
	}
	/**
	 * 获得已知全类名的对象
	 * @param loader
	 * @param allClassName
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 */
	public static Object instanceOf(ClassLoader loader, String allClassName)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		return loader.loadClass(allClassName).getConstructor().newInstance();
	}
}
