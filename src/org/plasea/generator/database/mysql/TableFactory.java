package org.plasea.generator.database.mysql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.plasea.generator.util.NameUtil;
/**
 * 读取数据库表生成相应的java对象
 * @author 朱素海
 *
 */
public class TableFactory {

	/**
	 * 把数据库表读取成java对象
	 * @param tableName
	 * @return
	 */
	public static Table generateTable(String tableName){
				//实例化Configuration，这行代码默认加载hibernate.cfg.xml文件,如果文件没有在src根路径下，可以用路径作为参数传递
				Configuration conf =  new AnnotationConfiguration().configure();
				//以Configuration创建SessionFactory
				SessionFactory sf = conf.buildSessionFactory();
				//实例化Session
				Session session = sf.openSession();
				//开始事务
				Transaction tx = session.beginTransaction();
				
				String sql = "show table status where name='"+tableName+"'";
				
				List<Table> tables = session.createSQLQuery(sql).addEntity(Table.class).list();
				
				if(tables.size() > 1){
					System.out.println("查询表数据异常！！！！！");
				}
				
				Table table = tables.get(0);
				
				
				System.out.println(table.getTableName()+" : "+table.getComment());
				
				//查询表字段
				String sql2 = "show full fields from "+tableName;
				
				List<TableField> fields = session.createSQLQuery(sql2).addEntity(TableField.class).list();
				
				//提交事务
				tx.commit();
				//关闭Session
				session.close();
				table.setTableName(NameUtil.dealName(table.getTableName()));//对表名进行处理，变成java对象格式
				System.out.println("类表名："+table.getTableName()+" : "+table.getComment());
				
				for(TableField filed : fields){
					NameUtil.dealTable(filed);
					
					System.out.println("字段名称："+filed.getField()+" 类型："+filed.getType()+" 注释:"+filed.getComment()+"是否主键："+filed.getKey()+" 表字段名称："+filed.getColumnName()+" 是否递增："+filed.getExtra());
				}
				table.setFields(fields);
				table.setRealTableName(tableName);
				return table;
	}
	
	
	public static void main(String[] args) {
		generateTable("news_table");
	}
	
}
