package br.com.trocafacil.ems.apps.trade.repository;

import br.com.trocafacil.ems.domain.model.trade.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    List<Trade> findByProductPostedId(Long id);

    Optional<Trade> findByProductPostedIdAndProductProposalIdOrProductPostedIdAndProductProposalId(
            Long productPostedId, Long productProposalId,
            Long productPostedId2, Long productProposalId2
            );

//    List<Trade> findByProduct_Account_IdAndStatus(Long accountId, Status status);

}
