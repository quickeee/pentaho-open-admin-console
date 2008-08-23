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
package org.pentaho.pac.client.scheduler.ctlr;

import java.util.List;

import org.pentaho.pac.client.PentahoAdminConsole;
import org.pentaho.pac.client.i18n.PacLocalizedMessages;
import org.pentaho.pac.client.scheduler.view.SolutionRepositoryActionSequenceListEditor;

public class SolutionRepositoryActionSequenceListEditorValidator implements IUiValidator {

  private static final PacLocalizedMessages MSGS = PentahoAdminConsole.getLocalizedMessages();
  
  private SolutionRepositoryActionSequenceListEditor solRepActionSequenceListEditor = null;
  private boolean isSubscriptionSched;
  
  public SolutionRepositoryActionSequenceListEditorValidator(SolutionRepositoryActionSequenceListEditor solRepActionSequenceListEditor,
      boolean isSubscriptionSched ) {
    this.solRepActionSequenceListEditor = solRepActionSequenceListEditor;
    this.isSubscriptionSched = isSubscriptionSched;
  }

  public boolean isValid() {
    boolean isValid = true;
    
    List<String> actionList = solRepActionSequenceListEditor.getActionsAsList();
    if ( !isSubscriptionSched && actionList.size() > 1 ) {
      isValid = false;
      solRepActionSequenceListEditor.setActionsError( MSGS.onlyOneActionSequence() );
    } else if ( actionList.size() <= 0 ) {
      isValid = false;
      solRepActionSequenceListEditor.setActionsError( MSGS.actionSequenceCannotBeEmpty() );
//    } else if () {
//      TODO sbarkdull
//      validate that each action sequence string has 2 "/" and ends in ".xaction"
    }
    
    return isValid;
  }

  public void clear() {
    solRepActionSequenceListEditor.setActionsError( null );
  }
}
