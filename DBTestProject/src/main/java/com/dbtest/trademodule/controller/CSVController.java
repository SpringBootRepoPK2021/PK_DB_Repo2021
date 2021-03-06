package com.dbtest.trademodule.controller;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.dbtest.trademodule.helper.CSVHelper;
import com.dbtest.trademodule.model.Orders;
import com.dbtest.trademodule.service.CSVService;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api/csv")
public class CSVController {
        @Autowired
        CSVService fileService;
        //Upload dataset Orders.csv;
        @PostMapping("/upload")
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
            String message = "";
          
            if (CSVHelper.hasCSVFormat(file)) {
                fileService.save(file);
                try {
                
                    fileService.save(file);
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
                    
                    return ResponseEntity.status(HttpStatus.OK).body( "\" message \": \" "+ message +" \"");
                } catch (Exception e) {
                
                    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                  
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("\" message \": \" "+ message +" \"");
                   
                }
            }
            message = "Please upload a csv file!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\" message \": \" "+ message +" \"");
        }
        //1-SELECT * FROM Order;
        @GetMapping("/orders")
        public ResponseEntity<List<Orders>> getAllOrders () {
            try {
                List<Orders> orders = fileService.getAllOrders();
    
                if (orders.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
    
                return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        //2-Stream :Get Order By Trade Id
	/*
	 * @GetMapping(value = "/orders/findByTradeId/{tradeId}") public
	 * ResponseEntity<List<Orders>> getOrdersByTradeId (@PathVariable String
	 * tradeId) { try { List<Orders> orders =
	 * fileService.getOrdersByTradeId(tradeId);
	 * 
	 * if (orders.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK); } catch
	 * (Exception e) { return new ResponseEntity<>(null,
	 * HttpStatus.INTERNAL_SERVER_ERROR); } } //3-Stream :Get Order By Trade Id and
	 * Maturity Date
	 * 
	 * @GetMapping("/orders/findByTradeIdAndDate/{name}/{orderDate}") public
	 * ResponseEntity<List<Orders>> getOrdersByTradeIdAndDate (@PathVariable String
	 * tradeId,@PathVariable String orderDate) { try { List<Orders> orders =
	 * fileService.getOrdersByTradeIdAndDate(tradeId,orderDate);
	 * 
	 * if (orders.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * 
	 * return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK); } catch
	 * (Exception e) { return new ResponseEntity<>(null,
	 * HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */
}