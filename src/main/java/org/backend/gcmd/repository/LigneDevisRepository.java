package org.backend.gcmd.repository;

import org.backend.gcmd.entity.LigneDevisEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneDevisRepository extends JpaRepository<LigneDevisEntity, Long> {

    Page<LigneDevisEntity> findAllByIsDeletedFalse(Pageable page);
}