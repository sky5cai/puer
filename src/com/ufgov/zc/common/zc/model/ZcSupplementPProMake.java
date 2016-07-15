/**   * @(#) project: zcxa* @(#) file: ZcSupplementPProMake.java* * Copyright 2010 UFGOV, Inc. All rights reserved.* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.* */package com.ufgov.zc.common.zc.model;import java.io.Serializable;import java.math.BigDecimal;import java.util.Date;import java.util.List;/*** @ClassName: ZcSupplementPProMake* @Description: TCJLODO(这里用一句话描述这个类的作用)* @date: 2010-7-29 下午02:03:08* @version: V1.0 * @since: 1.0* @author: Administrator* @modify: */public class ZcSupplementPProMake extends ZcBaseBill implements Serializable {  private static final long serialVersionUID = 1L;  private String zcMakeCode;  private String zcSuppleMentStatus;  private String zcSuppleMentCode;  private ZcPProMake zcPProMake;  private String zcMakeName;  private String zcCoCode;  private String zcCoCodeNd;  private String zcZgCsCode;  private String zcIsRemark;  private String zcMakeLinkman;  private String zcMakeTel;  private String zcAgeyCode;  private String zcAgeyName;  private String zcMakeSequence;  private String zcFukuanType;  private String zcPitemOpiway;  private String zcPifuCgfs;  private String zcFgkzbfsSmwj;  private String zcIsImp;  private String zcIsBudget;  private BigDecimal zcMoneyBiSum;  private List suppleMentBiList;  private String zcInputCode;  private String zcRemark;  public String getZcInputCode() {    return zcInputCode;  }  public void setZcInputCode(String zcInputCode) {    this.zcInputCode = zcInputCode;  }  public String getZcRemark() {    return zcRemark;  }  public void setZcRemark(String zcRemark) {    this.zcRemark = zcRemark;  }  public BigDecimal getAddMoney() {    return addMoney;  }  public void setAddMoney(BigDecimal addMoney) {    this.addMoney = addMoney;  }  private BigDecimal addMoney;  public String getZcInputName() {    return zcInputName;  }  public void setZcInputName(String zcInputName) {    this.zcInputName = zcInputName;  }  private String zcInputName;  public Date getZcInputDate() {    return zcInputDate;  }  public void setZcInputDate(Date zcInputDate) {    this.zcInputDate = zcInputDate;  }  private Date zcInputDate;  public List getSuppleMentBiList() {    return suppleMentBiList;  }  public void setSuppleMentBiList(List suppleMentBiList) {    this.suppleMentBiList = suppleMentBiList;  }  public String getZcMakeName() {    return zcMakeName;  }  public void setZcMakeName(String zcMakeName) {    this.zcMakeName = zcMakeName;  }  public String getZcCoCode() {    return zcCoCode;  }  public void setZcCoCode(String zcCoCode) {    this.zcCoCode = zcCoCode;  }  public String getZcCoCodeNd() {    return zcCoCodeNd;  }  public void setZcCoCodeNd(String zcCoCodeNd) {    this.zcCoCodeNd = zcCoCodeNd;  }  public String getZcZgCsCode() {    return zcZgCsCode;  }  public void setZcZgCsCode(String zcZgCsCode) {    this.zcZgCsCode = zcZgCsCode;  }  public String getZcIsRemark() {    return zcIsRemark;  }  public void setZcIsRemark(String zcIsRemark) {    this.zcIsRemark = zcIsRemark;  }  public String getZcMakeLinkman() {    return zcMakeLinkman;  }  public void setZcMakeLinkman(String zcMakeLinkman) {    this.zcMakeLinkman = zcMakeLinkman;  }  public String getZcMakeTel() {    return zcMakeTel;  }  public void setZcMakeTel(String zcMakeTel) {    this.zcMakeTel = zcMakeTel;  }  public String getZcAgeyCode() {    return zcAgeyCode;  }  public void setZcAgeyCode(String zcAgeyCode) {    this.zcAgeyCode = zcAgeyCode;  }  public String getZcAgeyName() {    return zcAgeyName;  }  public void setZcAgeyName(String zcAgeyName) {    this.zcAgeyName = zcAgeyName;  }  public String getZcMakeSequence() {    return zcMakeSequence;  }  public void setZcMakeSequence(String zcMakeSequence) {    this.zcMakeSequence = zcMakeSequence;  }  public String getZcFukuanType() {    return zcFukuanType;  }  public void setZcFukuanType(String zcFukuanType) {    this.zcFukuanType = zcFukuanType;  }  public String getZcPitemOpiway() {    return zcPitemOpiway;  }  public void setZcPitemOpiway(String zcPitemOpiway) {    this.zcPitemOpiway = zcPitemOpiway;  }  public String getZcPifuCgfs() {    return zcPifuCgfs;  }  public void setZcPifuCgfs(String zcPifuCgfs) {    this.zcPifuCgfs = zcPifuCgfs;  }  public String getZcFgkzbfsSmwj() {    return zcFgkzbfsSmwj;  }  public void setZcFgkzbfsSmwj(String zcFgkzbfsSmwj) {    this.zcFgkzbfsSmwj = zcFgkzbfsSmwj;  }  public String getZcIsImp() {    return zcIsImp;  }  public void setZcIsImp(String zcIsImp) {    this.zcIsImp = zcIsImp;  }  public String getZcIsBudget() {    return zcIsBudget;  }  public void setZcIsBudget(String zcIsBudget) {    this.zcIsBudget = zcIsBudget;  }  public BigDecimal getZcMoneyBiSum() {    return zcMoneyBiSum;  }  public void setZcMoneyBiSum(BigDecimal zcMoneyBiSum) {    this.zcMoneyBiSum = zcMoneyBiSum;  }  public ZcPProMake getZcPProMake() {    return zcPProMake;  }  public void setZcPProMake(ZcPProMake zcPProMake) {    this.zcPProMake = zcPProMake;  }  public String getZcMakeCode() {    return zcMakeCode;  }  public void setZcMakeCode(String zcMakeCode) {    this.zcMakeCode = zcMakeCode;  }  public String getZcSuppleMentStatus() {    return zcSuppleMentStatus;  }  public void setZcSuppleMentStatus(String zcSuppleMentStatus) {    this.zcSuppleMentStatus = zcSuppleMentStatus;  }  public String getZcSuppleMentCode() {    return zcSuppleMentCode;  }  public void setZcSuppleMentCode(String zcSuppleMentCode) {    this.zcSuppleMentCode = zcSuppleMentCode;  }}