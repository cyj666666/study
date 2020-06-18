package practise.factory_model.factory;

import practise.factory_model.factory.IFoodFactory;
import practise.factory_model.object.IChicken;
import practise.factory_model.object.ICoke;

/**
 * @Author: caoyunji
 * @Date: 2020/5/27 16:11
 */
public class KFCFoodFactory implements IFoodFactory {

    @Override
    public IChicken createChicken() {
        return null;
    }

    @Override
    public ICoke createCoke() {
        return null;
    }
}
