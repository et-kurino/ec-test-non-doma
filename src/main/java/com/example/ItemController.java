package com.example;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
public class ItemController {
	/*@Autowired
	ItemService itemService;*/
	/*
	 * 1.->main繧｢繧ｯ繧ｻ繧ｹ�ｼ井ｸ�隕ｧ陦ｨ遉ｺ縲��ｼ九��繝ｦ繝ｼ繧ｶ讀懃ｴ｢繝輔か繝ｼ繝��ｼ�
	 * 2.->detail繧｢繧ｯ繧ｻ繧ｹ�ｼ井ｸ�隕ｧ蜀�縺ｮ蝠�蜩∫判蜒擾ｼ�path譬ｼ邏搾ｼ峨��detail繝ｪ繝ｳ繧ｯ縺九ｉ�ｼ�
	 * 2-2.->search繧｢繧ｯ繧ｻ繧ｹ(繝ｦ繝ｼ繧ｶ讀懃ｴ｢繝輔か繝ｼ繝�縺九ｉ)
	 * */
	/*@Autowired
	private Item item;*/
	
	private List<Item> items = new ArrayList(){};


	// @Autowired
	/* @Qualifier("zipCodeSearchRestTemplate")
	 RestTemplate restTemplate;
	
	 
	 private static final String URL = "/items.json";

	 
	 public ZipCodeDto service(String item_cd) {
	        return restTemplate.getForObject(URL, ZipCodeDto.class, item_cd);
	    }*/
	
	String json;
	
	
    		
    		//readTree(new File("items.json"));
	
	
	
	
	//init 全件
	@GetMapping("/items")
	public String init(Model model) throws JsonProcessingException, IOException {
		
		//model.addAttribute("item", new Item());
		
		//model.addAttribute("items", itemService.getItems());
		
		/*item.itemCd = "1";
		item.itemNm = "";
		item.*/
		
		//Item new Item 
		
		/*for(Item i: items) {
			items.add(i);
		}*/
		//String json = items.json;
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(new File("C:/Users/et-kurino/eclipse-workspace/ec-test-demo-316/src/main/resources/static/items.json"));
		for (JsonNode n : root.get("items")) {
		    String item_cd = n.get("item_cd").asText();
		    JsonNode item_data = n.get("item_data");
		    String itemNm = item_data.get("itemNm").asText();
		    String itemVal = item_data.get("itemVal").asText();
		    String category = item_data.get("category").asText();
		    String imgSrc = item_data.get("imgSrc").asText();
		    String explain = item_data.get("explain").asText();
		    
		    Item item = new Item();
		    item.itemCd = item_cd;
		    item.itemNm = itemNm;
		    item.itemVal = itemVal;
		    item.category = category;
		    item.imgSrc = imgSrc;
		    item.explain = explain;

			items.add(item);
   
		 System.out.println(items);
		}
		
		model.addAttribute("items",items);
		return "item_list";
	}
	
	
	//search 検索結果
	@PostMapping("/item/search-list")
	public String search(@ModelAttribute Item item) {
		
		
		
		return "item_list"
		;}
	
	
	//detail 隧ｳ邏ｰ驕ｷ遘ｻ
	@RequestMapping(value="/item/{itemCd}", method = RequestMethod.GET)
	public String detail(@PathVariable("itemCd") String pItemCd, Model model) throws JsonProcessingException, IOException {
		
	    Item item = new Item();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(new File("C:/Users/et-kurino/eclipse-workspace/ec-test-demo-316/src/main/resources/static/items.json"));
		for (JsonNode n : root.get("items")) {
		    String itemCd = n.get("item_cd").asText();
		    
		    if(itemCd.equals(pItemCd)) {
		    JsonNode item_data = n.get("item_data");
		    String itemNm = item_data.get("itemNm").asText();
		    String itemVal = item_data.get("itemVal").asText();
		    String category = item_data.get("category").asText();
		    String imgSrc = item_data.get("imgSrc").asText();
		    String explain = item_data.get("explain").asText();
		    
		    item.itemCd = itemCd;
		    item.itemNm = itemNm;
		    item.itemVal = itemVal;
		    item.category = category;
		    item.imgSrc = imgSrc;
		    item.explain = explain;
		    }
		}
		
		model.addAttribute("itemData", item);
		return "item_detail";
	}
	
	
	
	
}
