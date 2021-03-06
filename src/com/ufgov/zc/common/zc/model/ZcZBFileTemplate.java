package com.ufgov.zc.common.zc.model;import java.io.Serializable;import java.text.SimpleDateFormat;import java.util.Date;import java.util.Map;import com.ufgov.zc.common.system.constants.ZcSettingConstants;public class ZcZBFileTemplate implements Cloneable, Serializable {  // 表的自增长id  private String templateID;  // 文件名称  private Object ztbFile;  // 模板目录路径  private String tplFullPath;  // 创建模板时间  private Date latestUsed;  // 模板编号  private String tplNo;  // 模板所属招标方式  private String tplBelongBidWay;  // 模板应用类型  private String tplAppType;  // 模板描述  private String description;  // 文件编号  private String fileId;  // 模板创建人  private String templateCreator;  // 来源的项目名称  private String projName;  // 来源的项目编号  private String projNo;  public Object getZtbFile() {    return ztbFile;  }  public void setZtbFile(Object ztbFile) {    this.ztbFile = ztbFile;  }  public Date getLatestUsed() {    return latestUsed;  }  public void setLatestUsed(Date latestUsed) {    this.latestUsed = latestUsed;  }  public String getTplNo() {    return tplNo;  }  public void setTplNo(String tplNo) {    this.tplNo = tplNo;  }  public String getTplBelongBidWay() {    return tplBelongBidWay;  }  public void setTplBelongBidWay(String tplBelongBidWay) {    this.tplBelongBidWay = tplBelongBidWay;  }  public String getTplAppType() {    return tplAppType;  }  public void setTplAppType(String tplAppType) {    this.tplAppType = tplAppType;  }  public String getTplFullPath() {    return tplFullPath;  }  public void setTplFullPath(String tplFullPath) {    this.tplFullPath = tplFullPath;  }  public String getDescription() {    return description;  }  public void setDescription(String description) {    this.description = description;  }  public String getTemplateID() {    return templateID;  }  public void setTemplateID(String templateID) {    this.templateID = templateID;  }  public String getFileId() {    return fileId;  }  public void setFileId(String fileId) {    this.fileId = fileId;  }  public String getTemplateCreator() {    return templateCreator;  }  public void setTemplateCreator(String templateCreator) {    this.templateCreator = templateCreator;  }  public String getProjName() {    return projName;  }  public void setProjName(String projName) {    this.projName = projName;  }  public String getProjNo() {    return projNo;  }  public void setProjNo(String projNo) {    this.projNo = projNo;  }  /**   * 组装Template数据   *    * @param templateMap   * @return   * @throws Exception   */  public ZcZBFileTemplate setMapData(Map templateMap) throws Exception {    ZcZBFileTemplate template = new ZcZBFileTemplate();    template.setTemplateID((String) templateMap.get("TEMPLATEID"));    template.setTplNo((String) templateMap.get("TPLNO"));    template.setTplFullPath((String) templateMap.get("TPLFULLPATH"));    template.setDescription((String) templateMap.get("DESCRIPTION"));    String templateTime = (String) templateMap.get("LATESTUSED");    SimpleDateFormat format = new SimpleDateFormat(ZcSettingConstants.SIMPLE_DATE_FORMAT_DATE_ONLY);    try {      Date date = format.parse(templateTime);      template.setLatestUsed(date);    } catch (Exception e) {    }    template.setTplAppType((String) templateMap.get("TPLAPPTYPE"));    template.setTplBelongBidWay((String) templateMap.get("TPLBELONGBIDWAY"));    template.setZtbFile((String) templateMap.get("ZTBFILE"));    template.setFileId((String) templateMap.get("FILEID"));    template.setTemplateCreator((String) templateMap.get("TEMPLATECREATOR"));    template.setProjName((String) templateMap.get("PROJNAME"));    template.setProjNo((String) templateMap.get("PROJNO"));    return template;  }}