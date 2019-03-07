package com.mgrzech.SEApp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgrzech.SEApp.dao.StockDateRepository;
import com.mgrzech.SEApp.entity.Company;
import com.mgrzech.SEApp.entity.StockDate;
import com.mgrzech.SEApp.entity.StockPrice;

@Service
@EnableScheduling
public class StockDateServiceImpl implements StockDateService {
	
	Logger logger = LoggerFactory.getLogger(StockDateServiceImpl.class);
	
	@Autowired
	StockDateRepository stockDateRepository;
	
	@Autowired
	CompanyService companyService;
	@Autowired
	StockPriceService stockPriceService;
	
	@Transactional
	@Scheduled(fixedRate = 5000)
	@Override
	public void getAndSaveDate() {
		
		try {
			
			// Get respond from request and save into db
			saveDataToDatabase(getRequest());
		
		} catch (MalformedURLException e) {
			logger.error("Something is wrong with your http request URL: " + e);
		} catch(IOException e) {
			logger.error("Cannon convert URL to HttpURLConnection object: " + e);
		} catch (Exception e) {
			logger.error("Unidentified error: " + e);
		}
	}
		
	// GET HTTP Request to API
	private StringBuffer getRequest() throws MalformedURLException, IOException {
		
		// Create request
		URL url = new URL("http://webtask.future-processing.com:8068/stocks");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		
		// Buffer respond from request
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));	
		String inputLine;
		StringBuffer content = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		
		//Close buffer and connection
		in.close();
		con.disconnect();
		logger.info("Recives respond from API");

		return content;
	}
	
	// Save data from request in database
	private void saveDataToDatabase(StringBuffer content) throws Exception {
		
		// Get date from request respond
		JsonNode jsonNode = new ObjectMapper().readTree(content.toString());	
		String stringData = jsonNode.get("publicationDate").asText();
		Date requestDate = getDateFromString(stringData, false);	
		// Get date from db
		Date latestStockDateDate =  new Date();
		try {
			latestStockDateDate = getDateFromString(getLatestStockDate().getDate().toString(), true);
		} catch (Exception e) {
			logger.info("Inserting first record.");
		}
			
		// Compare request data with data in db
		if(!(requestDate.compareTo(latestStockDateDate) == 0)) {

			// Get stock list and save it with date to object
			JsonNode nodeOfItems = new ObjectMapper().readTree(content.toString()).get("items");	
			StockDate newStockDate = new StockDate();
			newStockDate.setDate(requestDate);
			
			// Insert data into database
			stockDateRepository.save(newStockDate); // <-Insert new data
			companyService.saveCompaniesIntoDb(getListOfCompanies(nodeOfItems)); // <-Insert new Companies if exists		
			stockPriceService.savePricesIntoDB(getListOfStockPrices(nodeOfItems));
			
			logger.info("Insert new date and stock prices into database.");
		}		
	}
	
	// Convert date from request-String to Date type
	private Date getDateFromString(String stringDataTime, boolean dateFromDb) throws Exception {	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
		if(!dateFromDb) {
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		}
		Date date = dateFormat.parse(stringDataTime.substring(0, 10) + " " + stringDataTime.substring(11, 19));
		
		return date;
	}
	
	// Create list of companies
	private List<Company> getListOfCompanies(JsonNode nodeOfItems) {
		
		List<Company> listOfCompanies = new ArrayList<>();
		if(nodeOfItems.isArray()) {
			for(JsonNode objNode : nodeOfItems) {
				Company theCompany = new Company();
				theCompany.setName(objNode.get("name").asText());
				theCompany.setCode(objNode.get("code").asText());
				theCompany.setUnit(objNode.get("unit").asInt());
				listOfCompanies.add(theCompany);
			}
		}
		
		return listOfCompanies;
	}
	
	// Create list of Stock Prices
	private List<StockPrice> getListOfStockPrices(JsonNode nodeOfItems) {
		
		StockDate latestInsertedStockDate = getLatestStockDate();
		List<Company> listOfInsertedCompanies = companyService.getListOfCompanies();
		List<StockPrice> listOfStockPrices = new ArrayList<>();
		
		if(nodeOfItems.isArray()) {
			for(JsonNode objNode : nodeOfItems) {
				StockPrice theStockPrice = new StockPrice();	
				
				// Matching company code from db with code from request respond
				String currentCompanyCode = objNode.get("code").asText();
				for(int i = 0; i < listOfInsertedCompanies.size(); i++) {

					if(listOfInsertedCompanies.get(i).getCode().equals(currentCompanyCode)) {					

						theStockPrice.setCompany(listOfInsertedCompanies.get(i));
						break;
					}

				}		
				
				BigDecimal priceBigDecimal = new BigDecimal(objNode.get("price").asDouble());
				theStockPrice.setPrice(priceBigDecimal);
				theStockPrice.setStockDate(latestInsertedStockDate);
				listOfStockPrices.add(theStockPrice);
			}
		}
		
		
		return listOfStockPrices;
	}
	
	@Override
	public StockDate getLatestStockDate() {
		return stockDateRepository.getLatestStockDate();
	}
	
	@Override
	public List<StockDate> getAllStockDates() {
		return stockDateRepository.findAll();
	}
	
}
