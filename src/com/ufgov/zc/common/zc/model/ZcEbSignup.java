package com.ufgov.zc.common.zc.model;import java.io.Serializable;import java.math.BigDecimal;import java.util.ArrayList;import java.util.Date;import java.util.List;/** *  * @ClassName: ZcEbSignup *  * @Description: TCJLODO(这里用一句话描述这个类的作用) *  * @date: Apr 21, 2010 4:31:09 PM *  * @version: V1.0 *  * @since: 1.0 *  * @author: xiaofei *  * @modify: */public class ZcEbSignup extends ZcBaseBill implements Serializable {  /**   *    */  private static final long serialVersionUID = -1893493724571212830L;  private String signupId;  private String providerCode;  private String providerName;  private Date signupDate;  private String linkMan;  private String address;  private String zipcode;  private String phone;  private String mobilePhone;  private String email;  private String projCode;  private Date purDocBuyDate;  private String purDocFeeType;  private String purDocFeeBill;  private String status;  private BigDecimal purDocFee;  private String projName;  private String signupManner;  private String operator;  private String remark;  private String isPayGuarantee;  private String isSite;//是否到现场  private String isSubmitBidDoc;  private String bidDocFile;// 投标文件名称  private String bidDocFileId;// 投标文件的文件编号  private String submitBidDocType;  private List signupPacks = new ArrayList();  private String zbFileName;// 招标文件的名称，对应zc_eb_proj_zbfile中的file_name  private String zbFileId;// 招标文件的编号 对应zc_eb_proj_zbfile中的file_id    private String zbFileWordId;  private String purType;//项目的采购方式//用于报名时展现项目的计划信息，  private ZcEbPlan plan;      public String getSignupId() {    return signupId;  }  public void setSignupId(String signupId) {    this.signupId = signupId;  }  public String getProviderCode() {    return providerCode;  }  public void setProviderCode(String providerCode) {    this.providerCode = providerCode;  }  public Date getSignupDate() {    return signupDate;  }  public void setSignupDate(Date signupDate) {    this.signupDate = signupDate;  }  public String getLinkMan() {    return linkMan;  }  public void setLinkMan(String linkman) {    this.linkMan = linkman;  }  public String getAddress() {    return address;  }  public void setAddress(String address) {    this.address = address;  }  public String getZipcode() {    return zipcode;  }  public void setZipcode(String zipcode) {    this.zipcode = zipcode;  }  public String getPhone() {    return phone;  }  public void setPhone(String phone) {    this.phone = phone;  }  public String getMobilePhone() {    return mobilePhone;  }  public void setMobilePhone(String mobilePhone) {    this.mobilePhone = mobilePhone;  }  public String getEmail() {    return email;  }  public void setEmail(String email) {    this.email = email;  }  public String getProjCode() {    return projCode;  }  public void setProjCode(String projCode) {    this.projCode = projCode;  }  public Date getPurDocBuyDate() {    return purDocBuyDate;  }  public void setPurDocBuyDate(Date purDocBuyDate) {    this.purDocBuyDate = purDocBuyDate;  }  public String getPurDocFeeType() {    return purDocFeeType;  }  public void setPurDocFeeType(String purDocFeeType) {    this.purDocFeeType = purDocFeeType;  }  public String getPurDocFeeBill() {    return purDocFeeBill;  }  public void setPurDocFeeBill(String purDocFeeBill) {    this.purDocFeeBill = purDocFeeBill;  }  public String getStatus() {    return status;  }  public void setStatus(String status) {    this.status = status;  }  public BigDecimal getPurDocFee() {    return purDocFee;  }  public void setPurDocFee(BigDecimal purDocFee) {    this.purDocFee = purDocFee;  }  public static long getSerialVersionUID() {    return serialVersionUID;  }  public String getProjName() {    return projName;  }  public void setProjName(String projName) {    this.projName = projName;  }  public String getProviderName() {    return providerName;  }  public void setProviderName(String providerName) {    this.providerName = providerName;  }  public String getSignupManner() {    return signupManner;  }  public void setSignupManner(String signupManner) {    this.signupManner = signupManner;  }  public void setOperator(String operator) {    this.operator = operator;  }  public String getOperator() {    return operator;  }  public void setSignupPacks(List signupPacks) {    this.signupPacks = signupPacks;  }  public List getSignupPacks() {    return signupPacks;  }  public void setRemark(String remark) {    this.remark = remark;  }  public String getRemark() {    return remark;  }  public void setIsPayGuarantee(String isPayGuarantee) {    this.isPayGuarantee = isPayGuarantee;  }  public String getIsPayGuarantee() {    return isPayGuarantee;  }  public void setIsSubmitBidDoc(String isSubmitBidDoc) {    this.isSubmitBidDoc = isSubmitBidDoc;  }  public String getIsSubmitBidDoc() {    return isSubmitBidDoc;  }  /**   *    * @return the bidDocFile   */  public String getBidDocFile() {    return bidDocFile;  }  /**   *    * @param bidDocFile   *            the bidDocFile to set   */  public void setBidDocFile(String bidDocFile) {    this.bidDocFile = bidDocFile;  }  /**   *    * @return the bidDocFileId   */  public String getBidDocFileId() {    return bidDocFileId;  }  /**   *    * @param bidDocFileId   *            the bidDocFileId to set   */  public void setBidDocFileId(String bidDocFileId) {    this.bidDocFileId = bidDocFileId;  }  /**   *    * @return the serialversionuid   */  public static long getSerialversionuid() {    return serialVersionUID;  }  public void setSubmitBidDocType(String submitBidDocType) {    this.submitBidDocType = submitBidDocType;  }  public String getSubmitBidDocType() {    return submitBidDocType;  }  /**   * @return the zbFileName   */  public String getZbFileName() {    return zbFileName;  }  /**   * @param zbFileName   *            the zbFileName to set   */  public void setZbFileName(String zbFileName) {    this.zbFileName = zbFileName;  }  /**   * @return the zbFileId   */  public String getZbFileId() {    return zbFileId;  }  /**   * @param zbFileId   *            the zbFileId to set   */  public void setZbFileId(String zbFileId) {    this.zbFileId = zbFileId;  }  public String toString() {    return this.getProjName() + "[" + this.getProjCode() + "]";  }  /**   * @return the isSite   */  public String getIsSite() {    return isSite;  }  /**   * @param isSite the isSite to set   */  public void setIsSite(String isSite) {    this.isSite = isSite;  }  /**   * @return the purType   */  public String getPurType() {    return purType;  }  /**   * @param purType the purType to set   */  public void setPurType(String purType) {    this.purType = purType;  }public ZcEbPlan getPlan() {	return plan;}public void setPlan(ZcEbPlan plan) {	this.plan = plan;}public String getZbFileWordId() {  return zbFileWordId;}public void setZbFileWordId(String zbFileWordId) {  this.zbFileWordId = zbFileWordId;}}