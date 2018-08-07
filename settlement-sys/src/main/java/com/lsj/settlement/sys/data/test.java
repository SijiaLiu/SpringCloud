package com.lsj.settlement.sys.data;

import com.lsj.settlement.sys.service.StService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class test {

    @Autowired
    private StService stService;

    private StSettlementTicket stSettlementTicket = null;

//    public test(){
//        stSettlementTicket = stService.getStSettlementTicket(null, null, 0, 2).getResultContent().get(0);
//    }

}
