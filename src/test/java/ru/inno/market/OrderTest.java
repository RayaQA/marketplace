package ru.inno.market;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.inno.market.core.MarketService;
import ru.inno.market.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    @DisplayName("Возвращение корректного ID созданного заказа")
    public void testCorrectIdWhenCreateOrder() {
        Client client = new Client(0, "Рая");
        Order order = new Order(0, client);
        assertEquals(0, order.getId());
    }

    @Test
    @DisplayName("Созданный товар добавляется в корзину")
    public void testCorrectItemAddedToCart() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
        order.addItem(item);
        assertTrue(order.getCart().containsKey(item));
    }

    @Test
    @DisplayName("После добавленния товара в корзину, количичество товаров в корзине увеличилось на 1")
    public void correctSizeCart() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
        order.addItem(item);
        assertEquals(order.getCart().size(), 1);
    }

    @Test
    @DisplayName("После добавленния товара в корзину, её сумма увеличилась на сумму стоимости товара")
    public void correctTotalPrice() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
        order.addItem(item);
        assertEquals(order.getTotalPrice(), 100000.00);
    }

    @Test
    @DisplayName("После добавленния второго товара в корзину, заказ дбавился, а не перезаписался")
    public void correctSizeCartTwo() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
        Item item2 = new Item(2, "Poco 5", Category.SMARTPHONES, 33000.00);
        order.addItem(item);
        order.addItem(item2);
        assertEquals(order.getCart().size(), 2);
    }

    @Test
    @DisplayName("Скидка применяется")
    public void applyDiscount() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
        order.addItem(item);
        order.applyDiscount(PromoCodes.FIRST_ORDER.getDiscount());
        assertTrue(order.isDiscountApplied());
    }

    @Test
    @DisplayName("Применяется парвильная скидка")
    public void applyCorrectDiscount() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
        order.addItem(item);
        order.applyDiscount(PromoCodes.FIRST_ORDER.getDiscount());
        assertEquals(80000.00, order.getTotalPrice());
    }

    @Test
    @DisplayName("Скидку можно применить только один раз")
    public void applyDiscountOnce() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
        order.addItem(item);
        order.applyDiscount(PromoCodes.FIRST_ORDER.getDiscount());
        order.applyDiscount(PromoCodes.HAPPY_HOUR.getDiscount());
        assertEquals(order.getTotalPrice(), 80000.00);
    }

    @Test
    @DisplayName("Получение правильного клиента при создании заказа")
    public void getClient() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        assertEquals(expectedClient, order.getClient());
    }

    @Test
    @DisplayName("Получение правильного id клиента при создании заказа")
    public void getClientId() {
        Client expectedClient = new Client(1, "Рая");
        Order order = new Order(0, expectedClient);
        assertEquals(1, order.getClient().getId());
    }
}




