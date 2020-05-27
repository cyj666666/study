package practise.工厂模式.factory;

import practise.工厂模式.object.IChicken;
import practise.工厂模式.object.ICoke;

/**
 * @Author: caoyunji
 * @Date: 2020/5/27 16:09
 */
public interface IFoodFactory {

    public IChicken createChicken();

    public ICoke createCoke();


}
