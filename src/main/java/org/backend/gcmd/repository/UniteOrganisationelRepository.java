package org.backend.gcmd.repository;

import org.backend.gcmd.entity.UniteOrganisationelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniteOrganisationelRepository extends JpaRepository<UniteOrganisationelEntity, Long> {

    Page<UniteOrganisationelEntity> findAllByIsDeletedFalse(Pageable page);
}