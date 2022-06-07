package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.AS400.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("clientRepositoryAS400")
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Page<ClientEntity> findAllByIsDeletedFalse(Pageable page);

}
