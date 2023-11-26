package br.com.trocafacil.ems.apps.trade.repository;

import br.com.trocafacil.ems.domain.helpers.enums.Status;
import br.com.trocafacil.ems.domain.model.product.Product;
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

    List<Trade> findAllByProductPostedAndStatus(Product productPosted, Status status);

}
