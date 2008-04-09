package org.pentaho.pac.common;

import java.io.Serializable;


public class PacServiceException extends CheckedException {
  
  /**
   * 
   */
  private static final long serialVersionUID = 691L;

  public PacServiceException(String msg) {
    super(msg);
  }
  
  public PacServiceException(Throwable cause) {
    super(cause);
  }
  
  public PacServiceException(String msg, Throwable cause) {
    super(msg, cause);
  }
  
  public PacServiceException() {
    super();
  }
}