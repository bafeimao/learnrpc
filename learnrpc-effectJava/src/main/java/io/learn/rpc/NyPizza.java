package io.learn.rpc;

import java.util.Objects;

/**
 * @projectName: ibsen-service-api
 * @package: com.meddatas.ibsen.api.test
 * @className: NyPizza
 * @author: You Chuande
 * @description: TODO
 * @date: 2022/11/2 10:00
 * @version: 1.0
 */
public class NyPizza extends Pizza {
    public enum Size {SMALL, MEDIUM, LARGE}

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        public NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

    }

    private NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }
}
