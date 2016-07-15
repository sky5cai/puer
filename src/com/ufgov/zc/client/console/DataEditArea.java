package com.ufgov.zc.client.console;import java.awt.BorderLayout;import java.awt.FlowLayout;import java.awt.event.KeyAdapter;import java.awt.event.KeyEvent;import java.util.List;import java.util.Vector;import javax.swing.CellEditor;import javax.swing.JComponent;import javax.swing.JLabel;import javax.swing.JPanel;import javax.swing.JScrollPane;import javax.swing.JTable;import javax.swing.JTextField;import javax.swing.table.DefaultTableModel;import javax.swing.table.TableRowSorter;import com.ufgov.smartclient.component.table.JGroupableTable;import com.ufgov.zc.client.component.SimpleRowFilter;import com.ufgov.zc.client.util.GkPreferencesStore;import com.ufgov.zc.client.util.SwingUtil;public class DataEditArea extends JComponent {  private String dataAreaKey;  private String parentContainerKey;  private JGroupableTable editTable;  private TableRowSorter tableSorter = new TableRowSorter();  protected JTextField searchConditionTextField = new JTextField();  private boolean isVisibleSearch = true;  protected JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));  public DataEditArea(String name, String key) {    this(name, key, true);  }  public DataEditArea(String name, String key, boolean isVisibleSearch) {    this.isVisibleSearch = isVisibleSearch;    setName(name);    this.dataAreaKey = key;    initGUI();  }  private void initGUI() {    this.setLayout(new BorderLayout());    if (isVisibleSearch) {      searchPanel.add(new JLabel("查找："));      searchPanel.add(searchConditionTextField);      searchConditionTextField.setColumns(26);      searchConditionTextField.addKeyListener(new KeyAdapter() {        @Override        public void keyReleased(KeyEvent e) {          tableSorter.setRowFilter(new SimpleRowFilter(searchConditionTextField.getText()));        }      });    }    addComponentToPanel();    this.add(searchPanel, BorderLayout.NORTH);    JScrollPane scrollPane = new JScrollPane();    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);    scrollPane.getViewport().add(getTable());    this.add(scrollPane, BorderLayout.CENTER);  }  public void addComponentToPanel() {  }  public JGroupableTable getTable() {    if (editTable == null) {      editTable = createTable();    }    return editTable;  }  protected JGroupableTable createTable() {    JGroupableTable newTable = SwingUtil.createTable(JGroupableTable.class);    newTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    newTable.setShowCheckedColumn(false);    newTable.setRowSelectionAllowed(false);    newTable.setCellSelectionEnabled(true);    return newTable;  }  public void setTableModel(DefaultTableModel dataModel) {    editTable.setModel(dataModel);    tableSorter.setModel(dataModel);    editTable.setRowSorter(tableSorter);  }  public void setParentContainerKey(String key) {    this.parentContainerKey = key;    setPreferencesKey(this.parentContainerKey + "_" + this.dataAreaKey + "_tasble");  }  public void setPreferencesKey(String preferencesKey) {    editTable.setPreferencesKey(preferencesKey);    editTable.setPreferenceStore(GkPreferencesStore.preferenceStore());  }  //  public void saveTablePreferences() {  //    editTable.savePreferences();  //    editTable.loadPreferences();  //  }  public void stopDataEditArea() {    int i = editTable.getEditingRow();    int j = editTable.getEditingColumn();    if (i > -1 && j > -1) {      CellEditor ce = editTable.getCellEditor(i, j);      if (ce != null && editTable.isCellSelected(i, j) == true)        ce.stopCellEditing();    }  }  public List getAfterEditTableData() {    Vector data = new Vector();    int row = editTable.getRowCount();    int column = editTable.getColumnCount();    for (int i = 0; i < row; i++) {      Vector rowData = new Vector();      for (int j = 0; j < column; j++) {        CellEditor ce = editTable.getCellEditor(i, j);        if (ce != null && editTable.isCellSelected(i, j) == true) {          ce.stopCellEditing();          //          System.out.println("====>" + ce.getCellEditorValue());          rowData.add(ce.getCellEditorValue());          continue;        }        //        System.out.println("====>" + editTable.getValueAt(i, j));        rowData.add(editTable.getModel().getValueAt(i, j));      }      data.add(rowData);    }    return data;  }  public void refreshGUI() {    this.invalidate();    this.validate();    this.repaint();  }  public String getDataAreaKey() {    return dataAreaKey;  }  public void setDataAreaKey(String dataAreaKey) {    this.dataAreaKey = dataAreaKey;  }  @Override  public int hashCode() {    final int prime = 31;    int result = 1;    result = prime * result + ((dataAreaKey == null) ? 0 : dataAreaKey.hashCode());    result = prime * result + ((editTable == null) ? 0 : editTable.hashCode());    return result;  }  @Override  public boolean equals(Object obj) {    if (this == obj)      return true;    if (obj == null)      return false;    if (getClass() != obj.getClass())      return false;    final DataEditArea other = (DataEditArea) obj;    if (dataAreaKey == null) {      if (other.dataAreaKey != null)        return false;    } else if (!dataAreaKey.equals(other.dataAreaKey))      return false;    if (editTable == null) {      if (other.editTable != null)        return false;    } else if (!editTable.equals(other.editTable))      return false;    return true;  }}