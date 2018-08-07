package com.lsj.settlement.data.util;

import lombok.Data;

import java.io.Serializable;

/**
 * class_name: GeneralResult
 * package: com.lsj.settlement.data.util
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/27
**/

@Data
public class GeneralResult implements Serializable {

    private String resultCode;

    private String description;

}
