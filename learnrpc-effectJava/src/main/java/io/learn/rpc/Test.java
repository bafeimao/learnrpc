package io.learn.rpc;

import static com.meddatas.ibsen.api.test.NyPizza.Size.SMALL;
import static com.meddatas.ibsen.api.test.Pizza.Topping.*;

/**
 * @projectName: ibsen-service-api
 * @package: PACKAGE_NAME
 * @className: com.meddatas.ibsen.api.test.Test
 * @author: You Chuande
 * @description: TODO
 * @date: 2022/11/1 17:49
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        NutritionFacts cocaCola =
                new NutritionFacts.Builder(240, 8).calories(100).sodium(35).carbohydrate(27).build();
        NyPizza pizza = new NyPizza.Builder(SMALL).addTopping(SAUSAGE).addTopping(ONION).build();
        Calzone calzone = new Calzone.Builder().addTopping(HAM).sauceInside().build();
    }
}
