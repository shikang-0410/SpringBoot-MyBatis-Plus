package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author YSK
 * @version 1.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
