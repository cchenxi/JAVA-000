package io.github.cchenxi.w1.homework;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * {@link HelloClassLoader}
 * Date: 2020-10-18
 *
 * @author chenxi
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Class c = new HelloClassLoader().findClass("Hello");
            Method helloMethod = c.getDeclaredMethod("hello");
            helloMethod.invoke(c.newInstance());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            URL classFileUrl = this.getClass().getClassLoader().getResource("Hello/Hello.xlass");
            byte[] bytes = IOUtils.toByteArray(classFileUrl);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new ClassNotFoundException();
    }
}
