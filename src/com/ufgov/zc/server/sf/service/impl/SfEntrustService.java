package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.compiler.ITerminalSymbols;

import com.ufgov.zc.common.sf.model.SfChargeDetail;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.model.SfXysx;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.zc.model.SysEmp;
import com.ufgov.zc.server.sf.dao.SfChargeDetailMapper;
import com.ufgov.zc.server.sf.dao.SfEntrustMapper;
import com.ufgov.zc.server.sf.dao.SfMaterialsMapper;
import com.ufgov.zc.server.sf.dao.SfXysxMapper;
import com.ufgov.zc.server.sf.dao.SfXysxTypeMapper;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.service.IUserService;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class SfEntrustService implements ISfEntrustService {

  private SfEntrustMapper entrustMapper;

  private SfMaterialsMapper materialsMapper;

  private IWorkflowDao workflowDao;

  private WFEngineAdapter wfEngineAdapter;

  private SfXysxTypeMapper xysxTypeMapper;

  private SfXysxMapper xysxMapper;

  private SfChargeDetailMapper chargeDetailMapper;
  
  private IZcEbBaseService zcEbBaseService;
  
  private IUserService userService;

  public SfXysxTypeMapper getXysxTypeMapper() {
    return xysxTypeMapper;
  }

  public void setXysxTypeMapper(SfXysxTypeMapper xysxTypeMapper) {
    this.xysxTypeMapper = xysxTypeMapper;
  }

  public SfXysxMapper getXysxMapper() {
    return xysxMapper;
  }

  public void setXysxMapper(SfXysxMapper xysxMapper) {
    this.xysxMapper = xysxMapper;
  }

  public List getEntrustLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustMapper.getEntrustLst(elementConditionDto);
  }

  public SfEntrust selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    SfEntrust rtn = entrustMapper.selectByPrimaryKey(id);
    if (rtn.getWtIdParent() != null) {
      SfEntrust old = entrustMapper.selectByPrimaryKey(rtn.getWtIdParent());
      if (old != null) {
        rtn.setOldEntrust(old);
        rtn.setWtCodeParent(old.getCode());
      }
    }
    List materialLst = materialsMapper.selectByEntrustId(id);
    rtn.setMaterials(materialLst == null ? new ArrayList() : materialLst);
    List chargeLst = chargeDetailMapper.selectByPrimaryKey(id);
    rtn.setJdChargeDetaillst(chargeLst == null ? new ArrayList() : chargeLst);
    //设定一个唯一主键,用于界面的table展现
    for (int i = 0; i < rtn.getJdChargeDetaillst().size(); i++) {
      SfChargeDetail charge = (SfChargeDetail) rtn.getJdChargeDetaillst().get(i);
      charge.setTempId("" + System.currentTimeMillis());
    }
    List xysxLst = xysxMapper.selectByPrimaryKey(id);
    xysxLst = xysxLst == null ? new ArrayList() : xysxLst;
    rtn.setXysxLst(xysxLst);
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  public SfEntrust saveFN(SfEntrust inData, RequestMeta requestMeta) {

    //    System.out.println("service saveFN 1++++++++++++++++++++++++++=" + inData.getAcceptDate());

    // TCJLODO Auto-generated method stub
    if ("".equals(ZcSUtil.safeString(inData.getCode())) || inData.getCode().equals("自动编号")) {

      String code = NumUtil.getInstance().getNo("SF_ENTRUST", "CODE", inData);
      inData.setCode(code);
      BigDecimal id = new BigDecimal(ZcSUtil.getNextVal(SfEntrust.SEQ_SF_ENTRUST_ID));
      inData.setEntrustId(id);

      boolean isDraft = false;
      String userId = requestMeta.getSvUserID();
      String compoId = requestMeta.getCompoId();

      //      System.out.println("service saveFN 2++++++++++++++++++++++++++=" + inData.getAcceptDate());

      if (inData.getProcessInstId() == null || inData.getProcessInstId().longValue() == -1) {
        Long draftid = workflowDao.createDraftId();
        inData.setProcessInstId(draftid);
        isDraft = true;
      }
      insert(inData, requestMeta);

      //      System.out.println("service saveFN 3++++++++++++++++++++++++++=" + inData.getAcceptDate());

      if (isDraft) {
        AsWfDraft asWfDraft = new AsWfDraft();
        asWfDraft.setCompoId(compoId);
        asWfDraft.setWfDraftName(inData.getCode());
        asWfDraft.setUserId(userId);
        asWfDraft.setMasterTabId(compoId);
        asWfDraft.setWfDraftId(BigDecimal.valueOf(inData.getProcessInstId().longValue()));
        workflowDao.insertAsWfdraft(asWfDraft);
      }
    } else {
      update(inData, requestMeta);
    }
    return inData;
  }

  private void update(SfEntrust inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

    //    System.out.println("update ++++++++++++++++++++++++++=" + inData.getAcceptDate());

    entrustMapper.updateByPrimaryKey(inData);
    materialsMapper.deleteByEntrustId(inData.getEntrustId());
    chargeDetailMapper.deleteByPrimaryKey(inData.getEntrustId());
    xysxMapper.deleteByPrimaryKey(inData.getEntrustId());

    if (inData.getMaterials() != null) {
      for (int i = 0; i < inData.getMaterials().size(); i++) {
        SfMaterials m = (SfMaterials) inData.getMaterials().get(i);
        setMaterialsId(m);
        m.setEntrustId(inData.getEntrustId());
        materialsMapper.insert(m);
      }
    }
    if (inData.getJdChargeDetaillst() != null) {
      for (int i = 0; i < inData.getJdChargeDetaillst().size(); i++) {
        SfChargeDetail d = (SfChargeDetail) inData.getJdChargeDetaillst().get(i);
        d.setEntrustId(inData.getEntrustId());
        chargeDetailMapper.insert(d);
      }
    }
    if (inData.getXysxLst() != null) {
      //      System.out.println("========================================");
      for (int i = 0; i < inData.getXysxLst().size(); i++) {
        SfXysx d = (SfXysx) inData.getXysxLst().get(i);
        d.setEntrustId(inData.getEntrustId());
        //        System.out.println(d == null ? null : d.getXysxTypeId());
        xysxMapper.insert(d);
      }
    }
  }

  private void setMaterialsId(SfMaterials m) {
    // TCJLODO Auto-generated method stub
    if (m.getMaterialId() != null)
      return;
    BigDecimal id = new BigDecimal(ZcSUtil.getNextVal(SfMaterials.SEQ_SF_MATERIALS_ID));
    m.setMaterialId(id);
  }

  private void insert(SfEntrust inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    entrustMapper.insert(inData);
    if (inData.getMaterials() != null) {
      for (int i = 0; i < inData.getMaterials().size(); i++) {
        SfMaterials m = (SfMaterials) inData.getMaterials().get(i);
        setMaterialsId(m);
        m.setEntrustId(inData.getEntrustId());
        materialsMapper.insert(m);
      }
    }
    if (inData.getJdChargeDetaillst() != null) {
      for (int i = 0; i < inData.getJdChargeDetaillst().size(); i++) {
        SfChargeDetail d = (SfChargeDetail) inData.getJdChargeDetaillst().get(i);
        d.setEntrustId(inData.getEntrustId());
        chargeDetailMapper.insert(d);
      }
    }
    if (inData.getXysxLst() != null) {
      for (int i = 0; i < inData.getXysxLst().size(); i++) {
        SfXysx d = (SfXysx) inData.getXysxLst().get(i);
        d.setEntrustId(inData.getEntrustId());
        xysxMapper.insert(d);
      }
    }
  }

  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    entrustMapper.deleteByPrimaryKey(id);
    materialsMapper.deleteByEntrustId(id);
  }

  public SfEntrustMapper getEntrustMapper() {
    return entrustMapper;
  }

  public void setEntrustMapper(SfEntrustMapper entrustMapper) {
    this.entrustMapper = entrustMapper;
  }

  public SfMaterialsMapper getMaterialsMapper() {
    return materialsMapper;
  }

  public void setMaterialsMapper(SfMaterialsMapper materialsMapper) {
    this.materialsMapper = materialsMapper;
  }

  public IWorkflowDao getWorkflowDao() {
    return workflowDao;
  }

  public void setWorkflowDao(IWorkflowDao workflowDao) {
    this.workflowDao = workflowDao;
  }

  public WFEngineAdapter getWfEngineAdapter() {
    return wfEngineAdapter;
  }

  public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {
    this.wfEngineAdapter = wfEngineAdapter;
  }

  public SfEntrust unAuditFN(SfEntrust qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  public SfEntrust untreadFN(SfEntrust qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    sendMsgUntread(qx,requestMeta);
    return qx;
  }

  private void sendMsgUntread(SfEntrust qx, RequestMeta requestMeta) {
	  ElementConditionDto dto=new ElementConditionDto();
	  dto.setDattr1("SF_ENTRUST");
	  dto.setDattr2(""+qx.getProcessInstId());
	  List userLst=zcEbBaseService.queryDataForList("ZcEbUtil.selectToDoUser", dto);
	  if(userLst!=null ){
		  String mobile="";
		  String msg=qx.getCode()+"鉴定委托等待您审批,案事件:"+qx.getName()+",请登录鉴定管理系统进行审批。";
		  ZcSUtil su=new ZcSUtil();
		  for(int i=0;i<userLst.size();i++){
			  HashMap row=(HashMap) userLst.get(i);
			  String user=(String) row.get("EXECUTOR");
			  HashMap mobiles=su.getUserMobile(user, qx.getProcessInstId(), requestMeta);
			  Iterator keys=mobiles.keySet().iterator();
			  while(keys.hasNext()){
				  String key=keys.next().toString(); 
				  su.sendToBox(""+qx.getEntrustId().intValue(), "", msg, key, requestMeta.getSysDate(), requestMeta.getSysDate());
			  } 
		  }
	  }	  
}
  private void sendMsgAudit(SfEntrust qx, RequestMeta requestMeta) {
	  ElementConditionDto dto=new ElementConditionDto();
	  dto.setDattr1("SF_ENTRUST");
	  dto.setDattr2(""+qx.getProcessInstId());
	  List userLst=zcEbBaseService.queryDataForList("ZcEbUtil.selectUntreadUser", dto);
	  if(userLst!=null ){
		  String mobile="";
		  String msg=qx.getCode()+"鉴定委托被退回了,请登录鉴定管理系统进行查看处理。";
		  ZcSUtil su=new ZcSUtil();
		  for(int i=0;i<userLst.size();i++){
			  HashMap row=(HashMap) userLst.get(i);
			  String user=(String) row.get("EXECUTOR");
			  HashMap mobiles=su.getUserMobile(user, qx.getProcessInstId(), requestMeta);
			  Iterator keys=mobiles.keySet().iterator();
			  while(keys.hasNext()){
				  String key=keys.next().toString(); 
				  su.sendToBox(""+qx.getEntrustId().intValue(), "", msg, key, requestMeta.getSysDate(), requestMeta.getSysDate());
			  } 
		  }
	  }
	  
} 
 

public SfEntrust auditFN(SfEntrust qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    //    System.out.println("auditFN 1++++++++++++++++++++++++++=" + qx.getAcceptDate());
    qx = saveFN(qx, requestMeta);
    //    System.out.println("auditFN 2++++++++++++++++++++++++++=" + qx.getAcceptDate());
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    sendMsgAudit(qx,requestMeta);
     SfEntrust rtn=selectByPrimaryKey(qx.getEntrustId(), requestMeta);
     return rtn;
  }


public SfEntrust newCommitFN(SfEntrust qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

    //    System.out.println("newCommitFN 1++++++++++++++++++++++++++=" + qx.getAcceptDate());
    qx = saveFN(qx, requestMeta);
    //    System.out.println("newCommitFN 2++++++++++++++++++++++++++=" + qx.getAcceptDate());
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
    sendMsgAudit(qx,requestMeta);
    return selectByPrimaryKey(qx.getEntrustId(), requestMeta);
  }

  public SfEntrust callbackFN(SfEntrust qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    
    return qx;
  }

  public SfChargeDetailMapper getChargeDetailMapper() {
    return chargeDetailMapper;
  }

  public void setChargeDetailMapper(SfChargeDetailMapper chargeDetailMapper) {
    this.chargeDetailMapper = chargeDetailMapper;
  }

 /**
  * 受理,同时进行审核操作
 * @throws Exception 
  */
public SfEntrust acceptFN(SfEntrust inData, RequestMeta requestMeta) throws Exception {
	inData.setIsAccept("Y");
	auditFN(inData,requestMeta);
	
	return selectByPrimaryKey(inData.getEntrustId(), requestMeta);
}

 /**
  * 不受理，提交审批
 * @throws Exception 
  */
public SfEntrust unAcceptFN(SfEntrust inData, RequestMeta requestMeta) throws Exception {
	inData.setIsAccept("N");
	auditFN(inData,requestMeta);
	
	return selectByPrimaryKey(inData.getEntrustId(), requestMeta);
}

public IZcEbBaseService getZcEbBaseService() {
	return zcEbBaseService;
}

public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
	this.zcEbBaseService = zcEbBaseService;
}

public IUserService getUserService() {
	return userService;
}

public void setUserService(IUserService userService) {
	this.userService = userService;
}
}
