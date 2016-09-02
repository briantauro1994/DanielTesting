package com.covenant.app.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.covenant.app.dto.DealDto;
import com.covenant.app.model.Deal;
import com.covenant.app.services.DealService;

@Controller
@RequestMapping("/v1/deals")
public class DealController {
	
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<DealDto> findAllDeals(){
		
		List<Deal> dealList = dealService.findAllDeals();
		return new DealDto().getAllDto(dealList);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public DealDto findDeal(@PathVariable Long id){
		
		Deal deal = dealService.findById(id);		
		logger.info(deal);
		
		return new DealDto(deal.getTitle(),deal.getStatus());
		
	}
	
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
	public void saveDeal(@RequestBody DealDto dealDto){
    	
    	Deal deal = new Deal();
    	deal.setTitle(dealDto.getTitle());
    	deal.setStatus(dealDto.getStatus());
    	dealService.saveDeal(deal);
    	logger.info("New Deal is created");
    	
    }
	
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        logger.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	@Autowired
	private DealService dealService;
	
	private static final Logger logger = Logger.getLogger(DealController.class);
}
