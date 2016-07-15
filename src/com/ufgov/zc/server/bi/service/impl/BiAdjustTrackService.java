package com.ufgov.zc.server.bi.service.impl;import java.math.BigDecimal;import java.util.ArrayList;import java.util.Date;import java.util.HashMap;import java.util.List;import java.util.Map;import com.ufgov.zc.common.bi.model.BiAdjustTrack;import com.ufgov.zc.common.system.constants.BusinessOptionConstants;import com.ufgov.zc.common.system.dto.ElementConditionDto;import com.ufgov.zc.common.system.exception.BusinessException;import com.ufgov.zc.common.system.exception.DataAlreadyDeletedException;import com.ufgov.zc.common.system.model.AsWfDraft;import com.ufgov.zc.server.bi.dao.IBiAdjustTrackDao;import com.ufgov.zc.server.bi.service.IBiAdjustTrackService;import com.ufgov.zc.server.commonbiz.dao.IBillElementDao;import com.ufgov.zc.server.system.dao.IAsOptionDao;import com.ufgov.zc.server.system.dao.IWorkflowDao;import com.ufgov.zc.server.system.util.NumUtil;import com.ufgov.zc.server.system.util.RequestMetaUtil;import com.ufgov.zc.server.system.workflow.WFEngineAdapter;public class BiAdjustTrackService implements IBiAdjustTrackService {  private IBiAdjustTrackDao biAdjustTrackDao;  private IAsOptionDao asOptionDao;  private IWorkflowDao workflowDao;  private WFEngineAdapter wfEngineAdapter;  private IBillElementDao billElementDao;  private String BI_ADJUST_CODE_601 = "601";  public List getBiTrackByModel(BiAdjustTrack biAdjustTrack) {    return biAdjustTrackDao.getBiAdjustTrackByModel(biAdjustTrack);  }  public IBiAdjustTrackDao getBiAdjustTrackDao() {    return biAdjustTrackDao;  }  public void setBiAdjustTrackDao(IBiAdjustTrackDao biAdjustTrackDao) {    this.biAdjustTrackDao = biAdjustTrackDao;  }  public List getBiAdjustTrackEditByDto(ElementConditionDto elementConditionDto) {    return biAdjustTrackDao.getBiAdjustTrackEditByDto(elementConditionDto);  }  public List getBiAdjustTrackEditDetailByDto(BiAdjustTrack biAdjustTrack) {    return biAdjustTrackDao.getBiAdjustTrackEditDetailByDto(biAdjustTrack);  }  public List getBiAdjustTrackAuditByDto(ElementConditionDto elementConditionDto) {    return biAdjustTrackDao.getBiAdjustTrackAuditByDto(elementConditionDto);  }  public void insertBiAdjustTrack(BiAdjustTrack biAdjustTrack) {    BiAdjustTrack oldBiAdjustTrack = null;    BigDecimal adjustMoney = new BigDecimal("0");    //写余额时刻,保存时写余额0    String decbalFlag = asOptionDao.getAsOption(BusinessOptionConstants.OPT_BI_ADJUST53_DECBAL_FLAG)    .getOptVal();    if (biAdjustTrack.getBiTrackId() == null || biAdjustTrack.getBiTrackId().equalsIgnoreCase("")) {      String biTrackId = NumUtil.getInstance().getNo("BI_ADJUST", "BI_TRACK_ID", biAdjustTrack);      String inputGroupId = NumUtil.getInstance().getNo("BI_ADJUST", "INPUT_GROUP_ID", biAdjustTrack);      biAdjustTrack.setBiTrackId(biTrackId);      biAdjustTrack.setInputGroupId(inputGroupId);      biAdjustTrack.setProcDate(RequestMetaUtil.getTransDate());      biAdjustTrack.setCdate(new Date());      Long draftid = workflowDao.createDraftId();      biAdjustTrack.setProcessInstId(draftid);      biAdjustTrack.setbaccId("9");      AsWfDraft asWfDraft = new AsWfDraft();      asWfDraft.setCompoId("BI_ADJUST53");      asWfDraft.setWfDraftName(biAdjustTrack.getTitleField());      asWfDraft.setUserId(RequestMetaUtil.getSvUserID());      asWfDraft.setMasterTabId("BI_ADJUST_TRACK");      asWfDraft.setWfDraftId(BigDecimal.valueOf(draftid.longValue()));      workflowDao.insertAsWfdraft(asWfDraft);      biAdjustTrackDao.insertBiAdjustTrack(biAdjustTrack);      String saveOption = asOptionDao.getAsOption(BusinessOptionConstants.OPT_BI_GENBILL_POINT).getOptVal();      if (saveOption.equalsIgnoreCase("S")) {        biGenBillForBiBalance(biAdjustTrack);      }      adjustMoney = biAdjustTrack.getCurMoney();    } else {      oldBiAdjustTrack = biAdjustTrackDao.getBiAdjustTrackForUpdateById(biAdjustTrack.getBiTrackId());      if (oldBiAdjustTrack == null) {        throw new DataAlreadyDeletedException("调整指标id为：" + biAdjustTrack.getBiTrackId() + "的数据已被删除,保存失败！");      }      biAdjustTrackDao.updateBiAdjustTrack(biAdjustTrack);      adjustMoney = biAdjustTrack.getOrgMoney().subtract(oldBiAdjustTrack.getOrgMoney());    }    if ("0".equalsIgnoreCase(decbalFlag)) {      updateAdjustMoney(biAdjustTrack, adjustMoney);    }  }  public void deleteBiAdjustTrack(BiAdjustTrack biAdjustTrack) {    //写余额时刻,保存时写余额0    String decbalFlag = asOptionDao.getAsOption(BusinessOptionConstants.OPT_BI_ADJUST53_DECBAL_FLAG)    .getOptVal();    if ("0".equalsIgnoreCase(decbalFlag)) {      BiAdjustTrack oldBiAdjustTrack = (BiAdjustTrack) biAdjustTrackDao      .getBiAdjustTrackForUpdateById(biAdjustTrack.getBiTrackId());      List list = new ArrayList();      if ("602".equals(oldBiAdjustTrack.getBiAdjustCode())) {        list.add(oldBiAdjustTrack);      } else if ("601".equals(oldBiAdjustTrack.getBiAdjustCode())) {        list.add(oldBiAdjustTrack);        list.addAll(biAdjustTrackDao.getBiAdjustTrackEditDetailByDto(biAdjustTrack));//明细数据      }      for (int i = 0; i < list.size(); i++) {        BiAdjustTrack temp = (BiAdjustTrack) list.get(i);        updateAdjustMoney(temp, temp.getOrgMoney().negate());      }    }    int count = biAdjustTrackDao.deleteBiAdjustTrackById(biAdjustTrack.getBiTrackId());    if (count == 0) {      throw new DataAlreadyDeletedException("调整指标id为：" + biAdjustTrack.getBiTrackId() + "的数据已被删除！");    }  }  public String biGenBillForBiBalance(BiAdjustTrack biAdjustTrack) {    Map map = new HashMap();    map.put("pPkValue", biAdjustTrack.getBiTrackId());    map.put("pBalanceId", biAdjustTrack.getTargetBalanceId());    map.put("pBiAdjustCode", biAdjustTrack.getBiAdjustCode());    map.put("pBiAdjustBiMoney", biAdjustTrack.getOrgMoney().toString());    map.put("pBiAdjustMoney", biAdjustTrack.getOrgMoney().toString());    map.put("pBiGenBillType", biAdjustTrack.getGenBillType());    map.put("pUserId", biAdjustTrack.getInputorId());    map.put("pProcDate", biAdjustTrack.getProcDate().toString());    map.put("pRemark", biAdjustTrack.getRemark());    map.put("pIsIorD", biAdjustTrack.getIsIord().trim());    map.put("pDattr1", "");    map.put("pDattr2", "");    map.put("pDattr3", "");    map.put("pRetMessage", "");    return biAdjustTrackDao.biGenBillForBiBalance(map);  }  public IAsOptionDao getAsOptionDao() {    return asOptionDao;  }  public void setAsOptionDao(IAsOptionDao asOptionDao) {    this.asOptionDao = asOptionDao;  }  public IWorkflowDao getWorkflowDao() {    return workflowDao;  }  public void setWorkflowDao(IWorkflowDao workflowDao) {    this.workflowDao = workflowDao;  }  public WFEngineAdapter getWfEngineAdapter() {    return wfEngineAdapter;  }  public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {    this.wfEngineAdapter = wfEngineAdapter;  }  public IBillElementDao getBillElementDao() {    return billElementDao;  }  public void setBillElementDao(IBillElementDao billElementDao) {    this.billElementDao = billElementDao;  }  public void blankoutForBiAdjust(BiAdjustTrack biAdjustTrack) {    this.wfEngineAdapter.interrupt(biAdjustTrack.getComment(), biAdjustTrack, null);    //写余额时刻,保存时写余额0    String decbalFlag = asOptionDao.getAsOption(BusinessOptionConstants.OPT_BI_ADJUST53_DECBAL_FLAG)    .getOptVal();    if ("0".equalsIgnoreCase(decbalFlag)) {      BiAdjustTrack oldBiAdjustTrack = (BiAdjustTrack) biAdjustTrackDao      .getBiAdjustTrackForUpdateById(biAdjustTrack.getBiTrackId());      List list = new ArrayList();      if ("602".equals(oldBiAdjustTrack.getBiAdjustCode())) {        list.add(oldBiAdjustTrack);      } else if ("601".equals(oldBiAdjustTrack.getBiAdjustCode())) {        list.add(oldBiAdjustTrack);        list.addAll(biAdjustTrackDao.getBiAdjustTrackEditDetailByDto(biAdjustTrack));//明细数据      }      for (int i = 0; i < list.size(); i++) {        BiAdjustTrack temp = (BiAdjustTrack) list.get(i);        updateAdjustMoney(temp, temp.getOrgMoney().negate());      }    }    setIsValidFlag(biAdjustTrack);  }  public BiAdjustTrack callbackForBiAdjust(BiAdjustTrack biAdjustTrack) {    wfEngineAdapter.callback(biAdjustTrack.getComment(), biAdjustTrack, null);    return biAdjustTrack;  }  public BiAdjustTrack sendForBiAdjust(BiAdjustTrack biAdjustTrack) {    wfEngineAdapter.newCommit("", biAdjustTrack, null);    BiAdjustTrack newBiAdjustTrack = biAdjustTrackDao.getBiAdjustTrackById(biAdjustTrack);    if (newBiAdjustTrack.getastatusCode().equalsIgnoreCase("3")) {      String saveOption = asOptionDao.getAsOption(BusinessOptionConstants.OPT_BI_GENBILL_POINT).getOptVal();      if (saveOption.equalsIgnoreCase("A")) {        List detailData = new ArrayList();        if (biAdjustTrack.getBiAdjustCode().equalsIgnoreCase(BI_ADJUST_CODE_601)) {          detailData = biAdjustTrackDao.getBiAdjustTrackEditDetailByDto(biAdjustTrack);          detailData.add(biAdjustTrack);        } else {          detailData.add(biAdjustTrack);        }        for (int i = 0; i < detailData.size(); i++) {          BiAdjustTrack curBiAdjustTrack = (BiAdjustTrack) detailData.get(i);          String retMessage = biGenBillForBiBalance(curBiAdjustTrack);          if (retMessage != null && !retMessage.equals("")) {            throw new BusinessException(" " + retMessage);          }        }      }    }    return biAdjustTrack;  }  public BiAdjustTrack unAuditForBiAdjust(BiAdjustTrack biAdjustTrack) {    String saveOption = asOptionDao.getAsOption(BusinessOptionConstants.OPT_BI_GENBILL_POINT).getOptVal();    wfEngineAdapter.unAudit(biAdjustTrack.getComment(), biAdjustTrack, null);    if (saveOption.equalsIgnoreCase("A")) {      List detailData = new ArrayList();      if (biAdjustTrack.getBiAdjustCode().equalsIgnoreCase(BI_ADJUST_CODE_601)) {        detailData = biAdjustTrackDao.getBiAdjustTrackEditDetailByDto(biAdjustTrack);        detailData.add(biAdjustTrack);      } else {        detailData.add(biAdjustTrack);      }      for (int i = 0; i < detailData.size(); i++) {        BiAdjustTrack curBiAdjustTrack = (BiAdjustTrack) detailData.get(i);        Map map = new HashMap();        map.put("pPkValue", curBiAdjustTrack.getBiTrackId());        map.put("pBalanceId", curBiAdjustTrack.getTargetBalanceId());        map.put("pBiAdjustCode", curBiAdjustTrack.getBiAdjustCode());        map.put("pAdjustBiMoney", curBiAdjustTrack.getBiMoney().toString());        map.put("pAdjustMoney", curBiAdjustTrack.getCurMoney().toString());        map.put("pBiGenBillType", curBiAdjustTrack.getGenBillType());        map.put("pUserId", curBiAdjustTrack.getInputorId());        map.put("pProcDate", curBiAdjustTrack.getProcDate().toString());        map.put("pRemark", curBiAdjustTrack.getRemark());        map.put("pIsIorD", curBiAdjustTrack.getIsIord().trim());        map.put("pDattr1", "");        map.put("pDattr2", "");        map.put("pDattr3", "");        map.put("pRetMessage", "");        String retMessage = biAdjustTrackDao.biAdjustRework(map);        if (retMessage != null && !retMessage.equalsIgnoreCase("")) {          throw new BusinessException(" " + retMessage);        }      }    }    return biAdjustTrack;  }  public BiAdjustTrack untreadForBiAdjust(BiAdjustTrack biAdjustTrack) {    wfEngineAdapter.untreadGk(biAdjustTrack.getComment(), biAdjustTrack, null);    return biAdjustTrack;  }  public BiAdjustTrack auditForBiAdjust(BiAdjustTrack biAdjustTrack) {    wfEngineAdapter.commit(biAdjustTrack.getComment(), biAdjustTrack, null);    String saveOption = asOptionDao.getAsOption(BusinessOptionConstants.OPT_BI_GENBILL_POINT).getOptVal();    if (saveOption.equalsIgnoreCase("A")) {      List detailData = new ArrayList();      if (biAdjustTrack.getBiAdjustCode().equalsIgnoreCase(BI_ADJUST_CODE_601)) {        detailData = biAdjustTrackDao.getBiAdjustTrackEditDetailByDto(biAdjustTrack);        detailData.add(biAdjustTrack);      } else {        detailData.add(biAdjustTrack);      }      if (this.workflowDao.isFinalAudit(biAdjustTrack.getProcessInstId())) {        for (int i = 0; i < detailData.size(); i++) {          BiAdjustTrack curBiAdjustTrack = (BiAdjustTrack) detailData.get(i);          curBiAdjustTrack.setAuditorId(RequestMetaUtil.getSvUserID());          curBiAdjustTrack.setAuditorName(RequestMetaUtil.getSvUserName());          biAdjustTrackDao.updateAuditInfo(curBiAdjustTrack);          this.biAdjustBeforeSave(curBiAdjustTrack);          String retMessage = biGenBillForBiBalance(curBiAdjustTrack);          if (retMessage != null && !retMessage.equals("")) {            throw new BusinessException(" " + retMessage);          }        }      }    }    return biAdjustTrack;  }  public List insertBiAdjustTrack(List biAdjustTrackList) {    String inputGroupId = NumUtil.getInstance().getNo("BI_ADJUST", "INPUT_GROUP_ID",    (BiAdjustTrack) biAdjustTrackList.get(0));    //写余额时刻,保存时写余额0    String decbalFlag = asOptionDao.getAsOption(BusinessOptionConstants.OPT_BI_ADJUST53_DECBAL_FLAG)    .getOptVal();    for (int i = 0; i < biAdjustTrackList.size(); i++) {      BiAdjustTrack biAdjustTrack = (BiAdjustTrack) biAdjustTrackList.get(i);      BiAdjustTrack oldBiAdjustTrack = null;      BigDecimal adjustMoney = new BigDecimal("0");      if (biAdjustTrack.getBiTrackId() == null || biAdjustTrack.getBiTrackId().equalsIgnoreCase("")) {        biAdjustBeforeSave(biAdjustTrack);        String biTrackId = NumUtil.getInstance().getNo("BI_ADJUST", "BI_TRACK_ID", biAdjustTrack);        biAdjustTrack.setBiTrackId(biTrackId);        biAdjustTrack.setInputGroupId(biAdjustTrack.getInputGroupId() == null ? inputGroupId : biAdjustTrack        .getInputGroupId());        biAdjustTrack.setProcDate(RequestMetaUtil.getTransDate());        biAdjustTrack.setCdate(new Date());        biAdjustTrack.setbaccId("9");        if (biAdjustTrack.getIsIord().equalsIgnoreCase("-1")) {          Long draftid = workflowDao.createDraftId();          biAdjustTrack.setProcessInstId(draftid);          AsWfDraft asWfDraft = new AsWfDraft();          asWfDraft.setCompoId("BI_ADJUST53");          asWfDraft.setWfDraftName(biAdjustTrack.getTitleField());          asWfDraft.setUserId(RequestMetaUtil.getSvUserID());          asWfDraft.setMasterTabId("BI_ADJUST_TRACK");          asWfDraft.setWfDraftId(BigDecimal.valueOf(draftid.longValue()));          workflowDao.insertAsWfdraft(asWfDraft);        }        biAdjustTrackDao.insertBiAdjustTrack(biAdjustTrack);        String saveOption = asOptionDao.getAsOption(BusinessOptionConstants.OPT_BI_GENBILL_POINT).getOptVal();        if (saveOption.equalsIgnoreCase("S")) {          String retMessage = biGenBillForBiBalance(biAdjustTrack);          if (retMessage != null && !retMessage.equalsIgnoreCase("")) {            throw new BusinessException(" " + retMessage);          }        }        adjustMoney = biAdjustTrack.getCurMoney();      } else {        oldBiAdjustTrack = biAdjustTrackDao.getBiAdjustTrackForUpdateById(biAdjustTrack.getBiTrackId());        if (oldBiAdjustTrack == null) {          throw new DataAlreadyDeletedException("调整指标id为：" + biAdjustTrack.getBiTrackId() + "的数据已被删除,保存失败！");        }        biAdjustBeforeSave(biAdjustTrack);        biAdjustTrackDao.updateBiAdjustTrack(biAdjustTrack);        adjustMoney = biAdjustTrack.getOrgMoney().subtract(oldBiAdjustTrack.getOrgMoney());      }      if ("0".equalsIgnoreCase(decbalFlag)) {        updateAdjustMoney(biAdjustTrack, adjustMoney);      }    }    return biAdjustTrackList;  }  private void updateAdjustMoney(BiAdjustTrack biAdjustTrack, BigDecimal adjustMoney) {    if (adjustMoney.signum() != 0) {      Map map = new HashMap();      map.put("pBalanceId", biAdjustTrack.getTargetBalanceId());      map.put("pAdjustMoney", adjustMoney.toString());      map.put("pIsiord", biAdjustTrack.getIsIord().trim());      map.put("pRetMessage", "");      String retMessage = biAdjustTrackDao.updateAdjustMoney(map);      if (retMessage != null && !retMessage.equalsIgnoreCase("")) {        throw new BusinessException(" " + retMessage);      }    }  }  public void biAdjustBeforeSave(BiAdjustTrack biAdjustTrack) {    Map map = new HashMap();    map.put("pPkValue", biAdjustTrack.getBiTrackId());    map.put("pBalanceId", biAdjustTrack.getTargetBalanceId());    map.put("pAdjustMoney", biAdjustTrack.getCurMoney().toString());    map.put("pDattr1", biAdjustTrack.getIsIord().trim());    map.put("pDattr2", "");    map.put("pDattr3", "");    map.put("pRetMessage", "");    String retMessage = biAdjustTrackDao.biAdjustBeforeSave(map);    if (retMessage != null && !retMessage.equalsIgnoreCase("")) {      throw new BusinessException(" " + retMessage);    }  }  private void setIsValidFlag(BiAdjustTrack biAdjustTrack) {    String optionValue = asOptionDao.getAsOption(BusinessOptionConstants.OPT_CP_DEL_INVALIDATION).getOptVal();    String isValid = optionValue.equals("1") ? "9" : "0";    String astatusCode = "9";    biAdjustTrackDao.updateBiAdjustTrackIsValid(isValid, astatusCode, biAdjustTrack.getBiTrackId());  }  public BiAdjustTrack getBiAdjustTrack(BiAdjustTrack biAdjustTrack) {    return biAdjustTrackDao.getBiAdjustTrackById(biAdjustTrack);  }}