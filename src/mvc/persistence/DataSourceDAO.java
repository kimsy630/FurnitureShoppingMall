package mvc.persistence;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceDAO {
	private static DataSourceDAO instance=null;
	
	DataSource dataSource;
	private DataSourceDAO() {
		try {
			Context context=new InitialContext();
			dataSource=(DataSource)context.lookup("java:comp/env/jdbc/kim");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static DataSourceDAO getInstance() {
		if(instance==null)instance=new DataSourceDAO();
		return instance;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
}
