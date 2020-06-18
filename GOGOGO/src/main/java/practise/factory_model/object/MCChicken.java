package practise.factory_model.object;

/**
 * @Author: caoyunji
 * @Date: 2020/5/27 17:38
 */
public class MCChicken implements IChicken {
    @Override
    public IChicken getChicken() {
        return new MCChicken();
    }
}
