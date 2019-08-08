package org.fkjava.dto.support;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;


public final class DTOGenerator {
	
	/** 日期格式化对象 */
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 生成对应的DTO源文件
	 * @param tablePrefix 表的前缀名
	 * @param tableName 表名
	 * @param packageName 生成的包名
	 * @param columns 列的集合
	 */
	public static void generatorDto(String className, 
					String packageName, Map<String, Integer> columns) {
		try{
			
			/** 定义StringBuider来拼接生成类的字符串 */
			StringBuilder res = new StringBuilder();
			res.append("package " + packageName + ";\n\n");
			
			/** 拼接生成注释字符串 */
			res.append("/**\n");
			res.append(" * " + className + " 数据传输类\n");
			res.append(" * @author CHUNLONG.LUO\n");
			res.append(" * @email 584614151@qq.com\n");
			res.append(" * @date " + sdf.format(Calendar.getInstance().getTime()) + "\n");
			res.append(" * @version 1.0\n");
			res.append(" */\n");
			
			/** 拼接生成类的字符串 */
			res.append("public class " + className + " implements java.io.Serializable{\n\n");
			res.append("\tprivate static final long serialVersionUID = 1L;\n");
			/** 生成DTO类中相关属性 */
			for (Map.Entry<String, Integer> map : columns.entrySet()){
				/** 处理数据类型(数据库的数据类型转化成Java相关类型) */
				String fieldType = toJavaType(map.getValue());
				/** 定义属性名： 将数据库表中的列名全部转换成小写字母，作为属性名 */
				String fieldName = map.getKey().toLowerCase();
				/** 将属属中有"_"符号隔开的单词首字母大写  */
				String[] fields = fieldName.split("_");
				fieldName = fields[0];
				for (int i = 1; i < fields.length; i++){
					fieldName += StringUtils.transfer(fields[i]);
				}
				res.append("\tprivate " + fieldType + " " + fieldName + ";\n");
			}
			
			/** 生成对应的setter与getter方法 */
			res.append("\n\t/** setter and getter method */\n");
			for (Map.Entry<String, Integer> map : columns.entrySet()){
				/** 处理数据类型(数据库的数据类型转化成Java相关类型) */
				String fieldType = toJavaType(map.getValue());
				/** 定义属性名： 将数据库表中的列名全部转换成小写字母，作为属性名 */
				String fieldName = map.getKey().toLowerCase();
				/** 将属属中有"_"符号隔开的单词首字母大写  */
				String[] fields = fieldName.split("_");
				fieldName = fields[0];
				for (int i = 1; i < fields.length; i++){
					fieldName += StringUtils.transfer(fields[i]);
				}
				/** setter方法 */
				res.append("\tpublic void set" + StringUtils.transfer(fieldName) + "(");
				res.append(fieldType + " " + fieldName);
				res.append("){\n");
				res.append("\t\tthis." + fieldName + " = " + fieldName + ";\n");
				res.append("\t}\n");
				/** getter方法 */
				res.append("\tpublic " + fieldType + " get" + StringUtils.transfer(fieldName) + "(){\n");
				res.append("\t\treturn this." + fieldName + ";\n");
				res.append("\t}\n");
			}
			res.append("\n}");
			
			/** 将包名替换成相应的文件目录结构字符串 */
			File file = new File("src/" + packageName.replaceAll("\\.", "/"));
			/** 判断是否存在该文件目录;如果不存在就创建出相应的文件目录 */
			if (!file.exists()) file.mkdirs();
			/** 在该目录下创建生存相关的DTO Java文件 */
			file = new File("src/" +packageName.replaceAll("\\.", "/") + File.separator + className + ".java");
			/** 创建FileWriter输出流，将拼接好的Java源文件输出 */
			FileWriter fw = new FileWriter(file);
			fw.write(res.toString());
			fw.flush();
			fw.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 处理数据类型(数据库的数据类型转化成Java相关类型)
	 * @param dataType
	 * @return Java相关类型
	 */
	private static String toJavaType(int dataType){
		switch (dataType){
			case -5:
				return "long";
			case 4:
				return "int";
			case 5:
				return "short";
			case 8:
				return "double";
			case 12:
				return "String";
			case 91:
			case 93:
				return "java.util.Date";
		}
		return "String";
	}
}