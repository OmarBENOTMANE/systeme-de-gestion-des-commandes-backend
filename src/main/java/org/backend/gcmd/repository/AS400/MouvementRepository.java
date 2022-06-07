package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.AS400.MouvementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("mouvementRepositoryAS400")
public interface MouvementRepository extends JpaRepository<MouvementEntity, Long> {

    Page<MouvementEntity> findAllByIsDeletedFalse(Pageable page);
}