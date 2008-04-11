/*
 * Copyright 2005-2008 Pentaho Corporation.  All rights reserved. 
 * This software was developed by Pentaho Corporation and is provided under the terms 
 * of the Mozilla Public License, Version 1.1, or any later version. You may not use 
 * this file except in compliance with the license. If you need a copy of the license, 
 * please go to http://www.mozilla.org/MPL/MPL-1.1.txt. The Original Code is the Pentaho 
 * BI Platform.  The Initial Developer is Pentaho Corporation.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS" 
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to 
 * the license for the specific language governing your rights and limitations.
 *
 * Created  
 * @author Steven Barkdull
 */
package org.pentaho.pac.client.scheduler;

import java.io.Serializable;

/**
 * NOTE: I wanted this class to be a simple POJO, no getters/setters, just
 * public member data. In GWT 1.4, the compiler seems to choke when there are 
 * no setters/getters. When the getters/setters are added, GWT is happy. Based
 * on MD's experience, this requirement does NOT exist in GWT 1.5.
 * 
 * @author Steven Barkdull
 *
 */
public class Job implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 69L;
  
  public String triggerName;
  public String triggerGroup;
  public String triggerState;
  public String nextFireTime;
  public String prevFireTime;
  public String jobName;
  public String jobGroup;
  public String description;
  
  public String getTriggerName() {
    return triggerName;
  }
  public void setTriggerName(String triggerName) {
    this.triggerName = triggerName;
  }
  public String getTriggerGroup() {
    return triggerGroup;
  }
  public void setTriggerGroup(String triggerGroup) {
    this.triggerGroup = triggerGroup;
  }
  public String getTriggerState() {
    return triggerState;
  }
  public void setTriggerState(String triggerState) {
    this.triggerState = triggerState;
  }
  public String getNextFireTime() {
    return nextFireTime;
  }
  public void setNextFireTime(String nextFireTime) {
    this.nextFireTime = nextFireTime;
  }
  public String getPrevFireTime() {
    return prevFireTime;
  }
  public void setPrevFireTime(String prevFireTime) {
    this.prevFireTime = prevFireTime;
  }
  public String getJobName() {
    return jobName;
  }
  public void setJobName(String jobName) {
    this.jobName = jobName;
  }
  public String getJobGroup() {
    return jobGroup;
  }
  public void setJobGroup(String jobGroup) {
    this.jobGroup = jobGroup;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  
}