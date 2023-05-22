package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.example.enums.Gender;

/**
 * @author YSK
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("User")
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Gender gender;

    @TableLogic
    private Integer deleted;

    private Integer walletId;

}
