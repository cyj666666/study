package gogogo.singleton;

import java.io.IOException;
import java.util.Properties;

public class Singleton3 {
    public static final Singleton3 INSTANCE;

    private String name;

    /**
     * 同样是在类加载和初始化的时候初始化实例
     */
    static {
        try {
            Properties pro = new Properties();
            pro.load(Singleton3.class.getResourceAsStream("singleton/single.properties"));
            INSTANCE = new Singleton3(pro.getProperty("name"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private Singleton3(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Singleton3{" +
                "name='" + name + '\'' +
                '}';
    }

    ;


}
