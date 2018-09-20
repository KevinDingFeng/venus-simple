package com.kevin.venus.geo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kevin.venus.geo.entity.Geo;

@Repository
public interface GeoDao extends JpaRepository<Geo, Long>, JpaSpecificationExecutor<Geo> {

}
