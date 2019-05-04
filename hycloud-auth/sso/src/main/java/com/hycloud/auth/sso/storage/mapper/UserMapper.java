package com.hycloud.auth.sso.storage.mapper;

import com.hycloud.auth.sso.storage.entity.User;
import com.hycloud.auth.sso.storage.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String username);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<SimpleGrantedAuthority> getRolesByUsername(String username);
}