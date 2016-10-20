package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class ZcFaCardSub extends ZcBaseBill{
    /**
   * 
   */
  private static final long serialVersionUID = 8402050371319698741L;

  private String tempId;
  
  private BigDecimal faSubNum;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZC_FA_CARD_SUB.CARD_SUB_CODE
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    private String cardSubCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZC_FA_CARD_SUB.CARD_ID
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    private String cardId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZC_FA_CARD_SUB.FA_SUB_NAME
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    private String faSubName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZC_FA_CARD_SUB.FA_SUB_NUM
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZC_FA_CARD_SUB.FA_SUB_PRICE
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    private BigDecimal faSubPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZC_FA_CARD_SUB.FA_SUB_SPEC
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    private String faSubSpec;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZC_FA_CARD_SUB.FA_SUB_PURP
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    private String faSubPurp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZC_FA_CARD_SUB.FA_SUB_DATE
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    private Date faSubDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZC_FA_CARD_SUB.FA_SUB_CODE
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    private String faSubCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZC_FA_CARD_SUB.CARD_SUB_CODE
     *
     * @return the value of ZC_FA_CARD_SUB.CARD_SUB_CODE
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public String getCardSubCode() {
        return cardSubCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZC_FA_CARD_SUB.CARD_SUB_CODE
     *
     * @param cardSubCode the value for ZC_FA_CARD_SUB.CARD_SUB_CODE
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public void setCardSubCode(String cardSubCode) {
        this.cardSubCode = cardSubCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZC_FA_CARD_SUB.CARD_ID
     *
     * @return the value of ZC_FA_CARD_SUB.CARD_ID
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZC_FA_CARD_SUB.CARD_ID
     *
     * @param cardId the value for ZC_FA_CARD_SUB.CARD_ID
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZC_FA_CARD_SUB.FA_SUB_NAME
     *
     * @return the value of ZC_FA_CARD_SUB.FA_SUB_NAME
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public String getFaSubName() {
        return faSubName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZC_FA_CARD_SUB.FA_SUB_NAME
     *
     * @param faSubName the value for ZC_FA_CARD_SUB.FA_SUB_NAME
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public void setFaSubName(String faSubName) {
        this.faSubName = faSubName;
    }



    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZC_FA_CARD_SUB.FA_SUB_PRICE
     *
     * @return the value of ZC_FA_CARD_SUB.FA_SUB_PRICE
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public BigDecimal getFaSubPrice() {
        return faSubPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZC_FA_CARD_SUB.FA_SUB_PRICE
     *
     * @param faSubPrice the value for ZC_FA_CARD_SUB.FA_SUB_PRICE
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public void setFaSubPrice(BigDecimal faSubPrice) {
        this.faSubPrice = faSubPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZC_FA_CARD_SUB.FA_SUB_SPEC
     *
     * @return the value of ZC_FA_CARD_SUB.FA_SUB_SPEC
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public String getFaSubSpec() {
        return faSubSpec;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZC_FA_CARD_SUB.FA_SUB_SPEC
     *
     * @param faSubSpec the value for ZC_FA_CARD_SUB.FA_SUB_SPEC
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public void setFaSubSpec(String faSubSpec) {
        this.faSubSpec = faSubSpec;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZC_FA_CARD_SUB.FA_SUB_PURP
     *
     * @return the value of ZC_FA_CARD_SUB.FA_SUB_PURP
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public String getFaSubPurp() {
        return faSubPurp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZC_FA_CARD_SUB.FA_SUB_PURP
     *
     * @param faSubPurp the value for ZC_FA_CARD_SUB.FA_SUB_PURP
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public void setFaSubPurp(String faSubPurp) {
        this.faSubPurp = faSubPurp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZC_FA_CARD_SUB.FA_SUB_DATE
     *
     * @return the value of ZC_FA_CARD_SUB.FA_SUB_DATE
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public Date getFaSubDate() {
        return faSubDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZC_FA_CARD_SUB.FA_SUB_DATE
     *
     * @param faSubDate the value for ZC_FA_CARD_SUB.FA_SUB_DATE
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public void setFaSubDate(Date faSubDate) {
        this.faSubDate = faSubDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZC_FA_CARD_SUB.FA_SUB_CODE
     *
     * @return the value of ZC_FA_CARD_SUB.FA_SUB_CODE
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public String getFaSubCode() {
        return faSubCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZC_FA_CARD_SUB.FA_SUB_CODE
     *
     * @param faSubCode the value for ZC_FA_CARD_SUB.FA_SUB_CODE
     *
     * @mbggenerated Wed Mar 05 09:34:34 CST 2014
     */
    public void setFaSubCode(String faSubCode) {
        this.faSubCode = faSubCode;
    }

    public String getTempId() {
      return tempId;
    }

    public void setTempId(String tempId) {
      this.tempId = tempId;
    }

    public BigDecimal getFaSubNum() {
      return faSubNum;
    }

    public void setFaSubNum(BigDecimal faSubNum) {
      this.faSubNum = faSubNum;
    }
}