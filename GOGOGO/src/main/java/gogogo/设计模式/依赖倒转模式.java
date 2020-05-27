package gogogo.设计模式;

/**
 * @Author: caoyunji
 * @Date: 2020/5/23 22:32
 */
interface Revieve {
    void getInfo();
}

class A implements Revieve {
    @Override
    public void getInfo() {
        System.out.println("get call");
    }
}

class B implements Revieve {
    @Override
    public void getInfo() {
        System.out.println("get email");
    }
}

class People {

    void getinfo(Revieve revieve) {
        revieve.getInfo();
    }

}

public class 依赖倒转模式 {


    public static void main(String[] args) {
        People p = new People();
        p.getinfo(new A());
        p.getinfo(new B());
    }


}
