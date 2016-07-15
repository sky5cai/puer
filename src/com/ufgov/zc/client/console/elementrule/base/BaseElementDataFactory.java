package com.ufgov.zc.client.console.elementrule.base;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;import com.ufgov.zc.client.common.ServiceFactory;import com.ufgov.zc.client.common.WorkEnv;import com.ufgov.zc.client.datacache.BAccDataCache;import com.ufgov.zc.client.datacache.CompanyDataCache;import com.ufgov.zc.client.datacache.DAttrDataCache;import com.ufgov.zc.client.datacache.FundDataCache;import com.ufgov.zc.client.datacache.InceptDocDataCache;import com.ufgov.zc.client.datacache.ManageDataCache;import com.ufgov.zc.client.datacache.OrgDataCache;import com.ufgov.zc.client.datacache.OriginDataCache;import com.ufgov.zc.client.datacache.OutlayDataCache;import com.ufgov.zc.client.datacache.PayoutDataCache;import com.ufgov.zc.client.datacache.PaytypeDataCache;import com.ufgov.zc.client.datacache.ProjectDataCache;import com.ufgov.zc.client.datacache.ProjectTypeDataCache;import com.ufgov.zc.client.datacache.SendDocDataCache;import com.ufgov.zc.client.datacache.SendDocTypeDataCache;import com.ufgov.zc.common.commonbiz.model.BaseElement;import com.ufgov.zc.common.commonbiz.model.DAttr;import com.ufgov.zc.common.commonbiz.publish.IBaseDataServiceDelegate;import com.ufgov.zc.common.system.RequestMeta;import com.ufgov.zc.common.system.dto.ElementConditionDto;public class BaseElementDataFactory {  private static Map ELEMENT_TREEDATA = new HashMap();  public static List getBaseElementTreeData(String elementCode) {    List result = (List) ELEMENT_TREEDATA.get(elementCode);    if (result != null) {      return result;    } else {      IBaseDataServiceDelegate baseDataServiceDelegate = (IBaseDataServiceDelegate) ServiceFactory.create(      IBaseDataServiceDelegate.class, "baseDataServiceDelegate");      ElementConditionDto dto = new ElementConditionDto();      dto.setNd(WorkEnv.getInstance().getTransNd());      RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();      if ("CO_CODE".equals(elementCode)) {        result = CompanyDataCache.getCompany();      } else if ("PAYOUT_CODE".equals(elementCode)) {        result = PayoutDataCache.getPayout();      } else if ("FUND_CODE".equals(elementCode)) {        result = FundDataCache.getFund();      } else if ("B_ACC_CODE".equals(elementCode)) {        result = BAccDataCache.getBAcc();      } else if ("INCEPTDOC_CODE".equals(elementCode)) {        result = InceptDocDataCache.getInceptDoc();      } else if ("MANAGE_CODE".equals(elementCode)) {        result = ManageDataCache.getManage();      } else if ("ORG_CODE".equals(elementCode)) {        result = OrgDataCache.getOrg();      } else if ("ORIGIN_CODE".equals(elementCode)) {        result = OriginDataCache.getOrigin();      } else if ("OUTLAY_CODE".equals(elementCode)) {        result = OutlayDataCache.getOutlay();      } else if ("PROJECT_CODE".equals(elementCode)) {        result = ProjectDataCache.getProject();      } else if ("PROJECT_TYPE_CODE".equals(elementCode)) {        result = ProjectTypeDataCache.getProjectType();      } else if ("SENDDOC_TYPE_CODE".equals(elementCode)) {        result = SendDocTypeDataCache.getSendDocType();      } else if ("SENDDOC_CODE".equals(elementCode)) {        result = SendDocDataCache.getSendDoc();      } else if ("PAYTYPE_CODE".equals(elementCode)) {        result = PaytypeDataCache.getPaytype();      } else if ("D_ATTR1".equals(elementCode)) {        result = DAttrDataCache.getDAttr(DAttr.D_ATTR1_TYPE);      } else if ("D_ATTR2".equals(elementCode)) {        result = DAttrDataCache.getDAttr(DAttr.D_ATTR2_TYPE);      } else if ("D_ATTR3".equals(elementCode)) {        result = DAttrDataCache.getDAttr(DAttr.D_ATTR3_TYPE);      } else if ("D_ATTR4".equals(elementCode)) {        result = DAttrDataCache.getDAttr(DAttr.D_ATTR4_TYPE);      } else if ("D_ATTR5".equals(elementCode)) {        result = DAttrDataCache.getDAttr(DAttr.D_ATTR5_TYPE);      }      result = genTreeData(result);      ELEMENT_TREEDATA.put(elementCode, result);    }    return result;  }  private static List genTreeData(List elementList) {    List rootElementList = new ArrayList();    List childrenElementList = new ArrayList();    Map dataMap = new HashMap();    for (Object o : elementList) {      BaseElement ele = (BaseElement) o;      dataMap.put(ele.getCode(), ele);    }    for (Object o : elementList) {      BaseElement ele = (BaseElement) o;      if (dataMap.get(ele.getParentCode()) == null) {        rootElementList.add(ele);      } else {        childrenElementList.add(ele);      }    }    Map childrenMap = new HashMap();    for (int i = 0; i < childrenElementList.size(); i++) {      BaseElement child = (BaseElement) childrenElementList.get(i);      List childrenList = (List) childrenMap.get(child.getParentCode());      if (childrenList != null) {        childrenList.add(child);      } else {        List tempList = new ArrayList();        tempList.add(child);        childrenMap.put(child.getParentCode(), tempList);      }    }    for (int i = 0; i < rootElementList.size(); i++) {      BaseElement ele = (BaseElement) rootElementList.get(i);      BaseElementDataFactory.setBaseElementChildren(ele, childrenMap);    }    return rootElementList;  }  private static void setBaseElementChildren(BaseElement ele, Map childrenMap) {    List childrenList = (List) childrenMap.get(ele.getCode());    if (childrenList != null) {      ele.setChildren(childrenList);      for (int i = 0; i < childrenList.size(); i++) {        BaseElement c = (BaseElement) childrenList.get(i);        setBaseElementChildren(c, childrenMap);      }    }  }}