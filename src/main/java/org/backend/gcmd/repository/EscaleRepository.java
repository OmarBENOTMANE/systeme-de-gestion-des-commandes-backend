package org.backend.gcmd.repository;

import org.backend.gcmd.entity.EscaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EscaleRepository extends JpaRepository<EscaleEntity, Long> {

    Page<EscaleEntity> findAllByIsDeletedFalse(Pageable page);

    @Query(value=" (select el.*,mv.*,cmd.* from gcmd_mouvement mv, gcmd_escale el ,gcmd_commande cmd " +
            " where mv.description = 'appareiller' and mv.escale_id = el.id and cmd.escale_id= el.id and el.isfactured = false " +
            " and el.id not in (select distinct escale_id from gcmd_commande where escale_id is not null )) ", nativeQuery = true   )
    Page<EscaleEntity> findAllNavireAppareiller(Pageable page);

}