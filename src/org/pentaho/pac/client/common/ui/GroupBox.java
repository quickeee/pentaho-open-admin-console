package org.pentaho.pac.client.common.ui;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class GroupBox extends DockPanel{
  private Label title;
  private Grid grid;
  public static final int GREEN = 0;
  public static final int GREY = 1;
  
  public GroupBox(String title){
    this.setStylePrimaryName("GroupBox"); //$NON-NLS-1$
    
    this.title = new Label(title);
    this.title.setStylePrimaryName("groupBoxHeader"); //$NON-NLS-1$
    
    grid = new Grid(3, 3);
    grid.setWidth("100%"); //$NON-NLS-1$
    grid.setHeight("100%"); //$NON-NLS-1$
    grid.setCellPadding(0);
    grid.setCellSpacing(0);
    
    grid.setWidget(0,1,this.title);
    
    grid.getCellFormatter().setStyleName(0, 0, "groupbox-nw"); //$NON-NLS-1$ 
    grid.getCellFormatter().setStyleName(0, 1, "groupbox-n"); //$NON-NLS-1$
    grid.getCellFormatter().setStyleName(0, 2, "groupbox-ne"); //$NON-NLS-1$ 
    grid.getCellFormatter().setStyleName(1, 0, "groupbox-w"); //$NON-NLS-1$
    grid.getCellFormatter().setStyleName(1, 1, "groupbox-c"); //$NON-NLS-1$
    grid.getCellFormatter().setStyleName(1, 2, "groupbox-e"); //$NON-NLS-1$
    grid.getCellFormatter().setStyleName(2, 0, "groupbox-sw"); //$NON-NLS-1$
    grid.getCellFormatter().setStyleName(2, 1, "groupbox-s"); //$NON-NLS-1$
    grid.getCellFormatter().setStyleName(2, 2, "groupbox-se"); //$NON-NLS-1$
    
    this.add(grid, DockPanel.CENTER);

  }
  
  public GroupBox(String title, int style){
    this(title);
    
    String color = (style == GroupBox.GREEN) ? "" : "-grey";     //$NON-NLS-1$  //$NON-NLS-2$ 
    
    grid.getCellFormatter().setStyleName(0, 0, "groupbox-nw"+color); //$NON-NLS-1$ 
    grid.getCellFormatter().setStyleName(0, 1, "groupbox-n"+color); //$NON-NLS-1$
    grid.getCellFormatter().setStyleName(0, 2, "groupbox-ne"+color); //$NON-NLS-1$ 
    

  }
  
  public GroupBox(String title, Widget widget){
    this(title);
    this.setContent(widget);
  }
  
  public void setContent(Widget widget){
    grid.setWidget(1,1, widget);
  }
  
  public void setHorizontalAlignment(HorizontalAlignmentConstant alignment) {
    grid.getCellFormatter().setHorizontalAlignment(1, 1, alignment);
  }
  
  public void setVerticalAlignment(VerticalAlignmentConstant alignment) {
    grid.getCellFormatter().setVerticalAlignment(1, 1, alignment);
  }
}

  