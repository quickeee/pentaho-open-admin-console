/*
 * Copyright 2006-2008 Pentaho Corporation.  All rights reserved. 
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
 * @created May 19, 2008
 * 
 */
package org.pentaho.pac.client.scheduler;

import org.pentaho.pac.client.PentahoAdminConsole;
import org.pentaho.pac.client.common.ui.TableListCtrl;
import org.pentaho.pac.client.i18n.PacLocalizedMessages;

public class SchedulesListCtrl extends TableListCtrl {

  private static final PacLocalizedMessages MSGS = PentahoAdminConsole.getLocalizedMessages();
  private static final String[] COLUMN_HEADER_TITLE = {
    MSGS.triggerGroupName(),
    MSGS.description(),
    MSGS.fireTimeLastNext(),
    MSGS.state()
  };
  
  public SchedulesListCtrl()
  {
    super( COLUMN_HEADER_TITLE );
    
    setHeight( "230px" ); //$NON-NLS-1$
    setTableStyleName( "schedulesTable" ); //$NON-NLS-1$
    setTableHeaderStyleName( "schedulesTableHeader" ); //$NON-NLS-1$
  }
}
