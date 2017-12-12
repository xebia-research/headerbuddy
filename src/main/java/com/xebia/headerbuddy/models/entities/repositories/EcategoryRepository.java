package com.xebia.headerbuddy.models.entities.repositories;

import com.xebia.headerbuddy.models.entities.Ecategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EcategoryRepository extends CrudRepository<Ecategory, Long> {

    public Iterable<Ecategory> findCategoryByName(@Param("name") String name);
}
