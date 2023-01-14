package com.geekbrains.app.repositories;

import com.geekbrains.app.entities.Authorities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> { }
