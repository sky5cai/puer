package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfJdResultFile extends ZcBaseBill {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8907977513676448957L;
	

	  public static final String SEQ_SF_JD_RESULT_FILE_ID = "SEQ_SF_JD_RESULT_FILE_ID";
	
	private SfJdRecordFileModel model=new SfJdRecordFileModel();
	
	private String name;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_RESULT_FILE.SF_JD_RESULT_FILE_ID
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    private BigDecimal sfJdResultFileId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_RESULT_FILE.MODEL_ID
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    private BigDecimal modelId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_RESULT_FILE.JD_RESULT_ID
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    private BigDecimal jdResultId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_RESULT_FILE.FILE_ID
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    private String fileId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_RESULT_FILE.FILE_NAME
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    private String fileName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_RESULT_FILE.SF_JD_RESULT_FILE_ID
     *
     * @return the value of SF_JD_RESULT_FILE.SF_JD_RESULT_FILE_ID
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    public BigDecimal getSfJdResultFileId() {
        return sfJdResultFileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_RESULT_FILE.SF_JD_RESULT_FILE_ID
     *
     * @param sfJdResultFileId the value for SF_JD_RESULT_FILE.SF_JD_RESULT_FILE_ID
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    public void setSfJdResultFileId(BigDecimal sfJdResultFileId) {
        this.sfJdResultFileId = sfJdResultFileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_RESULT_FILE.MODEL_ID
     *
     * @return the value of SF_JD_RESULT_FILE.MODEL_ID
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    public BigDecimal getModelId() {
        return getModel().getModelId();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_RESULT_FILE.MODEL_ID
     *
     * @param modelId the value for SF_JD_RESULT_FILE.MODEL_ID
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    public void setModelId(BigDecimal modelId) {
        this.getModel().setModelId(modelId);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_RESULT_FILE.JD_RESULT_ID
     *
     * @return the value of SF_JD_RESULT_FILE.JD_RESULT_ID
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    public BigDecimal getJdResultId() {
        return jdResultId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_RESULT_FILE.JD_RESULT_ID
     *
     * @param jdResultId the value for SF_JD_RESULT_FILE.JD_RESULT_ID
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    public void setJdResultId(BigDecimal jdResultId) {
        this.jdResultId = jdResultId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_RESULT_FILE.FILE_ID
     *
     * @return the value of SF_JD_RESULT_FILE.FILE_ID
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_RESULT_FILE.FILE_ID
     *
     * @param fileId the value for SF_JD_RESULT_FILE.FILE_ID
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_RESULT_FILE.FILE_NAME
     *
     * @return the value of SF_JD_RESULT_FILE.FILE_NAME
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_RESULT_FILE.FILE_NAME
     *
     * @param fileName the value for SF_JD_RESULT_FILE.FILE_NAME
     *
     * @mbggenerated Mon Jul 18 03:06:09 CST 2016
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
        if(getModel()!=null)getModel().setFileName(fileName);
    }

	public SfJdRecordFileModel getModel() {
		return model;
	}

	public void setModel(SfJdRecordFileModel model) {
		this.model = model;
		setFileName(model==null?null:model.getFileName());
		setName(model==null?null:model.getName());
		setModelId(model==null?null:model.getModelId());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}