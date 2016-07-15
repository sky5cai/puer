package com.ufgov.zc.common.system.model;import java.io.Serializable;import java.math.BigDecimal;import java.util.Date;public class GkBusinessLog implements Serializable {  /**   *    */  private static final long serialVersionUID = -9094997559217869349L;  private String oid;  private String userId;  private String userName;  private String coCode;  private String coName;  private String orgCode;  private String orgName;  private String posiCode;  private String posiName;  private Date operTime;  private String remark;  private BigDecimal money;  private int nd;  private String billId;  private String tableName;  private String compoId;  private String funcId;  private String funcName;  private String dsignedData;  private String certDNCN;  public String getFuncName() {    return funcName;  }  public void setFuncName(String funcName) {    this.funcName = funcName;  }  public String getOid() {    return oid;  }  public void setOid(String oid) {    this.oid = oid;  }  public String getUserId() {    return userId;  }  public void setUserId(String userId) {    this.userId = userId;  }  public String getUserName() {    return userName;  }  public void setUserName(String userName) {    this.userName = userName;  }  public String getCoCode() {    return coCode;  }  public void setCoCode(String coCode) {    this.coCode = coCode;  }  public String getCoName() {    return coName;  }  public void setCoName(String coName) {    this.coName = coName;  }  public String getOrgCode() {    return orgCode;  }  public void setOrgCode(String orgCode) {    this.orgCode = orgCode;  }  public String getOrgName() {    return orgName;  }  public void setOrgName(String orgName) {    this.orgName = orgName;  }  public String getPosiCode() {    return posiCode;  }  public void setPosiCode(String posiCode) {    this.posiCode = posiCode;  }  public String getPosiName() {    return posiName;  }  public void setPosiName(String posiName) {    this.posiName = posiName;  }  public String getRemark() {    return remark;  }  public void setRemark(String remark) {    this.remark = remark;  }  public BigDecimal getMoney() {    return money;  }  public void setMoney(BigDecimal money) {    this.money = money;  }  public int getNd() {    return nd;  }  public void setNd(int nd) {    this.nd = nd;  }  public String getBillId() {    return billId;  }  public void setBillId(String billId) {    this.billId = billId;  }  public String getTableName() {    return tableName;  }  public void setTableName(String tableName) {    this.tableName = tableName;  }  public String getCompoId() {    return compoId;  }  public void setCompoId(String compoId) {    this.compoId = compoId;  }  public String getFuncId() {    return funcId;  }  public void setFuncId(String funcId) {    this.funcId = funcId;  }  public Date getOperTime() {    return operTime;  }  public void setOperTime(Date operTime) {    this.operTime = operTime;  }  public String getDsignedData() {    return dsignedData;  }  public void setDsignedData(String dsignedData) {    this.dsignedData = dsignedData;  }  public String getCertDNCN() {    return certDNCN;  }  public void setCertDNCN(String certDNCN) {    this.certDNCN = certDNCN;  }}