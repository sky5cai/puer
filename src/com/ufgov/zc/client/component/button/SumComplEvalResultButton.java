/**   * @(#) project: ZC* @(#) file: SumComplEvalResultButton.java* * Copyright 2010 UFGOV, Inc. All rights reserved.* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.* */package com.ufgov.zc.client.component.button;/*** @ClassName: SumComplEvalResultButton* @Description: TCJLODO(符合性评审汇总按钮)* @date: 2010-12-5 下午12:24:26* @version: V1.0 * @since: 1.0* @author: fanpl* @modify: */public class SumComplEvalResultButton extends FuncButton {  private static final long serialVersionUID = 8176162602374553497L;  public SumComplEvalResultButton() {    super();  }  protected void init() {    this.funcCtrl = false;    this.funcId = "fsumComplEvalResult";    this.defaultText = "汇总符合性评审结果";    this.iconName = "send.jpg";    super.init();  }}