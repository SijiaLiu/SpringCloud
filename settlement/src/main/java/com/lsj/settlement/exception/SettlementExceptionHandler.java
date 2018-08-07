package com.lsj.settlement.exception;

import com.lsj.settlement.constant.SettlementConstant;
import com.lsj.settlement.data.util.GeneralPagingResult;
import com.lsj.settlement.data.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * class_name: SettlementExceptionHandler
 * package: com.lsj.settlement.exception
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/31
**/

@ControllerAdvice
@Slf4j
public class SettlementExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK) //返回的Http状态码
    public GeneralPagingResult<Object> settlementFailedHandler(Exception e){
        log.error("error message : {}", e.getMessage());
        log.error(e.getMessage(), e);
        GeneralPagingResult<Object> result = new GeneralPagingResult<>();
        if (e instanceof SettlementException){
            SettlementException se = (SettlementException) e;
            result.setResultCode(se.getCode());
            result.setResultContent(null);
            result.setDetailDescription(se.getMessage());
            result.setPageInfo(new PageInfo());
        } else {
            result.setResultCode(SettlementConstant.resultCodeFailed);
            result.setDetailDescription(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
