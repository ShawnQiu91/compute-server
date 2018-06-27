package com.example.computeserver.feignclient;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用hystrix时，为feign打回退日志
 */
@Component
public class FeignHelloClientFallbackFactory implements FallbackFactory<HelloClient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeignHelloClientFallbackFactory.class);

    @Override
    public HelloClient create(Throwable throwable) {
        return new HelloClient() {
            @Override
            public String hello() {
                LOGGER.info("fallback reason:", throwable);
                return "Feign fallback factory: request server isn't working";
            }
        };
    }
}
