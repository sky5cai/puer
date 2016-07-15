package com.ufgov.zc.server.zc.publish.impl;import java.util.List;import com.ufgov.zc.common.system.RequestMeta;import com.ufgov.zc.common.system.dto.ElementConditionDto;import com.ufgov.zc.common.zc.model.ZcEbComplain;import com.ufgov.zc.common.zc.publish.IZcEbComplainDelegate;import com.ufgov.zc.server.zc.service.IZcEbComplainService;public class ZcEbComplainDelegate implements IZcEbComplainDelegate {  private IZcEbComplainService complainService;  public IZcEbComplainService getComplainService() {    return complainService;  }  public void setComplainService(IZcEbComplainService complainService) {    this.complainService = complainService;  }  public ZcEbComplain auditPassFN(ZcEbComplain zcEbComplain,  RequestMeta requestMeta) {    return complainService.auditPassFN(zcEbComplain, requestMeta);  }  public ZcEbComplain callbackFN(ZcEbComplain zcEbComplain,  RequestMeta requestMeta) {    return complainService.callbackFN(zcEbComplain, requestMeta);  }  public List getZCQuestionList(ElementConditionDto elementConditionDto,  RequestMeta requestMeta) {    return null;  }  public List getZCQuestionPack(ZcEbComplain zcEbComplain) {    return null;  }  public ZcEbComplain saveZcEbComplain(ZcEbComplain zcEbComplain,  RequestMeta requestMeta) {    return complainService.saveZcEbComplain(zcEbComplain, requestMeta);  }  public ZcEbComplain sendFN(ZcEbComplain zcEbComplain,  RequestMeta requestMeta) {    return null;// complainService.s;  }  public ZcEbComplain submitFN(ZcEbComplain zcEbComplain,  RequestMeta requestMeta) {    return complainService.submitFN(zcEbComplain, requestMeta);  }  public ZcEbComplain suggestPassFN(ZcEbComplain zcEbComplain,  RequestMeta requestMeta) {    return complainService.suggestPassFN(zcEbComplain, requestMeta);  }  public ZcEbComplain unAuditFN(ZcEbComplain zcEbComplain,  RequestMeta requestMeta) {    return complainService.unAuditFN(zcEbComplain, requestMeta);  }  public ZcEbComplain unTreadFN(ZcEbComplain zcEbComplain,  RequestMeta requestMeta) {    return complainService.unTreadFN(zcEbComplain, requestMeta);  }  public void deleteZcEbComplain(ZcEbComplain zcEbComplain,  RequestMeta requestMeta) {    complainService.deleteZcEbComplain(zcEbComplain);  }  public List getZcEbComplainList(ElementConditionDto elementConditionDto,  RequestMeta requestMeta) {    return complainService.getZcEbComplainList(elementConditionDto);  }}