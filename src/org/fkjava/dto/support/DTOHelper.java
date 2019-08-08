package org.fkjava.dto.support;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.fkjava.dto.support.DTOGenerator;


public class DTOHelper {
	
	/** 定义查询表的SQL语句 */
	private static final String QUERY_SQL = "SELECT table_name FROM information_schema.TABLES WHERE table_schema = ?";
	/** 定义默认加载的属性文件 */
	private static final String DTO_PROPS = "dto.properties";
	/** 定义生成DTO相关配置属性 */
	private String driverClass;
	private String jdbcUrl;
	private String user;
	private String password;
	/** 数据传输对象包名 */
	private String domainPackage;
	/** 数据访问对象包名 */
	private String mapperPackage;
	/** 数据库表的前缀 */
	private String tablePrefix;
	
	/** 构造器 */
	public DTOHelper(){
		Properties props = new Properties();
		try {
			InputStream is = DTOHelper.class.getClassLoader().getResourceAsStream(DTO_PROPS);
			if (is != null) {
				props.load(is);
				this.driverClass = props.getProperty("dto.driverClass");//获取驱动
				this.jdbcUrl = props.getProperty("dto.jdbcUrl");
				this.user = props.getProperty("dto.user");
				this.password = props.getProperty("dto.password");
				this.domainPackage = props.getProperty("dto.package");
				this.tablePrefix = props.getProperty("dto.tablePrefix");
				this.mapperPackage = props.getProperty("dto.mapper");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建DTO实体类
	 * @throws Exception
	 */
	public void createDto() throws Exception{

		/** 获取数据库名 jdbc:mysql://localhost:3306/ecshop*/
		String databaseName = jdbcUrl.substring(jdbcUrl.lastIndexOf("/") + 1);
		/** 加载驱动类 */
		Class.forName(driverClass);
		//获取连接
		Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
		//发送sq语句
		PreparedStatement pstmt = conn.prepareStatement(QUERY_SQL);
		pstmt.setString(1, databaseName);
		ResultSet rs = pstmt.executeQuery();
		// 保存该数据库中所有表名
		List<String> tables = new ArrayList<>();
		while (rs.next()){
			tables.add(rs.getString(1));
		}
		// 循环所有的表   Ec_article  ec_Article_type
		for (String tableName : tables){
			// 操作一张表
			pstmt = conn.prepareStatement("SELECT * FROM " + databaseName + "." + tableName + " LIMIT 0,1");
			//元数据    -- 获取表中有哪些列 以及每一列的数据类型
			ResultSetMetaData rd = pstmt.getMetaData();
			// 保存一张表的列名与列的类型信息
			Map<String, Integer> columns = new LinkedHashMap<>();
			for (int i = 0; i < rd.getColumnCount(); i++){
				System.out.println("列名"+rd.getColumnName(i + 1)+" 列类型:"+rd.getColumnType(i + 1));
				columns.put(rd.getColumnName(i + 1), rd.getColumnType(i + 1));
			}
			
			/** 通过表名生成DTO的类名 */
			String className = tableName.toLowerCase().replace(tablePrefix.toLowerCase(), ""); // 替换掉表的前缀
			/** 将类名中有"_"符号隔开的单词首字母大写 */ 
			String[] names = className.split("_"); // 将所有用_分隔的首字母大写
			className = StringUtils.transfer(names[0]);
			for (int i = 1; i < names.length; i++){
				className += StringUtils.transfer(names[i]);
			}
			// 得到一张表信息后，写出对应的DTO   className:类名   domainPackage：包名   columns：存放属性名称以及类型
			DTOGenerator.generatorDto(className, domainPackage, columns);
			// 写出该表对应的MAPPER接口与映射文件
			MapperGenerator.generatorMapper(className, mapperPackage);
		}
	}
	
	
	/** setter and getter method */
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDomainPackage() {
		return domainPackage;
	}
	public void setDomainPackage(String domainPackage) {
		this.domainPackage = domainPackage;
	}
	public String getTablePrefix() {
		return tablePrefix;
	}
	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}
}