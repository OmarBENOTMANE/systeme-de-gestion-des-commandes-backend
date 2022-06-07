package org.backend.gcmd.repository.AS400;


import org.backend.gcmd.entity.AS400.CodeMouvementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("codeMouvementRepositoryAS400")
public interface CodeMouvementRepository extends JpaRepository<CodeMouvementEntity, Long> {

    Page<CodeMouvementEntity> findAllByIsDeletedFalse(Pageable page);
}