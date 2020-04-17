package com.sqt.edu.common.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqt.edu.common.base.PageResult;
import io.swagger.models.auth.In;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-17 17:10
 */
public final class PageEduHelper extends PageHelper {

    /**查询分页数据
     * @return
     */
    public static <T>PageResult<T> selectPageResult(int pageSize, int pageNum, Supplier<List<T>> supplier){
        Assert.isTrue(pageSize > 0,"pageSize必须大于0！！");
        Assert.isTrue(pageNum >= 0,"pageNum必须大于等于0！！");
        PageHelper.startPage(pageNum, pageSize);
        List<T> models = supplier.get();
        return new PageResult(models);
    }


}
