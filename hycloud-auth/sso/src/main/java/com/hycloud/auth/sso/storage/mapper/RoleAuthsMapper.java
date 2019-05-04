package com.hycloud.auth.sso.storage.mapper;

import com.hycloud.auth.sso.storage.entity.RoleAuths;
import com.hycloud.auth.sso.storage.entity.RoleAuthsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleAuthsMapper {
    long countByExample(RoleAuthsExample example);

    int deleteByExample(RoleAuthsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleAuths record);

    int insertSelective(RoleAuths record);

    List<RoleAuths> selectByExample(RoleAuthsExample example);

    RoleAuths selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleAuths record, @Param("example") RoleAuthsExample example);

    int updateByExample(@Param("record") RoleAuths record, @Param("example") RoleAuthsExample example);

    int updateByPrimaryKeySelective(RoleAuths record);

    int updateByPrimaryKey(RoleAuths record);
}