package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static constant.BurgerConstant.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    Burger burger;

    @Mock
    Bun bun;
    @Mock
    Ingredient SauceMock;
    @Mock
    Ingredient FillingMock;


    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void addIngredientTest() {
        List<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(SauceMock);
        burger.addIngredient(SauceMock);
        assertEquals(expectedIngredients.size(), burger.ingredients.size());

    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(SauceMock);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        List<Ingredient> expectedOrder = new ArrayList<>(2);
        expectedOrder.add(SauceMock);
        expectedOrder.add(FillingMock);
        burger.addIngredient(FillingMock);
        burger.addIngredient(SauceMock);
        burger.moveIngredient(0, 1);
        assertEquals(expectedOrder, burger.ingredients);
    }

    @Test
    public void getPriceBurgerTest() {
        Mockito.when(bun.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(SauceMock.getPrice()).thenReturn(SAUCE_PRICE);
        Mockito.when(FillingMock.getPrice()).thenReturn(FILLING_PRICE);

        burger.setBuns(bun);
        burger.addIngredient(FillingMock);
        burger.addIngredient(SauceMock);

        assertEquals(EXPECTED_BURGER_PRICE, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptTest() {
        String burgerPriceFormat = String.format("%f%n", EXPECTED_BURGER_PRICE);
        String expectedReceipt = String.format("(==== %s ====)\n= %s %s =\n= %s %s =\n" + "(==== %s ====)\n\nPrice: %s",
                BUN_NAME,
                IngredientType.FILLING.toString().toLowerCase(), FILLING_NAME,
                IngredientType.SAUCE.toString().toLowerCase(), SAUCE_NAME,
                BUN_NAME,
                burgerPriceFormat);

        Mockito.when(bun.getName()).thenReturn(BUN_NAME);
        Mockito.when(FillingMock.getName()).thenReturn(FILLING_NAME);
        Mockito.when(SauceMock.getName()).thenReturn(SAUCE_NAME);
        Mockito.when(bun.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(FillingMock.getPrice()).thenReturn(FILLING_PRICE);
        Mockito.when(SauceMock.getPrice()).thenReturn(SAUCE_PRICE);
        Mockito.when(FillingMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(SauceMock.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(bun);
        burger.addIngredient(FillingMock);
        burger.addIngredient(SauceMock);
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
