package com.xebia.headerbuddy.models.entities.repositories;

import com.xebia.headerbuddy.models.entities.Eprofile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EprofileRepository extends CrudRepository<Eprofile, Long> {

    public List<Eprofile> findByName(@Param("name") String name);
}
