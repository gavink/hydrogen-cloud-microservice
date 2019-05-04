package com.hycloud.auth.sso.storage.mapper;

import com.hycloud.auth.sso.storage.entity.UserRoles;
import com.hycloud.auth.sso.storage.entity.UserRolesExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface UserRolesMapper {
    long countByExample(UserRolesExample example);

    int deleteByExample(UserRolesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoles record);

    int insertSelective(UserRoles record);

    List<UserRoles> selectByExample(UserRolesExample example);

    UserRoles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRoles record, @Param("example") UserRolesExample example);

    int updateByExample(@Param("record") UserRoles record, @Param("example") UserRolesExample example);

    int updateByPrimaryKeySelective(UserRoles record);

    int updateByPrimaryKey(UserRoles record);
    
    // 自定义查询
    List<SimpleGrantedAuthority> getRolesByUsername(UserRolesExample example);
}