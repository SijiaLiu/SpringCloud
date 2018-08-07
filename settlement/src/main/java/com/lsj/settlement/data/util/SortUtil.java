package com.lsj.settlement.data.util;

import com.lsj.settlement.constant.SettlementConstant;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

public class SortUtil {

    /**
     * @Describe 对查询结果排序
     * default : 根据创建时间倒序排序
     * @param sortDir
     * @param propertyList
     * @return
     */
    public static Sort sortSetting(String sortDir, List<String> propertyList){
        if (propertyList == null || propertyList.isEmpty()){
            propertyList = Arrays.asList(SettlementConstant.settlementDirectionCreateDate);
        }
        if (sortDir.equalsIgnoreCase(SettlementConstant.settlementDirectionAsc)){
            return new Sort(Sort.Direction.ASC, propertyList);
        }
        return new Sort(Sort.Direction.DESC, propertyList);
    }

}
