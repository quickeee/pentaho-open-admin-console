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

package org.pentaho.pac.client.services;

import org.pentaho.pac.client.PacServiceAsync;
import org.pentaho.pac.client.PacServiceFactory;
import org.pentaho.pac.client.PentahoAdminConsole;
import org.pentaho.pac.client.common.ui.MessageDialog;
import org.pentaho.pac.client.i18n.PacLocalizedMessages;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminServicesPanel extends VerticalPanel implements ClickListener {

  private static final PacLocalizedMessages MSGS = PentahoAdminConsole.getLocalizedMessages();
  Button refreshSolutionRepositoryBtn = new Button(MSGS.refreshSolutionRepository());
  Button cleanRepositoryBtn = new Button(MSGS.removeStaleContent());
  Button clearMondrianDataCacheBtn = new Button(MSGS.purgeMondrianDataCache());
  Button clearMondrianSchemaCacheBtn = new Button(MSGS.purgeMondrianSchemaCache());
  Button scheduleRepositoryCleaningBtn = new Button(MSGS.scheduleDailyRepositoryCleaning());
  Button resetRepositoryBtn = new Button(MSGS.restoreDefaultFilePermissions());
  Button refreshSystemSettingsBtn = new Button(MSGS.refreshSystemSettings());
  Button executeGlobalActionsBtn = new Button(MSGS.executeGlobalActions());
  Button refreshReportingMetadataBtn = new Button(MSGS.refreshReportingMetadata());
  
  public AdminServicesPanel() {
    Grid grid = new Grid(5, 2);
    grid.setWidth("100%"); //$NON-NLS-1$
    
    grid.setWidget(0, 0, refreshSolutionRepositoryBtn);
    grid.setWidget(0, 1, cleanRepositoryBtn);
    grid.setWidget(1, 0, clearMondrianDataCacheBtn);
    grid.setWidget(1, 1, clearMondrianSchemaCacheBtn);
    grid.setWidget(2, 0, scheduleRepositoryCleaningBtn);
    grid.setWidget(2, 1, resetRepositoryBtn);
    grid.setWidget(3, 0, refreshSystemSettingsBtn);
    grid.setWidget(3, 1, executeGlobalActionsBtn);
    grid.setWidget(4, 0, refreshReportingMetadataBtn);
    
    refreshSolutionRepositoryBtn.setWidth("100%"); //$NON-NLS-1$
    cleanRepositoryBtn.setWidth("100%"); //$NON-NLS-1$
    clearMondrianDataCacheBtn.setWidth("100%"); //$NON-NLS-1$
    clearMondrianSchemaCacheBtn.setWidth("100%"); //$NON-NLS-1$
    scheduleRepositoryCleaningBtn.setWidth("100%"); //$NON-NLS-1$
    resetRepositoryBtn.setWidth("100%"); //$NON-NLS-1$
    refreshSystemSettingsBtn.setWidth("100%"); //$NON-NLS-1$
    executeGlobalActionsBtn.setWidth("100%"); //$NON-NLS-1$
    refreshReportingMetadataBtn.setWidth("100%"); //$NON-NLS-1$
    
    refreshSolutionRepositoryBtn.addClickListener(this);
    cleanRepositoryBtn.addClickListener(this);
    clearMondrianDataCacheBtn.addClickListener(this);
    clearMondrianSchemaCacheBtn.addClickListener(this);
    scheduleRepositoryCleaningBtn.addClickListener(this);
    resetRepositoryBtn.addClickListener(this);
    refreshSystemSettingsBtn.addClickListener(this);
    executeGlobalActionsBtn.addClickListener(this);
    refreshReportingMetadataBtn.addClickListener(this);
    
    add(grid);
  }

  public void onClick(final Widget sender) {
    AsyncCallback callback = new AsyncCallback() {
      
      public void onSuccess(Object result) {
        MessageDialog messageDialog = new MessageDialog(MSGS.services(), 
            result.toString() );
        messageDialog.center();
      }

      public void onFailure(Throwable caught) {
        MessageDialog messageDialog = new MessageDialog(MSGS.error(), 
            caught.getMessage() );
        messageDialog.center();
      }
    }; // end AsyncCallback

    PacServiceAsync pacServiceAsync = PacServiceFactory.getPacService();
    
    ((Button)sender).setEnabled(false);
    if (sender == refreshSolutionRepositoryBtn) {
      pacServiceAsync.refreshSolutionRepository(callback);
    } else if (sender == cleanRepositoryBtn) {
      pacServiceAsync.cleanRepository(callback);
    } else if (sender == clearMondrianDataCacheBtn) {
      pacServiceAsync.clearMondrianDataCache(callback);
    } else if (sender == clearMondrianSchemaCacheBtn) {
      pacServiceAsync.clearMondrianSchemaCache(callback);
    } else if (sender == scheduleRepositoryCleaningBtn) {
      pacServiceAsync.scheduleRepositoryCleaning(callback);
    } else if (sender == resetRepositoryBtn) {
      pacServiceAsync.resetRepository(callback);
    } else if (sender == refreshSystemSettingsBtn) {
      pacServiceAsync.refreshSystemSettings(callback);
    } else if (sender == executeGlobalActionsBtn) {
      pacServiceAsync.executeGlobalActions(callback);
    } else if (sender == refreshReportingMetadataBtn) {
      pacServiceAsync.refreshReportingMetadata(callback);
    }
  }
  
}
