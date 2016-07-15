package com.ufgov.zc.client.component.setting.jj;import com.ufgov.smartclient.component.JComboBoxEx;import com.ufgov.zc.client.common.AsOptionMeta;import com.ufgov.zc.client.component.element.FundComboBox;import com.ufgov.zc.client.component.setting.AbstractOption;import com.ufgov.zc.common.commonbiz.model.Fund;import com.ufgov.zc.common.system.constants.BusinessOptionConstants;public class JjFundOption extends AbstractOption<JComboBoxEx> {  /**   *   */  private static final long serialVersionUID = 1L;  public JjFundOption() {    this.init(BusinessOptionConstants.OPT_JJ_JJ_FUND_CODE, "基建资金性质：");  }  protected JComboBoxEx createOptionEditor() {    String code = AsOptionMeta.getOptVal(optId);    this.oldValue = code;    final FundComboBox editor = new FundComboBox();    editor.initComboBox();    editor.setSelectedFundByCode(code);    return editor;  }  public void updateOption() {    Fund v = (Fund) this.optionEditor.getSelectedItem();    String code = "";    if (v != null) {      code = v.getCode();    }    if (!code.equals(oldValue)) {      AsOptionMeta.updateOptVal(optId, code);      oldValue = code;    }  }}