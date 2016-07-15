package com.ufgov.zc.server.zc.service.impl;import java.math.BigDecimal;import java.sql.SQLException;import java.util.ArrayList;import java.util.Date;import java.util.HashMap;import java.util.List;import java.util.Map;import java.util.Random;import java.util.Timer;import java.util.TimerTask;import com.ufgov.zc.common.system.RequestMeta;import com.ufgov.zc.common.system.dto.ElementConditionDto;import com.ufgov.zc.common.system.dto.MainSubBill;import com.ufgov.zc.common.system.dto.PrintObject;import com.ufgov.zc.common.system.model.AsWfDraft;import com.ufgov.zc.common.zc.model.EmCallExpertRecord;import com.ufgov.zc.common.zc.model.EmEvaluationCondition;import com.ufgov.zc.common.zc.model.EmExpert;import com.ufgov.zc.common.zc.model.EmExpertBillFilter;import com.ufgov.zc.common.zc.model.EmExpertEvaluation;import com.ufgov.zc.common.zc.model.EmExpertSelectionBill;import com.ufgov.zc.common.zc.model.ZcEbPack;import com.ufgov.zc.server.system.dao.ibatis.WorkflowDao;import com.ufgov.zc.server.system.print.PrintManager;import com.ufgov.zc.server.system.util.NumUtil;import com.ufgov.zc.server.system.workflow.WFEngineAdapter;import com.ufgov.zc.server.zc.dao.IBaseDao;import com.ufgov.zc.server.zc.service.IZcEmExpertSelectionService;import com.ufgov.zc.server.zc.service.IZcEmExpertService;public class ZcEmExpertSelectionService implements IZcEmExpertSelectionService {  private IBaseDao baseDao;  private WorkflowDao workflowDao;  private WFEngineAdapter wfEngineAdapter;    public List getList(ElementConditionDto dto, RequestMeta requestMeta) {    //    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(),    //      NumLimConstants.FWATCH));    return baseDao.query("EmExpertSelectionBill.list", dto);  }  public EmExpertSelectionBill getModel(Map map, RequestMeta requestMeta) {    return (EmExpertSelectionBill) baseDao.read("EmExpertSelectionBill.read", map);  }  public EmExpertSelectionBill newCommitFN(EmExpertSelectionBill currentObject, boolean isFromList, RequestMeta requestMeta) throws Exception {    if (!isFromList) {      this.updateExpertSelection(currentObject, requestMeta);    }    wfEngineAdapter.newCommit(currentObject.getComment(), currentObject, requestMeta);    return selectByPrimaryKey(currentObject.getBillCode());  }  public EmExpertSelectionBill auditFN(EmExpertSelectionBill bill, RequestMeta requestMeta) {    wfEngineAdapter.commit(bill.getComment(), bill, requestMeta);    return bill;  }  public EmExpertSelectionBill unAuditFN(EmExpertSelectionBill bill, RequestMeta requestMeta) {    //    Map map = new HashMap();    //    map.put("EM_BILL_CODE", bill.getBillCode());    //baseDao.delete("EmExpertSelectionBill.delete", map);    wfEngineAdapter.rework(bill.getComment(), bill, requestMeta);    return bill;  }  public EmExpertSelectionBill callbackFN(EmExpertSelectionBill bill, RequestMeta requestMeta) {    wfEngineAdapter.callback(bill.getComment(), bill, requestMeta);    return bill;  }  public EmExpertSelectionBill untreadFN(EmExpertSelectionBill bill, RequestMeta requestMeta) {    wfEngineAdapter.untread(bill.getComment(), bill, requestMeta);    return bill;  }  public EmExpertSelectionBill selectByPrimaryKey(String emBillCode) {    Map map = new HashMap();    map.put("EM_BILL_CODE", emBillCode);    EmExpertSelectionBill emExpertSelectionBill = (EmExpertSelectionBill) baseDao.read("EmExpertSelectionBill.read", map);    return emExpertSelectionBill;  }  public EmExpertSelectionBill updateExpertSelection(EmExpertSelectionBill emExpertSelectionBill, RequestMeta requestMeta) throws Exception {    String userId = requestMeta.getSvUserID();    String compoId = requestMeta.getCompoId();    boolean isDraft = false;    if (emExpertSelectionBill.getProcessInstId() == null || emExpertSelectionBill.getProcessInstId().longValue() == -1) {      Long draftid = workflowDao.createDraftId();      emExpertSelectionBill.setProcessInstId(draftid);      isDraft = true;    }    if (isDraft) {      AsWfDraft asWfDraft = new AsWfDraft();      asWfDraft.setCompoId(compoId);      asWfDraft.setWfDraftName(emExpertSelectionBill.getBillCode());      asWfDraft.setUserId(userId);      asWfDraft.setMasterTabId(compoId);      asWfDraft.setWfDraftId(BigDecimal.valueOf(emExpertSelectionBill.getProcessInstId().longValue()));      workflowDao.insertAsWfdraft(asWfDraft);    }    return emExpertSelectionBill;  }  public void delete(Map map, RequestMeta requestMeta) {    baseDao.delete("EmExpertSelectionBill.delete", map);  }  public List getCallExpertRecordList(Map m, RequestMeta requestMeta) {    //这里显示和隐藏数据，是用于打印对象   if ("true".equals((String) m.get("SHOW_INFO"))|| m.get("SHOW_INFO")==null) {      return baseDao.query("EmCallExpertRecord.listByEmBillCode", m);    } else {      List cerList = baseDao.query("EmCallExpertRecord.listByEmBillCode", m);      if (cerList != null && cerList.size() > 0) {        for (int i = 0; i < cerList.size(); i++) {          EmCallExpertRecord cer = (EmCallExpertRecord) cerList.get(i);          cer.setEmExpert(hideExpertInfo(cer.getEmExpert()));        }      }      return cerList;    }  }  private String hideMobileStr = "***********";  private EmExpert hideExpertInfo(EmExpert e) {    e.setEmExpertCode("******");    e.setEmExpertName("***");    String mobile = e.getEmMobile();    if (mobile != null) {      // 保留后4位手机号      //mobile = "*******" + mobile.subSequence(mobile.length() - 4, mobile.length());      e.setEmMobile(hideMobileStr);    }    return e;  }  public List getExpertEvaluationList(Map m, RequestMeta requestMeta) {          if ("true".equals((String) m.get("SHOW_INFO")) || m.get("SHOW_INFO")==null) {      return baseDao.query("EmExpertEvaluation.listByEmBillCode", m);    } else {      List eeList = baseDao.query("EmExpertEvaluation.listByEmBillCode", m);      if (eeList != null && eeList.size() > 0) {        for (int i = 0; i < eeList.size(); i++) {          EmExpertEvaluation ee = (EmExpertEvaluation) eeList.get(i);          ee.setEmExpert(hideExpertInfo(ee.getEmExpert()));        }      }      return eeList;    }  }  public List getPackList(Map m, RequestMeta requestMeta) {    return baseDao.query("EmEvaluationCondition.packListByEmBillCode", m);  }  public List getAllPackListByProjCode(Map m, RequestMeta requestMeta) {    return baseDao.query("EmExpertSelectionBill.packListByProjCode", m);  }  public List getAllExcludeExpertListAuto(Map m, RequestMeta requestMeta) {    return baseDao.query("EmExpertBillFilter.listAllExcludeExpertListAuto", m);  }  public List getEvaluationConditionList(Map m, RequestMeta requestMeta) {    return baseDao.query("EmEvaluationCondition.listByEmBillCode", m);  }  public List getExpertBillFilterList(Map m, RequestMeta requestMeta) {    return baseDao.query("EmExpertBillFilter.listByEmBillCode", m);  }  public List getEmExpertTypeSelectionList(ElementConditionDto dto, RequestMeta requestMeta) {    return baseDao.query("EmExpertType.selectionList", dto);  }  public EmExpertSelectionBill save(EmExpertSelectionBill bill, RequestMeta requestMeta) {    bill.setYear(String.valueOf(requestMeta.getSvNd()));    if (bill.getBillCode() == null || "".equals(bill.getBillCode()) || bill.getBillCode().equals("自动编号")) {      return insert(bill, requestMeta);    } else {      return update(bill);    }  }  public EmExpertSelectionBill insert(EmExpertSelectionBill bill, RequestMeta requestMeta) {    String userId = requestMeta.getSvUserID();    String compoId = requestMeta.getCompoId();    String billCode = NumUtil.getInstance().getNo(compoId, "EM_BILL_CODE", bill);    Long draftid = workflowDao.createDraftId();    bill.setBillCode(billCode);//    bill.setInputorDate(new Date());    bill.setProcessInstId(draftid);    baseDao.insert("EmExpertSelectionBill.insert", bill);    insertEmEvaluationCondition(bill);    insertEmExpertBillFilter(bill);    insertEmPack(bill);        //扬中增加抽取结果插入，用于前期手工编制抽取单    deleteEmResult(bill);    insertEmResult(bill);        AsWfDraft asWfDraft = new AsWfDraft();    asWfDraft.setCompoId(compoId);    asWfDraft.setWfDraftName(billCode);    asWfDraft.setUserId(userId);    asWfDraft.setMasterTabId(compoId);    asWfDraft.setWfDraftId(BigDecimal.valueOf(draftid.longValue()));    workflowDao.insertAsWfdraft(asWfDraft);    return bill;  }  private void insertEmResult(EmExpertSelectionBill bill) {    // TCJLODO Auto-generated method stub    List eelList = bill.getExpertEvaluationList();    if (eelList != null) {      for (int i = 0; i < eelList.size(); i++) {        EmExpertEvaluation ebf = (EmExpertEvaluation) eelList.get(i);        ebf.setEmBillCode(bill.getBillCode());                if(ebf.getEmResponseStatus()==null){//手工录入的专家，将其应答和通知状态置为 参加和已通知          ebf.setEmResponseStatus("9");          ebf.setEmNoticeStatus("1");        }        baseDao.insert("EmExpertEvaluation.insertEmExpertEvaluation", ebf);      }    }      }  public void insertEmEvaluationCondition(EmExpertSelectionBill bill) {    List eecList = bill.getExpertEvalConditionList();    if (eecList != null) {      for (int i = 0; i < eecList.size(); i++) {        EmEvaluationCondition eec = (EmEvaluationCondition) eecList.get(i);        eec.setEmBillCode(bill.getBillCode());        eec.setEmYear(bill.getYear());        baseDao.insert("EmEvaluationCondition.insert", eec);      }    }  }  public void insertEmExpertBillFilter(EmExpertSelectionBill bill) {    List eelList = bill.getExcludeExpertList();    if (eelList != null) {      for (int i = 0; i < eelList.size(); i++) {        EmExpertBillFilter ebf = (EmExpertBillFilter) eelList.get(i);        ebf.setEmBillCode(bill.getBillCode());        baseDao.insert("EmExpertBillFilter.insert", ebf);      }    }  }  public void insertEmPack(EmExpertSelectionBill bill) {    List packList = bill.getPackList();    if (packList != null) {      for (int i = 0; i < packList.size(); i++) {        ZcEbPack pack = (ZcEbPack) packList.get(i);        Map map = new HashMap();        map.put("EM_BILL_CODE", bill.getBillCode());        map.put("PROJ_CODE", pack.getProjCode());        map.put("PACK_CODE", pack.getPackCode());        baseDao.insert("EmEvaluationCondition.insertPack", map);      }    }  }  public EmExpertSelectionBill update(EmExpertSelectionBill bill) {    //checkConsistency(proj);    baseDao.update("EmExpertSelectionBill.update", bill);    Map m = new HashMap();    m.put("EM_BILL_CODE", bill.getBillCode());    baseDao.delete("EmEvaluationCondition.deleteByEmBillCode", m);    baseDao.delete("EmExpertBillFilter.deleteByEmBillCode", m);    baseDao.delete("EmEvaluationCondition.deletePackByEmBillCode", m);    insertEmEvaluationCondition(bill);    insertEmExpertBillFilter(bill);    insertEmPack(bill);        //扬中增加抽取结果插入，用于前期手工编制抽取单    deleteEmResult(bill);    insertEmResult(bill);        //更新通话记录，这里其实是不需要的，但有客户提出需要，在前台界面的呼叫记录表下面增加了删除按钮，因此这里也进行更新    baseDao.delete("EmCallExpertRecord.deleteCallRecordByBillCode", m);    insertEmCallRecords(bill);    return bill;  }  private void insertEmCallRecords(EmExpertSelectionBill bill) {    // TCJLODO Auto-generated method stub    List eelList = bill.getCallExpertRecordList();    if (eelList != null) {      for (int i = 0; i < eelList.size(); i++) {        EmCallExpertRecord ebf = (EmCallExpertRecord) eelList.get(i);        ebf.setEmBillCode(bill.getBillCode());        baseDao.insert("EmCallExpertRecord.insertCallRecord", ebf);      }    }  }  private void deleteEmResult(EmExpertSelectionBill bill){    Map m = new HashMap();    m.put("emBillCode", bill.getBillCode());    baseDao.delete("EmExpertEvaluation.deleteExpertEvaluationList", m);  }  public void updateBillStatus(EmExpertSelectionBill bill, RequestMeta requestMeta) {    Map m = new HashMap();    m.put("EM_BILL_CODE", bill.getBillCode());    m.put("EM_STATUS", bill.getBillStatus());    baseDao.update("EmExpertSelectionBill.updateBillStatus", m);  }  public boolean updateBillServer(Map m, RequestMeta requestMeta) {    Map rlt = (Map) baseDao.read("EmExpertSelectionBill.readBillServer", m);    if (rlt == null) {      baseDao.insert("EmExpertSelectionBill.insertBillServer", m);      //return true;    } else {      baseDao.update("EmExpertSelectionBill.updateBillServer", m);      //return true;    }    return true;  }  public PrintObject genEmExpertEvaluationPrintObject(EmExpertSelectionBill currentBill, RequestMeta requestMeta) {    MainSubBill mainSubBill = new MainSubBill();    mainSubBill.setMainBill(currentBill);    Map m = new HashMap();    m.put("EM_BILL_CODE", currentBill.getBillCode());    if ("fprint".equals(requestMeta.getFuncId())) {      m.put("SHOW_INFO", "true");    } else if ("fprint_preview".equals(requestMeta.getFuncId())) {      m.put("SHOW_INFO", "false");    }    mainSubBill.setSubBillList(getExpertEvaluationList(m, requestMeta));    return PrintManager.genMainSubPrintObject(mainSubBill);  }  public IBaseDao getBaseDao() {    return baseDao;  }  public void setBaseDao(IBaseDao baseDao) {    this.baseDao = baseDao;  }  public WorkflowDao getWorkflowDao() {    return workflowDao;  }  public void setWorkflowDao(WorkflowDao workflowDao) {    this.workflowDao = workflowDao;  }  public WFEngineAdapter getWfEngineAdapter() {    return wfEngineAdapter;  }  public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {    this.wfEngineAdapter = wfEngineAdapter;  }     public EmExpertSelectionBill fakeSelectExperts( EmExpertSelectionBill currentBill, RequestMeta requestMeta) {    // TCJLODO Auto-generated method stub    try {      //获取全部专家      ElementConditionDto dto=new ElementConditionDto();      dto.setStatus(EmExpert.STATUS_ENABLE);//      List expertLst=expertService.getEmExpertList(dto, requestMeta);             List selectedExpertLst=new ArrayList();       List calledExpertLst=new ArrayList();              List evalLst=new ArrayList();       List callRecordLst=new ArrayList();              boolean notEnaugh=false;          // TCJLODO Auto-generated method stub          if(currentBill.getExpertEvalConditionList()==null){            currentBill.setBillStatus(EmExpertSelectionBill.BILL_STATUS_SELECT_FINISH);                        return update(currentBill);          }else{            for(int i=0;i<currentBill.getExpertEvalConditionList().size();i++){              EmEvaluationCondition condition=(EmEvaluationCondition) currentBill.getExpertEvalConditionList().get(i);              //获取指定条件的专家列表                            List expertLst=baseDao.query("EmExpert.selectByTypeCode", condition.getEmExpertType().getEmTypeCode());                            if(expertLst==null || expertLst.size()==0){                currentBill.setBillStatus(EmExpertSelectionBill.BILL_STATUS_SELECT_NOT_ENAUGH);                                return update(currentBill);              }              //去掉屏蔽的专家              if(currentBill.getExcludeExpertList()!=null && currentBill.getExcludeExpertList().size()>0){                List excludeLst=new ArrayList();                for(int j=0;j<expertLst.size();j++){                  EmExpert expert=(EmExpert) expertLst.get(j);                  for(int k=0;k<currentBill.getExcludeExpertList().size();k++){                    EmExpertBillFilter filter=(EmExpertBillFilter) currentBill.getExcludeExpertList().get(k);                    if(expert.getEmExpertCode().equals(filter.getEmExpert().getEmExpertCode())){                      excludeLst.add(expert);                      break;                    }                  }                }                expertLst.removeAll(excludeLst);              }              int num=condition.getExpertNum().intValue();              if(num>expertLst.size()){                num=expertLst.size();                notEnaugh=true;              }              if(num==expertLst.size()){                                for(int j=0;j<num;j++){                  EmExpert expert=(EmExpert) expertLst.get(j);                  EmExpertEvaluation eval=new EmExpertEvaluation();                  eval.setEmExpert(expert);                  eval.setEmExpertType(condition.getEmExpertType());                  evalLst.add(eval);                                    EmCallExpertRecord callRecord=new EmCallExpertRecord();                  callRecord.setEmExpert(expert);                  callRecord.setCallState("9");                  callRecordLst.add(callRecord);                }              }else{              while(num>0){                //随机获取一个专家                Random random=new Random();                int max=expertLst.size();                int s=random.nextInt(max);                EmExpert expert=(EmExpert) expertLst.get(s);                if(!selectedExpertLst.contains(expert)){                   selectedExpertLst.add(expert);                  EmExpertEvaluation eval=new EmExpertEvaluation();                  eval.setEmExpert(expert);                  eval.setEmExpertType(condition.getEmExpertType());                  evalLst.add(eval);                                    EmCallExpertRecord callRecord=new EmCallExpertRecord();                  callRecord.setEmExpert(expert);                  callRecord.setCallState("9");                  callRecordLst.add(callRecord);                  --num;                }              }              //再获取5位专家作为电话没打通或拒绝的专家              int s1=5;              if(expertLst.size()<condition.getExpertNum().intValue()+5){                s1=expertLst.size()-condition.getExpertNum().intValue();                              }              if(s1>0){                expertLst.removeAll(selectedExpertLst);                while(s1>0){                  Random random=new Random();                  int max=expertLst.size();                  int s=random.nextInt(max);                  EmExpert expert=(EmExpert) expertLst.get(s);                  if(!calledExpertLst.contains(expert)){                    calledExpertLst.add(expert);                    EmCallExpertRecord callRecord=new EmCallExpertRecord();                    callRecord.setEmExpert(expert);                    callRecord.setCallState("8");                    callRecordLst.add(callRecord);                    --s1;                  }                }              }            }          }            //更新单据状态            if(notEnaugh){              currentBill.setBillStatus(EmExpertSelectionBill.BILL_STATUS_SELECT_NOT_ENAUGH);                          }else{              currentBill.setBillStatus(EmExpertSelectionBill.BILL_STATUS_SELECT_FINISH);               }            //保存抽取结果            for (int i = 0; i < evalLst.size(); i++) {              EmExpertEvaluation ebf = (EmExpertEvaluation) evalLst.get(i);              ebf.setEmBillCode(currentBill.getBillCode());              ebf.setEmMakeCode(currentBill.getMakeCode());              ebf.setEmNoticeStatus("0");              ebf.setEmResponseStatus("9");              currentBill.getExpertEvaluationList().add(ebf);//              baseDao.insert("EmExpertEvaluation.insertEmExpertEvaluation", ebf);            }          //保存抽取记录            for (int i = 0; i < callRecordLst.size(); i++) {              EmCallExpertRecord ee = (EmCallExpertRecord) callRecordLst.get(i);              ee.setEmBillCode(currentBill.getBillCode());              ee.setCallNum(new Integer(1));              ee.setCallTime(requestMeta.getSysDate());              currentBill.getCallExpertRecordList().add(ee);              baseDao.insert("EmCallExpertRecord.insertCallRecord", ee);                     }            currentBill=update(currentBill);          }    } catch (Exception e) {      // TCJLODO Auto-generated catch block      e.printStackTrace();    }    return currentBill;  }    }