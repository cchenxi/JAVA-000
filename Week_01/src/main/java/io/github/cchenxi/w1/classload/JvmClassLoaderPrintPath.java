package io.github.cchenxi.w1.classload;

import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * 打印当前ClassLoader加载了哪些jar
 * Date: 2021-02-08
 *
 * @author chenxi
 */
public class JvmClassLoaderPrintPath {
    public static void main(String[] args) {
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");

        for (URL url : urls) {
            System.out.println("==>" + url.toExternalForm());
        }

        // 扩展类加载器
        printClassloader("扩展类加载器", JvmClassLoaderPrintPath.class.getClassLoader().getParent());
        // 应用类加载器
        printClassloader("应用类加载器", JvmClassLoaderPrintPath.class.getClassLoader());
    }

    public static void printClassloader(String name, ClassLoader cl) {
        if (cl != null) {
            System.out.println(name + " Classloader -> " + cl.toString());
            printURLForClassLoader(cl);
        } else {
            System.out.println(name + " Classloader -> null");
        }
    }

    public static void printURLForClassLoader(ClassLoader cl) {
        Object ucp = insightField(cl, "ucp");
        Object path = insightField(ucp, "path");
        ArrayList ps = (ArrayList) path;
        for (Object p : ps) {
            System.out.println("==> " + p.toString());
        }
    }

    private static Object insightField(Object obj, String fName) {
        try {
            Field f = null;
            if (obj instanceof URLClassLoader) {
                f = URLClassLoader.class.getDeclaredField(fName);
            } else {
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
