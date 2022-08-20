package pl.km.exercise281.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findOfferByTitleContainingIgnoreCase(String param);

    List<Offer> findAllByCategory_Id(Long id);
}
