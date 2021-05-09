package com.dbtest.trademodule.repository;


import com.dbtest.trademodule.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersRepo extends JpaRepository<Orders,Long> {
}
