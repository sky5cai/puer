package com.ufgov.zc.client.console.elementrule.event;import java.util.EventObject;public class ElementRelationEvent extends EventObject {  /**   *    */  private static final long serialVersionUID = -5744580038664501548L;  private final static String SRC = "src";  private final static String DES = "des";  private String direction;  public ElementRelationEvent(Object source) {    super(source);  }}