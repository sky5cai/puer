/** * * Copyright (C) 2009 UFGOV *  * 修订历史记录： *  * Revision	1.0	 2009-4-8  hpj_inter  创建。 *  */package com.ufgov.zc.server.console.service;import java.util.List;public interface IComponentService {  List getCompoList();  List getFunctionList(String compoId);}