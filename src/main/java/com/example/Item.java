package com.example;

import org.springframework.stereotype.Component;

import lombok.Data;

//@Entity(naming = NamingType.SNAKE_LOWER_CASE) 
@Component
@Data
public class Item {
	//@Id
	String itemCd;
	String itemNm;
	String itemVal;
	String category;
	String imgSrc;
	String explain;
	//String lastUpdate;
}