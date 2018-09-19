package com.kevin.venus.geo.entity;

import javax.persistence.Entity;
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

}
