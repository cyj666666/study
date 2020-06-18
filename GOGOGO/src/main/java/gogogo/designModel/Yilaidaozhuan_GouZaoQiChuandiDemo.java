package gogogo.designModel;

/**
 * @Author: caoyunji
 * @Date: 2020/5/23 23:02
 */

interface OpenAndClose3 {
    void openAndClose();
}

interface TV3 {
    void TvOPenAndClose();
}

class OpenAndCloseIM3 implements OpenAndClose3 {
    private TV3 tv;

    public OpenAndCloseIM3(TV3 tv) {
        this.tv = tv;
    }

    @Override
    public void openAndClose() {
        this.tv.TvOPenAndClose();
    }
}

class CHUangwei3 implements TV3 {
    @Override
    public void TvOPenAndClose() {
        System.out.println("创维OPEN");
    }
}

class XIMENZI implements TV3 {
    @Override
    public void TvOPenAndClose() {
        System.out.println("西门子OPEN");
    }
}


public class Yilaidaozhuan_GouZaoQiChuandiDemo {
    public static void main(String[] args) {

        OpenAndCloseIM3 openAndCloseIM1 = new OpenAndCloseIM3(new CHUangwei3());
        OpenAndCloseIM3 openAndCloseIM2 = new OpenAndCloseIM3(new XIMENZI());
        openAndCloseIM1.openAndClose();
        openAndCloseIM2.openAndClose();


    }

}
