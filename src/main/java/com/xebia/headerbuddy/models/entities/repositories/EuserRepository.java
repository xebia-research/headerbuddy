package com.xebia.headerbuddy.models.entities.repositories;

import com.xebia.headerbuddy.models.entities.Euser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EuserRepository extends CrudRepository<Euser, Long> {

    Iterable<Euser> findUserByApikey(@Param("apikey") String apikey);

    Iterable<Euser> findUserByEmail(@Param("email") String email);
}
