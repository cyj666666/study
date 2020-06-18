package practise.factory_model.factory;

import practise.factory_model.object.IChicken;
import practise.factory_model.object.ICoke;

/**
 * @Author: caoyunji
 * @Date: 2020/5/27 16:09
 */
public interface IFoodFactory {

    public IChicken createChicken();

    public ICoke createCoke();


}
