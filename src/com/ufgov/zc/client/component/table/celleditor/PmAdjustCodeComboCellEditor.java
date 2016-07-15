package com.ufgov.zc.client.component.table.celleditor;import java.awt.Component;import java.awt.event.ItemEvent;import java.awt.event.ItemListener;import javax.swing.AbstractCellEditor;import javax.swing.JTable;import javax.swing.table.TableCellEditor;import com.ufgov.zc.client.component.PlanTypeComboBox;import com.ufgov.zc.common.system.model.AsVal;public class PmAdjustCodeComboCellEditor extends AbstractCellEditor implements TableCellEditor {  private PlanTypeComboBox pmAdjustombox = new PlanTypeComboBox("VS_BI_ADJUST_TRACK_CODE");  private PmAdjustCodeComboCellEditor self = this;  public PmAdjustCodeComboCellEditor() {    pmAdjustombox.addItemListener(new ItemListener() {      public void itemStateChanged(ItemEvent e) {        self.stopCellEditing();      }    });  }  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,  int column) {    if (value instanceof AsVal) {      AsVal asVal = (AsVal) value;      pmAdjustombox.setSelectedAsValByCode(asVal.getValId());    } else {      if (value != null)        pmAdjustombox.setSelectedAsValByCode(value.toString());    }    return pmAdjustombox;  }  public Object getCellEditorValue() {    Object obj = pmAdjustombox.getSelectedAsVal();    if (obj != null) {      AsVal asVal = (AsVal) obj;      return asVal;    } else {      return pmAdjustombox.getSelectedItem();    }  }}