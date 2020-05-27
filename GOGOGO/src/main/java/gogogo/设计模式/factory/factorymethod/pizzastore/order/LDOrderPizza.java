package gogogo.设计模式.factory.factorymethod.pizzastore.order;

import gogogo.设计模式.factory.factorymethod.pizzastore.pizza.BJCheesePizza;
import gogogo.设计模式.factory.factorymethod.pizzastore.pizza.BJPepperPizza;
import gogogo.设计模式.factory.factorymethod.pizzastore.pizza.LDCheesePizza;
import gogogo.设计模式.factory.factorymethod.pizzastore.pizza.LDPepperPizza;
import gogogo.设计模式.factory.factorymethod.pizzastore.pizza.Pizza;

import java.util.concurrent.ThreadPoolExecutor;


public class LDOrderPizza extends OrderPizza {

	
	@Override
	Pizza createPizza(String orderType) {

		Pizza pizza = null;
		if(orderType.equals("cheese")) {
			pizza = new LDCheesePizza();
		} else if (orderType.equals("pepper")) {
			pizza = new LDPepperPizza();
		}
		// TODO Auto-generated method stub
		return pizza;
	}

}
