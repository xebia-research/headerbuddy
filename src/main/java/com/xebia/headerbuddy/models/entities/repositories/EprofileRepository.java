package com.xebia.headerbuddy.models.entities.repositories;

import com.xebia.headerbuddy.models.entities.Eprofile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EprofileRepository extends CrudRepository<Eprofile, Long> {

    List<Eprofile> findByName(@Param("name") String name);
}
