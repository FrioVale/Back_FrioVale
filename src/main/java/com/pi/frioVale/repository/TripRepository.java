package com.pi.frioVale.repository;

import com.pi.frioVale.domain.Trip;
import com.pi.frioVale.domain.enums.TripStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @EntityGraph(attributePaths = {"container", "driver", "fruit"})
    List<Trip> findByStatus(TripStatus status);

    @EntityGraph(attributePaths = {"container", "driver", "fruit"})
    Optional<Trip> findDetailedById(Long id);

    @EntityGraph(attributePaths = {"container", "driver", "fruit"})
    @Query("select t from Trip t order by t.id desc")
    List<Trip> findAllDetailed();

    boolean existsByContainerIdAndStatus(Long containerId, TripStatus status);
}
