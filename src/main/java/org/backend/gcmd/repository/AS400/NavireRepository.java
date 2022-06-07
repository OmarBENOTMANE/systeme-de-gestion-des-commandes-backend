package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.AS400.NavireEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("navireRepositoryAS400")
public interface NavireRepository extends JpaRepository<NavireEntity, Long> {

    Page<NavireEntity> findAllByIsDeletedFalse(Pageable page);
}