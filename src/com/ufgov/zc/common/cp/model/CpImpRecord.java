package com.ufgov.zc.common.cp.model;import java.io.Serializable;import java.util.Date;import com.ufgov.zc.common.commonbiz.model.BaseBill;public class CpImpRecord extends BaseBill implements Serializable {  private static final long serialVersionUID = -6039989099966932503L;  private String cpImpRecordId;  private String billDate;  private String balModeCode;  private String balModeName;  private String impPerson;  private Date impDate;  private String createPerson;  private String createType;  private String docType;  private String vouNo;  private String isCreate;  public String getBalModeCode() {    return balModeCode;  }  public void setBalModeCode(String balModeCode) {    this.balModeCode = balModeCode;  }  public String getBalModeName() {    return balModeName;  }  public void setBalModeName(String balModeName) {    this.balModeName = balModeName;  }  public String getImpPerson() {    return impPerson;  }  public void setImpPerson(String impPerson) {    this.impPerson = impPerson;  }  public Date getImpDate() {    return impDate;  }  public void setImpDate(Date impDate) {    this.impDate = impDate;  }  public String getCreatePerson() {    return createPerson;  }  public void setCreatePerson(String createPerson) {    this.createPerson = createPerson;  }  public String getDocType() {    return docType;  }  public void setDocType(String docType) {    this.docType = docType;  }  public String getVouNo() {    return vouNo;  }  public void setVouNo(String vouNo) {    this.vouNo = vouNo;  }  public String getBillDate() {    return billDate;  }  public void setBillDate(String billDate) {    this.billDate = billDate;  }  public String getCreateType() {    return createType;  }  public void setCreateType(String createType) {    this.createType = createType;  }  public String getCpImpRecordId() {    return cpImpRecordId;  }  public void setCpImpRecordId(String cpImpRecordId) {    this.cpImpRecordId = cpImpRecordId;  }  public String getIsCreate() {    return isCreate;  }  public void setIsCreate(String isCreate) {    this.isCreate = isCreate;  }}