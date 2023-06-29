package ru.inno.market;

import org.junit.jupiter.api.Test;
import ru.inno.market.core.MarketService;
import ru.inno.market.model.Client;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarketServiceTest {
    @Test
    public void createOrderFor(){
        MarketService marketService = new MarketService();

    }


}







//    @Test
//    public void testCreateOrder(){
//        Client expectedClient = new Client(0, "Рая");
//        MarketService service = new MarketService();
//        int id = service.createOrderFor(expectedClient);
//        Client actualClient = service.getOrderInfo(id).getClient();
//        assertEquals(expectedClient, actualClient, "Тест пройден");
//    }