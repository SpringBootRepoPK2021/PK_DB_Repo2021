package com.dbtest.trademodule.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityScan
public class Orders {
    
    private int version;
    private String tradeId;
    private String mDate;
    private String cpId;
    private String bId;
    private String createdOn;
    private String expiredFlag;
	public Orders(String tradeId,int version, String mDate, String cpId, String bId, String createdOn,
			String expiredFlag) {
		super();
		this.version = version;
		this.tradeId = tradeId;
		this.mDate = mDate;
		this.cpId = cpId;
		this.bId = bId;
		this.createdOn = createdOn;
		this.expiredFlag = expiredFlag;
	}
	public String getTradeId() {
		// TODO Auto-generated method stub
		return this.tradeId;
	}
	public String getDate() {
		// TODO Auto-generated method stub
		return this.mDate;
	}
    

}