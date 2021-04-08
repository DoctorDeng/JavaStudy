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
	
	public String getSQL(List<String> fields, List<String> uniqueFields, String tableName, String primaryKey, String seq_name, String dblinkName) {
		StringBuilder sb = new StringBuilder();
		sb.append("create or replace procedure pro_update_").append(tableName).append(LINE_SEPARATOR);
		appendSingleLine(sb,"is");
		appendSingleLine(sb,"cnt integer;");
		appendSingleLine(sb,"begin");
		sb.append(" FOR obj IN (SELECT * from ").append(tableName).append("@").append(dblinkName).append(" ) LOOP").append(LINE_SEPARATOR);
		appendSingleLine(sb,"select count(1)");
		appendSingleLine(sb,"into cnt");
		sb.append("from ").append(tableName).append(" obj_tab ").append(LINE_SEPARATOR);
		buildWhere(sb, uniqueFields);
		
		sb.append(LINE_SEPARATOR);
		appendSingleLine(sb, " if cnt > 0 then ");
		sb.append(" update ").append(tableName).append(" obj_tab");
		sb.append(" set ");
		
		List<String> updateFiedls = getUpdateFields(fields, uniqueFields, primaryKey);
		for (int i = 0, len = updateFiedls.size(); i < len; i++) {
			String fieldName = updateFiedls.get(i);
			sb.append("obj_tab.").append(fieldName).append(" = ").append("obj.").append(fieldName);
			if (i != len -1) {
				sb.append(",");
			} 
			sb.append(LINE_SEPARATOR);
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
	
	private List<String> getUpdateFields(List<String> fields, List<String> uniqueFields, String primaryKey) {
		List<String> updateFiedls = new ArrayList<String>();
		Set<String> uniqueFieldsSet =  listToSet(uniqueFields);
		
		for (int i = 0, len = fields.size(); i < len; i++) {
			String fieldName = fields.get(i);
			
			if (!fieldName.equals(primaryKey) &&
				!uniqueFieldsSet.contains(fieldName)) {
				updateFiedls.add(fieldName);
			}
		}
		return updateFiedls;
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
			if (i != 0 && i <= len -1) {
				sb.append(" and ");
			} 
			sb.append(" obj_tab.").append(fieldName).append(" = ").append("obj.").append(fieldName);
			if (i != 0 && i < len - 1) {
					sb.append(LINE_SEPARATOR);
			}
		}
		sb.append(";");
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
		System.out.println(getSQL(fields, uniqueFields, "WFUSER_USERGROUP", "PKID", "SEQ_WFUSER_USERGROUP_PKID", "DBL_MAIN_PORTAL"));
	}
	
	public void build_wf_master_user() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("USERID");
		fields.add("UNO");
		fields.add("UNAME");
		fields.add("UKIND");
		fields.add("UTYPE");
		fields.add("BRNO");
		fields.add("CLTNO");
		fields.add("DEPTID");
		fields.add("PWDBS");
		fields.add("PWDCS");
		fields.add("ISUSABLE");
		fields.add("ACTIVE");
		fields.add("FIRSTENTER");
		fields.add("ACTION");
		fields.add("ACTION_DTL");
		fields.add("AUTH_MODE");
		fields.add("DN");
		fields.add("FINGERPRINT");
		fields.add("ENCRYPTINFO");
		fields.add("ALLOWIP");
		fields.add("FAILCOUNT");
		fields.add("LOCKTIME");
		fields.add("UNLOCK_TIME");
		fields.add("ISDATASAFE");
		fields.add("REMARK");
		fields.add("DELETED");
		fields.add("CREATE_USER_NO");
		fields.add("CREATE_DATE");
		fields.add("ORGCLASSID");
		fields.add("FRTIME");
		fields.add("GANGWEI");
		
		
		
		uniqueFields.add("UNO");
		System.out.println(getSQL(fields, uniqueFields, "WF_MASTER_USER", "USERID", null , "DBL_MAIN_PORTAL"));
	}
	
	public void build_wfdept() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("DID");
		fields.add("DEPT_ID");
		fields.add("CUST_NO");
		fields.add("DEPT_CODE");
		fields.add("DEPT_WORD");
		fields.add("DEPT_NAME");
		fields.add("DEPT_TREENO");
		fields.add("DEPT_PID");
		fields.add("DEPT_MSC");
		fields.add("DEPT_ORDER");
		fields.add("CREATE_USER_NO");
		fields.add("CREATE_DATE");
		fields.add("MOD_USER_NO");
		fields.add("MOD_DATE");
		fields.add("ISUSABLE");
		fields.add("DELETED");
		fields.add("ORGCLASSID");
		fields.add("DEPT_PPID");
		
		uniqueFields.add("DEPT_ID");
		System.out.println(getSQL(fields, uniqueFields, "WFDEPT", "DID", "WFDEPT_SEQ", "DBL_MAIN_PORTAL"));
	}
	
	
	public void build_wfcltsofuser() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("PKID");
		fields.add("USERID");
		fields.add("CLTNO");
		fields.add("ISUSABLE");
		
		uniqueFields.add("USERID");
		uniqueFields.add("CLTNO");
		
		System.out.println(getSQL(fields, uniqueFields, "WFCLTSOFUSER", "PKID", "WFCLTSOFUSER_SEQ", "DBL_MAIN_PORTAL"));
	}
	
	public void build_wfusergroup() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("RID");
		fields.add("UGNAME");
		fields.add("GROUP_CODE");
		fields.add("SWITCH_USER");
		fields.add("ORGCLASSID");
		fields.add("BRNO");
		
		uniqueFields.add("GROUP_CODE");
		
		System.out.println(getSQL(fields, uniqueFields, "WFUSERGROUP", "RID", null, "DBL_MAIN_PORTAL"));
	}
	
	public void build_wfpersonal() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("ID");
		fields.add("USERID");
		fields.add("EMPLOYEENO");
		fields.add("NAME");
		fields.add("EMAIL");
		fields.add("MOBILE");
		fields.add("WELCOMETEXT");
		fields.add("SEXFLAG");
		fields.add("PHONE");
		fields.add("BIRTHDAY");
		fields.add("ID_CARD_NO");
		fields.add("FAX");
		
		uniqueFields.add("USERID");
		
		System.out.println(getSQL(fields, uniqueFields, "WF_PERSONAL", "ID", "WF_PERSONAL_SEQ", "DBL_MAIN_PORTAL"));
	}
	
	public void build_WF_SAFE_POLICY() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("PKID");
		fields.add("PWDNAME");
		fields.add("FLAGOV");
		fields.add("FAILCOUNTSET");
		fields.add("PWDCYCLE");
		fields.add("NOTLOGINDAY");
		fields.add("IPBINDFLAG");
		fields.add("PWDLONGS");
		fields.add("PWDLONGE");
		fields.add("UPWDDTIMES");
		fields.add("FREEZEDAYS");
		fields.add("ISENFORCEPWD");
		fields.add("ITIALPSWD");
		fields.add("ALIKEPSWD");
		fields.add("NOTETIME");
		fields.add("CREATE_USER_NO");
		fields.add("CREATE_DATE");
		fields.add("MOD_USER_NO");
		fields.add("MOD_DATE");
		fields.add("UNLOCKDELAY");
		fields.add("ISUSABLE");
		fields.add("ORGCLASSID");
		fields.add("ISDAYUNLOCKUSER");
		
		uniqueFields.add("PKID");
		
		System.out.println(getSQL(fields, uniqueFields, "WF_SAFE_POLICY", "PKID", null, "DBL_MAIN_PORTAL"));
	}
	
	public void build_WFUSER_SAFEPOLICY() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("PKID");
		fields.add("USERID");
		fields.add("PTID");
		fields.add("ISUSABLE");
		
		uniqueFields.add("USERID");
		uniqueFields.add("PTID");
		
		System.out.println(getSQL(fields, uniqueFields, "WF_SAFE_POLICY", "PKID", "SEQ_WFUSER_SAFEPOLICY_ID", "DBL_MAIN_PORTAL"));
	}
	
	public void build_WFROLEGRP() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("ID");
		fields.add("NAME");
		fields.add("REMARK");
		fields.add("CREATE_USER_NO");
		fields.add("CREATE_DATE");
		fields.add("MOD_USER_NO");
		fields.add("MOD_DATE");
		fields.add("ISUSABLE");
		fields.add("DELETED");
		fields.add("ORGCLASSID");
		fields.add("STATE");
		fields.add("BRNO");
		
		uniqueFields.add("ID");
		
		System.out.println(getSQL(fields, uniqueFields, "WFROLEGRP", "ID", null, "DBL_MAIN_PORTAL"));
	}
	
	public void build_WFROLE() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("ROLEID");
		fields.add("ROLEGROUPID");
		fields.add("ROLENO");
		fields.add("ROLENAME");
		fields.add("TREENO");
		fields.add("PROLEID");
		fields.add("DESCRIPTION");
		fields.add("DISPLAY_ORDER");
		fields.add("MAX_NUM");
		fields.add("CREATE_USER_NO");
		fields.add("CREATE_DATE");
		fields.add("MOD_USER_NO");
		fields.add("MOD_DATE");
		fields.add("ISUSABLE");
		fields.add("DELETED");
		fields.add("ORGCLASSID");
		fields.add("STATE");
		fields.add("BRNO");
		
		uniqueFields.add("ROLEID");
		
		System.out.println(getSQL(fields, uniqueFields, "WFROLE", "ID", null, "DBL_MAIN_PORTAL"));
	}
	
	
	public void build_WFUSERROLES() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("PKID");
		fields.add("USERID");
		fields.add("ROLEID");
		fields.add("ISUSABLE");
		
		uniqueFields.add("USERID");
		uniqueFields.add("ROLEID");
		
		System.out.println(getSQL(fields, uniqueFields, "WFUSERROLES", "PKID", "WFUSERROLES_SEQ", "DBL_MAIN_PORTAL"));
	}
	
	public void build_WFPAGE() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("PAGEID");
		fields.add("PAGENO");
		fields.add("PAGECODE");
		fields.add("PAGENAME");
		fields.add("APPNO");
		fields.add("RSPMODE");
		fields.add("URL");
		fields.add("CAPTION");
		fields.add("REFIMAGE");
		fields.add("REFHINT");
		fields.add("CREATE_USER_NO");
		fields.add("CREATE_DATE");
		fields.add("MOD_USER_NO");
		fields.add("MOD_DATE");
		fields.add("ID_N3");
		fields.add("DELETED");
		fields.add("ISUSABLE");
		fields.add("ISSHOWSTATUS");
		fields.add("ORGCLASSID");
		fields.add("PRODUCT_NO");
		fields.add("OPENTYPE");
		fields.add("MENUID_P");
		fields.add("CAPTION");
		
		uniqueFields.add("PAGENO");
		uniqueFields.add("PRODUCT_NO");
		
		System.out.println(getSQL(fields, uniqueFields, "WFPAGE", "PAGEID", "WFPAGE_SEQ", "DBL_MAIN_PORTAL"));
	}
	
	public void build_WFPAGESOFUSER() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("PKID");
		fields.add("USERID");
		fields.add("PAGEID");
		fields.add("PAGENO");
		fields.add("ISUSABLE");
		fields.add("PRODUCT_NO");
		
		uniqueFields.add("USERID");
		uniqueFields.add("PAGENO");
		uniqueFields.add("PRODUCT_NO");
		uniqueFields.add("PAGEID");
		
		System.out.println(getSQL(fields, uniqueFields, "WFPAGESOFUSER", "PKID", "SEQ_WFPAGESOFUSER_ID", "DBL_MAIN_PORTAL"));
	}
	
	public void build_WFPAGESOFROLE() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("PKID");
		fields.add("ROLEID");
		fields.add("PAGEID");
		fields.add("PAGENO");
		fields.add("ISUSABLE");
		fields.add("PRODUCT_NO");
		
		uniqueFields.add("ROLEID");
		uniqueFields.add("PAGENO");
		uniqueFields.add("PRODUCT_NO");
		uniqueFields.add("PAGEID");
		
		System.out.println(getSQL(fields, uniqueFields, "WFPAGESOFROLE", "PKID", "WFPAGESOFROLE_SEQ", "DBL_MAIN_PORTAL"));
	}
	
	public void build_WFPAGESOFROLEGRP() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("PKID");
		fields.add("RGOUPID");
		fields.add("PAGEID");
		fields.add("PAGENO");
		fields.add("ISUSABLE");
		fields.add("PRODUCT_NO");
		
		uniqueFields.add("RGOUPID");
		uniqueFields.add("PAGENO");
		uniqueFields.add("PRODUCT_NO");
		uniqueFields.add("PAGEID");
		
		System.out.println(getSQL(fields, uniqueFields, "WFPAGESOFROLEGRP", "PKID", "WFPAGESOFROLEGRP_SEQ", "DBL_MAIN_PORTAL"));
	}
	
	public void build_WF_MENU_TREE() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("MENUID");
		fields.add("CAPTION");
		fields.add("TREENO");
		fields.add("IN_NEW_WINDOW");
		fields.add("PAGENO");
		fields.add("MENU_FROM");
		fields.add("MENU_LEVEL");
		fields.add("SORTNUM");
		fields.add("PMID");
		fields.add("ISHIDDEN");
		fields.add("MENU_CODE");
		fields.add("MENU_WORD");
		fields.add("CREATE_USER_NO");
		fields.add("CREATE_DATE");
		fields.add("MOD_USER_NO");
		fields.add("CREATE_DATE");
		fields.add("MOD_DATE");
		fields.add("PATH");
		fields.add("ISLEAF");
		fields.add("DESCRIPTION");
		fields.add("LOGO");
		fields.add("LISTICON");
		fields.add("NEWTREE");
		fields.add("TREEDATE");
		fields.add("PRODUCT_NO");
		
		uniqueFields.add("MENUID");
		
		System.out.println(getSQL(fields, uniqueFields, "WF_MENU_TREE", "MENUID", null, "DBL_MAIN_PORTAL"));
	}
	
	
	public void build_WF_NOTICE() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("NID");
		fields.add("TITLE");
		fields.add("CONTENT");
		fields.add("SENDERID");
		fields.add("RECEIVEIDS");
		fields.add("RECEIVEROLES");
		fields.add("RECEIVERCLTS");
		fields.add("CREATETIME");
		fields.add("STATE");
		fields.add("TYPE");
		fields.add("POPUP");
		
		uniqueFields.add("MENUID");
		
		System.out.println(getSQL(fields, uniqueFields, "WF_NOTICE", "NID", null, "DBL_MAIN_PORTAL"));
	}
	public void build_WF_NOTICE_RECEIVE() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("RID");
		fields.add("NID");
		fields.add("TITLE");
		fields.add("USERID");
		fields.add("RECEIVETIME");
		fields.add("STATE");
		fields.add("TYPE");
		fields.add("POPUP");
		
		uniqueFields.add("RID");
		
		System.out.println(getSQL(fields, uniqueFields, "WF_NOTICE_RECEIVE", "RID", null, "DBL_MAIN_PORTAL"));
	}
	public void build_NSBRANCH() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("brno");
		fields.add("brname");
		fields.add("iscenter");
		fields.add("ctacntno");
		fields.add("bankacntno");
		fields.add("openbank");
		fields.add("address");
		
		uniqueFields.add("RID");
		
		System.out.println(getSQL(fields, uniqueFields, "NSBRANCH", "brno", null, "DBL_MAIN_PORTAL"));
	}
	public void build_WF_ATTACHMENT() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("id");
		fields.add("nid");
		fields.add("file_blob");
		fields.add("file_name");
		
		uniqueFields.add("nid");
		
		System.out.println(getSQL(fields, uniqueFields, "WF_ATTACHMENT", "id", "WFPAGESOFROLEGRP_SEQ", "DBL_MAIN_PORTAL"));
	}
	public void build_WFBRANCH_PAGE_MAPPING() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("BRNO");
		fields.add("PAGENO");
		fields.add("ISUSABLE");
		fields.add("PAGEID");
		fields.add("PRODUCT_NO");
		
		uniqueFields.add("BRNO");
		uniqueFields.add("PAGENO");
		uniqueFields.add("PAGEID");
		uniqueFields.add("PRODUCT_NO");
		
		System.out.println(getSQL(fields, uniqueFields, "WFBRANCH_PAGE_MAPPING", null, null, "DBL_MAIN_PORTAL"));
	}
	public void build_WF_PRODUCT() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("product_no");
		fields.add("product_name");
		fields.add("product_url");
		fields.add("type");
		
		uniqueFields.add("product_no");
		
		System.out.println(getSQL(fields, uniqueFields, "WF_PRODUCT", "product_no", null, "DBL_MAIN_PORTAL"));
	}
	public void build_WF_PRODUCT_INNERURL() {
		List<String> fields       = new ArrayList<String>();
		List<String> uniqueFields = new ArrayList<String>();
		fields.add("product_no");
		fields.add("INNER_URL");
		
		uniqueFields.add("product_no");
		
		System.out.println(getSQL(fields, uniqueFields, "WF_PRODUCT_INNERURL", "product_no", null, "DBL_MAIN_PORTAL"));
	}

	private StringBuilder appendSingleLine(StringBuilder sb, String str) {
		return sb.append(str).append(LINE_SEPARATOR);
	}
	
	public static void main(String[] args) {
		SQLUtil sql = new SQLUtil();
		sql.build_wfuser_usergroup();
		//sql.build_wf_master_user();
		//sql.build_wfdept();
		//sql.build_wfcltsofuser();
		//sql.build_wfusergroup();
		//sql.build_wfpersonal();
		//sql.build_WF_SAFE_POLICY();
		//sql.build_WFUSER_SAFEPOLICY();
		//sql.build_WFROLEGRP();
		//sql.build_WFROLE();
		//sql.build_WFUSERROLES();
		//sql.build_WFPAGE();
		//sql.build_WFPAGESOFUSER();
		//sql.build_WFPAGESOFROLE();
		//sql.build_WFPAGESOFROLEGRP();
		//sql.build_WF_MENU_TREE();
		//sql.build_WF_NOTICE();
		//sql.build_WF_NOTICE_RECEIVE();
		//sql.build_NSBRANCH();
		//sql.build_WF_ATTACHMENT();
		//sql.build_WFBRANCH_PAGE_MAPPING();
		//sql.build_WF_PRODUCT();
		//sql.build_WF_PRODUCT_INNERURL();
	}
}
