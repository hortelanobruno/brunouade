package com.callistech.policyserver.dsm.session.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.FastTreeMap;
import org.apache.log4j.Logger;

import com.callistech.policyserver.af.entities.DynamicServiceSessionState;
import com.callistech.policyserver.af.entities.vo.CountingTimeTypeVO;
import com.callistech.policyserver.common.utils.HashCode;
import com.callistech.policyserver.configuration.utils.constants.database.ConstantsDataBaseTable;
import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.subscriber.SubscriberDS;
import com.callistech.policyserver.dsm.core.DSMCore;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBManager {

	private ComboPooledDataSource cpdsDB = null;
	private ComboPooledDataSource cpdsDBReport = null;
	private static final String ODBC_DRIVER = "com.mysql.jdbc.Driver";
	private DSMCore dsmCore;
	private Logger logger = Logger.getLogger(getClass());

	public DBManager(DSMCore dsmCore) {
		this.dsmCore = dsmCore;
	}

	public void init() {
		if (cpdsDB == null) {
			try {
				cpdsDB = new ComboPooledDataSource();
				cpdsDB.setDriverClass(ODBC_DRIVER);
				cpdsDB.setJdbcUrl(dsmCore.getConfiguration().getDbConfiguration().getUrl());
				cpdsDB.setUser(dsmCore.getConfiguration().getDbConfiguration().getUser());
				cpdsDB.setPassword(dsmCore.getConfiguration().getDbConfiguration().getPassword());
				cpdsDB.setMinPoolSize(5);
				cpdsDB.setAcquireIncrement(5);
				cpdsDB.setMaxPoolSize(100);
				cpdsDB.setInitialPoolSize(10);
				cpdsDB.setNumHelperThreads(60);
				cpdsDB.setAutoCommitOnClose(true);
				cpdsDB.setTestConnectionOnCheckin(false);
				cpdsDB.setTestConnectionOnCheckout(false);
				cpdsDB.setDebugUnreturnedConnectionStackTraces(false);

				cpdsDB.setMaxConnectionAge(5 * 60 * 60);
				cpdsDB.setMaxIdleTime(30 * 60);
				cpdsDB.setMaxIdleTimeExcessConnections(10 * 60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (cpdsDBReport == null) {
			try {
				cpdsDBReport = new ComboPooledDataSource();
				cpdsDBReport.setDriverClass(ODBC_DRIVER);
				cpdsDBReport.setJdbcUrl(dsmCore.getConfiguration().getDbConfigurationReport().getUrl());
				cpdsDBReport.setUser(dsmCore.getConfiguration().getDbConfigurationReport().getUser());
				cpdsDBReport.setPassword(dsmCore.getConfiguration().getDbConfigurationReport().getPassword());
				cpdsDBReport.setMinPoolSize(5);
				cpdsDBReport.setAcquireIncrement(5);
				cpdsDBReport.setMaxPoolSize(100);
				cpdsDBReport.setInitialPoolSize(10);
				cpdsDBReport.setNumHelperThreads(60);
				cpdsDBReport.setAutoCommitOnClose(true);
				cpdsDBReport.setTestConnectionOnCheckin(false);
				cpdsDBReport.setTestConnectionOnCheckout(false);
				cpdsDBReport.setDebugUnreturnedConnectionStackTraces(false);

				cpdsDBReport.setMaxConnectionAge(5 * 60 * 60);
				cpdsDBReport.setMaxIdleTime(30 * 60);
				cpdsDBReport.setMaxIdleTimeExcessConnections(10 * 60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() throws SQLException {
		Connection con = cpdsDB.getConnection();
		// con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		// con.setAutoCommit(false);
		return con;
	}

	public Connection getConnectionReport() throws SQLException {
		Connection con = cpdsDBReport.getConnection();
		// con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		// con.setAutoCommit(false);
		return con;
	}

	private void setValuePreparedStatement(PreparedStatement pst, Integer index, Integer type, Object value) throws SQLException {
		if (value != null) {
			if (value instanceof Integer) {
				pst.setInt(index, (Integer) value);
			} else if (value instanceof String) {
				pst.setString(index, (String) value);
			} else if (value instanceof Long) {
				pst.setLong(index, (Long) value);
			}
		} else {
			pst.setNull(index, type);
		}
	}

	protected void executeFinally(Connection connection, PreparedStatement pst, Integer transaction) {
		commitTransaction(connection, transaction);
		closePrepareStatement(pst);
		closeConnection(connection);
	}

	protected void executeFinally(Connection connection, PreparedStatement pst, ResultSet result) {
		closeResultSet(result);
		closePrepareStatement(pst);
		closeConnection(connection);
	}

	protected void closeResultSet(ResultSet result) {
		try {
			if (result != null) {
				result.close();
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	protected void commitTransaction(Connection connection, Integer transaccion) {
		try {
			if (connection != null && transaccion != null && transaccion > 0) {
				connection.commit();
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	protected void closePrepareStatement(PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	protected void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////

	public SubscriberDS getSubscriberDS(String formatedSubscriberId) {
		SubscriberDS subscriber = new SubscriberDS();

		subscriber.setSubscriberId(formatedSubscriberId);
		subscriber.setSiteId(1);
		subscriber.setScabbPolicyId(0);
		subscriber.setTierId(1);
		subscriber.getDsSubscriptions().add(1);
		subscriber.getDsSubscriptions().add(2);

		return subscriber;
	}

	public void startSession(DynamicSession ds) {
		Integer transaction = null;

		Long upstreamBW = null;
		Long downstreamBW = null;
		String sourceIp = null;
		String sourceMask = null;
		Integer sourcePortStart = null;
		Integer sourcePortEnd = null;
		String destinationIp = null;
		String destinationMask = null;
		Integer destinationPortStart = null;
		Integer destinationPortEnd = null;
		Integer protocolId = null;

		Connection connection = null;
		PreparedStatement pst = null;
		try {
			String query = "INSERT INTO " + ConstantsDataBaseTable.AF_SESSION_ACTIVATION + " (time,timeConsume,downStreamVolume,downStreamVolumeConsume,upStreamVolume," + " upStreamVolumeConsume," + " bothStreamVolume,bothStreamVolumeConsume,countingTimeType," + " activate,activateDate,leaseTime," + " SITE_ID,CMTS_ID,TECHNOLOGY_ID,POLICYDOMAIN_ID,SCABB_POLICY_ID,technologyPolicyId," + " timestamp,sessionId,state,sourceIp,sourceMask,sourcePortStart,sourcePortEnd,"
					+ " destinationIp,destinationMask,destinationPortStart,destinationPortEnd," + " protocolId,upstreamBW,downstreamBW,serviceId,SUBSCRIBER_ID_HASHCODE,SUBSCRIBER_ID,userId)" + " VALUES (?,0,?,0,?,0,?,0,?,1,?,?,?,?,?,?,?,?," + " ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			connection = getConnection();
			java.sql.Timestamp date = new java.sql.Timestamp(ds.getStartTime());

			pst = connection.prepareStatement(query);
			// pst.registerOutParameter(31, Types.BIGINT);

			pst.setLong(1, ds.getUl_time());
			pst.setLong(2, ds.getUl_downVolume());
			pst.setLong(3, ds.getUl_upVolume());
			pst.setLong(4, ds.getUl_bothVolume());
			pst.setInt(5, CountingTimeTypeVO.absolute.ordinal());
			pst.setTimestamp(6, date);
			pst.setTimestamp(7, date);

			pst.setInt(8, -1);// siteId
			pst.setInt(9, -1);// cmtsId
			pst.setInt(10, -1);// policyTechnologyVO id
			pst.setInt(11, -1);// policyDomainId
			pst.setInt(12, -1);// scabbId
			pst.setInt(13, -1);// technologyId
			pst.setTimestamp(14, date);
			pst.setString(15, ds.getSessionId().toString());
			pst.setInt(16, DynamicServiceSessionState.activate.getId());// para mi esto es si esta prendida o pausada

			// if (classifier != null) {
			// sourceIp = classifier.getSourceIp();
			// sourceMask = classifier.getSourceMask();
			// sourcePortStart = classifier.getSourcePortStart();
			// sourcePortEnd = classifier.getSourcePortEnd();
			// destinationIp = classifier.getDestinationIp();
			// destinationMask = classifier.getDestinationMask();
			// destinationPortStart = classifier.getDestinationPortStart();
			// destinationPortEnd = classifier.getDestinationPortEnd();
			// protocolId = classifier.getProtocolId();
			// }
			setValuePreparedStatement(pst, 17, Types.VARCHAR, sourceIp);
			setValuePreparedStatement(pst, 18, Types.VARCHAR, sourceMask);
			setValuePreparedStatement(pst, 19, Types.INTEGER, sourcePortStart);
			setValuePreparedStatement(pst, 20, Types.INTEGER, sourcePortEnd);

			setValuePreparedStatement(pst, 21, Types.VARCHAR, destinationIp);
			setValuePreparedStatement(pst, 22, Types.VARCHAR, destinationMask);
			setValuePreparedStatement(pst, 23, Types.INTEGER, destinationPortStart);
			setValuePreparedStatement(pst, 24, Types.INTEGER, destinationPortEnd);

			setValuePreparedStatement(pst, 25, Types.INTEGER, protocolId);

			setValuePreparedStatement(pst, 26, Types.BIGINT, upstreamBW);
			setValuePreparedStatement(pst, 27, Types.BIGINT, downstreamBW);

			pst.setInt(28, ds.getServiceId());
			pst.setLong(29, HashCode.hash(ds.getSubscriberId()));
			pst.setString(30, ds.getSubscriberId());
			pst.setLong(31, -1);// userId

			transaction = pst.executeUpdate();

			connection.commit();
		} catch (Exception ex) {
			logger.error("ERROR en startSession. DS: " + ds + ".", ex);
		} finally {
			executeFinally(connection, pst, transaction);
		}
	}

	public List<DynamicSession> dynamicSessionQuery(String subscriberId, Integer dynamicServiceId, Integer sessionId) {
		List<DynamicSession> sessionActivations = null;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		String query = "";
		Integer index = 0;
		try {

			query = " SELECT ssa.id,ssa.userId,ssa.SUBSCRIBER_ID,ssa.SUBSCRIBER_ID_HASHCODE,ssa.serviceId,IFNULL(ssa.id,-1) ssa_id," + " ssa.SITE_ID siteId,ssa.CMTS_ID cmtsId,ssa.leaseTime," + " ssa.POLICYDOMAIN_ID policyDomainId, ssa.SCABB_POLICY_ID scabbPolicyId," + " ssa.technologyPolicyId , ssa.sessionId,ssa.activate,ssa.activateDate," + " ssa.counterSession," + " ssa.bothStreamVolume,ssa.bothStreamVolumeConsume,ssa.countingTimeType,"
					+ " ssa.downStreamVolume,ssa.downStreamVolumeConsume,ssa.time,ssa.timeConsume," + " ssa.timestamp,ssa.upStreamVolume,ssa.upStreamVolumeConsume,ssa.state," + " ssa.sourceIp,ssa.sourceMask,ssa.sourcePortStart,ssa.sourcePortEnd," + " ssa.destinationIp,ssa.destinationMask,ssa.destinationPortStart," + " ssa.destinationPortEnd,ssa.protocolId,ssa.upstreamBW,ssa.downstreamBW,ssa.counterSession " + " FROM " + ConstantsDataBaseTable.AF_SESSION_ACTIVATION + " ssa "
					+ " where ssa.SUBSCRIBER_ID_HASHCODE=?";

			if (dynamicServiceId != null) {
				query += " AND ssa.serviceId=? ";
			}
			if (sessionId != null) {
				query += " AND ssa.sessionId=? ";
			}
			connection = getConnection();
			pst = connection.prepareStatement(query);
			index++;
			pst.setLong(index, HashCode.hash(subscriberId));
			if (dynamicServiceId != null) {
				index++;
				pst.setInt(index, dynamicServiceId);
			}
			if (sessionId != null) {
				index++;
				pst.setString(index, sessionId.toString());
			}
			result = pst.executeQuery();
			if (result.next()) {
				sessionActivations = new LinkedList<DynamicSession>();
				do {
					DynamicSession sessionActivation = new DynamicSession();

					// sessionActivation.setId(result.getLong("ssa.id"));
					// sessionActivation.setUserId(result.getLong("ssa.userId"));
					sessionActivation.setServiceId(result.getInt("ssa.serviceId"));
					// sessionActivation.setSubscriberIdHashCode(result.getLong("ssa.SUBSCRIBER_ID_HASHCODE"));
					sessionActivation.setSubscriberId(result.getString("ssa.SUBSCRIBER_ID"));
					sessionActivation.setSessionId(Integer.valueOf(result.getString("ssa.sessionId")));
					Calendar activateDate = Calendar.getInstance();
					activateDate.setTime(result.getTimestamp("ssa.activateDate"));
					sessionActivation.setStartTime(activateDate.getTimeInMillis());
					// Calendar leaseTime = Calendar.getInstance();
					// leaseTime.setTime(result.getTimestamp("ssa.leaseTime"));
					// sessionActivation.setLeaseTime(leaseTime);
					sessionActivation.setActive(result.getBoolean("ssa.activate"));

					// sessionActivation.setState(DynamicServiceSessionState.stateById(result.getInt("ssa.state")));

					// Classifier classifier = new Classifier();
					//
					// classifier.setSourceIp(result.getString("ssa.sourceIp"));
					// classifier.setSourceMask(result.getString("ssa.sourceMask"));
					// classifier.setSourcePortStart(result.getInt("ssa.sourcePortStart"));
					// classifier.setSourcePortEnd(result.getInt("ssa.sourcePortEnd"));
					// classifier.setDestinationIp(result.getString("ssa.destinationIp"));
					// classifier.setDestinationMask(result.getString("ssa.destinationMask"));
					// classifier.setDestinationPortStart(result.getInt("ssa.destinationPortStart"));
					// classifier.setDestinationPortEnd(result.getInt("ssa.destinationPortEnd"));
					// classifier.setProtocolId(result.getInt("ssa.protocolId"));
					//
					// sessionActivation.setClassifer(classifier);
					//
					// sessionActivation.setUpstreamBW(result.getLong("ssa.upstreamBW"));
					// sessionActivation.setDownstreamBW(result.getLong("ssa.downstreamBW"));

					// sessionActivation.setCounterSession(result.getInt("ssa.counterSession"));
					// PolicyCombination policyCombination = new PolicyCombination();
					//
					// policyCombination.setSiteId(result.getInt("siteId"));
					// policyCombination.setCmtsId(result.getInt("cmtsId"));
					// policyCombination.setPolicyDomainId(result.getInt("policyDomainId"));
					// policyCombination.setScabbPolicyId(result.getInt("ssa.scabbPolicyId"));
					// policyCombination.setTechnologyPolicyId(result.getInt("ssa.technologyPolicyId"));
					//
					// sessionActivation.setPolicyLocation(policyCombination);

					sessionActivation.setUl_bothVolume(result.getLong("ssa.bothStreamVolume"));
					sessionActivation.setTc_bothVolume(result.getLong("ssa.bothStreamVolumeConsume"));

					sessionActivation.setUl_downVolume(result.getLong("ssa.downStreamVolume"));
					sessionActivation.setTc_downVolume(result.getLong("ssa.downStreamVolumeConsume"));
					sessionActivation.setUl_time(result.getLong("ssa.time"));
					sessionActivation.setTc_time(result.getLong("ssa.timeConsume"));
					// Calendar date = Calendar.getInstance();
					// if (result.getDate("ssa.timestamp") != null) {
					// date.setTime(result.getDate("ssa.timestamp"));
					// }
					// limit.setTimestamp(date);
					sessionActivation.setUl_upVolume(result.getLong("ssa.upStreamVolume"));
					sessionActivation.setTc_upVolume(result.getLong("ssa.upStreamVolumeConsume"));

					sessionActivations.add(sessionActivation);
				} while (result.next());
			} else {
				logger.warn("subscriber id " + subscriberId + " not found ");
			}
		} catch (Exception e) {
			logger.error(e + " query : " + query);
		} finally {
			executeFinally(connection, pst, result);
		}
		return sessionActivations;
	}

	public DynamicSession dynamicSessionQuery(Integer sessionId) {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		String query = "";
		DynamicSession sessionActivation = null;
		try {

			query = " SELECT ssa.id,ssa.userId,ssa.SUBSCRIBER_ID,ssa.SUBSCRIBER_ID_HASHCODE,ssa.serviceId,IFNULL(ssa.id,-1) ssa_id," + " ssa.SITE_ID siteId,ssa.CMTS_ID cmtsId,ssa.leaseTime," + " ssa.POLICYDOMAIN_ID policyDomainId, ssa.SCABB_POLICY_ID scabbPolicyId," + " ssa.technologyPolicyId , ssa.sessionId,ssa.activate,ssa.activateDate," + " ssa.counterSession," + " ssa.bothStreamVolume,ssa.bothStreamVolumeConsume,ssa.countingTimeType,"
					+ " ssa.downStreamVolume,ssa.downStreamVolumeConsume,ssa.time,ssa.timeConsume," + " ssa.timestamp,ssa.upStreamVolume,ssa.upStreamVolumeConsume,ssa.state," + " ssa.sourceIp,ssa.sourceMask,ssa.sourcePortStart,ssa.sourcePortEnd," + " ssa.destinationIp,ssa.destinationMask,ssa.destinationPortStart," + " ssa.destinationPortEnd,ssa.protocolId,ssa.upstreamBW,ssa.downstreamBW,ssa.counterSession " + " FROM " + ConstantsDataBaseTable.AF_SESSION_ACTIVATION + " ssa "
					+ " where ssa.sessionId=? ";
			connection = getConnection();
			pst = connection.prepareStatement(query);
			pst.setString(1, sessionId.toString());
			result = pst.executeQuery();
			if (result.next()) {
				sessionActivation = new DynamicSession();

				// sessionActivation.setId(result.getLong("ssa.id"));
				// sessionActivation.setUserId(result.getLong("ssa.userId"));
				sessionActivation.setServiceId(result.getInt("ssa.serviceId"));
				// sessionActivation.setSubscriberIdHashCode(result.getLong("ssa.SUBSCRIBER_ID_HASHCODE"));
				sessionActivation.setSubscriberId(result.getString("ssa.SUBSCRIBER_ID"));
				sessionActivation.setSessionId(Integer.valueOf(result.getString("ssa.sessionId")));
				Calendar activateDate = Calendar.getInstance();
				activateDate.setTime(result.getTimestamp("ssa.activateDate"));
				sessionActivation.setStartTime(activateDate.getTimeInMillis());
				// Calendar leaseTime = Calendar.getInstance();
				// leaseTime.setTime(result.getTimestamp("ssa.leaseTime"));
				// sessionActivation.setLeaseTime(leaseTime);
				sessionActivation.setActive(result.getBoolean("ssa.activate"));

				// sessionActivation.setState(DynamicServiceSessionState.stateById(result.getInt("ssa.state")));

				// Classifier classifier = new Classifier();
				//
				// classifier.setSourceIp(result.getString("ssa.sourceIp"));
				// classifier.setSourceMask(result.getString("ssa.sourceMask"));
				// classifier.setSourcePortStart(result.getInt("ssa.sourcePortStart"));
				// classifier.setSourcePortEnd(result.getInt("ssa.sourcePortEnd"));
				// classifier.setDestinationIp(result.getString("ssa.destinationIp"));
				// classifier.setDestinationMask(result.getString("ssa.destinationMask"));
				// classifier.setDestinationPortStart(result.getInt("ssa.destinationPortStart"));
				// classifier.setDestinationPortEnd(result.getInt("ssa.destinationPortEnd"));
				// classifier.setProtocolId(result.getInt("ssa.protocolId"));
				//
				// sessionActivation.setClassifer(classifier);
				//
				// sessionActivation.setUpstreamBW(result.getLong("ssa.upstreamBW"));
				// sessionActivation.setDownstreamBW(result.getLong("ssa.downstreamBW"));

				// sessionActivation.setCounterSession(result.getInt("ssa.counterSession"));
				// PolicyCombination policyCombination = new PolicyCombination();
				//
				// policyCombination.setSiteId(result.getInt("siteId"));
				// policyCombination.setCmtsId(result.getInt("cmtsId"));
				// policyCombination.setPolicyDomainId(result.getInt("policyDomainId"));
				// policyCombination.setScabbPolicyId(result.getInt("ssa.scabbPolicyId"));
				// policyCombination.setTechnologyPolicyId(result.getInt("ssa.technologyPolicyId"));
				//
				// sessionActivation.setPolicyLocation(policyCombination);

				sessionActivation.setUl_bothVolume(result.getLong("ssa.bothStreamVolume"));
				sessionActivation.setTc_bothVolume(result.getLong("ssa.bothStreamVolumeConsume"));

				sessionActivation.setUl_downVolume(result.getLong("ssa.downStreamVolume"));
				sessionActivation.setTc_downVolume(result.getLong("ssa.downStreamVolumeConsume"));
				sessionActivation.setUl_time(result.getLong("ssa.time"));
				sessionActivation.setTc_time(result.getLong("ssa.timeConsume"));
				// Calendar date = Calendar.getInstance();
				// if (result.getDate("ssa.timestamp") != null) {
				// date.setTime(result.getDate("ssa.timestamp"));
				// }
				// limit.setTimestamp(date);
				sessionActivation.setUl_upVolume(result.getLong("ssa.upStreamVolume"));
				sessionActivation.setTc_upVolume(result.getLong("ssa.upStreamVolumeConsume"));
			} else {
				logger.warn("sessionId " + sessionId + " not found ");
			}
		} catch (Exception e) {
			logger.error(e + " query : " + query);
		} finally {
			executeFinally(connection, pst, result);
		}
		return sessionActivation;
	}

	public void stopSession(Integer sessionId) {
		int transaction = 0;
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			String query = "DELETE FROM " + ConstantsDataBaseTable.AF_SESSION_ACTIVATION + "  WHERE sessionId=?";
			connection = getConnection();
			pst = connection.prepareStatement(query);

			pst.setString(1, sessionId.toString());

			transaction = pst.executeUpdate();

			connection.commit();
		} catch (Exception e) {
			logger.error(e, e.fillInStackTrace());
		} finally {
			executeFinally(connection, pst, transaction);
		}
	}

	// 260,148

	public void quotaVolumeUpdates(FastTreeMap consumptions) {
		// String sessionId;
		// for (Object obj : consumptions.keySet()) {
		// sessionId = (String) obj;
		// }
	}
}
