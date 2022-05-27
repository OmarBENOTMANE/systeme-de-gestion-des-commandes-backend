package org.backend.gcmd.mapper;

import org.springframework.data.domain.Page;

import java.util.List;

public interface Mapper<D, E> {

    Page<D> convertToPageDto(Page<E> page);

    D convertToDto(E entity);

    E convertToEntity(D dto);

    List<D> convertToDtoList(List<E> entities);

    List<E> convertToEntitiesList(List<D> entities);
}
