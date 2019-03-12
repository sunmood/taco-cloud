package com.example.demo.repository;

import com.example.demo.domain.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sunmood on 2019/3/7.
 */
@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {

}
