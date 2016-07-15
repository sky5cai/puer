/**   * @(#) project: GK* @(#) file: ZcEbFormulaTemplateItem.java* * Copyright 2010 UFGOV, Inc. All rights reserved.* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.* */package com.ufgov.zc.common.zc.model;import java.io.Serializable;import java.math.BigDecimal;import java.util.Date;/*** @ClassName: ZcEbFormulaTemplateItem* @Description: TCJLODO(这里用一句话描述这个类的作用)* @date: 2010-7-5 下午12:59:15* @version: V1.0 * @since: 1.0* @author: Administrator* @modify: */public class ZcEbFormulaTemplateItem extends ZcBaseBill implements Serializable {  private String itemCode;  private Integer indx;  private String name;  private BigDecimal weight;  private String isRequiredField;  private String isAddScore;  private BigDecimal maxAddScore;  private BigDecimal maxMinusScore;  private String creator;  private Date createTime;  private String itemType;  private String parentItemCode;  private String parentItemName;  private String templateCode;  private String formulaExpression;  private boolean isExisChild;  private String description;  private String formula;  private String complianceValue;  private BigDecimal standardScore;  private BigDecimal baseScore;  private BigDecimal leastScore;  private String isPriceTarget;  private String scoreSumWay;  private String remark;  //是否独立打分  private String isZiZhuScore;  public String toString() {    if (name != null && name.length() > 15) {      return "(I)" + name.substring(0, 14) + "...";    }    return name == null ? "" : "(I)" + name;  }  public String getItemCode() {    return itemCode;  }  public void setItemCode(String itemCode) {    this.itemCode = itemCode;  }  public Integer getIndx() {    return indx;  }  public void setIndx(Integer indx) {    this.indx = indx;  }  public String getName() {    return name;  }  public void setName(String name) {    this.name = name;  }  public BigDecimal getWeight() {    return weight;  }  public void setWeight(BigDecimal weight) {    this.weight = weight;  }  public String getIsRequiredField() {    return isRequiredField;  }  public void setIsRequiredField(String isRequiredField) {    this.isRequiredField = isRequiredField;  }  public String getIsAddScore() {    return isAddScore;  }  public void setIsAddScore(String isAddScore) {    this.isAddScore = isAddScore;  }  public BigDecimal getMaxAddScore() {    return maxAddScore;  }  public void setMaxAddScore(BigDecimal maxAddScore) {    this.maxAddScore = maxAddScore;  }  public BigDecimal getMaxMinusScore() {    return maxMinusScore;  }  public void setMaxMinusScore(BigDecimal maxMinusScore) {    this.maxMinusScore = maxMinusScore;  }  public String getCreator() {    return creator;  }  public void setCreator(String creator) {    this.creator = creator;  }  public Date getCreateTime() {    return createTime;  }  public void setCreateTime(Date createTime) {    this.createTime = createTime;  }  public String getItemType() {    return itemType;  }  public void setItemType(String itemType) {    this.itemType = itemType;  }  public String getParentItemCode() {    return parentItemCode;  }  public void setParentItemCode(String parentItemCode) {    this.parentItemCode = parentItemCode;  }  public String getParentItemName() {    return parentItemName;  }  public void setParentItemName(String parentItemName) {    this.parentItemName = parentItemName;  }  public String getTemplateCode() {    return templateCode;  }  public void setTemplateCode(String templateCode) {    this.templateCode = templateCode;  }  public String getFormulaExpression() {    return formulaExpression;  }  public void setFormulaExpression(String formulaExpression) {    this.formulaExpression = formulaExpression;  }  public boolean isExisChild() {    return isExisChild;  }  public void setExisChild(boolean isExisChild) {    this.isExisChild = isExisChild;  }  public String getDescription() {    return description;  }  public void setDescription(String description) {    this.description = description;  }  public String getFormula() {    return formula;  }  public void setFormula(String formula) {    this.formula = formula;  }  public String getComplianceValue() {    return complianceValue;  }  public void setComplianceValue(String complianceValue) {    this.complianceValue = complianceValue;  }  public BigDecimal getStandardScore() {    return standardScore;  }  public void setStandardScore(BigDecimal standardScore) {    this.standardScore = standardScore;  }  public BigDecimal getBaseScore() {    return baseScore;  }  public void setBaseScore(BigDecimal baseScore) {    this.baseScore = baseScore;  }  public BigDecimal getLeastScore() {    return leastScore;  }  public void setLeastScore(BigDecimal leastScore) {    this.leastScore = leastScore;  }  public String getIsPriceTarget() {    return isPriceTarget;  }  public void setIsPriceTarget(String isPriceTarget) {    this.isPriceTarget = isPriceTarget;  }  public String getScoreSumWay() {    return scoreSumWay;  }  public void setScoreSumWay(String scoreSumWay) {    this.scoreSumWay = scoreSumWay;  }  public String getRemark() {    return remark;  }  public void setRemark(String remark) {    this.remark = remark;  }  public String getIsZiZhuScore() {    return isZiZhuScore;  }  public void setIsZiZhuScore(String isZiZhuScore) {    this.isZiZhuScore = isZiZhuScore;  }}