package com.example.demo.repository;

import com.example.demo.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by sunmood on 2019/3/7.
 * Spring Data会自动分析方法名来猜测方法的功能
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    /**
     * 根据zip查询order
     * @param zip
     * @return
     */
    List<Order> findByZip(String zip);

    /**
     * 根据zip查询日期范围内的order
     * @param zip
     * @param startDate
     * @param endDate
     * @return
     */
    List<Order> readOrdersByZipAndPlacedAtBetween(String zip, Date startDate, Date endDate);
}
