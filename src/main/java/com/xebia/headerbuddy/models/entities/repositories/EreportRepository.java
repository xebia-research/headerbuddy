package com.xebia.headerbuddy.models.entities.repositories;

import com.xebia.headerbuddy.models.entities.Ereport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EreportRepository extends CrudRepository<Ereport, Long> {

}
