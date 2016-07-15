package com.ufgov.zc.client.component;import java.awt.BorderLayout;import java.awt.Dialog;import java.awt.event.WindowAdapter;import java.awt.event.WindowEvent;import javax.swing.JFrame;import javax.swing.JPanel;import com.ufgov.zc.common.console.model.Component;public class ComponentSelectField extends JButtonTextField {  public ComponentSelectField(int col, Dialog owner) {    super(col);    this.owner = owner;    this.init();  }  public ComponentSelectField(int col) {    super(col);    this.init();  }  public ComponentSelectField() {    super();    this.init();  }  public void setValue(Object value) {    this.value = value;    if (value != null) {      Component data = (Component) value;      this.setText(data.getCompoName());      if (data.getCompoName() == null)        this.setToolTipText(null);      else        this.setToolTipText("[" + data.getCompoCode() + "]"        + data.getCompoName());    } else {      this.setText("");      this.setToolTipText(null);    }    this.fireValueChanged();  }  public void setCompo(Component value) {    this.setValue(value);  }  public Component getCompo() {    return (Component) this.getValue();  }  public void handleClick(JButtonTextField jButtonTextField) {    selectDialog.setVisible(true);  }  private void init() {    selectDialog = new ComponentSelectDialog(owner, true, this);  }  public static void main(String[] args) {    JFrame f = new JFrame();    ComponentSelectField textField = new ComponentSelectField(20, null);    textField.setValueByCode("BI_ABI_ADJ");    textField.setEditable(false);    textField.setEnabled(false);    textField.setEnabled(true);    textField.setRandomEdit(false);    // textField.setPrefix("01");    JPanel panel = new JPanel();    panel.add(textField);    f.getContentPane().add(panel, BorderLayout.NORTH);    f.setSize(400, 300);    f.setLocationRelativeTo(null);    f.setVisible(true);    f.addWindowListener(new WindowAdapter() {      public void windowClosing(WindowEvent e) {        System.exit(0);      }    });  }}