package gogogo.设计模式.factory.absfactory.pizzastore.order;


import gogogo.设计模式.factory.absfactory.pizzastore.pizza.LDCheesePizza;
import gogogo.设计模式.factory.absfactory.pizzastore.pizza.LDPepperPizza;
import gogogo.设计模式.factory.absfactory.pizzastore.pizza.Pizza;


public class LDFactory implements AbsFactory {

	@Override
	public Pizza createPizza(String orderType) {
		System.out.println("~ʹ�õ��ǳ��󹤳�ģʽ~");
		Pizza pizza = null;
		if (orderType.equals("cheese")) {
			pizza = new LDCheesePizza();
		} else if (orderType.equals("pepper")) {
			pizza = new LDPepperPizza();
		}
		return pizza;
	}

}
