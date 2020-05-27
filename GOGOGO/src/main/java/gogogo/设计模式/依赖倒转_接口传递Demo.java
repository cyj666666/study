package gogogo.设计模式;

/**
 * @Author: caoyunji
 * @Date: 2020/5/23 23:02
 */

interface OpenAndClose {
    void openAndClose(TV tv);
}

interface TV {
    void TvOPenAndClose();
}

class OpenAndCloseIM implements OpenAndClose {

    @Override
    public void openAndClose(TV tv) {
        tv.TvOPenAndClose();
    }
}

class CHUangwei implements TV {
    @Override
    public void TvOPenAndClose() {
        System.out.println("创维OPEN");
    }
}


public class 依赖倒转_接口传递Demo {
    public static void main(String[] args) {

        OpenAndCloseIM openAndCloseIM = new OpenAndCloseIM();
        openAndCloseIM.openAndClose(new CHUangwei());


    }

}
