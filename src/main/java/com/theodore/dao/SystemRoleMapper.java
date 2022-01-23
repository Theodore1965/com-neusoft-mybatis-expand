package com.theodore.dao;

import com.theodore.entity.SystemRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * (SystemRole)表数据库访问层
 *
 * @author yufeng
 * @since 2022-01-08 15:50:53
 */
@Mapper
public interface SystemRoleMapper extends BaseMapper<SystemRole> {

    List<SystemRole> selectByStaffId(@Param("staffId") String staffId);
}