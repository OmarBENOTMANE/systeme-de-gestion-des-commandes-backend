package org.backend.gcmd.repository;

import org.backend.gcmd.entity.NavireEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NavireRepository extends JpaRepository<NavireEntity, Long> {

    Page<NavireEntity> findAllByIsDeletedFalse(Pageable page);
}