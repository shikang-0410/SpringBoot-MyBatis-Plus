package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.example.enums.TradeType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YSK
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Trade")
@TableName("user_trade")
public class Trade {
    @TableId("trade_id")
    private Integer id;

    @TableField("trade_amount")
    private BigDecimal amount;

    private TradeType tradeType;

    private String description;

    private Integer walletId;

    private Date created;
}
