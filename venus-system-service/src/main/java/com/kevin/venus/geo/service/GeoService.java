package com.kevin.venus.geo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.venus.geo.dao.GeoDao;
import com.kevin.venus.geo.entity.Geo;

@Service
public class GeoService {

	@Autowired
	private GeoDao geoDao;

	public List<Geo> findAll() {
		return geoDao.findAll();
	}
}
