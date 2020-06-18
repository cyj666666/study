package gogogo.designModel.factory.absfactory.pizzastore.order;


import gogogo.designModel.factory.absfactory.pizzastore.pizza.BJPepperPizza;
import gogogo.designModel.factory.absfactory.pizzastore.pizza.Pizza;
import gogogo.designModel.factory.absfactory.pizzastore.pizza.BJCheesePizza;

//???????????
public class BJFactory implements AbsFactory {

	@Override
	public Pizza createPizza(String orderType) {
		System.out.println("~????????????~");
		// TODO Auto-generated method stub
		Pizza pizza = null;
		if(orderType.equals("cheese")) {
			pizza = new BJCheesePizza();
		} else if (orderType.equals("pepper")){
			pizza = new BJPepperPizza();
		}
		return pizza;
	}

}
