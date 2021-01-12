package com.my.mealsys.mapper;

import com.my.mealsys.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    int insert(User record);

    List<User> selectAll();

    /**
     * 检查账号是否被注册
     * @param acot
     * @return
     */
    @Select("select count(*) from user where acot=#{acot}")
    int checkUserExist(String acot);

    /**
     * 注册用户
     * @param acot
     * @param password
     * @return
     */
    @Insert("insert into user (acot,password) values(#{acot},#{password})")
    int signUpUser(String acot,String password);

    /**
     * 登录时检查账号密码
     * @param acot
     * @param password
     * @return
     */
    @Select("select count(*) from user where acot=#{acot} and password=#{password}")
    int checkUserAcotPsw(String acot,String password);
}