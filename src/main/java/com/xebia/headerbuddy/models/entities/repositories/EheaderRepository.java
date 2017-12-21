package com.xebia.headerbuddy.models.entities.repositories;

import com.xebia.headerbuddy.models.entities.Eheader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EheaderRepository extends CrudRepository<Eheader, Long> {

    public List<Eheader> findByName(@Param("name") String name);
}
