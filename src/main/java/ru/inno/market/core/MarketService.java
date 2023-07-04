package ru.inno.market.core;

import ru.inno.market.model.*;

import java.util.HashMap;
import java.util.Map;

public class MarketService {

    private int orderCounter;//счетчик заказов
    private Map<Integer, Order> orders;// список заказов: номер заказа и сам заказ

    public MarketService() {
        orderCounter = 0;
        orders = new HashMap<>();
    }

    public int createOrderFor(Client client){
        int id = ++orderCounter;//сначала надо увеличить четчик, а потом присвоить номер заказа
        Order order = new Order(id, client);// создание заказа
        orders.put(id, order);//кладет в список заказ ключ - id? знчение - заказ

        return order.getId();
    }

    public void addItemToOrder(Item item, int orderId ){ //добавление товара в заказ, принимает товар и номер заказа
        orders.get(orderId).addItem(item);// положить в заказ позицию
    }
    public double applyDiscountForOrder(int orderId, PromoCodes codes){
        Order order = orders.get(orderId);
        order.applyDiscount(codes.getDiscount());
        return order.getTotalPrice();
    }

    public Order getOrderInfo(int id) {
        return orders.get(id);
    }
}

