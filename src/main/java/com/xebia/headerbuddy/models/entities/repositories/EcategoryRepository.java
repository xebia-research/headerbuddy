package com.xebia.headerbuddy.models.entities.repositories;

import com.xebia.headerbuddy.models.entities.Ecategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EcategoryRepository extends CrudRepository<Ecategory, Long> {

    public Iterable<Ecategory> findByName(@Param("name") String name);
}
