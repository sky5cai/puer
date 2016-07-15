package com.ufgov.zc.server.system.service.impl;import java.math.BigDecimal;import java.util.Map;import com.ufgov.zc.common.commonbiz.model.MaExpSerial;import com.ufgov.zc.server.system.dao.IMaExpSerialDao;import com.ufgov.zc.server.system.service.IMaExpSerialService;public class MaExpSerialService implements IMaExpSerialService {  private IMaExpSerialDao serialDao;  public IMaExpSerialDao getSerialDao() {    return serialDao;  }  public void setSerialDao(IMaExpSerialDao serialDao) {    this.serialDao = serialDao;  }  public MaExpSerialService() {    super();  }  public MaExpSerial getMaExpSerial(Map params) {    // TCJLODO Auto-generated method stub    MaExpSerial result = serialDao.getMaExpSerial(params);    MaExpSerial temp = new MaExpSerial();    if (result != null) {      temp.setExpDate(result.getExpDate());      temp.setExpType(result.getExpType());      temp.setNd(result.getNd());      BigDecimal serial = new BigDecimal(1);      serial = serial.add(result.getSerial());      temp.setSerial(serial);      updateMaExpSerial(temp);      return result;    } else {      String expDate = (String) params.get(MaExpSerial.EXP_DATE);      String expType = (String) params.get(MaExpSerial.EXP_TYPE);      BigDecimal nd = (BigDecimal) params.get(MaExpSerial.EXP_ND);      temp.setExpDate(expDate);      temp.setExpType(expType);      temp.setNd(nd);      temp.setSerial(new BigDecimal(2));      insertMaExpSerial(temp);      temp.setSerial(new BigDecimal(1));      return temp;    }  }  public void insertMaExpSerial(MaExpSerial serial) {    // TCJLODO Auto-generated method stub    serialDao.insertMaExpSerial(serial);  }  public void updateMaExpSerial(MaExpSerial serial) {    // TCJLODO Auto-generated method stub    serialDao.updateMaExpSerial(serial);  }}