package com.theodore.dao;
import com.theodore.entity.SystemStaffInfo;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表(SystemStaffInfo)表数据库访问层
 *
 * @author yufeng
 * @since 2022-01-08 15:04:23
 */
@Mapper
public interface SystemStaffInfoMapper extends BaseMapper<SystemStaffInfo> {

    SystemStaffInfo selectByStaffIdAndEnabled(@Param("staffId") String staffId, @Param("enabled") boolean enabled);
}