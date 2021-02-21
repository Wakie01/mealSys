package com.my.mealsys.mapper;

import com.my.mealsys.entity.Menu;
import com.my.mealsys.entity.MenuImage;
import com.my.mealsys.vo.MenuBillInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {

    @Select("select id,name,quantity,price,typeId,image,description from menu")
    List<Menu> getAllMenusSimpleInfo();

    @Select("select id,name,quantity,price,typeId,image from menu where id=#{id}")
    Menu getMenuSimpleInfoByPrimaryKey(Integer id);

    @Select("select * from menu where id=#{id}")
    Menu getMenuInfoByPrimaryKey(Integer id);

    @Update("update menu set name=#{name},quantity=#{quantity},price=#{price},description=#{description},typeId=#{typeId} where id=#{id}")
    int updateMenuInfo(Menu menu);


    /**
     * 插入数据，并返回主键
     * @param menu
     * @return
     */
    int addMenu(Menu menu);

    @Delete("delete from menu where id=#{id}")
    int deleteMenuById(Integer id);

    @Select("select quantity from menu where id=#{id}")
    int getQuantityById(Integer id);

    @Update("update menu set quantity=quantity - #{quantity} where id=#{menuId}")
    int consumeMenuById(MenuBillInfo menuBillInfo);

    /**
     * 根据menuId插入菜单图片，在后面append("imageId,")
     * @param menuImage
     * @return
     */
    @Update("update menu set image = concat(#{id} , ',') where id=#{menuId}")
    Integer insertMenuImageOfFirst(MenuImage menuImage);

    /**
     * concat函数，当有个参数为null时，结果就为null
     * @param menuImage
     * @return
     */
    @Update("update menu set image = concat(image, #{id} , ',') where id=#{menuId}")
    Integer insertMenuImage(MenuImage menuImage);
}