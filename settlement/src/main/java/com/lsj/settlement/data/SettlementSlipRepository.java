package com.lsj.settlement.data;

import com.lsj.settlement.data.domain.StSettlementSlip;
import com.lsj.settlement.data.domain.StSettlementSlipItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

/**
 * class_name: SettlementSlipRepository
 * package: com.lsj.settlement.data
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/30
**/

@Component
public interface SettlementSlipRepository extends JpaRepository<StSettlementSlipItem, String>, JpaSpecificationExecutor<StSettlementSlip> {

}
