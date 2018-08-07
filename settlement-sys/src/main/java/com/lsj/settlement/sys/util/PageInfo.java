package com.lsj.settlement.sys.util;

import lombok.Data;

import java.io.Serializable;

/**
 * class_name: PageInfo
 * package: com.lsj.settlement.data.util
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/25
**/

@Data
public class PageInfo implements Serializable {

    /**
     * serialVersionUID:TODO Description.
     */
    private static final long serialVersionUID = -7644066325412964122L;

    /**
     * Required. currentPage:the No. of current page.
     */
    private Integer currentPage;
    /**
     * Required. totalPage: Total count of the pages.
     */
    private Integer totalPage;
    /**
     * Required. pageSize: the size of the page.
     */
    private Integer pageSize;
    /**
     * Optional. totalRecords: total records of the entities.
     */
    private Integer totalRecords;
}
