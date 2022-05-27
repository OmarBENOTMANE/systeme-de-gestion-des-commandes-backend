package org.backend.gcmd.repository;

import org.backend.gcmd.entity.CommandeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommandeRepository extends JpaRepository<CommandeEntity, Long> {

    Page<CommandeEntity> findAllByIsDeletedFalse(Pageable page);

    Page<CommandeEntity> findAllByIsfacturedFalse(Pageable page);
}