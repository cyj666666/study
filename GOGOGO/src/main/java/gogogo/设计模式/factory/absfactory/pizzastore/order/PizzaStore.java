package gogogo.设计模式.factory.absfactory.pizzastore.order;

import gogogo.设计模式.factory.absfactory.pizzastore.order.LDFactory;

public class PizzaStore {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //new OrderPizza(new BJFactory());
        new OrderPizza(new LDFactory());
    }

}
