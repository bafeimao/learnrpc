package io.learn.rpc;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * @projectName: ibsen-service-api
 * @package: com.meddatas.ibsen.api.test
 * @className: Pizza
 * @author: You Chuande
 * @description: TODO
 * @date: 2022/11/1 18:01
 * @version: 1.0
 */
public abstract class Pizza {
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}
