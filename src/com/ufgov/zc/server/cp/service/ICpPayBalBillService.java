package com.ufgov.zc.server.cp.service;import java.util.List;import java.util.Map;import com.ufgov.zc.common.system.dto.ElementConditionDto;import com.ufgov.zc.common.system.dto.PrintObject;public interface ICpPayBalBillService {  public List getCpPayBalBill(ElementConditionDto dto);  public void genCpPayBalBill(ElementConditionDto condition, List cpVoucherList);  public void cancelCpPayBalBill(List cpPayBalBillList);  public void updateCpPayBalBillStatus(Map params);  public PrintObject genCpPayBalBillPrintObject(List cpPayBalBillList);  public void updateCpPayBalBillPrintTimes(String ids);}