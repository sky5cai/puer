package com.ufgov.zc.client.component.table.codecelleditor;import java.awt.Component;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.util.List;import javax.swing.JTable;import javax.swing.table.TableCellEditor;import com.ufgov.zc.client.component.OperationTypeComboBox;import com.ufgov.zc.client.component.table.GkAbstractCellEditor;import com.ufgov.zc.common.commonbiz.model.OperationType;public class OperationTypeCellEditor extends GkAbstractCellEditor implements TableCellEditor {  private static final long serialVersionUID = -1014964575090820890L;  private OperationTypeComboBox operationTypeCombo;  OperationTypeCellEditor self = this;  public OperationTypeCellEditor() {    operationTypeCombo = new OperationTypeComboBox();    operationTypeCombo.initComboBox();    operationTypeCombo.addActionListener(new ActionListener() {      public void actionPerformed(ActionEvent e) {        self.stopCellEditing();      }    });  }  public OperationTypeCellEditor(List flowCodeList) {    operationTypeCombo = new OperationTypeComboBox(flowCodeList);    operationTypeCombo.initComboBox();    operationTypeCombo.addActionListener(new ActionListener() {      public void actionPerformed(ActionEvent e) {        self.stopCellEditing();      }    });  }  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,  int column) {    operationTypeCombo.setSelectedOperationTypeByCode((String) value);    return operationTypeCombo;  }  public Object getCellEditorValue() {    String code = null;    OperationType value = operationTypeCombo.getSelectedOperationType();    if (value != null) {      code = value.getCode();    }    return code;  }}