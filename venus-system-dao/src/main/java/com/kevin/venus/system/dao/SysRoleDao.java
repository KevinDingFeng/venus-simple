package com.kevin.venus.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kevin.venus.system.entity.SysRole;
@Repository
public interface SysRoleDao extends JpaRepository<SysRole, Long>, JpaSpecificationExecutor<SysRole> {


}
