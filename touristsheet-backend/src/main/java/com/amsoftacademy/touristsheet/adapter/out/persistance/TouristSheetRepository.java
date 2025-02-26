package com.amsoftacademy.touristsheet.adapter.out.persistance;

import com.amsoftacademy.touristsheet.domain.TouristSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex Pumnea
 */
@Repository
public interface TouristSheetRepository extends JpaRepository<TouristSheet, Long> {
}
