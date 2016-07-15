/**   * @(#) project: Gk* @(#) file: ZcEbFormulaTemplateService.java* * Copyright 2010 UFGOV, Inc. All rights reserved.* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.* */package com.ufgov.zc.server.zc.service.impl;import java.util.List;import java.util.Map;import com.ufgov.zc.common.system.RequestMeta;import com.ufgov.zc.common.system.dto.ElementConditionDto;import com.ufgov.zc.common.system.util.UUID;import com.ufgov.zc.common.zc.model.ZcEbFormulaTemplate;import com.ufgov.zc.common.zc.model.ZcEbFormulaTemplateItem;import com.ufgov.zc.common.zc.model.ZcEbFormulaTemplateParam;import com.ufgov.zc.server.zc.dao.IZcEbFormulaTemplateDao;import com.ufgov.zc.server.zc.service.IZcEbFormulaTemplateService;/*** @ClassName: ZcEbFormulaTemplateService* @Description: TCJLODO(这里用一句话描述这个类的作用)* @date: 2010-7-4 下午04:25:08* @version: V1.0 * @since: 1.0* @author: Administrator* @modify: */public class ZcEbFormulaTemplateService implements IZcEbFormulaTemplateService {  private IZcEbFormulaTemplateDao zcEbFormulaTemplateDao;  public IZcEbFormulaTemplateDao getZcEbFormulaTemplateDao() {    return zcEbFormulaTemplateDao;  }  public void setZcEbFormulaTemplateDao(IZcEbFormulaTemplateDao zcEbFormulaTemplateDao) {    this.zcEbFormulaTemplateDao = zcEbFormulaTemplateDao;  }  public int deleteForZcEbFormulaTemplate(String templateCode) {    zcEbFormulaTemplateDao.deleteForZcEbFormulaTemplateItemByTemplateCode(templateCode);    zcEbFormulaTemplateDao.deleteForZcEbFormulaTemplateParam(templateCode);    return zcEbFormulaTemplateDao.deleteForZcEbFormulaTemplate(templateCode);  }  public int deleteForZcEbFormulaTemplateItem(String itemCode) {    return zcEbFormulaTemplateDao.deleteForZcEbFormulaTemplateItem(itemCode);  }  public int deleteForZcEbFormulaTemplateItemByTemplateCode(String templateCode) {    return zcEbFormulaTemplateDao.deleteForZcEbFormulaTemplateItemByTemplateCode(templateCode);  }  public List getZcEbFormulaTemplateList(ElementConditionDto dto) {    return zcEbFormulaTemplateDao.getZcEbFormulaTemplateList(dto);  }  public ZcEbFormulaTemplateItem getZcEbFormulaTemplateItem(String itemCode) {    return zcEbFormulaTemplateDao.getZcEbFormulaTemplateItem(itemCode);  }  public ZcEbFormulaTemplate getZcEbFormulaTemplate(String templateCode) {    return zcEbFormulaTemplateDao.getZcEbFormulaTemplate(templateCode);  }  public List getZcEbFormulaTemplateItemList(Map map, ElementConditionDto dto) {    return zcEbFormulaTemplateDao.getZcEbFormulaTemplateItemList(map, dto);  }  public List getZcEbFormulaTemplateItemByPcode(String templateCode, String pcode, RequestMeta meta) {    return zcEbFormulaTemplateDao.getZcEbFormulaTemplateItemByPcode(templateCode, pcode);  }  public List getTemplateParamList(String templateCode) {    return zcEbFormulaTemplateDao.getTemplateParamList(templateCode);  }  public ZcEbFormulaTemplate getZcEbFormulaTemplate(String templateCode, ElementConditionDto dto) {    return zcEbFormulaTemplateDao.getZcEbFormulaTemplate(templateCode);  }  public ZcEbFormulaTemplate insertZcEbFormulaTemplate(ZcEbFormulaTemplate zcEbFormulaTemplate) {    return zcEbFormulaTemplateDao.insertZcEbFormulaTemplate(zcEbFormulaTemplate);  }  public ZcEbFormulaTemplateItem insertZcEbFormulaTemplateItem(ZcEbFormulaTemplateItem zcEbFormulaTemplateItem) {    if (zcEbFormulaTemplateItem.getItemCode() == null) {      String itemCode = UUID.randomUUID().toString();      zcEbFormulaTemplateItem.setItemCode(itemCode);    }    return zcEbFormulaTemplateDao.insertZcEbFormulaTemplateItem(zcEbFormulaTemplateItem);  }  public ZcEbFormulaTemplate updateZcEbFormulaTemplate(ZcEbFormulaTemplate zcEbFormulaTemplate) {    return zcEbFormulaTemplateDao.updateZcEbFormulaTemplate(zcEbFormulaTemplate);  }  public ZcEbFormulaTemplateItem updateZcEbFormulaTemplateItem(ZcEbFormulaTemplateItem zcEbFormulaTemplateItem) {    return zcEbFormulaTemplateDao.updateZcEbFormulaTemplateItem(zcEbFormulaTemplateItem);  }  public int isExistsByItemName(String templateCode, String itemName, String itemCode) {    return zcEbFormulaTemplateDao.isExistsByItemName(templateCode, itemName, itemCode);  }  public void saveZcEbFormulaTemplateParam(List params, String templateCode) {    //通用参数的保存是先删除原来的数据重新保存    deleteForZcEbFormulaTemplateParam(templateCode);    for (int i = 0; i < params.size(); i++) {      ZcEbFormulaTemplateParam bean = (ZcEbFormulaTemplateParam) params.get(i);      bean.setTemplateCode(templateCode);      zcEbFormulaTemplateDao.insertZcEbFormulaTemplateParam(bean);    }  }  private void deleteForZcEbFormulaTemplateParam(String templateCode) {    zcEbFormulaTemplateDao.deleteForZcEbFormulaTemplateParam(templateCode);  }}