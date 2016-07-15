package com.ufgov.zc.server.cp.dao.ibatis;import java.sql.SQLException;import java.util.HashMap;import java.util.List;import java.util.Map;import org.springframework.orm.ibatis.SqlMapClientCallback;import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;import com.ibatis.sqlmap.client.SqlMapExecutor;import com.ufgov.zc.common.cp.model.CpPlanAgentList;import com.ufgov.zc.common.system.exception.DaoException;import com.ufgov.zc.server.cp.dao.ICpPlanAgentListDao;public class CpPlanAgentListDao extends SqlMapClientDaoSupport implements ICpPlanAgentListDao {  public List getCpPlanAgentList(String planAgentBillId) {    return this.getSqlMapClientTemplate().queryForList("CpPlanAgentList.getCpPlanAgentList", planAgentBillId);  }  public List getCpPlanAgentListForList(List planAgentBillIdList) {    if (planAgentBillIdList.size() == 0) {      throw new DaoException("getCpPlanAgentListForList的参数planAgentBillIdList为空");    }    Map map = new HashMap();    map.put("planAgentBillIdList", planAgentBillIdList);    return this.getSqlMapClientTemplate().queryForList("CpPlanAgentList.getCpPlanAgentListForList", map);  }  public void insertCpPlanAgentList(final List cpPlanAgentListList) {    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {        executor.startBatch();        for (int i = 0; i < cpPlanAgentListList.size(); i++) {          CpPlanAgentList bill = (CpPlanAgentList) cpPlanAgentListList.get(i);          executor.insert("CpPlanAgentList.insert", bill);        }        executor.executeBatch();        return null;      }    });  }}