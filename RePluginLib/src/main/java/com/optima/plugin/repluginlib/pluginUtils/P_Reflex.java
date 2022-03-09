package com.optima.plugin.repluginlib.pluginUtils;

import com.qihoo360.replugin.RePlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * create by wma
 * on 2020/8/20 0020
 * <p>
 * 插件与宿主之间相互调用，宿主与插件之间相互调用
 * <p>
 * 使用方法：
 * （1）查看需要调用类的构造方法，查看需要调用类的对象改如何创建
 * （2）如果只需要无参构造创建对象，那么直接调用{@link P_Reflex#createObject(Constructor, Object...)}两个参数传 null。
 * 如果需要有参构造创建对象，那么直接调用{@link P_Reflex#getConstructor(Class[])}通过参数类型调用不同的构造方法得到构造方法，
 * 获得构造方法对象后，再执行{@link P_Reflex#createObject(Constructor, Object...)}将构造方法对象和构造方法所需要的的参数传入，创建对象
 * （3）获得需要使用类的对象后，
 * 可以通过{@link P_Reflex#getMethod(String, Class[])}获取对应的方法，然后通过{@link P_Reflex#excuse(Object, Method, Object...)}执行该方法
 * 可以通过{@link P_Reflex#getFieldValue(Object, String)}获取对应属性的值，然后通过{@link P_Reflex#setFieldValue(Object, String, Object)}修改该属性的值
 * （4）访问静态方法和属性不需要对象，所以直接使用{@link P_Reflex#excuseStatic(Method, Object...)}、{@link P_Reflex#getStaticFieldValue(String)}、{@link P_Reflex#setStaticFieldValue(String, Object)}
 */
public class P_Reflex {


    private ClassLoader classLoader;
    private String className;

    /**
     * 插件之间相互调用，宿主调用插件使用带插件名字的构造方法构建对象
     *
     * @param pluginName
     * @param className
     */
    public P_Reflex(String pluginName, String className) {
        this.className = className;
        this.classLoader = RePlugin.fetchClassLoader(pluginName);
    }


    /**
     * 插件调用宿主，使用带有宿主Classloader的构造方法构建对象
     *
     * @param hostClassLoader
     * @param className
     */
    public P_Reflex(ClassLoader hostClassLoader, String className) {
        this.classLoader = classLoader;
        this.className = className;
    }

    /**
     * 获取方法
     *
     * @param methodName 方法名称
     * @param cls        方法参数类型
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    public Method getMethod(String methodName, Class<?>... cls) throws ClassNotFoundException, NoSuchMethodException {
        Method method;
        Class<?> aClass = classLoader.loadClass(className);
        if (cls == null || cls.length <= 0) {
            method = aClass.getDeclaredMethod(methodName);
        } else {
            method = aClass.getDeclaredMethod(methodName, cls);
        }
        boolean accessible = method.isAccessible();
        if (!accessible) {
            method.setAccessible(true);
        }
        return method;
    }

    /**
     * 通过无参构造方法获取对象
     *
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private Object getObject() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Object obj;
        Class<?> aClass = classLoader.loadClass(className);
        obj = aClass.newInstance();
        return obj;
    }


    /**
     * 执行方法
     *
     * @param o      调用该方法的对象 可以为空（直接使用无参构造创建的对象），可以以这种方式获取{@link P_Reflex#createObject(Constructor, Object...)}（指定构造参数创建的对象）
     * @param method
     * @param args   方法所需要的参数
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     */
    public Object excuse(Object o, Method method, Object... args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        Object object;
        o = o == null ? getObject() : o;
        if (args == null || args.length <= 0) {
            object = method.invoke(o);
        } else {
            object = method.invoke(o, args);
        }
        return object;
    }

    /**
     * 执行静态方法
     *
     * @param method
     * @param args
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object excuseStatic(Method method, Object... args) throws InvocationTargetException, IllegalAccessException {
        Object object;
        object = method.invoke(null, args);
        return object;
    }


    /**
     * 获取构造方法
     *
     * @param cls 构造方法的参数类型
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    public Constructor<?> getConstructor(Class<?>... cls) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> aClass = classLoader.loadClass(className);
        Constructor<?> constructor;
        if (cls == null || cls.length <= 0) {
            constructor = aClass.getConstructor();
        } else {
            constructor = aClass.getConstructor(cls);
        }
        boolean accessible = constructor.isAccessible();
        if (!accessible) {
            constructor.setAccessible(true);
        }
        return constructor;
    }


    /**
     * 创建对象，根据构造方法创建对象（如果两个参数都为空，那么就是通过无参构造创建对象）
     *
     * @param constructor 构造方法，获取方式{@link P_Reflex#getConstructor(Class[])}
     * @param args        构造方法需要的参数
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public Object createObject(Constructor<?> constructor, Object... args) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        if (constructor == null || args == null || args.length <= 0) {
            return getObject();
        } else {
            return constructor.newInstance(args);
        }
    }


    /**
     * 获取属性
     *
     * @param fieldName 属性名字
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    private Field getField(String fieldName) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> aClass = classLoader.loadClass(className);
        Field field = aClass.getDeclaredField(fieldName);
        boolean accessible = field.isAccessible();
        if (!accessible) {
            field.setAccessible(true);
        }
        return field;
    }

    /**
     * 获取属性值
     *
     * @param o         调用该属性的对象 可以为空（直接使用无参构造创建的对象），可以以这种方式获取{@link P_Reflex#createObject(Constructor, Object...)}（指定构造参数创建的对象）
     * @param fieldName 属性名字
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public Object getFieldValue(Object o, String fieldName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        Field field = getField(fieldName);
        if (o == null) {
            return field.get(getObject());
        } else {
            return field.get(o);
        }
    }

    /**
     * @param o          调用该属性的对象 可以为空（直接使用无参构造创建的对象），可以以这种方式获取{@link P_Reflex#createObject(Constructor, Object...)}（指定构造参数创建的对象）
     * @param fieldName  属性名字
     * @param fieldValue 属性值
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public void setFieldValue(Object o, String fieldName, Object fieldValue) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        Field field = getField(fieldName);
        if (o == null) {
            field.set(getObject(), fieldValue);
        } else {
            field.set(o, fieldValue);
        }
    }


    /**
     * 获取静态属性的值
     *
     * @param fieldName
     * @return
     * @throws NoSuchFieldException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public Object getStaticFieldValue(String fieldName) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        Field field = getField(fieldName);
        return field.get(null);

    }

    /**
     * 设置静态属性
     *
     * @param fieldName
     * @param fieldValue
     * @throws NoSuchFieldException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public void setStaticFieldValue(String fieldName, Object fieldValue) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        Field field = getField(fieldName);
        field.set(null, fieldValue);
    }
}
