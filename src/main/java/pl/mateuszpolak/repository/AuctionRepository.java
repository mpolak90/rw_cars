package pl.mateuszpolak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszpolak.model.Auction;

import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findAllByStatusOrderByStartDesc(String status);

    List<Auction> findAllByStatusOrderBySoldDesc(String status);

    List<Auction> findAllByStatusOrderByPriceAsc(String status);
}