package com.ufgov.zc.server.commonbiz.dao.ibatis;import java.math.BigDecimal;import java.sql.SQLException;import java.util.HashMap;import java.util.List;import java.util.Map;import org.springframework.orm.ibatis.SqlMapClientCallback;import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;import com.ibatis.sqlmap.client.SqlMapExecutor;import com.ufgov.zc.common.commonbiz.model.DpBalance;import com.ufgov.zc.common.commonbiz.util.DpBalanceViewGetter;import com.ufgov.zc.common.dp.model.DpDetail;import com.ufgov.zc.common.system.constants.NumLimConstants;import com.ufgov.zc.common.system.dto.ElementConditionDto;import com.ufgov.zc.server.commonbiz.dao.IDpBalanceDao;import com.ufgov.zc.server.commonbiz.helper.DpBalanceUpdaterForDpdetail;import com.ufgov.zc.server.system.util.GkGetDataRuleUtil;import com.ufgov.zc.server.system.util.NumLimUtil;import com.ufgov.zc.server.system.util.RequestMetaUtil;public class DpBalanceDao extends SqlMapClientDaoSupport implements IDpBalanceDao {  public int getBalanceNumByJjPlanDetailOid(String jjPlanDetailOid) {    return ((Integer) this.getSqlMapClientTemplate().queryForObject(    "DpBalance.getBalanceNumByJjPlanDetailOid", jjPlanDetailOid)).intValue();  }  public void updateDpbalanceByJjPlanDetailOid(String moneyField, BigDecimal m, String jjPlanDetailOid) {    Map params = new HashMap();    params.put("moneyField", moneyField);    params.put("curMoney", m);    params.put("jjPlanDetailOid", jjPlanDetailOid);    this.getSqlMapClientTemplate().update("DpBalance.updateDpbalanceByJjPlanDetailOid", params);  }  public DpBalance getDpbalanceByJjPlanDetailOid(String jjPlanDetailOid) {    Map params = new HashMap();    params.put("jjPlanDetailOid", jjPlanDetailOid);    return (DpBalance) this.getSqlMapClientTemplate().queryForObject(    "DpBalance.getDpbalanceByJjPlanDetailOid", params);  }  public List getDpBalance() {    return this.getSqlMapClientTemplate().queryForList("DpBalance.getDpBalance");  }  public void updateDpbalanceByOid(DpBalance dpBalance) {    this.getSqlMapClientTemplate().update("DpBalance.updateDpbalanceByOid", dpBalance);  }  public List getDpBalanceForDpAdjust(ElementConditionDto balanceelementConditionDto) {//    balanceelementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(////    balanceelementConditionDto.getNumLimCompoId(), NumLimConstants.FWATCH));    return this.getSqlMapClientTemplate().queryForList("DpBalance.getDpBalanceForDpAdjust",    balanceelementConditionDto);  }  public DpBalance getDpBalanceById(String id, String dpBalanceView) {    Map map = new HashMap();    map.put("dpBalanceId", id);    map.put("DPVIEW", dpBalanceView);    return (DpBalance) this.getSqlMapClientTemplate().queryForObject("DpBalance.getDpBalanceById", map);  }  public List getDpBalanceForCpAv(ElementConditionDto elementConditionDto) {    elementConditionDto.setPaytypeCode("0202");//    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(////    elementConditionDto.getNumLimCompoId(), NumLimConstants.FWATCH));    return this.getSqlMapClientTemplate().queryForList("DpBalance.getDpBalanceForCpAv", elementConditionDto);  }  /**   * 更新计划余额表写money的值与业务支出表相关   */  public int updateDpBalance(String instanceId, String montyField, String dpBalanceId,  String businessTableName, String policy) {    Map mCondition = new HashMap();    mCondition.put("moneyField", montyField);    mCondition.put("dpBalanceId", dpBalanceId);    mCondition.put("instanceId", instanceId);    mCondition.put("businessTableName", businessTableName);    mCondition.put("policy", policy);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalance", mCondition);  }  /**   * 更新计划余额表写money的值用于用款计划调整   * --dp_money2 是按照用户选择的月份计算出来要写到哪个余额金额字段   --cur_money 是当前要追减的金额,要考虑保存后 再次修改的差值处理 调整金额   UPDATE dp_balance SET dp_money2 = dp_money2 - cur_money   WHERE (dp_money1 + dp_money2 - AM_MONEY - CP_MONEY - FREEZE_MONEY - CD_MONEY + cur_money ) >=0   AND  dp_balance_id = '001'   */  public int updateDpBalanceForDpAdjustDecreaseSave(String balmoneyField, BigDecimal adjustMoney,  String moneyField, String dpBalanceId) {    Map mCondition = new HashMap();    mCondition.put("balmoneyField", balmoneyField);    mCondition.put("adjustMoney", adjustMoney);    mCondition.put("moneyField", moneyField);    mCondition.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForDpAdjustDecreaseSave",    mCondition);  }  public int updateDpBalanceForCp(BigDecimal adjustMoney, BigDecimal newRequest, BigDecimal oldDpCdUseMoney,  String balanceStr, String dpBalanceId) {    Map param = new HashMap();    param.put("adjustMoney", adjustMoney);    param.put("newRequest", newRequest);    param.put("oldDpCdUseMoney", oldDpCdUseMoney);    param.put("newRequest", newRequest);    param.put("oldDpCdUseMoney", oldDpCdUseMoney);    param.put("newRequest", newRequest);    param.put("newRequest", newRequest);    param.put("oldDpCdUseMoney", oldDpCdUseMoney);    param.put("oldDpCdUseMoney", oldDpCdUseMoney);    param.put("dpBalanceId", dpBalanceId);    param.put("adjustMoney", adjustMoney);    param.put("balanceStr", balanceStr);    param.put("adjustMoney", adjustMoney);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForCp", param);  }  public BigDecimal queryMayUsedDpcdMoney(BigDecimal newCurMoney, BigDecimal oldCdUseMoney, String dpBalanceId) {    BigDecimal result = null;    Map param = new HashMap();    param.put("oldCdUseMoney", oldCdUseMoney);    param.put("oldCdUseMoney", oldCdUseMoney);    param.put("newCurMoney", newCurMoney);    param.put("newCurMoney", newCurMoney);    param.put("oldCdUseMoney", oldCdUseMoney);    param.put("dpBalanceId", dpBalanceId);    return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("DpBalance.queryMayUsedDpcdMoney",    param);  }  public BigDecimal queryDpCdUseMoney(String dpBalanceId) {    Map param = new HashMap();    param.put("dpBalanceId", dpBalanceId);    return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("DpBalance.queryDpCdUseMoney", param);  }  public int updateDpBalanceForDpAdjustFreezeAudit(BigDecimal adjustMoney, String moneyField,  String dpBalanceId) {    Map mCondition = new HashMap();    mCondition.put("adjustMoney", adjustMoney);    mCondition.put("moneyField", moneyField);    mCondition.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForDpAdjustFreezeAudit",    mCondition);  }  public int updateDpBalanceForDpAdjustFreezeSave(BigDecimal adjustMoney, String moneyField,  String dpBalanceId) {    Map mCondition = new HashMap();    mCondition.put("adjustMoney", adjustMoney);    mCondition.put("moneyField", moneyField);    mCondition.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate()    .update("DpBalance.updateDpBalanceForDpAdjustFreezeSave", mCondition);  }  public int updateDpBalanceForDpAdjustunFreezeAudit(BigDecimal adjustMoney, String moneyField,  String dpBalanceId) {    Map mCondition = new HashMap();    mCondition.put("adjustMoney", adjustMoney);    mCondition.put("moneyField", moneyField);    mCondition.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForDpAdjustunFreezeAudit",    mCondition);  }  public int updateDpBalanceForDpAdjustDecreaseAudit(String balmoneyField, BigDecimal adjustMoney,  String dpBalanceId) {    Map map = new HashMap();    map.put("balmoneyField", balmoneyField);    map.put("adjustMoney", adjustMoney);    map.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForDpAdjustDecreaseAudit", map);  }  public String queryMaxDpBalance(String elementWhere) {    return (String) this.getSqlMapClientTemplate()    .queryForObject("DpBalance.queryMaxDpBalance", elementWhere);  }  public void insertDpBalance(DpBalance dpBalance) {    this.getSqlMapClientTemplate().insert("DpBalance.insertDpBalance", dpBalance);  }  public int updateDpBalanceForDpEditAudit(String setStr, BigDecimal curMoney, String dpBalanceId) {    Map map = new HashMap();    map.put("curMoney", curMoney);    map.put("dpBalanceId", dpBalanceId);    map.put("setStr", setStr);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpbalanceForDpEditAudit", map);  }  public List getDpBalanceForCpDa(ElementConditionDto dto) {    Map param = new HashMap();    param.put("nd", new Integer(dto.getNd()));    param.put("DPVIEW", DpBalanceViewGetter.getRightQueryView(dto.getMonth()));    param.put("paytypeCode", dto.getPaytypeCode());    param.put("fundCode", dto.getFundCode());    param.put("baccCode", dto.getBaccCode());    param.put("manageCode", dto.getManageCode());    param.put("originCode", dto.getOriginCode());    param.put("projectCode", dto.getProjectCode());//    param.put("numLimitStr", NumLimUtil.getInstance().getNumLimCondByCoType(dto.getNumLimCompoId(),////    NumLimConstants.FWATCH));    param.put("dpBalanceId", dto.getDpBalanceId());    param.put("dataRuleCondiStr", dto.getDataRuleCondiStr());    return this.getSqlMapClientTemplate().queryForList("DpBalance.getDpBalanceForCp", param);  }  public List getDpBalanceForCpDv(ElementConditionDto dto) {    Map param = new HashMap();    param.put("nd", new Integer(dto.getNd()));    param.put("DPVIEW", DpBalanceViewGetter.getRightQueryView(dto.getMonth()));    param.put("paytypeCode", dto.getPaytypeCode());    param.put("fundCode", dto.getFundCode());    param.put("coCodeFilter", dto.getCoCodeFilter());    param.put("baccCode", dto.getBaccCode());    param.put("manageCode", dto.getManageCode());    param.put("originCode", dto.getOriginCode());    param.put("projectCode", dto.getProjectCode());    param.put("balanceStatus", dto.getBalanceStatus());//    param.put("numLimitStr", NumLimUtil.getInstance().getNumLimCondByCoType(dto.getNumLimCompoId(),////    NumLimConstants.FWATCH));    return this.getSqlMapClientTemplate().queryForList("DpBalance.getDpBalanceForCpDv", param);  }  //作废时余额更新  public int updateDpBalanceForCpApplyUnDo(String cpApplyId, String dpBalanceId) {    Map map = new HashMap();    map.put("cpApplyId", cpApplyId);    map.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForCpApplyUnDo", map);  }  public int updateDpBalanceForCpVoucherUnDo(String cpVoucherId, String dpBalanceId) {    Map map = new HashMap();    map.put("cpVoucherId", cpVoucherId);    map.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForCpVoucherUnDo", map);  }  //销审时余额更新  public int updateDpBalanceForCpApplyUnAudit(BigDecimal curMoney, BigDecimal dpCdUseMoney, String dpBalanceId) {    Map map = new HashMap();    map.put("curMoney", curMoney);    map.put("dpCdUseMoney", dpCdUseMoney);    map.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForCpApplyUnAudit", map);  }  /**   * 用款计划追减销审余额更新   * @param moneyField   * @param adjustMoney   * @param monthField   * @param dpBalanceId   * @return   */  public int updateDpBalanceForDpDecreaseUnaudit(String moneyField, String calcField, String monthField,  String dpDetailId, String dpBalanceId) {    Map map = new HashMap();    map.put("moneyField", moneyField);    map.put("calcField", calcField);    map.put("monthField", monthField);    map.put("dpDetailId", dpDetailId);    map.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForDpDecreaseUnaudit", map);  }  /**   * 用款计划编辑销审余额更新   * @param moneyField   * @param calcField   * @param monthField   * @param dpDetailId   * @param dpBalanceId   * @return   */  public int updateDpBalanceForDpEditUnaudit(String moneyField, String monthField, String dpDetailId,  String dpBalanceId) {    Map map = new HashMap();    map.put("moneyField", moneyField);    map.put("monthField", monthField);    map.put("dpDetailId", dpDetailId);    map.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForDpEditUnaudit", map);  }  /**   * 用款计划冻结销审   */  public int updateDpBalanceForDpFreezeUnaudit(String calcField, String dpDetailId, String dpBalanceId) {    Map map = new HashMap();    map.put("calcField", calcField);    map.put("dpDetailId", dpDetailId);    map.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForDpFreezeUnaudit", map);  }  /**   * 用款计划冻结保存时候占余额销审   */  public int updateDpBalanceForDpFreezeUnaudit0(String calcField, String dpDetailId, String dpBalanceId,  String monthField) {    Map map = new HashMap();    map.put("calcField", calcField);    map.put("dpDetailId", dpDetailId);    map.put("dpBalanceId", dpBalanceId);    map.put("monthField", monthField);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForDpFreezeUnaudit0", map);  }  /**   * 用款计划解冻销审   */  public int updateDpBalanceForDpUnFreezeUnaudit(String dpDetailId, String dpBalanceId, String monthField) {    Map map = new HashMap();    map.put("dpDetailId", dpDetailId);    map.put("dpBalanceId", dpBalanceId);    map.put("monthField", monthField);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForDpUnFreezeUnaudit", map);  }  public int updateDpBalanceForDpUnFreezeInterrupt(BigDecimal curMoney, String dpBalanceId) {    Map map = new HashMap();    map.put("curMoney", curMoney);    map.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForDpUnFreezeInterrupt", map);  }  public List getDpBalanceForAm(ElementConditionDto dto) {    Map param = new HashMap();    param.put("nd", new Integer(dto.getNd()));    param.put("DPVIEW", DpBalanceViewGetter.getRightQueryView(dto.getMonth()));    param.put("PAYTYPE_CODE", dto.getPaytypeCode());    param.put("coCodeFilter", dto.getCoCodeFilter());    param.put("fundCode", dto.getFundCode());    param.put("manageCode", dto.getManageCode());    param.put("originCode", dto.getOriginCode());    param.put("projectCode", dto.getProjectCode());//    param.put("numLimitStr", NumLimUtil.getInstance().getNumLimCondByCoType(dto.getNumLimCompoId(),////    NumLimConstants.FWATCH));    return this.getSqlMapClientTemplate().queryForList("DpBalance.getDpBalanceForAm", param);  }  public int updateDpBalanceForAm(BigDecimal money, String dpBalanceId, int monthId) {    Map map = new HashMap();    map.put("money", money);    map.put("dpBalanceId", dpBalanceId);    map.put("monthStr", DpBalanceViewGetter.getBalanceMonth(monthId)    + "-AM_MONEY-CP_MONEY-FREEZE_MONEY-CD_MONEY");    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForAm", map);  }  public List getDpBalanceForDpCarry(ElementConditionDto dto) {    dto.setDataRuleCondiStr(GkGetDataRuleUtil.getInstance().getDataRuleCondiStr(dto.getDataRuleId()));    return this.getSqlMapClientTemplate().queryForList("DpBalance.getDpBalanceForCarry", dto);  }  public String handleDpBalanceForCarry(String dpBalanceIds, int nd, String inputor, String procDate) {    final Map map = new HashMap();    map.put("nd", new Integer(nd));    map.put("inputor", inputor);    map.put("procDate", procDate);    map.put("rtnStr", "");    final String[] dpBalnaceIdArray = dpBalanceIds.split(",");    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {        executor.startBatch();        for (int i = 0; i < dpBalnaceIdArray.length; i++) {          String id = dpBalnaceIdArray[i];          map.put("dpBalanceIds", id);          executor.insert("DpBalance.SP_CP_DP_CARRY", map);        }        executor.executeBatch();        return null;      }    });    return null;  }  public String handleDpBalanceForUpdate(final List dpDetailList) {    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {        executor.startBatch();        for (int i = 0; i < dpDetailList.size(); i++) {          DpDetail dpDetail = (DpDetail) dpDetailList.get(i);          DpBalanceUpdaterForDpdetail updateDpDetail = new DpBalanceUpdaterForDpdetail();          String dpBalanceId = updateDpDetail.genBalanceForUpdate(dpDetail);          Map map = new HashMap();          map.put("dpBalanceId", dpBalanceId);          map.put("dpDetailId", dpDetail.getDpDetailId());          map.put("effectId", RequestMetaUtil.getSvUserID());          map.put("effectDate", RequestMetaUtil.getTransDate());          executor.insert("DpBalance.updateDpDetailForUpdate", map);        }        executor.executeBatch();        return null;      }    });    return null;  }  public int updateDpbalanceForUnUpdate(String moneyField, BigDecimal money, String dpBalanceId) {    Map params = new HashMap();    params.put("moneyField", moneyField);    params.put("money", money);    params.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForUnUpdate", params);  }  public void handleDpBalanceForUnUpdate(final List dpDetailList) {    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {        executor.startBatch();        for (int i = 0; i < dpDetailList.size(); i++) {          DpDetail dpDetail = (DpDetail) dpDetailList.get(i);          DpBalanceUpdaterForDpdetail updateDpDetail = new DpBalanceUpdaterForDpdetail();          updateDpDetail.returnBalanceForUnUpdate(dpDetail);          Map map = new HashMap();          map.put("dpDetailId", dpDetail.getDpDetailId());          executor.insert("DpBalance.updateDpDetailForUnUpdate", map);        }        executor.executeBatch();        return null;      }    });  }  public String handleDpBalanceForCarryCancel(String dpBalanceIds) {    final Map map = new HashMap();    map.put("rtnStr", "");    final String[] dpBalnaceIdArray = dpBalanceIds.split(",");    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {        executor.startBatch();        for (int i = 0; i < dpBalnaceIdArray.length; i++) {          String id = dpBalnaceIdArray[i];          map.put("dpBalanceIds", id);          executor.insert("DpBalance.SP_CP_DP_CARRY_CANCEL", map);        }        executor.executeBatch();        return null;      }    });    return null;  }  public DpBalance getDpBalanceForJjPlan(String dpBalanceId) {    return (DpBalance) this.getSqlMapClientTemplate().queryForObject("DpBalance.getDpBalanceForJjPlan",    dpBalanceId);  }  public int updateDpBalanceForAmVoucherDelete(String amVoucherId, String dpBalanceId) {    Map map = new HashMap();    map.put("amVoucherId", amVoucherId);    map.put("dpBalanceId", dpBalanceId);    return this.getSqlMapClientTemplate().update("DpBalance.updateDpBalanceForAmVoucherDelete", map);  }}