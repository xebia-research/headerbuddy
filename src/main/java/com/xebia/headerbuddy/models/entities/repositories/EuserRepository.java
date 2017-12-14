package com.xebia.headerbuddy.models.entities.repositories;

import com.xebia.headerbuddy.models.entities.Euser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EuserRepository extends CrudRepository<Euser, Long> {

    public Iterable<Euser> findByApikey(@Param("apikey") String apikey);
}
