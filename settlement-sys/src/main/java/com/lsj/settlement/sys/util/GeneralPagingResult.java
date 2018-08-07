package com.lsj.settlement.sys.util;

import lombok.Data;

import java.io.Serializable;

/**
 * class_name: GeneralPagingResult
 * package: com.lsj.settlement.data.util
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/25
**/

@Data
public class GeneralPagingResult<T> implements Serializable {

    /**
     * serialVersionUID:TODO Description.
     */
    private static final long serialVersionUID = 1540315626080625718L;

    private String resultCode;
    private String detailDescription;
    private T resultContent;

    /**
     * pageInfo: For paging result ONLY.
     */
    private PageInfo pageInfo;

}
