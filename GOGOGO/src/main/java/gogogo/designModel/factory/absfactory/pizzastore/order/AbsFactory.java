package gogogo.designModel.factory.absfactory.pizzastore.order;


import gogogo.designModel.factory.absfactory.pizzastore.pizza.Pizza;

//һ�����󹤳�ģʽ�ĳ����(�ӿ�)
public interface AbsFactory {
    //������Ĺ��������� ����ʵ��
    public Pizza createPizza(String orderType);
}
