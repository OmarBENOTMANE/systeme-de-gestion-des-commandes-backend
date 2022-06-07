package org.backend.gcmd.repository.AS400;


import org.backend.gcmd.entity.AS400.BulltinPrestationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bulltinPrestationRepositoryAS400")
public interface BulltinPrestationRepository extends JpaRepository<BulltinPrestationEntity, Long> {

    Page<BulltinPrestationEntity> findAllByIsDeletedFalse(Pageable page);

}