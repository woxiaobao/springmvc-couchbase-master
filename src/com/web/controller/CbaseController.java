package com.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.web.common.cbase.utils.CbaseUtils;
import com.web.common.couchbase.CouchbaseClient;
import com.web.common.web.utils.RequestUtils;


@Controller
@RequestMapping("/cbase")
public class CbaseController {
	public static Logger log = LogManager.getLogger(CbaseController.class);

	/**
	 * 这个用来测试是否可用
	 * URI:	http://localhost:8089/springmvc-couchbase-master/cbase/hi
	 * @param request
	 * @param response
	 * @return
	 */
 	@RequestMapping(value = "/hi", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
 	public @ResponseBody String hello(HttpServletRequest request, HttpServletResponse response) {
 		//logger.info("测试hi");
 		return "Hello World !!!";
 	}
 	
 	/**
 	 * 
 	 * 向couchbase新增数据
 	 * URI:	http://localhost:8089/springmvc-couchbase-master/cbase/add
 	 * @param request
 	 * @param response
 	 * @return
 	 */
  	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  	public @ResponseBody String add(HttpServletRequest request, HttpServletResponse response) {
  		log.info("CbaseController come here ");
  		long now = System.currentTimeMillis();
  		Bucket bucket = CouchbaseClient.getBucket();
  		
  		long connect = System.currentTimeMillis();
  		log.info("CbaseController connect time : " + (connect-now));
  		
  		//生成id counter("rest:id", 1, 1) 
  		long id = bucket.counter("rest:id", 1, 1).content();
  		
  		//获取request 中的参数返回map
  		Map<String, Object> params = RequestUtils.getQueryParams(request);
  		JsonObject content = CbaseUtils.getJsonObject(params);
  		//为数据中加入id
  		content.put("id", id);
  		
  		//log.info("CbaseController request info : " + content.toString());
  		
  		//保存到couchbase数据库中
  		JsonDocument doc = bucket.upsert(JsonDocument.create("rest:data:"+id, content));
  		long time = System.currentTimeMillis();
  		log.info("CbaseController add date time : " + (time-connect) + ",data : " + doc.toString());
  		return "success";
  	}
}
