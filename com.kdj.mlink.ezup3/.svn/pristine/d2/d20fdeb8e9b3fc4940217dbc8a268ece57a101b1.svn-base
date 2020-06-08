package com.kdj.mlink.ezup3.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.kdj.mlink.ezup3.common.AES256Util;
import com.kdj.mlink.ezup3.common.YDMAProperties;

public class DBCPInit {

	private static final Log LOGGER = LogFactory.getLog(DBCPInit.class);

	private static DBCPInit instance;

	public static DBCPInit getInstance() throws Exception {

		if (instance == null) {
			instance = new DBCPInit();
			LOGGER.info("DBManager initialize: {}");
		}
		return instance;

	}

	public DBCPInit() throws Exception {
		initConnectionPool();
	}

	public void initConnectionPool() throws Exception {

		// 커넥션 풀에서 사용 할 수 있는 드라이버 로딩
		LOGGER.info("================Apache DBCP2 Init Start================");

		String jdbcDriver = YDMAProperties.getInstance().getAppProperty("jdbc.driver");
		// String jdbcDriver = "com.mysql.jdbc.Driver";

		Class.forName(jdbcDriver);

		String jdbcurl = YDMAProperties.getInstance().getAppProperty("jdbc.url");
		String user = YDMAProperties.getInstance().getAppProperty("jdbc.username");
		user = AES256Util.getInstance(AES256Util.KEY_WORD).decrypt(user);
		String pwd = YDMAProperties.getInstance().getAppProperty("jdbc.password");
		pwd = AES256Util.getInstance(AES256Util.KEY_WORD).decrypt(pwd);

		// 커넥션풀이 새로운 커넥션을 생성할 때 사용할 커넥션팩토리를 생성.
		ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcurl, user, pwd);

		// PoolableConnection을 생성하는 팩토리 생성.
		// DBCP는 커넥션을 보관할 때 PoolableConnection 을 사용
		// 실제 커넥션을 담고 있있으며, 커넥션 풀을 관리하는데 필요한 기능을 제공한다.
		// 커넥션을 close하면 종료하지 않고 커넥션 풀에 반환
		PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);

		// 커넥션이 유효한지 여부를 검사할 때 사용하는 쿼리를 지정한다.
		poolableConnFactory.setValidationQuery("select 1");

		// 커넥션 풀의 설정 정보를 생성한다.
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		// 유휴 커넥션 검사 주기
		poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
		// 풀에 보관중인 커넥션이 유효한지 검사할지 유무 설정
		poolConfig.setTestWhileIdle(true);
		// 기본값 : false /true 일 경우 validationQuery 를 매번 수행한다.
		poolConfig.setTestOnBorrow(false);

		// 커넥션 최소 개수
		int maxCount = 5;
		poolConfig.setMinIdle(3);
		poolConfig.setMaxIdle(maxCount);// maxIdle와 maxTotal 은 같아야함
		// 커넥션 최대 개수
		poolConfig.setMaxTotal(maxCount);// maxIdle와 maxTotal 은 같아야함

		// 커넥션 풀을 생성. 생성자는 PoolabeConnectionFactory와
		// GenericObjectPoolConfig를 사용
		GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<PoolableConnection>(
				poolableConnFactory, poolConfig);
		// PoolabeConnectionFactory에도 커넥션 풀을 연결

		poolableConnFactory.setPool(connectionPool);

		// 커넥션 풀을 제공하는 jdbc 드라이버를 등록.
		Class.forName("org.apache.commons.dbcp2.PoolingDriver");

		PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");

		// 위에서 커넥션 풀 드라이버에 생성한 커넥션 풀을 등록[ydincdb]
		driver.registerPool("ydincdb", connectionPool);

		LOGGER.info("================Apache DBCP2 Init End================");

	}

	static int connectionCount = 0;

	public Connection getConnection() throws Exception {
		Connection con = null;
		con = DriverManager.getConnection("jdbc:apache:commons:dbcp:ydincdb");
		connectionCount++;
		System.out.println(
				"현재 총 커넥션 갯수 : =================================================================================> "
						+ connectionCount);
		return con;
	}

	public void freeConnection(Connection con, List<PreparedStatement> list, ResultSet rs) {
		try {
			freeResultSet(rs);

			for (PreparedStatement pstmt : list) {
				freeStatement(pstmt);
			}

			freeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(Connection con, List<PreparedStatement> pstmtlist, List<ResultSet> rsList) {
		try {

			for (ResultSet rs : rsList) {
				freeResultSet(rs);
			}

			for (PreparedStatement pstmt : pstmtlist) {
				freeStatement(pstmt);
			}

			freeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			freeResultSet(rs);
			freeStatement(pstmt);
			freeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void freeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
				connectionCount--;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void freeStatement(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void freeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
