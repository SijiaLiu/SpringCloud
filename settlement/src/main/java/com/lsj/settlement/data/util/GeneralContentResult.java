package com.lsj.settlement.data.util;

import lombok.Data;

import java.io.Serializable;

/**
 * class_name: GeneralContentResult
 * package: com.lsj.settlement.data.util
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/27
**/

@Data
public class GeneralContentResult<T> implements Serializable {

    /**
     * serialVersionUID:TODO Description.
     */
    private static final long serialVersionUID = -8104955278209569617L;

    private String resultCode;
    private String detailDescription;
    private T resultContent;

}
