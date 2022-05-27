package org.backend.gcmd.repository;

import org.backend.gcmd.entity.LigneCommandeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommandeEntity, Long> {

    Page<LigneCommandeEntity> findAllByIsDeletedFalse(Pageable page);
}