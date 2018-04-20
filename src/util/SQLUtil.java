package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author denghuajie
 * 
 * @since 1.1.2
 * 
 */
public class SQLUtil {
	private final String CONST_TABLE_ALIAS = "obj_tab";
	private final String SYMBOL_SPOT = ".";
	private final String SYMBOL_EQUAL_SIGN = " = ";
	private final String CONST_OBJECT_ALIAS = "obj";
	private final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	public String getSQL(List<String> fields, List<String> uniqueFields, String tableName, String primaryKey, String seq_name) {
		StringBuilder sb = new StringBuilder();
		sb.append("create or replace procedure pro_update_").append(tableName).append(LINE_SEPARATOR);
		appendSingleLine(sb,"is");
		appendSingleLine(sb,"cnt integer;");
		appendSingleLine(sb,"begin");
		sb.append(" FOR obj IN (SELECT * from ").append(tableName).append(" ) LOOP").append(LINE_SEPARATOR);
		appendSingleLine(sb,"select count(1)");
		appendSingleLine(sb,"into cnt");
		sb.append("from ").append(tableName).append(" obj_tab ").append(LINE_SEPARATOR);
		buildWhere(sb, uniqueFields);
		
		sb.append(LINE_SEPARATOR);
		appendSingleLine(sb, " if cnt > 0 then ");
		sb.append(" update ").append(tableName).append(" obj_tab");
		sb.append(" set ");
		
		Set<String> uniqueFieldsSet =  listToSet(uniqueFields);
		for (int i = 0, len = fields.size(); i < len; i++) {
			String fieldName = fields.get(i);
			
			if (fieldName.equals(primaryKey) || uniqueFieldsSet.contains(fieldName)) {
				continue;
			}
			
			if (i == 0) {
				sb.append("obj_tab.").append(fieldName).append(" = ").append("obj.").append(fieldName).append(LINE_SEPARATOR);
			} 
			else {
				sb.append(" and ").append("obj_tab.").append(fieldName).append(" = ").append("obj.").append(fieldName);
				if (i == len - 1) {
					sb.append(";");
				} else {
					sb.append(LINE_SEPARATOR);
				}
			}
		}

		buildWhere(sb, uniqueFields).append(LINE_SEPARATOR);
		appendSingleLine(sb," else ");
		
		sb.append(" insert into ").append(tableName).append(LINE_SEPARATOR);
		sb.append("(");
		buildFields(sb, fields);
		sb.append(")").append(LINE_SEPARATOR);
		appendSingleLine(sb," values ");
		sb.append("(");
		buildValues(sb, fields, primaryKey, seq_name);
		sb.append(");").append(LINE_SEPARATOR);
		appendSingleLine(sb," end if; ");
		appendSingleLine(sb," END LOOP; ");
		appendSingleLine(sb,"end; ");
		appendSingleLine(sb,"/");
		
		return sb.toString();
	}
	
	private <T> Set<T>  listToSet(List<T> list) {
		if (list == null) {
			return null;
		}
		
		Set<T> set = new HashSet<T>();
		for (int i = 0, len = list.size(); i < len; i++) {
			T t = list.get(i);
			set.add(t);
		}
		return set;
	}
	
	private StringBuilder buildWhere(StringBuilder sb, List<String> uniqueFields) {
		sb.append(" where ");
		for (int i = 0, len = uniqueFields.size(); i < len; i++) {
			String fieldName = uniqueFields.get(i);
			if (i == 0) {
				sb.append(" obj_tab.").append(fieldName).append(" = ").append("obj.").append(fieldName).append(LINE_SEPARATOR);
			} 
			else {
				sb.append(" and ").append("obj_tab.").append(fieldName).append(" = ").append("obj.").append(fieldName);
				if (i == len - 1) {
					sb.append(";");
				} else {
					sb.append(LINE_SEPARATOR);
				}
			}
		}
		
		return sb;
	}
	
	private StringBuilder buildFields(StringBuilder sb, List<String> fields) {
		for (int i = 0, len = fields.size(); i < len; i++) {
			String fieldName = fields.get(i);
			if (i == len - 1) {
				sb.append(fieldName);
			} else {
				sb.append(fieldName).append(",").append(LINE_SEPARATOR);
			}
		}
		
		return sb;
	}
	
	private StringBuilder buildValues(StringBuilder sb, List<String> fields, String primaryKey, String seq_name) {
		boolean hasSeq = (seq_name != null && seq_name.length() > 0);
		
		for (int i = 0, len = fields.size(); i < len; i++) {
			String fieldName = fields.get(i);
			
			if (fieldName.equals(primaryKey) && hasSeq) {
				sb.append(seq_name).append(".NEXTVAL");
			} else {
				sb.append("obj_tab.").append(fieldName);
			}
			
			if (i != len - 1) {
				sb.append(",").append(LINE_SEPARATOR);
			} else {
				
			}
		}
		
		return sb;
	}
	
	public void build_wfuser_usergroup() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("PKID");
		fields.add("URID");
		fields.add("UUID");
		fields.add("ISUSABLE");
		uniqueFields.add("URID");
		uniqueFields.add("UUID");
		System.out.println(getSQL(fields, uniqueFields, "WFUSER_USERGROUP", "PKID", "SEQ_WFUSER_USERGROUP_PKID"));
	}
	
	
	private StringBuilder appendSingleLine(StringBuilder sb, String str) {
		return sb.append(str).append(LINE_SEPARATOR);
	}
	
	public static void main(String[] args) {
		SQLUtil sql = new SQLUtil();
		sql.build_wfuser_usergroup();
	}
}
