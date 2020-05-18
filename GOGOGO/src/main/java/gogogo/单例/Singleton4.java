package gogogo.单例;

public class Singleton4 {
    private static Singleton4 INSTANCE;

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
//        if (INSTANCE == null) {
//            synchronized (Singleton4.class) {
//                if (INSTANCE == null) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    INSTANCE = new Singleton4();
//                }
//            }
//        }
        if (INSTANCE == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton4();
        }

        return INSTANCE;
    }

}
