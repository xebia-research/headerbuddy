package com.xebia.headerbuddy.models.entities.repositories;

import com.xebia.headerbuddy.models.entities.Ecategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EcategoryRepository extends CrudRepository<Ecategory, Long> {

    public Ecategory findByName(@Param("name") String name);
}
