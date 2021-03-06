package com.dbtest.trademodule.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dbtest.trademodule.helper.CSVHelper;
import com.dbtest.trademodule.model.Orders;
import com.dbtest.trademodule.repository.OrdersRepo;

@Service
public class CSVService {
    @Autowired
    OrdersRepo repository;
    public void save(MultipartFile file) {
        try {
            List<Orders> orders = CSVHelper.csvToOrders(file.getInputStream());
            
            repository.saveAll(orders);
        }catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
    public List<Orders> getAllOrders() {
        return repository.findAll(); 
    }
	/*
	 * public List<Orders> getOrdersByTradeId(String tradeId){ return
	 * repository.findAll().stream()
	 * .filter(s->s.getTradeId().contentEquals(tradeId))
	 * .collect(Collectors.toList());} public List<Orders>
	 * getOrdersByTradeIdAndDate(String tradeId,String date) { return
	 * repository.findAll().stream()
	 * .filter(s->s.getTradeId().contentEquals(tradeId))
	 * .filter(s->s.getDate().contentEquals(date)) .collect(Collectors.toList()); }
	 */
    }