package org.plasea.generator.database.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
/**
 * 表字段对象
 * @author 朱素海
 *
 */
@Entity
public class TableField {

	public TableField(){
		
	}
	
	
	
	/**
	 * 字段名称，对象属性名称
	 */
	@Transient
	private String field;
	
	
	/**
	 * 字段类型
	 */
	@Column(name="type")
	private String type;
	
	
	/**
	 * 是否是主键（PRI）
	 */
	@Column(name="key")
	private String key;
	
	
	/**
	 * 是否递增(auto_increment)
	 */
	@Column(name="extra")
	private String extra;
	
	
	/**
	 * 字段注释
	 */
	@Column(name="comment")
	private String comment;
	
	
	/**
	 * 表字段名称，例如：create_time
	 */
	@Id
	@Column(name="field")
	private String columnName;
	
	@Transient
	private String importType;
	
	
	

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	
	
	
}
