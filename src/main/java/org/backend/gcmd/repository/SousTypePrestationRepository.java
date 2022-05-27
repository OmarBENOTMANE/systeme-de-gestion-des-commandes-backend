package org.backend.gcmd.repository;

import org.backend.gcmd.entity.SousTypePrestationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousTypePrestationRepository extends JpaRepository<SousTypePrestationEntity, Long> {

    Page<SousTypePrestationEntity> findAllByIsDeletedFalse(Pageable page);
}