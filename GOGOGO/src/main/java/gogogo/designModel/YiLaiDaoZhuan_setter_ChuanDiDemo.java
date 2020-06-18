package gogogo.designModel;

/**
 * @Author: caoyunji
 * @Date: 2020/5/23 23:02
 */

interface OpenAndClose4 {
    void openAndClose();
}

interface TV4 {
    void TvOPenAndClose();
}

class OpenAndCloseIM4 implements OpenAndClose4 {
    private TV4 tv;

    public void setTv(TV4 tv) {
        this.tv = tv;
    }

    @Override
    public void openAndClose() {
        this.tv.TvOPenAndClose();
    }
}

class CHUangwei4 implements TV4 {
    @Override
    public void TvOPenAndClose() {
        System.out.println("创维OPEN");
    }
}


public class YiLaiDaoZhuan_setter_ChuanDiDemo {
    public static void main(String[] args) {

        OpenAndCloseIM4 openAndCloseIM1 = new OpenAndCloseIM4();
        openAndCloseIM1.setTv(new CHUangwei4());
        openAndCloseIM1.openAndClose();


    }

}
