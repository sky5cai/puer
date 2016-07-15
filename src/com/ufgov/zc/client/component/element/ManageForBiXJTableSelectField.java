package com.ufgov.zc.client.component.element;import java.awt.BorderLayout;import java.awt.Dialog;import java.awt.event.WindowAdapter;import java.awt.event.WindowEvent;import javax.swing.JFrame;import javax.swing.JPanel;import com.ufgov.zc.client.component.JButtonTextField;import com.ufgov.zc.common.commonbiz.model.Manage;public class ManageForBiXJTableSelectField extends JButtonTextField {  public void setValue(Object value) {    this.value = value;    if (value != null) {      Manage data = (Manage) value;      this.setText(data.getName());      this.setToolTipText("[" + data.getCode() + "]" + data.getName());    } else {      this.setText("");      this.setToolTipText(null);    }    this.fireValueChanged();  }  public void setManage(Manage value) {    this.setValue(value);  }  public Manage getManage() {    return (Manage) this.getValue();  }  public void handleClick(JButtonTextField jButtonTextField) {    await();    selectDialog.setVisible(true);  }  private void init() {    this.elementCode = "KIND";    selectDialog = new ManageForBiXJTableSelectDialog(owner, true, this);  }  public ManageForBiXJTableSelectField(int col) {    super(col);    this.init();  }  public ManageForBiXJTableSelectField(int col, Dialog owner) {    super(col);    this.owner = owner;    this.init();  }  public void refreshData() {    if (selectDialog != null) {      ((ManageForBiXJTableSelectDialog) selectDialog).initDataBufferList();    }  }  public static void main(String[] args) {    JFrame f = new JFrame();    JButtonTextField textField = new ManageForBiXJTableSelectField(20);    textField.setEditable(false);    textField.setEnabled(false);    textField.setEnabled(true);    JPanel panel = new JPanel();    panel.add(textField);    f.getContentPane().add(panel, BorderLayout.NORTH);    // f.pack();    // SwingUtilities.updateComponentTreeUI(panel);    f.setSize(400, 300);    f.setLocationRelativeTo(null);    f.setVisible(true);    f.addWindowListener(new WindowAdapter() {      public void windowClosing(WindowEvent e) {        System.exit(0);      }    });  }}