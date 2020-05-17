/**
 * 在内部类被加载和创建时，才会创建instance实例
 */
public class Singleton5 {
    private Singleton5() {
    }

    private static class Inner {
        private static final Singleton5 INSTANCE = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return Inner.INSTANCE;
    }


}
