package org.backend.gcmd.repository.AS400;


import org.backend.gcmd.entity.AS400.TypeNavireEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("typeNavireRepositoryAS400")
public interface TypeNavireRepository extends JpaRepository<TypeNavireEntity, Long> {

    Page<TypeNavireEntity> findAllByIsDeletedFalse(Pageable page);
}