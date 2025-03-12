package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;


    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Булочка: ({0}), цена: {1}")
    public static Object[] getBunData() {
        return new Object[][]{
                {"Булочка", 10}, //Название на русском, цена подходит
                {"Bun", 20}, //Название на английском, цена подходит
                {"", 30}, //Название - пустая строка, цена подходит
                {null, 40}, //Название - отсутствующий параметр, цена подходит
                {"Булка 111", 50}, //Название с цифрой, цена подходит
                //Очень длинное название, цена подходит
                {"Бууууууууууууулчииииииииииииииииииииищщщщщщщщщщеееееееееееееееееее", 10},
                {"Булка", 0}, //Название подходит, цена = 0
                {"Булка", -1}, //Название подходит, цена = -1
                {"Булка", (float) 0.5}, //Название подходит, цена = 0.5
        };
    }

    @Test
    public void getBunNameTest() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals(name, bun.getName());
    }

    @Test
    public void getBunPriceTest() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals(price, bun.getPrice(), 0);
    }
}
