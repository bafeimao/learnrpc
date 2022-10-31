package io.learn.rpc;

/**
 * @projectName: ibsen-service-api
 * @package: com.meddatas.ibsen.api.test
 * @className: Calzone
 * @author: You Chuande
 * @description: TODO
 * @date: 2022/11/2 10:05
 * @version: 1.0
 */
public class Calzone extends Pizza {
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false;

        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        public Calzone build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }
}
