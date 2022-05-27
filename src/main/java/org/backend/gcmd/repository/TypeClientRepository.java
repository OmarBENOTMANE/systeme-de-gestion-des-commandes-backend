package org.backend.gcmd.repository;

import org.backend.gcmd.entity.TypeClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeClientRepository extends JpaRepository<TypeClientEntity, Long> {

    Page<TypeClientEntity> findAllByIsDeletedFalse(Pageable page);
}