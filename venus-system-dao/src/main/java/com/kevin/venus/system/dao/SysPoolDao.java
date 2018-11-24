package com.kevin.venus.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kevin.venus.system.entity.SysPool;
@Repository
public interface SysPoolDao extends JpaRepository<SysPool, Long>, JpaSpecificationExecutor<SysPool> {

	List<SysPool> findByRemoved(boolean removed);

}
