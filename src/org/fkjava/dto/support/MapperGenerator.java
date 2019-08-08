package org.fkjava.dto.support;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MapperGenerator {
	
	/** 日期格式化对象 */
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 生成MAPPER接品与映射文件
	 * @param tableName
	 * @param mapperPackage
	 */
	public static void generatorMapper(String className, String mapperPackage){
		try{
			
			/** ####################### 生成接口类 ###################### **/
			/** 定义StringBuider来拼接生成接口类的字符串 */
			StringBuilder res = new StringBuilder();
			res.append("package " + mapperPackage + ";\n\n");
			
			/** 拼接生成注释字符串 */
			res.append("/**\n");
			res.append(" * " + className + "Mapper 数据访问类\n");
			res.append(" * @author CHUNLONG.LUO\n");
			res.append(" * @email 584614151@qq.com\n");
			res.append(" * @date " + sdf.format(Calendar.getInstance().getTime()) + "\n");
			res.append(" * @version 1.0\n");
			res.append(" */\n");
			
			/** 拼接生成接口类的字符串 */
			res.append("public interface " + className + "Mapper {\n\n");
			res.append("\n\n}");
			
			/** 将包名替换成相应的文件目录结构字符串 */
			File file = new File("src/" + mapperPackage.replaceAll("\\.", "/"));
			/** 判断是否存在该文件目录;如果不存在就创建出相应的文件目录 */
			if (!file.exists()) file.mkdirs();
			/** 在该目录下创建生存相关的DTO Java文件 */
			file = new File("src/" + mapperPackage.replaceAll("\\.", "/") + File.separator + className + "Mapper.java");
			/** 创建FileWriter输出流，将拼接好的Java源文件输出 */
			FileWriter fw = new FileWriter(file);
			fw.write(res.toString());
			fw.flush();
			fw.close();
			
			
			
			/** ####################### 生成SQL映射文件  ###################### **/
			/** 定义StringBuider来拼接生成SQL映射文件的字符串 */
			res = new StringBuilder();
			res.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n")
			   .append("<!DOCTYPE mapper\n")
			   .append("\tPUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n")
			   .append("\t\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n\n")
			   .append("<mapper namespace=\""+ mapperPackage +"."+ className +"Mapper\">\n\n")
			   .append("</mapper>");
			/** 在该目录下创建生存相关的DTO Java文件 */
			file = new File("src/" + mapperPackage.replaceAll("\\.", "/") + File.separator + className + "Mapper.xml");
			/** 创建FileWriter输出流，将拼接好的Java源文件输出 */
			fw = new FileWriter(file);
			fw.write(res.toString());
			fw.flush();
			fw.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
