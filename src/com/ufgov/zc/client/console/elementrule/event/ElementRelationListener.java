package com.ufgov.zc.client.console.elementrule.event;import java.util.EventListener;public interface ElementRelationListener extends EventListener {  public void addedSrcElementRelation(ElementRelationEvent event);  public void removedSrcElementRelation(ElementRelationEvent event);  public void addedDesElementRelation(ElementRelationEvent event);  public void removedDesElementRelation(ElementRelationEvent event);  public void modefiedSrcElementRelation(ElementRelationEvent event, Object oldValue);  public void modefiedDesElementRelation(ElementRelationEvent event, Object oldValue);}