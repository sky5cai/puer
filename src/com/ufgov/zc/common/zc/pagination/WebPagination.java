package com.ufgov.zc.common.zc.pagination;import java.util.Map;/** * * <p>Title: 提供JSP页面显示的分页信息</p> * <p>Description: </p> * <p>Copyright: Copyright (c) 2007</p> * <p>Company: MiracleSoft</p> * @version 1.0 */public interface WebPagination extends Pagination {  String DEFAULT_HTML_FORM_NAME = "pageQueryForm";  String DEFAULT_CURRENT_PAGE_PARAM_NAME = "default_current_page_param_name";  String DEFAULT_PAGE_SIZE_PARAM_NAME = "default_page_size_param_name";  public void setPaginationStyle(PaginationStyle paginationStyle);  public PaginationStyle getPaginationStyle();  /**   * 设置的HMTL<form/>标签的名称   * @param formName HMTL<form/>标签的名称   */  public void setFormName(String formName);  /**   * 取得设置的HMTL<form/>标签的名称   * @return HMTL<form/>标签的名称   */  public String getFormName();  /**   * 设置访问路径   * @param url 访问路径   */  public void setUrl(String url);  /**   * 取得访问路径。   * @return 访问路径。   */  public String getUrl();  /**   * 设置参数名称，该参数是代表了要访问的页号。在Action层是通过取得该参数的值来计算用户访问的页号。   * @param paramName 参数名称   */  public void setCurrentPageParamName(String paramName);  /**   * 取得参数名称，该参数是代表了要访问的页号。在Action是通过取得该参数的值来计算用户访问的页号。   *    * @return 参数名称   */  public String getCurrentPageParamName();  /**   * 获取参数名称，该参数是代表了用户设置的每页显示的记录数。通过该名称从ServletRequest对象中获取每页显示的记录数目。   * @return 参数名称   */  public String getPageSizeParamName();  /**   * 设置每页显示记录数在JSP页面中的名称。   * @param pageSizeParamName   */  public void setPageSizeParamName(String pageSizeParamName);  /**   * 获取页面显示信息   * @return 页面显示信息，该信息在视图层显示   */  public String getPaginationText();  /**   * 获取查询结果   * @return  获取查询结果   */  public Object getQueryResult();  /**   * 设置查询结果   * @param queryResult 查询结果   */  public void setQueryResult(Object queryResult);  /**   * 存放参数的Map   * @return 存放参数的Map   */  public Map getParameters();  /**   * 获取分页文本的时候是否包含脚本。   * @return 脚本程序   */  public boolean isPrintScript();  /**   * 设置是否打印脚本程序。   * @param printable    */  public void setPrintScript(boolean printable);  public String getScript();  /**   * 当使用模板的方式生成分页字符串信息时，可能会需要循环总页数，因此   * 提供了这样一个数组：假设共分4页，则该数组中存放的就是1、2、3、4   * @return 整形数组   */  public int[] getTotalPages();  /**   * 在url(或者是<FORM/>)中添加参数名称和参数值。   * @param paramName 参数名称   * @param paramValue 参数值    */  public void appendParam(String paramName, String paramValue);  /**   * 将字符串转换成int。在视图层通常是传递一个字符串的页号，可以使用该类进行转换。   * @param str 字符串   * @return 转换后的long值。如果不能进行转换，则返回1。    */  public int stringToInt(String str);}