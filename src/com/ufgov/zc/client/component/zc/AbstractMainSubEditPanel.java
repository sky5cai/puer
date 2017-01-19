package com.ufgov.zc.client.component.zc;import java.awt.BorderLayout;import java.awt.Color;import java.awt.Component;import java.awt.Dimension;import java.awt.Font;import java.awt.GridBagConstraints;import java.awt.GridBagLayout;import java.awt.Insets;import java.io.Serializable;import java.util.Arrays;import java.util.List;import java.util.Map;import javax.swing.JComponent;import javax.swing.JLabel;import javax.swing.JMenuBar;import javax.swing.JOptionPane;import javax.swing.JPanel;import javax.swing.JTabbedPane;import javax.swing.SwingUtilities;import com.ufgov.smartclient.component.table.fixedtable.JPageableFixedTable;import com.ufgov.zc.client.common.AsOptionMeta;import com.ufgov.zc.client.common.BillElementMeta;import com.ufgov.zc.client.common.ListCursor;import com.ufgov.zc.client.component.JFuncToolBar;import com.ufgov.zc.client.component.JTablePanel;import com.ufgov.zc.client.component.button.FuncButton;import com.ufgov.zc.client.component.table.BeanTableModel;import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;import com.ufgov.zc.client.zc.ExcelFileUtil;import com.ufgov.zc.client.zc.WordFileUtil;import com.ufgov.zc.client.zc.ZcUtil;import com.ufgov.zc.common.commonbiz.fieldmap.FieldMapRegister;import com.ufgov.zc.common.commonbiz.model.BillElement;import com.ufgov.zc.common.commonbiz.model.WfAware;import com.ufgov.zc.common.system.RequestMeta;import com.ufgov.zc.common.system.constants.SystemOptionConstants;import com.ufgov.zc.common.system.util.Utils;import com.ufgov.zc.common.zc.model.ZcBaseBill;@SuppressWarnings({ "unchecked", "serial" })public abstract class AbstractMainSubEditPanel extends JPanel {  protected JFuncToolBar toolBar = new JFuncToolBar();  protected final JPanel workPanel = new JPanel();  protected Object editingObject;  protected List<AbstractFieldEditor> fieldEditors;  protected JPanel fieldEditorPanel = new JPanel();  public Class billClass;  public BillElementMeta eleMeta;  public int preferredFontSize = Integer.valueOf(AsOptionMeta.getOptVal(SystemOptionConstants.OPT_PREFERRED_FONT_SIZE));  public abstract void initToolBar(JFuncToolBar toolBar);  public abstract List<AbstractFieldEditor> createFieldEditors();  public abstract JComponent createSubBillPanel();  public AbstractMainSubEditPanel() {  }  public AbstractMainSubEditPanel(ZcBaseBill model, String compoId) {    this(model.getClass(), BillElementMeta.getBillElementMetaWithoutNd(compoId));  }  public AbstractMainSubEditPanel(Class billClass, BillElementMeta eleMeta) {    this.billClass = billClass;    this.eleMeta = eleMeta;    WordFileUtil.setDir("sf");   }  /**   * 部件id   */  protected String compoId = "";  protected String getCompoId() {    return compoId;  }  protected void setCompoId(String compoId) {    this.compoId = compoId;  }  protected void init() {    this.initToolBar(toolBar);    this.setLayout(new BorderLayout());    this.add(toolBar, BorderLayout.NORTH);    this.add(workPanel, BorderLayout.CENTER);    if (this.billClass != null && this.eleMeta != null) {      initFieldEditorPanel(this.billClass, this.eleMeta);    } else {      initFieldEditorPanel();    }    workPanel.setLayout(new BorderLayout());    workPanel.add(fieldEditorPanel, BorderLayout.NORTH);    JComponent tabTable = createSubBillPanel();    if (tabTable != null) {      workPanel.add(tabTable, BorderLayout.CENTER);    }  }  protected int colCount = 2;  protected void initFieldEditorPanel() {    fieldEditors = createFieldEditors();    int row = 0;    int col = 0;    fieldEditorPanel.setLayout(new GridBagLayout());    for (int i = 0; i < fieldEditors.size(); i++) {      AbstractFieldEditor comp = fieldEditors.get(i);      if (comp.isVisible()) {        if (comp instanceof NewLineFieldEditor) {          row++;          col = 0;          continue;        } else if (comp instanceof TextAreaFieldEditor) {          // 转到新的一行          row++;          col = 0;          JLabel label = new JLabel(getLabelText(comp));          comp.setPreferredSize(new Dimension(120,          comp.getOccRow() * 26));          fieldEditorPanel.add(label, new GridBagConstraints(col,          row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST,          GridBagConstraints.NONE, new Insets(4, 0, 4, 4), 0,          0));          fieldEditorPanel.add(comp, new GridBagConstraints(col + 1,          row, comp.getOccCol(), comp.getOccRow(), 1.0, 1.0,          GridBagConstraints.WEST,          GridBagConstraints.HORIZONTAL, new Insets(4, 0, 4,          4), 0, 0));          // 将当前所占的行空间偏移量计算上          row += comp.getOccRow();          col = 0;          continue;        }        JLabel label = new JLabel(comp.getName());        comp.setPreferredSize(new Dimension(120, 23));        fieldEditorPanel.add(label, new GridBagConstraints(col, row, 1,        1, 1.0, 1.0, GridBagConstraints.EAST,        GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));        fieldEditorPanel.add(comp, new GridBagConstraints(col + 1, row,        1, 1, 1.0, 1.0, GridBagConstraints.WEST,        GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5),        0, 0));        if (col == colCount * 2 - 2) {          row++;          col = 0;        } else {          col += 2;        }      }    }  }  public String getLabelText(AbstractFieldEditor comp) {    StringBuffer buff = new StringBuffer();    buff.append("<html><a>&nbsp;");    buff.append(comp.getName());    if (comp.getMaxContentSize() <= 0 || comp.getMaxContentSize() == 9999) {      buff.append("</a></html>");    } else {      if (comp.getOccRow() >= 2) {        buff.append("<br>(");      } else {        buff.append("(");      }      buff.append(comp.getMaxContentSize());      buff.append("字内)</a></html>");    }    return buff.toString();  }  /**   *    * @param zcBaseBill   * @param compoId   *            Administrator 2010-5-12   */  protected void initFieldEditorPanel(ZcBaseBill zcBaseBill, String compoId) {    BillElementMeta eleMeta = BillElementMeta    .getBillElementMetaWithoutNd(compoId);    this.initFieldEditorPanel(zcBaseBill.getClass(), eleMeta);  }  protected void initFieldEditorPanel(Class billClass, BillElementMeta eleMeta) {    fieldEditors = createFieldEditors();    int row = 0;    int col = 0;    List<BillElement> notNullFields = eleMeta.getNotNullBillElement();    fieldEditorPanel.setLayout(new GridBagLayout());    for (int i = 0; i < fieldEditors.size(); i++) {      AbstractFieldEditor comp = fieldEditors.get(i);      if (comp.isVisible()) {        if (comp instanceof NewLineFieldEditor) {          row++;          col = 0;          continue;        } else if (comp instanceof TextAreaFieldEditor) {          // 转到新的一行          row++;          col = 0;          JLabel label = new JLabel(getLabelText(comp));          if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {            /*label.setText(comp.getName() + "*");            if (comp.getMaxContentSize() != 9999) {              label.setText(comp.getName() + "\n("              + comp.getMaxContentSize() + "字内)" + "*");            }*/            label.setText(label.getText() + "*");            label.setForeground(new Color(254, 100, 1));            label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));          }          //          label.setBorder(new LineBorder(Color.RED));          comp.setPreferredSize(new Dimension(150 * comp.getOccCol(), comp.getOccRow() * 26));          fieldEditorPanel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(            4, 0, 4, 4), 0, 0));          fieldEditorPanel.add(comp, new GridBagConstraints(col + 1, row, comp.getOccCol(), comp.getOccRow(), 1.0, 1.0, GridBagConstraints.WEST,            GridBagConstraints.HORIZONTAL, new Insets(4, 0, 4, 4), 0, 0));          // 将当前所占的行空间偏移量计算上          row += comp.getOccRow();          col = 0;          continue;        }        JLabel label = new JLabel(comp.getName());        if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {          // label = new AsteriskLabel(comp.getName() + "*");          // label.setText("<html><a>" + comp.getName() +          // "<font color='red'>*</font></a></html>");          label.setText(label.getText() + "*");          label.setForeground(new Color(254, 100, 1));          label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));        }        comp.setPreferredSize(new Dimension(150, 23));        fieldEditorPanel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,          0, 5, 5), 0, 0));        fieldEditorPanel.add(comp, new GridBagConstraints(col + 1, row, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,          new Insets(5, 0, 5, 5), 0, 0));        if (col == colCount * 2 - 2) {          row++;          col = 0;        } else {          col += 2;        }      }    }  }  public boolean isNotNullField(Class billClass, String fieldName,  List<BillElement> notNullFields) {    for (BillElement billElement : notNullFields) {      String name = null;      try {        name = (String) FieldMapRegister.get(billClass).get(billElement.getElementCode());        if (name == null || "".equals(name.trim())) {          name = ZcUtil.convertColumnToField(billElement.getElementCode());        }      } catch (RuntimeException e) {        name = ZcUtil.convertColumnToField(billElement.getElementCode());      }      if (name.equalsIgnoreCase(fieldName))        return true;    }    return false;  }  protected boolean validateData(ZcBaseBill currentModel, String compoId) {    BillElementMeta eleMeta = BillElementMeta    .getBillElementMetaWithoutNd(compoId);    List<BillElement> notNullFields = eleMeta.getNotNullBillElement();    String str = ZcUtil    .validateBillElementNull(currentModel, notNullFields);    if (str != null && str.trim().length() > 0) {      JOptionPane.showMessageDialog(this, str, "提示",      JOptionPane.INFORMATION_MESSAGE);      return false;    }    return true;  }  public Object getEditingObject() {    return editingObject;  }  public void setEditingObject(WfAware editingObject) {    this.editingObject = editingObject;    updateFieldEditors();  }  protected void updateFieldEditors() {    for (AbstractFieldEditor editor : fieldEditors) {      editor.setEditObject(editingObject);    }  }  protected Map wfCanEditFieldMap;  protected boolean isEdit;  protected void updateWFEditorEditable(WfAware baseBill,  RequestMeta requestMeta) {    Long processInstId = baseBill.getProcessInstId();    isEdit = false;    if (processInstId != null && processInstId.longValue() > 0) {      // 工作流的单据      wfCanEditFieldMap = BillElementMeta.getWfCanEditField(baseBill,      requestMeta);      for (AbstractFieldEditor editor : fieldEditors) {        // 工作流中定义可编辑的字段        if (wfCanEditFieldMap != null        && wfCanEditFieldMap.containsKey(Utils        .getDBColNameByFieldName(        editor.getEditObject(), editor        .getFieldName()))) {          isEdit = true;          editor.setEnabled(true);        } else {          editor.setEnabled(false);        }      }      // 子表的设置      updateWFSubTableEditable();    }  }  protected void setWFSubTableEditable(JTablePanel tablePanel, boolean isEnabled) {    BeanTableModel tablemodel = (BeanTableModel) tablePanel.getTable()    .getModel();    tablemodel.setEditable(isEnabled);    Component[] components = tablePanel.getComponents();    for (Component component : components) {      if (component instanceof JFuncToolBar) {        component.setEnabled(isEnabled);      }    }  }  protected JTablePanel[] getSubTables() {    return null;  }  protected void fitTable() {    JTablePanel[] tablePanel = this.getSubTables();    if (tablePanel != null) {      for (int i = 0; i < tablePanel.length; i++) {        final JPageableFixedTable table = tablePanel[i].getTable();        tablePanel[i].getScrollPane().getHorizontalScrollBar()        .setValue(0);        tablePanel[i].getScrollPane().getVerticalScrollBar()        .setValue(0);        SwingUtilities.invokeLater(new Runnable() {          public void run() {            try {              fitColumnWidth(table);            } catch (Exception e) {            }          }        });      }    }  }  protected void updateFieldEditorsEditable() {    // 根据数据库中的是否可编辑字段来控制页面属性的可编辑性    if (eleMeta != null) {      Map editAbleMap = eleMeta.getAllEditAbleField();      for (AbstractFieldEditor editor : fieldEditors) {        if (editor instanceof NewLineFieldEditor) {          continue;        }        editor.getFieldName();        //        System.out.println(editor.getFieldName());        if (editAbleMap.containsKey(Utils.getDBColNameByFieldName(editor.getEditObject(), editor.getFieldName()))) {          editor.setEnabled(true);        } else {          editor.setEnabled(false);        }      }    }  }  protected void updateWFSubTableEditable() {    // 默认的方法是判断isEdit属性（主表中是否存在可编辑字段）,如果主表中存在可编辑字段，就设置从表字段都可编辑.    // 如果这个逻辑满足不了业务，可以在实现类里覆盖updateWFSubTableEditable方法，自己做判断.    if (getSubTables() != null) {      if (isEdit) {        for (JTablePanel tablePanel : getSubTables()) {          setWFSubTableEditable(tablePanel, true);        }      } else {        for (JTablePanel tablePanel : getSubTables()) {          setWFSubTableEditable(tablePanel, false);        }      }    }  }  protected void setButtonStatus(WfAware baseBill, RequestMeta requestMeta, ListCursor listCursor) {    Long processInstId = baseBill.getProcessInstId();    if (processInstId == null || processInstId.longValue() < 0) {      // 新增单据,草稿单据或不挂接工作流的单据      Component[] funcs = toolBar.getComponents();      String funcId;      for (Component func : funcs) {        funcId = ((FuncButton) func).getFuncId();        if ("fauditfinal" == funcId || "fcallback" == funcId        || "fautocommit" == funcId || "funaudit" == funcId        || "funtread" == funcId        || "fshowinstancetrace" == funcId        || "f_uncollectcreate" == funcId        //        || "fnew" == funcId          || "fconfirmsup" == funcId || "fmanualcommit" == funcId          || "fsendnextcommit" == funcId          || funcId != null && funcId.startsWith("fmanualcommit")) {          func.setVisible(false);        }      }      setButtonStatusWithoutWf();    } else {      // 流程已经启动      List enableFuncs = this.getWFNodeEnableFunc(baseBill, requestMeta);      ZcUtil.setWfNodeEnableFunc(toolBar, enableFuncs, processInstId,  requestMeta);    }    if (listCursor == null || listCursor.getDataList() == null    || listCursor.getDataList().size() <= 1) {      // 如果listCursor中只有一条记录，就隐藏上一条下一条按钮      FuncButton previousButton = toolBar.getButtonByDefaultText("上一条");      FuncButton nextButton = toolBar.getButtonByDefaultText("下一条");      if (previousButton != null) {        previousButton.setVisible(false);      }      if (nextButton != null) {        nextButton.setVisible(false);      }    }  }  /**   * 子类重写该方法，用于非工作流控制状态下按钮的编辑性   */  public void setButtonStatusWithoutWf() {    // TCJLODO Auto-generated method stub    Component[] funcs = toolBar.getComponents();    String funcId;    for (Component func : funcs) {      funcId = ((FuncButton) func).getFuncId();      if (funcId != null) {        if ("fauditfinal" == funcId || "fcallback" == funcId        || "fautocommit" == funcId || "funaudit" == funcId        || "funtread" == funcId        || "fshowinstancetrace" == funcId        || "f_uncollectcreate" == funcId        //        || "fnew" == funcId          || "fconfirmsup" == funcId || "fmanualcommit" == funcId          || "fsendnextcommit" == funcId          || funcId != null && funcId.startsWith("fmanualcommit")) {          func.setVisible(false);          continue;        }        func.setVisible(true);      }    }  }  /**   * 取消状态数据设置按钮显示   * @param listCursor   */  protected void setCancelStatus(ListCursor listCursor) {    Component[] funcs = toolBar.getComponents();    String funcId;    for (Component func : funcs) {      funcId = ((FuncButton) func).getFuncId();      if (funcId != null) {        func.setVisible(false);      }    }    if (listCursor == null || listCursor.getDataList() == null    || listCursor.getDataList().size() <= 1) {      // 如果listCursor中只有一条记录，就隐藏上一条下一条按钮      FuncButton previousButton = toolBar.getButtonByDefaultText("上一条");      FuncButton nextButton = toolBar.getButtonByDefaultText("下一条");      if (previousButton != null) {        previousButton.setVisible(false);      }      if (nextButton != null) {        nextButton.setVisible(false);      }    }  }  protected List getWFNodeEnableFunc(WfAware baseBill, RequestMeta requestMeta) {    return BillElementMeta.getWfNodeEnableFunc(baseBill, requestMeta);  }  protected void fitColumnWidth(JPageableFixedTable table) {    for (int j = 0; j < table.getColumnModel().getColumnCount(); j++) {      table.getTableHeader().fitColumnWidth(j);    }  }  /*   * 从表的添加行方法   */  protected int addSub(JTablePanel tablePanel, Serializable bean) {    tablePanel.getTable().clearSelection();    if (tablePanel.getTable().isEditing()) {      tablePanel.getTable().getCellEditor().stopCellEditing();    }    BeanTableModel editTableModel = (BeanTableModel) tablePanel.getTable().getModel();    editTableModel.insertRow(editTableModel.getRowCount(), bean);    fitColumnWidth(tablePanel.getTable());    return editTableModel.getRowCount() - 1;  }  /*   * 从表的插入行方法   */  protected int insertSub(JTablePanel tablePanel, Serializable bean) {    if (tablePanel.getTable().isEditing()) {      tablePanel.getTable().getCellEditor().stopCellEditing();    }    BeanTableModel editTableModel = (BeanTableModel) tablePanel.getTable()    .getModel();    int selectedRow = tablePanel.getTable().getSelectedRow();    if (selectedRow != -1) {      editTableModel.insertRow(selectedRow + 1, bean);      selectedRow = selectedRow + 1;    } else {      editTableModel.insertRow(editTableModel.getRowCount(), bean);      selectedRow = editTableModel.getRowCount() - 1;    }    fitColumnWidth(tablePanel.getTable());    return selectedRow;  }  /*   * 从表的删除行方法   */  protected Integer[] deleteSub(JTablePanel tablePanel) {    JPageableFixedTable table = tablePanel.getTable();    Integer[] checkedRows;    // 是否显示行选择框    if (tablePanel.getTable().isShowCheckedColumn()) {      checkedRows = table.getCheckedRows();    } else {      int[] selectedRows = table.getSelectedRows();      checkedRows = new Integer[selectedRows.length];      for (int i = 0; i < checkedRows.length; i++) {        checkedRows[i] = selectedRows[i];      }    }    if (checkedRows.length == 0) {      JOptionPane.showMessageDialog(this, "没有选择数据！", "提示",      JOptionPane.INFORMATION_MESSAGE);    } else {      if (table.isEditing()) {        table.getCellEditor().stopCellEditing();      }      BeanTableModel tableModel = (BeanTableModel) table.getModel();      int[] selRows = new int[checkedRows.length];      for (int i = 0; i < selRows.length; i++) {        selRows[i] = table.convertRowIndexToModel(checkedRows[i]);      }      Arrays.sort(selRows);      for (int i = selRows.length - 1; i >= 0; i--) {        tableModel.deleteRow(selRows[i]);      }    }    fitColumnWidth(tablePanel.getTable());    return checkedRows;  }  public boolean budgetExcuce(List selectedDatas) {    return true;  }  public boolean budgetAfterClear() {    return true;  }  /**   * 用于刷新word控件，因为word用swt来显示，其显示前，其控件所在的面板必须已经显示，否则会报peer not created错误   * 因此，如果编辑界面使用了word控件，则加载word的过程写在下面这个方法中，这个方法在dialog显示以后，在进行调用   *  by chenjl 20150117   */  public void refreshFilePanel() {  }    public JMenuBar getMenuBar(){	  return null;  }}