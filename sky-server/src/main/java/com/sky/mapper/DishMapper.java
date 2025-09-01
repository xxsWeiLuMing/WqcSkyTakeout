package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     *
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 插入菜品信息
     *
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 分页查询菜品信息
     *
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据菜品id集合批量删除菜品
     *
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 根据id动态修改菜品
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    @Select("select * from dish where category_id = #{categoryId}")
    List<Dish> getByCategoryId(Long categoryId);

    List<Dish> getByIds(List<Long> ids);

    @Select("select d.* from dish d left join setmeal_dish sd on d.id = sd.dish_id where sd.setmeal_id = #{setmealId}")
    List<Dish> getBySetmealId(Long setmealId);

    List<Dish> list(Dish dish);

    Integer countByMap(Map map);
}
