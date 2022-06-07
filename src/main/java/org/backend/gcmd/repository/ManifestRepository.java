package org.backend.gcmd.repository;

import org.backend.gcmd.entity.ManifestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManifestRepository extends JpaRepository<ManifestEntity, Long> {

    Page<ManifestEntity> findAllByIsDeletedFalse(Pageable page);
}