package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * @author YSK
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Wallet")
@TableName("user_wallet")
public class Wallet {
    @TableId("wallet_id")
    private Integer id;

    @TableField("wallet_amount")
    private BigDecimal amount;

    @TableField(exist = false)
    private User user;
}
