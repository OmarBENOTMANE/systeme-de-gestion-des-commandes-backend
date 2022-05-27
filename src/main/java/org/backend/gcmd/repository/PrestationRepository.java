package org.backend.gcmd.repository;

import org.backend.gcmd.entity.PrestationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestationRepository extends JpaRepository<PrestationEntity, Long> {

    Page<PrestationEntity> findAllByIsDeletedFalse(Pageable page);
}