package com.xebia.headerbuddy.models.entities.repositories;

import com.xebia.headerbuddy.models.entities.Euser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EuserRepository extends CrudRepository<Euser, Long> {
    Euser findByApikey(@Param("apikey") String apikey);
    Euser findByEmail(@Param("email") String email);
}
