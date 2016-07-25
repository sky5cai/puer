/** * ZcEbBaseServiceDao.java * com.ufgov.gk.server.zc.dao.ibatis * Administrator * 2010-4-30 */package com.ufgov.zc.server.zc.dao.ibatis;import java.sql.SQLException;import java.util.List;import java.util.Map;import org.springframework.orm.ibatis.SqlMapClientCallback;import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;import com.ibatis.sqlmap.client.SqlMapExecutor;import com.ufgov.zc.common.system.RequestMeta;import com.ufgov.zc.common.system.constants.NumLimConstants;import com.ufgov.zc.common.system.dto.ElementConditionDto;import com.ufgov.zc.common.zc.model.ZcPProMakeforF3;import com.ufgov.zc.common.zc.model.ZcPayforF3;import com.ufgov.zc.server.system.util.NumLimUtil;import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;/** * @author Administrator * */public class ZcEbBaseServiceDao extends SqlMapClientDaoSupport implements IZcEbBaseServiceDao {  public List getForeignEntitySelectedData(String sqlMapSelectId, ElementConditionDto dto, RequestMeta meta) {    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));    return this.getSqlMapClientTemplate().queryForList(sqlMapSelectId, dto);  }  public List queryDataForList(String sqlMapSelectId, Map parameter) {    return this.getSqlMapClientTemplate().queryForList(sqlMapSelectId, parameter);  }  public List queryDataForList(String sqlMapSelectId) {    return this.getSqlMapClientTemplate().queryForList(sqlMapSelectId);  }  public String getSequenceNextVal(String sequenceName) {    return this.getSqlMapClientTemplate().queryForObject(sequenceName).toString();  }  public List queryDataForList(String sqlMapSelectId, Object param) {    return this.getSqlMapClientTemplate().queryForList(sqlMapSelectId, param);  }  public void insertDataForObject(String sqlMapSelectId, Object param) {    this.getSqlMapClientTemplate().insert(sqlMapSelectId, param);  }  public Object queryObject(String sqlMapSelectId, Object param) {    return this.getSqlMapClientTemplate().queryForObject(sqlMapSelectId, param);  }  public void updateObjectList(final String sqlMapUpdateId, final List list) {    if (list != null) {      this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {        public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {          executor.startBatch();          for (int i = 0; i < list.size(); i++) {            executor.update(sqlMapUpdateId, list.get(i));          }          executor.executeBatch();          return null;        }      });    }  }  public void alert() {    System.out.println("这个应该是半分钟执行一次!");  }  public void insertF3ProMake(ZcPProMakeforF3 proMake) {    this.getSqlMapClientTemplate().insert("ZC_INTERFACE_DATA_FOR_F3.insertF3ProMake", proMake);  }  public void insertF3Pay(ZcPayforF3 payForF3) {    this.getSqlMapClientTemplate().insert("ZC_INTERFACE_DATA_FOR_F3.insertF3Pay", payForF3);  }  public List getZcVBiDetail(String biNo) {    return this.getSqlMapClientTemplate().queryForList("ZC_INTERFACE_DATA_FOR_F3.getZcVBiDetail", biNo);  }  public String getPayStatus(String balBiId) {    return (String) this.getSqlMapClientTemplate().queryForObject("ZC_INTERFACE_DATA_FOR_F3.getPayStatus", balBiId);  }  public List getCurrentOpenBidList(Map map, RequestMeta meta) {    String numLimitStr = NumLimUtil.getInstance().getNumLimCondByCoType("ZC_CURRENT_OPEN_BID", NumLimConstants.FWATCH);    map.put("numLimitStr", numLimitStr);    return this.getSqlMapClientTemplate().queryForList("ZcEbSupplierPack.getPojectWillOpenBidByExecutor", map);  }  public List getCurrentBidEndList(Map map, RequestMeta meta) {    String numLimitStr = NumLimUtil.getInstance().getNumLimCondByCoType("ZC_CURRENT_OPEN_BID", NumLimConstants.FWATCH);    map.put("numLimitStr", numLimitStr);    return this.getSqlMapClientTemplate().queryForList("ZcEbSupplierPack.getPojectWillBidEndByExecutor", map);  }  public void delete(String sqlMapDeleteId, Object parameter) {    // TCJLODO Auto-generated method stub    this.getSqlMapClientTemplate().delete(sqlMapDeleteId, parameter);  }     public String getNextVal(String sequenceName) {    // TCJLODO Auto-generated method stub    return this.getSqlMapClientTemplate().queryForObject("ZcEbUtil.getNextVal",sequenceName).toString();  } public void insertObjectList(final String sqlMapUpdateId, final List list) {    if (list != null) {      this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {        public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {          executor.startBatch();          for (int i = 0; i < list.size(); i++) {            executor.insert(sqlMapUpdateId, list.get(i));          }          executor.executeBatch();          return null;        }      });    }  }}