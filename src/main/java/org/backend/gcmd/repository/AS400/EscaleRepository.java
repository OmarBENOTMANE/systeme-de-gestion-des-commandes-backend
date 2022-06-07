package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.AS400.EscaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("EscaleRepositoryAS400")
public interface EscaleRepository extends JpaRepository<EscaleEntity, Long> {

    Page<EscaleEntity> findAllByIsDeletedFalse(Pageable page);

}