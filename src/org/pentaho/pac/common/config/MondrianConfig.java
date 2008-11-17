package org.pentaho.pac.common.config;

import java.io.Serializable;

public class MondrianConfig implements IMondrianConfig, Serializable {

  boolean cacheHitCounters;
  boolean ignoreInvalidMembers;
  String logFileLocation;
  Integer queryLimit;
  Integer queryTimeout;
  Integer resultLimit;
  Integer traceLevel;
  
  public MondrianConfig() {
    
  }
  
  public MondrianConfig(IMondrianConfig mondrianConfig) {
    setCacheHitCounters(mondrianConfig.getCacheHitCounters());
    setIgnoreInvalidMembers(mondrianConfig.getIgnoreInvalidMembers());
    setLogFileLocation(mondrianConfig.getLogFileLocation());
    setQueryLimit(mondrianConfig.getQueryLimit());
    setQueryTimeout(mondrianConfig.getQueryTimeout());
    setResultLimit(mondrianConfig.getResultLimit());
    setTraceLevel(mondrianConfig.getTraceLevel());
  }
  
  public boolean getCacheHitCounters() {
    return cacheHitCounters;
  }
  public void setCacheHitCounters(boolean cacheHitCounters) {
    this.cacheHitCounters = cacheHitCounters;
  }
  public boolean getIgnoreInvalidMembers() {
    return ignoreInvalidMembers;
  }
  public void setIgnoreInvalidMembers(boolean ignoreInvalidMembers) {
    this.ignoreInvalidMembers = ignoreInvalidMembers;
  }
  public String getLogFileLocation() {
    return logFileLocation;
  }
  public void setLogFileLocation(String logFileLocation) {
    this.logFileLocation = logFileLocation;
  }
  public Integer getQueryLimit() {
    return queryLimit;
  }
  public void setQueryLimit(Integer queryLimit) {
    this.queryLimit = queryLimit;
  }
  public Integer getQueryTimeout() {
    return queryTimeout;
  }
  public void setQueryTimeout(Integer queryTimeout) {
    this.queryTimeout = queryTimeout;
  }
  public Integer getResultLimit() {
    return resultLimit;
  }
  public void setResultLimit(Integer resultLimit) {
    this.resultLimit = resultLimit;
  }
  public Integer getTraceLevel() {
    return traceLevel;
  }
  public void setTraceLevel(Integer traceLevel) {
    this.traceLevel = traceLevel;
  }
  
}
