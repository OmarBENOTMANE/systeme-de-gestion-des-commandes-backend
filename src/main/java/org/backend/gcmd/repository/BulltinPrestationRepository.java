package org.backend.gcmd.repository;

import org.backend.gcmd.entity.BulltinPrestationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BulltinPrestationRepository extends JpaRepository<BulltinPrestationEntity, Long> {

    Page<BulltinPrestationEntity> findAllByIsDeletedFalse(Pageable page);

    @Query(value = " select  bp.* " +
            "    from gcmd_bulltin_prestation bp , gcmd_commande cmd , gcmd_escale el " +
            "    where el.isfactured = false and cmd.bulltin_prestation_id = bp.id and cmd.escale_id = el.id ", nativeQuery = true)
    Page<BulltinPrestationEntity> findAllBpNotFacturedForEscale(Pageable pageable);
}