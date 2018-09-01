package com.kevin.venus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kevin.venus.entity.SysPool;
@Repository
public interface SysPoolDao extends JpaRepository<SysPool, Long>, JpaSpecificationExecutor<SysPool> {

}
