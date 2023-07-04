package ru.inno.market;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.inno.market.core.MarketService;
import ru.inno.market.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class MarketServiceTest {
    @Test
    @DisplayName("Возвращение корректного ID созданного заказа")
    public void correctOrderIdForClient() {
        MarketService marketService = new MarketService();
        Client client = new Client(1, "Рая");
        int id = marketService.createOrderFor(client);
        assertEquals(1, id );
    }
    @Test
    @DisplayName("Заказ попал в список заказов")
    public void correctOrderAddedToList() {
        MarketService marketService = new MarketService();
        Client client = new Client(1, "Рая");
        int id = marketService.createOrderFor(client);
        Order expectedOrder = new Order(1, client);
        Order actualOrder = marketService.getOrderInfo(id);
        assertEquals(expectedOrder, actualOrder );
    }

    @Test
    @DisplayName("Заказ с данным ID существует")
    public void correctGetOrderInfo() {
        MarketService marketService = new MarketService();
        Client client = new Client(1, "Рая");
        int id = marketService.createOrderFor(client);
        assertNotNull(marketService.getOrderInfo(id));
    }

    @Test
    @DisplayName("Созданный товар добавляется в заказ")
    public void correctAddItemToOrder(){
        MarketService marketService = new MarketService();
        Client client = new Client(1, "Рая");
        int id = marketService.createOrderFor(client);
        Item item = new Item(1, "Apple iPhone SE", Category.SMARTPHONES, 97990);
        marketService.addItemToOrder(item, id);
        Order order = marketService.getOrderInfo(id);
        assertTrue(order.getItems().containsKey(item));
    }
    @Test
    @DisplayName("Скидка применяется")
    public void applyDiscountForOrder() {
        MarketService marketService = new MarketService();
        Client client = new Client(1, "Рая");
        int id = marketService.createOrderFor(client);
        Item item = new Item(1, "Apple iPhone SE", Category.SMARTPHONES, 97990);
        marketService.addItemToOrder(item, id);
        Order order = marketService.getOrderInfo(id);
        marketService.applyDiscountForOrder(1, PromoCodes.LOVE_DAY);
        assertEquals(84271.4, order.getTotalPrice());
    }
    @Test
    @DisplayName("Второй заказа создается корректно")
    public void correctSecondOrderForClient() {
        MarketService marketService = new MarketService();
        Client client = new Client(1, "Рая");
        int id = marketService.createOrderFor(client);
        int idSecond = marketService.createOrderFor(client);
        Order expectedOrder = new Order(2, client);
        Order actualOrder = marketService.getOrderInfo(idSecond);
        assertEquals(expectedOrder, actualOrder );
    }
}


