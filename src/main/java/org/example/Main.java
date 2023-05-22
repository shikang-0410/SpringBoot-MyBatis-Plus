package org.example;

import org.example.entity.Trade;
import org.example.service.IUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

/**
 * @author YSK
 * @version 1.0
 */
@SpringBootApplication
@MapperScan("org.example.mapper")
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        IUserService userService = context.getBean(IUserService.class);

        try {
            // 测试消费100元
            boolean consume = userService.consume(1L, 100);
            LOGGER.info(String.valueOf(consume));

            // 测试退款20园
            boolean drawback = userService.drawback(1L, 20);
            LOGGER.info(String.valueOf(drawback));

            // 测试查询用户钱包余额
            LOGGER.info(String.valueOf(userService.queryAmountById(1L)));

            // 测试查询用户钱包金额变动明细
            List<Trade> trades = userService.tradeDetail(1L);
            trades.forEach(trade -> LOGGER.info(trade.getDescription()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }
}