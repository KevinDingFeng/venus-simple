package com.kevin.venus.geo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kevin.venus.entity.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 地域
 * 	国家、省（直辖市）、地级市
 * 测试 有三个层级的实体，怎么设置数据和上下级关系
 * 
 * @author 程任强
 *
 */
@Entity
@Table
@Data
@ToString(callSuper = true, exclude = {  })
@EqualsAndHashCode(callSuper = true, exclude = {  })
public class Geo extends BaseEntity {

	
	/**
	 * 编号
	 */
	private int code;

	/**
	 * 名称
	 */
	@Column(nullable = false, length = 64)
	private String name;
	
	/**
	 * 级别
	 */
	private int level;
	
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_code", referencedColumnName = "code")
	private Geo parent;

	/**
	 * 父级 编号
	 */
	@Column(name = "parent_code", insertable = false, updatable = false, nullable = true)
	private Integer parentCode;
	
}
