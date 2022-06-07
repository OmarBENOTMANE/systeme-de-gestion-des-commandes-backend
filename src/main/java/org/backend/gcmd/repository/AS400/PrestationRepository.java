package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.AS400.PrestationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("prestationRepositoryAS400")
public interface PrestationRepository extends JpaRepository<PrestationEntity, Long> {

    Page<PrestationEntity> findAllByIsDeletedFalse(Pageable page);
}