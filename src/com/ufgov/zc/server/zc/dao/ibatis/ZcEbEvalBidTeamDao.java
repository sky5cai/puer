package com.ufgov.zc.server.zc.dao.ibatis;import java.util.HashMap;import java.util.List;import java.util.Map;import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;import com.ufgov.zc.common.commonbiz.model.Position;import com.ufgov.zc.common.console.model.AsEmp;import com.ufgov.zc.common.console.model.AsUserGroup;import com.ufgov.zc.common.system.RequestMeta;import com.ufgov.zc.common.system.constants.NumLimConstants;import com.ufgov.zc.common.system.dto.ElementConditionDto;import com.ufgov.zc.common.system.model.User;import com.ufgov.zc.common.zc.model.EmExpertEvaluation;import com.ufgov.zc.common.zc.model.EmExpertTypeJoin;import com.ufgov.zc.common.zc.model.ExpertPo;import com.ufgov.zc.common.zc.model.ZcEbEvalBidTeam;import com.ufgov.zc.common.zc.model.ZcEbEvalBidTeamMember;import com.ufgov.zc.server.system.util.NumLimUtil;import com.ufgov.zc.server.zc.dao.IZcEbEvalBidTeamDao;public class ZcEbEvalBidTeamDao extends SqlMapClientDaoSupport implements IZcEbEvalBidTeamDao {  public ZcEbEvalBidTeamDao() {    super();  }  public void updateEvalBidTeam(final ZcEbEvalBidTeam record) {    getSqlMapClientTemplate().update("ZcEbEvalBidTeam.updateEvalBidTeam", record);  }  public void updateEvalBidTeamMemberEvalProgress(final ZcEbEvalBidTeamMember record) {    getSqlMapClientTemplate().update("ZcEbEvalBidTeam.updateEvalBidTeamMemberEvalProgress", record);  }  public List getEvalBidTeam(ElementConditionDto dto, RequestMeta meta) {    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));    return getSqlMapClientTemplate().queryForList("ZcEbEvalBidTeam.getEvalBidTeam", dto);  }  public List getEvalBidTeamMembers(ZcEbEvalBidTeam team) {    // TCJLODO Auto-generated method stub    return getSqlMapClientTemplate().queryForList("ZcEbEvalBidTeam.getEvalBidTeamMember", team);  }  /**   * 根据项目分包Code删除所有此分包下的所有专家信息(省厅版)   * @param record   */  public int deleteEvalBidTeamMemersSt(String projCode, String packCode) {    Map map = new HashMap();    map.put("projCode", projCode);    map.put("packCode", packCode);    int rows = getSqlMapClientTemplate().delete("ZcEbEvalBidTeam.deleteByProjPackCode", map);    return rows;  }  /**   * 插入评标专家管理信息(省厅版)   * @param record   */  public void insertEvalBidTeamMemersSt(ZcEbEvalBidTeamMember record) {    getSqlMapClientTemplate().insert("ZcEbEvalBidTeam.insertMemberToZcEbPackExpert", record);  }  /**   *插入as_user表中的信息(省厅版)   * @param record   */  public void insertEvalBidTeamMemberToUser(User record) {    getSqlMapClientTemplate().insert("User.insertUser", record);  }  /**   *察看user表中是否存在此专家(省厅版)   * @param idCard：身份证号码作为userid   */  public boolean ifUserExistsEvalBidTeamMember(String idCard) {    boolean flag = false;    if (getSqlMapClientTemplate().queryForObject("User.getAsEmpByUserId", idCard) != null) {      flag = true;    }    return flag;  }  public String getUserIdByUserCode(String useID) {    /**     * 存在问题：6.0平台的User_code应该唯一，现在的情况是不唯一，主键是user_id     */    return (String) getSqlMapClientTemplate().queryForObject("SysUserManage.selectUserIdByUserCode", useID);  }  /**   * 插入as_emp表中的信息(省厅版)   * @param record   */  public void insertEvalBidTeamMemberToEmp(AsEmp record) {    getSqlMapClientTemplate().insert("User.insertAsEmpLogin", record);  }  /**   * 插入as_emp_position表中的信息(省厅版)   * @param record   */  public void insertEvalBidTeamMemberToPosition(Position record) {    getSqlMapClientTemplate().insert("User.insertAsEmpPosition", record);  }  /**   * 插入as_user_group表中的信息(省厅版)   * @param record   */  public void insertEvalBidTeamMemberToGroup(AsUserGroup record) {    getSqlMapClientTemplate().insert("User.insertAsUserGroup", record);  }  public int deleteExpertEvaluateList(Map map) {    return getSqlMapClientTemplate().delete("EmExpertEvaluation.deleteExpertEvaluationList", map);  }  public void insertExpertEvaluate(EmExpertEvaluation bean) {    getSqlMapClientTemplate().insert("EmExpertEvaluation.insertEmExpertEvaluation", bean);  }  public boolean checkExpertExists(ZcEbEvalBidTeamMember member) {    boolean flag = false;    String userId = member.getExpertIdCard();    if (getSqlMapClientTemplate().queryForObject("EmExpert.getEmExperBySpellId", userId) != null) {      flag = true;    }    return flag;  }  public boolean checkUserCodeExists(ZcEbEvalBidTeamMember member) {    boolean flag = false;    String userCode = member.getExpertIdCard();    if (getSqlMapClientTemplate().queryForObject("EmExpert.selectUserIdByUserCode", userCode) != null) {      flag = true;    }    return flag;  }  public void insertInToEmExpert(ZcEbEvalBidTeamMember member) {    ExpertPo bean = new ExpertPo();    bean.setEm_expert_code(member.getExpertCode());    bean.setEm_expert_name(member.getExpertName());    bean.setEm_expert_namespell(member.getExpertIdCard());    bean.setEm_expert_sex(member.getSex());    bean.setEm_mobile(member.getPhone());    bean.setEm_email(member.getEmail());    bean.setEm_unit_name(member.getDepartment());    bean.setEm_expert_protitle(member.getDuty());    getSqlMapClientTemplate().insert("EmExpert.insertExpert", bean);    if (null != member.getExpertTypeCode() && "".equals(member.getExpertTypeCode())) {      EmExpertTypeJoin typeJoin = new EmExpertTypeJoin();      typeJoin.setEmExpertCode(member.getExpertCode());      typeJoin.setEmTypeCode(member.getExpertTypeCode());      getSqlMapClientTemplate().insert("EmExpert.insert", typeJoin);    }  }    public AsEmp getAsEmpByUserId(String useID) {    return (AsEmp) getSqlMapClientTemplate().queryForObject("User.getAsEmpByUserId", useID);  }}