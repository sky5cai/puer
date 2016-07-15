package com.ufgov.zc.client.console.elementrule.accounter;import java.awt.BorderLayout;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import javax.swing.JButton;import javax.swing.JToolBar;import com.ufgov.zc.client.component.JSaveableSplitPane;import com.ufgov.zc.client.console.elementrule.RuleEntryModel;import com.ufgov.zc.client.console.elementrule.RuleView;import com.ufgov.zc.client.console.elementrule.dialog.AccounterSetDialog;import com.ufgov.zc.client.console.elementrule.dialog.ElementRelationSetDialog;import com.ufgov.zc.common.commonbiz.model.MaElementRelationRuleDetail;public class AccounterRuleView extends RuleView {  private static final long serialVersionUID = -2609871415532233464L;  private JSaveableSplitPane panel = new JSaveableSplitPane(JSaveableSplitPane.VERTICAL_SPLIT);  private JToolBar toolbar;  private AccounterInfoPanel infoPanel = null;  private AccounterRuleInfoPanel rulePanel = null;  private AccounterSetDialog dialog = null;  public AccounterRuleView() {    this.setLayout(new BorderLayout());    initToolBar();    initComponent();  }  private void initToolBar() {    toolbar = new JToolBar(JToolBar.HORIZONTAL);    toolbar.setFloatable(false);    JButton chooseBtn = new JButton("选择账户");    chooseBtn.addActionListener(new ActionListener() {      public void actionPerformed(ActionEvent event) {        if (dialog == null) {          dialog = new AccounterSetDialog(null, true, AccounterRuleView.this.ruleEntryModel);        }        dialog.setVisible(true);      }    });    toolbar.add(chooseBtn);    JButton relationSetBtn = new JButton("设置关联要素");    relationSetBtn.addActionListener(new ActionListener() {      public void actionPerformed(ActionEvent event) {        ElementRelationSetDialog dialog = new ElementRelationSetDialog(null, true, AccounterRuleView.this.ruleEntryModel,          MaElementRelationRuleDetail.DIRECTION_SRC);      }    });    toolbar.add(relationSetBtn);    this.add(toolbar, BorderLayout.NORTH);  }  private void initComponent() {    panel.setDividerDefaultLocation(this.getClass().getName() + "_splitPane_dividerLocation", 150);    panel.setOneTouchExpandable(true);    infoPanel = new AccounterInfoPanel();    rulePanel = new AccounterRuleInfoPanel();    panel.setTopComponent(infoPanel);    panel.setBottomComponent(rulePanel);    this.add(panel, BorderLayout.CENTER);  }  @Override  public void initWithRuleEntryModel(RuleEntryModel model) {    // TCJLODO Auto-generated method stub    infoPanel.initWithRuleEntryModel(model);    rulePanel.initWithRuleEntryModel(model);  }}