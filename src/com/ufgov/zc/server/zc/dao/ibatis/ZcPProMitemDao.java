package com.ufgov.zc.server.zc.dao.ibatis;import java.math.BigDecimal;import java.sql.SQLException;import java.util.List;import org.springframework.orm.ibatis.SqlMapClientCallback;import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;import com.ibatis.sqlmap.client.SqlMapExecutor;import com.ufgov.zc.common.system.dto.ElementConditionDto;import com.ufgov.zc.common.zc.model.ZcPProBaoJia;import com.ufgov.zc.common.zc.model.ZcPProMake;import com.ufgov.zc.common.zc.model.ZcPProMitem;import com.ufgov.zc.common.zc.model.ZcPProMitemBaoJia;import com.ufgov.zc.common.zc.model.ZcPProMitemExample;import com.ufgov.zc.server.zc.dao.IZcPProMitemDao;public class ZcPProMitemDao extends SqlMapClientDaoSupport implements IZcPProMitemDao {  /**   * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  public ZcPProMitemDao() {    super();  }  /**   * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  public int countByExample(ZcPProMitemExample example) {    Integer count = (Integer) getSqlMapClientTemplate().queryForObject("ZC_P_PRO_MITEM.ibatorgenerated_countByExample", example);    return count.intValue();  }  /**   * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  public int deleteByExample(ZcPProMitemExample example) {    int rows = getSqlMapClientTemplate().delete("ZC_P_PRO_MITEM.ibatorgenerated_deleteByExample", example);    return rows;  }  /**   * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  public int deleteByPrimaryKey(String zcMakeCode, BigDecimal zcPitemCode) {    ZcPProMitem key = new ZcPProMitem();    key.setZcMakeCode(zcMakeCode);    key.setZcPitemCode(zcPitemCode);    int rows = getSqlMapClientTemplate().delete("ZC_P_PRO_MITEM.ibatorgenerated_deleteByPrimaryKey", key);    return rows;  }  /**   * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  public void insert(ZcPProMitem record) {    getSqlMapClientTemplate().insert("ZC_P_PRO_MITEM.ibatorgenerated_insert", record);  }  /**   * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  public void insertSelective(ZcPProMitem record) {    getSqlMapClientTemplate().insert("ZC_P_PRO_MITEM.ibatorgenerated_insertSelective", record);  }  /**   * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  public List selectByExample(ZcPProMitemExample example) {    List list = getSqlMapClientTemplate().queryForList("ZC_P_PRO_MITEM.ibatorgenerated_selectByExample", example);    return list;  }  public List selectJingJiaItem(ElementConditionDto dto) {    List list = getSqlMapClientTemplate().queryForList("ZC_P_PRO_MITEM.selectJingJiaItem", dto);    return list;  }  /**   * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  public ZcPProMitem selectByPrimaryKey(String zcMakeCode, BigDecimal zcPitemCode) {    ZcPProMitem key = new ZcPProMitem();    key.setZcMakeCode(zcMakeCode);    key.setZcPitemCode(zcPitemCode);    ZcPProMitem record = (ZcPProMitem) getSqlMapClientTemplate().queryForObject("ZC_P_PRO_MITEM.ibatorgenerated_selectByPrimaryKey", key);    return record;  }  /**   * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  public int updateByExampleSelective(ZcPProMitem record, ZcPProMitemExample example) {    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);    int rows = getSqlMapClientTemplate().update("ZC_P_PRO_MITEM.ibatorgenerated_updateByExampleSelective", parms);    return rows;  }  /**   * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  public int updateByExample(ZcPProMitem record, ZcPProMitemExample example) {    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);    int rows = getSqlMapClientTemplate().update("ZC_P_PRO_MITEM.ibatorgenerated_updateByExample", parms);    return rows;  }  /**   * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  public int updateByPrimaryKeySelective(ZcPProMitem record) {    int rows = getSqlMapClientTemplate().update("ZC_P_PRO_MITEM.ibatorgenerated_updateByPrimaryKeySelective", record);    return rows;  }  public int updateMitemPrimaryKeyByTempCode(ZcPProMake record) {    int rows = getSqlMapClientTemplate().update("ZC_P_PRO_MITEM.ibatorgenerated_updateMitemPrimaryKeyByTempCode", record);    return rows;  }  /**   * This method was generated by Apache iBATIS ibator. This method corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  public int updateByPrimaryKey(ZcPProMitem record) {    int rows = getSqlMapClientTemplate().update("ZC_P_PRO_MITEM.ibatorgenerated_updateByPrimaryKey", record);    return rows;  }  /**   * This class was generated by Apache iBATIS ibator. This class corresponds to the database table ZC_P_PRO_MITEM   * @ibatorgenerated  Thu Apr 29 16:50:54 CST 2010   */  private static class UpdateByExampleParms extends ZcPProMitemExample {    private Object record;    public UpdateByExampleParms(Object record, ZcPProMitemExample example) {      super(example);      this.record = record;    }    public Object getRecord() {      return record;    }  }  public void updateBaoJia(ZcPProMake make) {    deleteBaoJia(make);    insertBaoJia(make);  }  public void deleteBaoJia(final ZcPProMake make) {    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {        executor.startBatch();        if (make.getBaoJiaList().size() == 0) {          return null;        }        ZcPProMitemBaoJia bj = (ZcPProMitemBaoJia) make.getBaoJiaList().get(0);        if (bj == null) {          return null;        }        for (int i = 0; i < bj.getBaoJiaDetailList().size(); i++) {          ZcPProMitem item = (ZcPProMitem) bj.getBaoJiaDetailList().get(i);          executor.delete("ZC_P_PRO_MITEM.deleteBaoJiaByKey", item);        }        executor.executeBatch();        return null;      }    });  }  private void insertBaoJia(final ZcPProMake make) {    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {        executor.startBatch();        if (make.getBaoJiaList().size() == 0) {          return null;        }        ZcPProMitemBaoJia bj = (ZcPProMitemBaoJia) make.getBaoJiaList().get(0);        if (bj == null) {          return null;        }        for (int i = 0; i < bj.getBaoJiaDetailList().size(); i++) {          ZcPProMitem item = (ZcPProMitem) bj.getBaoJiaDetailList().get(i);          executor.insert("ZC_P_PRO_MITEM.ibatorgenerated_insert", item);        }        executor.executeBatch();        return null;      }    });  }  //添加供应商报价记录信息 guoss  public void addBaojia(ZcPProBaoJia baojia) {    getSqlMapClientTemplate().insert("ZC_P_PRO_MITEM.ibatorgenerated_addBaoJia", baojia);  }  public List queryExportsDatas(ElementConditionDto dto) {    // TCJLODO Auto-generated method stub    return getSqlMapClientTemplate().queryForList("ZC_P_PRO_MITEM.queryExportsDatas", dto);  }}