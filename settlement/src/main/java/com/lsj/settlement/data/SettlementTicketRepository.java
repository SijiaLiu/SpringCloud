package com.lsj.settlement.data;

import com.lsj.settlement.data.domain.StSettlementTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementTicketRepository extends JpaRepository<StSettlementTicket, String>, JpaSpecificationExecutor<StSettlementTicket> {
}
