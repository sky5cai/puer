package com.ufgov.zc.server.zc.publish.impl;import java.util.List;import java.util.Map;import com.ufgov.zc.common.system.RequestMeta;import com.ufgov.zc.common.system.dto.ElementConditionDto;import com.ufgov.zc.common.zc.model.ZcEbAuditSheet;import com.ufgov.zc.common.zc.publish.IZcEbAuditSheetServiceDelegate;import com.ufgov.zc.server.zc.service.IZcEbAuditSheetService;public class ZcEbAuditSheetServiceDelegate implements IZcEbAuditSheetServiceDelegate {  private IZcEbAuditSheetService zcEbAuditSheetService;  public List getList(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {    return zcEbAuditSheetService.getList(elementConditionDto, requestMeta);  }  public void saveFN(Object o, RequestMeta requestMeta) {    zcEbAuditSheetService.save(o, requestMeta);  }  public void saveXbPersoinFN(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {    zcEbAuditSheetService.saveXbPersoin(elementConditionDto, requestMeta);  }  public void delXbPersoinFN(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {    zcEbAuditSheetService.delXbPersoin(elementConditionDto, requestMeta);  }  public Object read(Map map, RequestMeta requestMeta) {    return zcEbAuditSheetService.read(map, requestMeta);  }  public void deleteFN(Map m, RequestMeta requestMeta) {    zcEbAuditSheetService.delete(m, requestMeta);  }  public List getEntrustDetailList(Map m, RequestMeta requestMeta) {    return zcEbAuditSheetService.getEntrustDetailList(m, requestMeta);  }  public List getXbDetailList(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {    return zcEbAuditSheetService.getXbDetailList(elementConditionDto, requestMeta);  }  public IZcEbAuditSheetService getZcEbAuditSheetService() {    return zcEbAuditSheetService;  }  public void setZcEbAuditSheetService(IZcEbAuditSheetService zcEbAuditSheetService) {    this.zcEbAuditSheetService = zcEbAuditSheetService;  }  public ZcEbAuditSheet newCommitFN(ZcEbAuditSheet currSheet, boolean isFromList, RequestMeta requestMeta) throws Exception {    return this.zcEbAuditSheetService.newCommitFN(currSheet, isFromList, requestMeta);  }  public ZcEbAuditSheet auditFN(ZcEbAuditSheet sheet, boolean isFromList, RequestMeta requestMeta) {    return this.zcEbAuditSheetService.auditFN(sheet, isFromList, requestMeta);  }  public ZcEbAuditSheet callbackFN(ZcEbAuditSheet sheet, boolean isFromList, RequestMeta requestMeta) {    return this.zcEbAuditSheetService.callbackFN(sheet, isFromList, requestMeta);  }  public ZcEbAuditSheet unAuditFN(ZcEbAuditSheet sheet, boolean isFromList, RequestMeta requestMeta) {    return this.zcEbAuditSheetService.unAuditFN(sheet, isFromList, requestMeta);  }  public ZcEbAuditSheet untreadFN(ZcEbAuditSheet sheet, boolean isFromList, RequestMeta requestMeta) {    return this.zcEbAuditSheetService.untreadFN(sheet, isFromList, requestMeta);  }  public ZcEbAuditSheet auditSetNextExecutorFN(ZcEbAuditSheet sheet, List nextExecutor, boolean isFormList, RequestMeta requestMeta) {    return this.zcEbAuditSheetService.auditSetNextExecutorFN(sheet, nextExecutor, isFormList, requestMeta);  }  public List findTransData(ElementConditionDto dto, RequestMeta meta) {    return zcEbAuditSheetService.findTransData(dto, meta);  }  public void insertFN(ZcEbAuditSheet zeas, RequestMeta meta) {    // TCJLODO Auto-generated method stub    zcEbAuditSheetService.insert(zeas, meta);  }     public List getAuditSheetExportData(List dutySheetIdLst, RequestMeta meta) {    // TCJLODO Auto-generated method stub    return zcEbAuditSheetService.getAuditSheetExportData(dutySheetIdLst,   meta);  }     public String importTransDatasFN(ZcEbAuditSheet bill, RequestMeta meta) {    // TCJLODO Auto-generated method stub    return zcEbAuditSheetService.importTransDatasFN(bill, meta);  }}