package util;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBhelper2 {
	public Connection conn=null;
	InitialContext ctx;
	public DBhelper2(){
		try {
			ctx = new InitialContext();
			DataSource ds=(DataSource) ctx.lookup("java:comp/env/LOGINSHOPPING");//��ȡ���ӳض���
			conn=ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
