package org.example.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author YSK
 * @version 1.0
 */
public enum TradeType {
    CONSUME(1),

    DRAWBACK(2);

    @EnumValue
    private final Integer type;

    TradeType(Integer type) {
        this.type = type;
    }
}
