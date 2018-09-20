package com.kevin.venus.geo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.kevin.venus.geo.entity.Geo;
import com.kevin.venus.geo.service.GeoService;
import com.kevin.venus.utils.JsonUtils;

@RestController
@RequestMapping(value = "/geo")
public class GeoController {

	@Autowired
	private GeoService geoService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list() {
		List<Geo> list = geoService.findAll();
		return JsonUtils.getSuccessJSONObject(list);
	}
}
