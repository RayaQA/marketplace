package ru.inno.market;

import org.junit.jupiter.api.Test;
import ru.inno.market.core.MarketService;
import ru.inno.market.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test
    // проверка получения правильного id заказа
    public void testCorrectIdWhenCreateOrder() {
        Client client = new Client(0, "Рая");
        Order order = new Order(0, client);

        int actual = order.getId();
        int expected = 0;

        assertEquals(actual, expected);
    }
//    @Test
//    public void testGetId() {
//        Client expectedClient = new Client(0, "Рая");
//        Order order = new Order(0, expectedClient);
//        assertEquals(order.getId(), 0);
//    }

//    @Test
//    public void testCreateOrder() {
//        Client expectedClient = new Client(0, "Рая");
//        Order order = new Order(0, expectedClient);
//
//        assertEquals(expectedClient, order.getClient());
//        assertEquals(order.getTotalPrice(), 0, "Проверка цены");
//        //assertEquals(order.isDiscountApplied(), false);
//        assertFalse(order.isDiscountApplied());
//        assertEquals(order.getCart().size(), 0);
//    }

    @Test
    //проверка добавления конкретоного товара (заказа) в корзину
    public void testCorrectItemAddedToCart() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
        order.addItem(item);
        assertTrue(order.getCart().containsKey(item));// проверяем, что именно этот товар был добавлен в корзину
    }

    @Test
    //проверка того,что после добавленния одного товара в корзину, количичество товаров в корзине увеличилось на 1
    public void testCorrectSizeCart() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
        order.addItem(item);
        assertEquals(order.getCart().size(), 1);
    }

    @Test
    //проверка того,что после добавленния одного товара в корзину, её сумма увеличилась на сумму стоимости товара
    public void testCorrectTotalPrice() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
        order.addItem(item);
        assertEquals(order.getTotalPrice(), 100000.00);
    }
    @Test
    //проверка того,что после добавленния второго товара в корзину, заказ дбавился, а не перезаписался
    public void testCorrectSizeCartTwo() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
        Item item2 = new Item(2, "Poco 5", Category.SMARTPHONES, 33000.00);
        order.addItem(item);
        order.addItem(item2);
        assertEquals(order.getCart().size(), 2);
    }

    @Test
    //проверка того, что скидка применяется вообще
    public void testApplyDiscount() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);

        order.addItem(item);
        order.applyDiscount(PromoCodes.FIRST_ORDER.getDiscount());

        assertTrue(order.isDiscountApplied());
    }

    @Test
    //проверка того, что применяется парвильная скидка
    public void testApplyCorrectDiscount() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);

        order.addItem(item);
        order.applyDiscount(PromoCodes.FIRST_ORDER.getDiscount());

        assertEquals(80000.00, order.getTotalPrice());
    }

    @Test
    // проверка того, что скидку можно применить один раз
    public void testApplyDiscountOnce() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
        order.addItem(item);

        order.applyDiscount(PromoCodes.FIRST_ORDER.getDiscount());
        order.applyDiscount(PromoCodes.FIRST_ORDER.getDiscount());

        assertEquals(order.getTotalPrice(), 80000.00);
    }


    @Test
    public void testGetClient() {
        Client expectedClient = new Client(0, "Рая");
        Order order = new Order(0, expectedClient);
        assertEquals(expectedClient, order.getClient());
    }

    @Test
    // проверка получения правильного id клиента при создании заказа
    public void testGetClientId() {
        Client expectedClient = new Client(1, "Рая");
        Order order = new Order(0, expectedClient);

        int actual = order.getClient().getId();
        //int expected = 1;
        int expected = expectedClient.getId();

        assertEquals(actual, expected);
    }
}



//    @Test
//    public void testCorrectIdWhenCreateOrder1() {
//        Client client = new Client(0, "Рая");
//        Order order = new Order(0, client);
//
//        int actual = order.getId();
//        int expected = 0;
//
//        assertEquals(actual, expected);





//    @Test
//    public void testAddItem() {
//        Client expectedClient = new Client(0, "Рая");
//        Order order = new Order(0, expectedClient);
//        Item item = new Item(1, "Ноутбук", Category.LAPTOPS, 100000.00);
//        Item item1 = new Item(2, "Ноутбук", Category.LAPTOPS, 100000.00);
//        order.addItem(item);
//        order.addItem(item1);
//        assertEquals(order.getCart().size(), 2);
//        assertTrue(order.getCart().containsKey(item));
//        assertTrue(order.getCart().containsKey(item1));
//        assertEquals(order.getTotalPrice(), 200000.00);
//
//
//    }
