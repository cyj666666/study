/**
 * 饿汉式单例
 * 1、构造器私有化
 * <p>
 * 2、自己实例化 定义静态变量
 * <p>
 * 3、向外部提供实例
 */

public class Singleton1 {

    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }




}
