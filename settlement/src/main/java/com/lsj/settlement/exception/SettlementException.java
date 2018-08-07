package com.lsj.settlement.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * class_name: SettlementException
 * package: com.lsj.settlement.exception
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/31
**/

@Data
public class SettlementException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1370800535492375942L;

    private String code;

    public SettlementException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
