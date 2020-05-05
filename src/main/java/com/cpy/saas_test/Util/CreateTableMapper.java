package com.cpy.saas_test.Util;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CreateTableMapper {

    @Update("CREATE TABLE `${name}` (`Id` varchar(255) NOT NULL DEFAULT '',`Gname` varchar(255) DEFAULT NULL,`Gtype` varchar(255) DEFAULT NULL,`amount` int(11) DEFAULT NULL,PRIMARY KEY (`Id`)) ")
    public void CreateGoods(@Param("name") String name);

    @Update("CREATE TABLE `${name}` (`Id` varchar(255) NOT NULL,`Gname` varchar(255) DEFAULT NULL,`Gtype` varchar(255) DEFAULT NULL,`amount` int(11) DEFAULT NULL,`date` varchar(10) DEFAULT NULL,PRIMARY KEY (`Id`))")
    public void CreateGoodsin(@Param("name") String name);

    @Update("CREATE TABLE `${name}` (`Id` varchar(255) NOT NULL DEFAULT '',`Gname` varchar(255) DEFAULT NULL,`Gtype` varchar(255) DEFAULT NULL,`amount` int(11) DEFAULT NULL,`OutDate` varchar(10) DEFAULT NULL,PRIMARY KEY (`Id`))")
    public void CreateGoodsout(@Param("name") String name);

    @Delete("DROP table ${table}")
    public void deleteTable(@Param("table") String table);
}