package org.backend.gcmd.repository;

import org.backend.gcmd.entity.MarchandiseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarchandiseRepository extends JpaRepository<MarchandiseEntity, Long> {

    Page<MarchandiseEntity> findAllByIsDeletedFalse(Pageable page);
}