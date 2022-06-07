package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.AS400.LibellePrestationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("libellePrestationRepositoryAS400")
public interface LibellePrestationRepository extends JpaRepository<LibellePrestationEntity, Long> {

    Page<LibellePrestationEntity> findAllByIsDeletedFalse(Pageable page);
}