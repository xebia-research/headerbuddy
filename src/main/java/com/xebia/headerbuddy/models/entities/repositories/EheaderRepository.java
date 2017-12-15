package com.xebia.headerbuddy.models.entities.repositories;

import com.xebia.headerbuddy.models.entities.Eheader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EheaderRepository extends CrudRepository<Eheader, Long> {
    
    public List<Eheader> findByName(@Param("name") String name);
}
