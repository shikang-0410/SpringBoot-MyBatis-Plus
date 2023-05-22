package org.example.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author YSK
 * @version 1.0
 */
public enum Gender {
    MALE(1),

    FEMALE(2);

    @EnumValue
    private final Integer gender;

    Gender(Integer gender) {
        this.gender = gender;
    }
}
