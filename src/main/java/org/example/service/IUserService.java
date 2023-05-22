package org.example.service;

import org.example.entity.Trade;

import java.util.List;

/**
 * @author YSK
 * @version 1.0
 */
public interface IUserService {
    // 1. 查询用户钱包余额
    double queryAmountById(Long id);

    // 2. 用户消费100元的接口
    boolean consume(Long id, double amount);

    // 3. 用户退款20元接口
    boolean drawback(Long id, double amount);

    // 4. 查询用户钱包金额变动明细的接口
    List<Trade> tradeDetail(Long id);
}
