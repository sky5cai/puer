package com.ufgov.zc.common.commonbiz.fieldmap;import java.util.HashMap;import java.util.Map;import com.ufgov.zc.common.commonbiz.model.BaseBill;import com.ufgov.zc.common.sf.model.SfAgreement;import com.ufgov.zc.common.sf.model.SfAppendMaterialNotice;import com.ufgov.zc.common.sf.model.SfCharge;import com.ufgov.zc.common.sf.model.SfChargeDetail;import com.ufgov.zc.common.sf.model.SfDossier;import com.ufgov.zc.common.sf.model.SfEntrust;import com.ufgov.zc.common.sf.model.SfEvaluation;import com.ufgov.zc.common.sf.model.SfJdDocAudit;import com.ufgov.zc.common.sf.model.SfJdDocAuditDetail;import com.ufgov.zc.common.sf.model.SfJdPerson;import com.ufgov.zc.common.sf.model.SfJdPersonMajor;import com.ufgov.zc.common.sf.model.SfJdReport;import com.ufgov.zc.common.sf.model.SfJdResult;import com.ufgov.zc.common.sf.model.SfJdResultFile;import com.ufgov.zc.common.sf.model.SfMaterialsTransfer;import com.ufgov.zc.common.sf.model.SfOutInfo;import com.ufgov.zc.common.sf.model.SfOutInfoDetail;import com.ufgov.zc.common.sf.model.SfOutInfoValidateDetail;import com.ufgov.zc.common.sf.model.SfReceipt;import com.ufgov.zc.common.sf.model.SfSj;public class FieldMapRegister {  private static Map register = new HashMap();  static {    register.put(BaseBill.class.getName(), BaseBillFM.fieldMap);  /*  register.put(DpDetail.class.getName(), DpDetailFM.fieldMap);    register.put(CpVoucher.class.getName(), CpVoucherFM.fieldMap);    register.put(CpApply.class.getName(), CpApplyFM.fieldMap);    register.put(CpPlanAgentBill.class.getName(), CpPlanAgentBillFM.fieldMap);    register.put(CpPlanAgentList.class.getName(), CpPlanAgentListFM.fieldMap);    register.put(CpPlanClearBill.class.getName(), CpPlanClearBillFM.fieldMap);    register.put(CpPlanClearList.class.getName(), CpPlanClearListFM.fieldMap);    register.put(CpPlanAgentDbill.class.getName(), CpPlanAgentDBillFM.fieldMap);    register.put(CpPayClearBill.class.getName(), CpPayClearBillFM.fieldMap);    register.put(CpPayClearList.class.getName(), CpPayClearListFM.fieldMap);    register.put(AmVoucher.class.getName(), AmVoucherFM.fieldMap);    register.put(AmApply.class.getName(), AmApplyFM.fieldMap);    register.put(CpPayBalBill.class.getName(), CpPayBalBillFM.fieldMap);    register.put(CpPayBalList.class.getName(), CpPayBalListFM.fieldMap);    register.put(BiCdTrack.class.getName(), BiCdTrackFM.fieldMap);    register.put(BiCdMoneyCheck.class.getName(), BiCdMoneyCheckFM.fieldMap);    register.put(DpTempBiReport.class.getName(), DpTempBiReportFM.fieldMap);    register.put(CpPayDayBill.class.getName(), CpPayDayBillFM.fieldMap);    register.put(CpPayDayList.class.getName(), CpPayDayListFM.fieldMap);    register.put(CpPayTransferVou.class.getName(), CpPayTransferVouFM.fieldMap);    register.put(CpPayTransferList.class.getName(), CpPayTransferListFM.fieldMap);    register.put(DpApplyTableAsMonth.class.getName(), DpApplyTableAsMonthFM.fieldMap);    register.put(BiAdjustTrack.class.getName(), BiAdjustTrackFM.fieldMap);    register.put(BankAccount.class.getName(), BankAccountFM.fieldMap);    register.put(JjPlan.class.getName(), JjPlanFM.fieldMap);    register.put(JjPlanDetail.class.getName(), JjPlanDetailFM.fieldMap);    register.put(Project.class.getName(), ProjectFM.fieldMap);    register.put(BiTrack.class.getName(), BiTrackFM.fieldMap);*/        register.put(SfReceipt.class.getName(), SfReceiptFM.fieldMap);    register.put(SfAppendMaterialNotice.class.getName(), SfAppendMaterialNoticeFM.fieldMap);    register.put(SfOutInfo.class.getName(), SfOutInfoFM.fieldMap);    register.put(SfOutInfoDetail.class.getName(), SfOutInfoDetailFM.fieldMap);    register.put(SfOutInfoValidateDetail.class.getName(), SfOutInfoValidateDetailFM.fieldMap);    register.put(SfMaterialsTransfer.class.getName(), SfMaterialsTransferFM.fieldMap);    register.put(SfJdResult.class.getName(), SfJdResultFM.fieldMap);    register.put(SfJdResultFile.class.getName(), SfJdResultFileFM.fieldMap);    register.put(SfJdDocAudit.class.getName(), SfJdDocAuditFM.fieldMap);    register.put(SfJdDocAuditDetail.class.getName(), SfJdDocAuditDetailFM.fieldMap);    register.put(SfDossier.class.getName(), SfDossierFM.fieldMap);    register.put(SfJdPerson.class.getName(), SfJdPersonFM.fieldMap);    register.put(SfJdPersonMajor.class.getName(), SfJdPersonMajorFM.fieldMap);    register.put(SfEntrust.class.getName(), SfEntrustFM.fieldMap);    register.put(SfEvaluation.class.getName(), SfEvaluationFM.fieldMap);    register.put(SfAgreement.class.getName(), SfAgreementFM.fieldMap);    register.put(SfCharge.class.getName(), SfChargeFM.fieldMap);    register.put(SfChargeDetail.class.getName(), SfChargeDetailFM.fieldMap);    register.put(SfJdReport.class.getName(), SfJdReportFM.fieldMap);    register.put(SfSj.class.getName(), SfSjFM.fieldMap);      }  public static Map get(Class clazz) {    Map fieldMap = (Map) register.get(clazz.getName());    /*    if (fieldMap == null && BaseBill.class.isAssignableFrom(clazz)) {          fieldMap = (Map) register.get(BaseBill.class.getName());        }else if(fieldMap == null && ZcBaseBill.class.isAssignableFrom(clazz)){          fieldMap = (Map) register.get(BaseBill.class.getName());        }*/    if (fieldMap == null) {      throw new RuntimeException("没有找到" + clazz.getName() + "对应的FieldMap,在FieldMapRegister没有注册.");    }    return fieldMap;  }}