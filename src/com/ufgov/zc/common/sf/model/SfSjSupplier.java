package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfSjSupplier extends ZcBaseBill {
	
	public static final String TAB_ID="SfSjSupplier_Tab";

	public static final String VS_SF_SUPPLIER_STATUS="VS_SF_SUPPLIER_STATUS";//enable 启用，disable 冻结
	
	public static final String VS_SF_SUPPLIER_TYPE="VS_SF_SUPPLIER_TYPE";
	/**
	 * 供应商
	 */
	public static final String VS_SF_SUPPLIER_TYPE_GYS="gys";
	/**
	 * 生产商
	 */
	public static final String VS_SF_SUPPLIER_TYPE_SCS="scs";
	
	public static final String COL_ADDRESS="SF_SJ_SUPPLIER_ADDRESS"; // 地址
	public static final String COL_DUTY="SF_SJ_SUPPLIER_DUTY"; // 职务
	public static final String COL_EMAIL="SF_SJ_SUPPLIER_EMAIL"; // email
	public static final String COL_FAX="SF_SJ_SUPPLIER_FAX"; // 传真
	public static final String COL_LINK_MAN="SF_SJ_SUPPLIER_LINK_MAN"; // 联系人
	public static final String COL_NAME="SF_SJ_SUPPLIER_NAME"; // 名称
	public static final String COL_PYM="SF_SJ_SUPPLIER_PYM"; // 拼音码
	public static final String COL_STATUS="SF_SJ_SUPPLIER_STATUS"; // 状态
	public static final String COL_SUPPLIER_ID="SF_SJ_SUPPLIER_SUPPLIER_ID"; // 经销商ID
	public static final String COL_SUPPLIER_TYPE="SF_SJ_SUPPLIER_SUPPLIER_TYPE"; // 类别 
	public static final String COL_TEL="SF_SJ_SUPPLIER_TEL"; // 手机
	public static final String COL_TEL_WORK="SF_SJ_SUPPLIER_TEL_WORK"; // 工作电话
	public static final String COL_URL="SF_SJ_SUPPLIER_URL"; // 网址
	public static final String COL_ZIP="SF_SJ_SUPPLIER_ZIP"; // 邮编

	public static final String SEQ_SF_SJ_SUPPLIER = "SEQ_SF_SJ_SUPPLIER";

	public static final String VS_SF_SUPPLIER_STATUS_ENABLE = "enable";
	public static final String VS_SF_SUPPLIER_STATUS_DISABLE = "disable";

	
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.SUPPLIER_ID
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private BigDecimal supplierId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.NAME
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.PYM
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String pym;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.ZIP
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String zip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.ADDRESS
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.URL
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.LINK_MAN
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String linkMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.DUTY
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String duty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.EMAIL
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.TEL_WORK
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String telWork;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.TEL
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String tel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.FAX
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String fax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.STATUS
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_SJ_SUPPLIER.SUPPLIER_TYPE
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    private String supplierType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.SUPPLIER_ID
     *
     * @return the value of SF_SJ_SUPPLIER.SUPPLIER_ID
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public BigDecimal getSupplierId() {
        return supplierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.SUPPLIER_ID
     *
     * @param supplierId the value for SF_SJ_SUPPLIER.SUPPLIER_ID
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setSupplierId(BigDecimal supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.NAME
     *
     * @return the value of SF_SJ_SUPPLIER.NAME
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.NAME
     *
     * @param name the value for SF_SJ_SUPPLIER.NAME
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.PYM
     *
     * @return the value of SF_SJ_SUPPLIER.PYM
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getPym() {
        return pym;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.PYM
     *
     * @param pym the value for SF_SJ_SUPPLIER.PYM
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setPym(String pym) {
        this.pym = pym;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.ZIP
     *
     * @return the value of SF_SJ_SUPPLIER.ZIP
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getZip() {
        return zip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.ZIP
     *
     * @param zip the value for SF_SJ_SUPPLIER.ZIP
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.ADDRESS
     *
     * @return the value of SF_SJ_SUPPLIER.ADDRESS
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.ADDRESS
     *
     * @param address the value for SF_SJ_SUPPLIER.ADDRESS
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.URL
     *
     * @return the value of SF_SJ_SUPPLIER.URL
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.URL
     *
     * @param url the value for SF_SJ_SUPPLIER.URL
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.LINK_MAN
     *
     * @return the value of SF_SJ_SUPPLIER.LINK_MAN
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getLinkMan() {
        return linkMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.LINK_MAN
     *
     * @param linkMan the value for SF_SJ_SUPPLIER.LINK_MAN
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.DUTY
     *
     * @return the value of SF_SJ_SUPPLIER.DUTY
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getDuty() {
        return duty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.DUTY
     *
     * @param duty the value for SF_SJ_SUPPLIER.DUTY
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setDuty(String duty) {
        this.duty = duty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.EMAIL
     *
     * @return the value of SF_SJ_SUPPLIER.EMAIL
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.EMAIL
     *
     * @param email the value for SF_SJ_SUPPLIER.EMAIL
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.TEL_WORK
     *
     * @return the value of SF_SJ_SUPPLIER.TEL_WORK
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getTelWork() {
        return telWork;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.TEL_WORK
     *
     * @param telWork the value for SF_SJ_SUPPLIER.TEL_WORK
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setTelWork(String telWork) {
        this.telWork = telWork;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.TEL
     *
     * @return the value of SF_SJ_SUPPLIER.TEL
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.TEL
     *
     * @param tel the value for SF_SJ_SUPPLIER.TEL
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.FAX
     *
     * @return the value of SF_SJ_SUPPLIER.FAX
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getFax() {
        return fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.FAX
     *
     * @param fax the value for SF_SJ_SUPPLIER.FAX
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.STATUS
     *
     * @return the value of SF_SJ_SUPPLIER.STATUS
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.STATUS
     *
     * @param status the value for SF_SJ_SUPPLIER.STATUS
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_SJ_SUPPLIER.SUPPLIER_TYPE
     *
     * @return the value of SF_SJ_SUPPLIER.SUPPLIER_TYPE
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public String getSupplierType() {
        return supplierType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_SJ_SUPPLIER.SUPPLIER_TYPE
     *
     * @param supplierType the value for SF_SJ_SUPPLIER.SUPPLIER_TYPE
     *
     * @mbggenerated Tue Aug 09 23:01:07 CST 2016
     */
    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }
}