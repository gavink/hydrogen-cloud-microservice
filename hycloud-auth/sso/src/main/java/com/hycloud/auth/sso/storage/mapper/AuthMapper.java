package com.hycloud.auth.sso.storage.mapper;

import com.hycloud.auth.sso.storage.entity.Auth;
import com.hycloud.auth.sso.storage.entity.AuthExample;
import java.util.List;

import com.hycloud.auth.sso.storage.entity.Menu;
import org.apache.ibatis.annotations.Param;

public interface AuthMapper {
    int countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(String auth);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(String auth);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    Auth getAuthTree(int i);

    List<Menu> getMenus(String username);
}