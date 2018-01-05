package com.xebia.headerbuddy.models.entities.repositories;

import com.xebia.headerbuddy.models.entities.Eprofile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EprofileRepository extends CrudRepository<Eprofile, Long> {

    Iterable<Eprofile> findByName(@Param("name") String name);
}
