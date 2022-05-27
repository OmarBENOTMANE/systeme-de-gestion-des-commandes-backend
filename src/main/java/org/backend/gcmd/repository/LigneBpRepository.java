package org.backend.gcmd.repository;

import org.backend.gcmd.entity.LigneBpEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneBpRepository extends JpaRepository<LigneBpEntity, Long> {

    Page<LigneBpEntity> findAllByIsDeletedFalse(Pageable page);


    @Query(nativeQuery = true, value = "SELECT lbp.* FROM gcmd_ligne_bp lbp," +
            "gcmd_bulltin_prestation bp,gcmd_commande cmd, gcmd_ligne_commande lcmd " +
            "where  lbp.bulltin_prestation_id=bp.id and bp.id=cmd.bulltin_prestation_id " +
            "and lcmd.commande_id=cmd.id and lcmd.id= :ligneCmdId")
    List<LigneBpEntity> findByLigneCmdId(Long ligneCmdId);

}