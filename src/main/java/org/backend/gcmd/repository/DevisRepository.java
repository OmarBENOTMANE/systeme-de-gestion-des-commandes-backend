package org.backend.gcmd.repository;

import org.backend.gcmd.entity.DevisEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevisRepository extends JpaRepository<DevisEntity, Long> {

    Page<DevisEntity> findAllByIsDeletedFalse(Pageable page);
}