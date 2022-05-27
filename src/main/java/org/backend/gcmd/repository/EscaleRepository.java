package org.backend.gcmd.repository;

import org.backend.gcmd.entity.EscaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscaleRepository extends JpaRepository<EscaleEntity, Long> {

    Page<EscaleEntity> findAllByIsDeletedFalse(Pageable page);
}