package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.entity.Trade;
import org.example.entity.User;
import org.example.entity.Wallet;
import org.example.enums.TradeType;
import org.example.mapper.TradeMapper;
import org.example.mapper.UserMapper;
import org.example.mapper.WalletMapper;
import org.example.service.IUserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author YSK
 * @version 1.0
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;
    private final WalletMapper walletMapper;
    private final TradeMapper tradeMapper;

    public UserServiceImpl(UserMapper userMapper, WalletMapper walletMapper, TradeMapper tradeMapper) {
        this.userMapper = userMapper;
        this.walletMapper = walletMapper;
        this.tradeMapper = tradeMapper;
    }

    // 1. 查询用户钱包余额
    @Override
    public double queryAmountById(Long id) {
        User user = userMapper.selectById(id);
        Wallet wallet = walletMapper.selectById(user.getWalletId());
        if (wallet == null) {
            throw new RuntimeException("用户 " + id + " 没有创建用户钱包");
        }
        return wallet.getAmount().doubleValue();
    }

    // 2. 用户消费100元的接口
    @Override
    public boolean consume(Long id, double amount) {
        User user = userMapper.selectById(id);
        Wallet wallet = walletMapper.selectById(user.getWalletId());
        if (wallet == null) {
            throw new RuntimeException("用户 " + id + " 没有创建用户钱包");
        }
        BigDecimal consume = BigDecimal.valueOf(amount);
        BigDecimal remaining = wallet.getAmount().subtract(consume);
        if (remaining.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("余额不足");
        }

        // 记录消费
        Trade trade = new Trade(null, consume, TradeType.CONSUME, "消费" + consume, wallet.getId(), new Date());
        tradeMapper.insert(trade);

        // 更新用户钱包的余额
        wallet.setAmount(remaining);
        return walletMapper.updateById(wallet) > 0;
    }

    // 3. 用户退款20元接口
    @Override
    public boolean drawback(Long id, double amount) {
        User user = userMapper.selectById(id);
        Wallet wallet = walletMapper.selectById(user.getWalletId());
        if (wallet == null) {
            throw new RuntimeException("用户 " + id + " 没有创建用户钱包");
        }

        BigDecimal consume = BigDecimal.valueOf(amount);
        // 记录退款
        Trade trade = new Trade(null, consume, TradeType.DRAWBACK, "退款" + consume, wallet.getId(), new Date());
        tradeMapper.insert(trade);

        // 更新用户钱包的余额
        wallet.setAmount(wallet.getAmount().add(consume));
        return walletMapper.updateById(wallet) > 0;
    }


    // 4. 查询用户钱包金额变动明细的接口
    @Override
    public List<Trade> tradeDetail(Long id) {
        User user = userMapper.selectById(id);
        Wallet wallet = walletMapper.selectById(user.getWalletId());
        if (wallet == null) {
            throw new RuntimeException("用户 " + id + " 没有创建用户钱包");
        }

        LambdaQueryWrapper<Trade> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Trade::getWalletId, wallet.getId());
        wrapper.orderByAsc(Trade::getCreated);
        return tradeMapper.selectList(wrapper);
    }


}
