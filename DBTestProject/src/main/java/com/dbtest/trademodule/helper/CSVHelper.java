package com.dbtest.trademodule.helper;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.dbtest.trademodule.model.Orders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Trade_ID","Version","CP_ID","B_ID","Maturity_Date","Created_On","Expired" };

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) { return false;}
        return true;
    }
    public static List<Orders> csvToOrders(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
             CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){
        
                    List<Orders> orders = new ArrayList<Orders>();
                    Iterable<CSVRecord> csvRecords = csvParser.getRecords();
                    for (CSVRecord csvRecord : csvRecords) {
                        Orders order = new Orders(
                        		csvRecord.get(0),
                        		Integer.parseInt(csvRecord.get(1)),
                                csvRecord.get(2),
                                csvRecord.get(3),
                                csvRecord.get(4),
                                csvRecord.get(5),
                                csvRecord.get(6)                           
                        );
                        orders.add(order);
                        
                    }
                   // System.out.println("List of orders "+orders);
                    return orders;
        }catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }}}