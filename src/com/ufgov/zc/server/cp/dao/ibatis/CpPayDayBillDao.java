package com.ufgov.zc.server.cp.dao.ibatis;import java.sql.SQLException;import java.util.HashMap;import java.util.List;import java.util.Map;import org.springframework.orm.ibatis.SqlMapClientCallback;import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;import com.ibatis.sqlmap.client.SqlMapExecutor;import com.ufgov.zc.common.cp.model.CpPayDayBill;import com.ufgov.zc.common.cp.model.CpPayDayList;import com.ufgov.zc.common.cp.model.CpPayDayRelation;import com.ufgov.zc.common.cp.model.CpVoucher;import com.ufgov.zc.common.system.constants.NumLimConstants;import com.ufgov.zc.common.system.dto.ElementConditionDto;import com.ufgov.zc.server.cp.dao.ICpPayDayBillDao;import com.ufgov.zc.server.system.util.NumLimUtil;import com.ufgov.zc.server.system.util.Util;public class CpPayDayBillDao extends SqlMapClientDaoSupport implements ICpPayDayBillDao {  public List getCpPayDayBill(ElementConditionDto dto) {//    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getNumLimCompoId(),////    NumLimConstants.FWATCH));    List list = this.getSqlMapClientTemplate().queryForList("CpPayDayBill.getCpPayDayBill", dto);    List cpPayDayList = this.getSqlMapClientTemplate().queryForList("CpPayDayBill.getCpPayDayList", dto);    Map cpPayDayListMap = Util.createKeyListMap("payDayBillId", cpPayDayList);    List cpPayDayRelation = this.getSqlMapClientTemplate().queryForList("CpPayDayBill.getCpPayDayRelation", dto);    Map cpPayDayRelationMap = Util.createKeyListMap("payDayBillId", cpPayDayRelation);    for (int i = 0; i < list.size(); i++) {      CpPayDayBill bill = (CpPayDayBill) list.get(i);      List tempList1 = (List) cpPayDayListMap.get(bill.getPayDayBillId());      List tempList2 = (List) cpPayDayRelationMap.get(bill.getPayDayBillId());      if (tempList1 != null) {        bill.setCpPayDayLists(tempList1);      }      if (tempList2 != null) {        bill.setCpPayDayRelations(tempList2);      }    }    return list;  }  public int updateCpvoucherByPayDayBillIdForDel(CpPayDayBill bill) {    Map params = new HashMap();    params.put("payDayBillId", bill.getPayDayBillId());    return this.getSqlMapClientTemplate().update("CpPayDayBill.updateCpvoucherByPayDayBillIdForDel", params);  }  public int updateCpvoucherByPayDayBillId(CpPayDayBill bill) {    Map params = new HashMap();    params.put("payDayBillId", bill.getPayDayBillId());    String vouNoListStr = bill.getPayVouNoList();    String[] buffer = vouNoListStr.split(",");    String temp = "";    for (int i = 0; i < buffer.length; i++) {      temp += "," + "'" + buffer[i] + "'";    }    temp = temp.substring(1);    params.put("payVouNoList", temp);    return this.getSqlMapClientTemplate().update("CpPayDayBill.updateCpvoucherByPayDayBillId", params);  }  public void insertCpPayDayBill(CpPayDayBill bill) {    this.getSqlMapClientTemplate().insert("CpPayDayBill.insertCpPayDayBill", bill);  }  public void insertCpPayDayList(CpPayDayList dayList) {    this.getSqlMapClientTemplate().insert("CpPayDayBill.insertCpPayDayList", dayList);  }  public void insertCpPayDayRelation(CpPayDayRelation dayRelation) {    this.getSqlMapClientTemplate().insert("CpPayDayBill.insertCpPayDayRelation", dayRelation);  }  public void deleteCpPayDayBill(CpPayDayBill bill) {    this.getSqlMapClientTemplate().delete("CpPayDayBill.deleteCpPayDayBill", bill);  }  public void deleteCpPayDayListByDayBill(CpPayDayBill bill) {    this.getSqlMapClientTemplate().delete("CpPayDayBill.deleteCpPayDayListByDayBill", bill);  }  public void deleteCpPayRelationByDayBill(CpPayDayBill bill) {    this.getSqlMapClientTemplate().delete("CpPayDayBill.deleteCpPayRelationByDayBill", bill);  }  public void updateCpPayDayBillStatus(Map params) {    this.getSqlMapClientTemplate().update("CpPayDayBill.updateCpPayDayBillAstatus", params);  }  public void updateCpPayDayBillPrintTimes(String ids) {    this.getSqlMapClientTemplate().update("CpPayDayBill.updateCpPayDayBillPrintTimes", ids);  }  public List getCpPayDayListByPayDayBillId(String payDayBillId) {    Map params = new HashMap();    params.put("payDayBillId", payDayBillId);    return this.getSqlMapClientTemplate().queryForList("CpPayDayBill.getCpPayDayListByPayDayBillId", params);  }  public void cpapdrAccepted(List payDayBilllist) {    final List list = payDayBilllist;    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {        executor.startBatch();        for (int i = 0; i < list.size(); i++) {          CpPayDayBill value = (CpPayDayBill) list.get(i);          executor.update("CpPayDayBill.updateCpPayDayBillForAccepted", value);        }        executor.executeBatch();        return null;      }    });  }  public void cpapdrCancelAccepted(List payDayBilllist) {    final List list = payDayBilllist;    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {        executor.startBatch();        for (int i = 0; i < list.size(); i++) {          CpPayDayBill value = (CpPayDayBill) list.get(i);          executor.update("CpPayDayBill.updateCpPayDayBillForCancelAccepted", value);        }        executor.executeBatch();        return null;      }    });  }}