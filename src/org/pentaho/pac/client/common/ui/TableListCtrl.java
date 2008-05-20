package org.pentaho.pac.client.common.ui;

import java.util.ArrayList;
import java.util.List;

import org.pentaho.pac.client.PentahoAdminConsole;
import org.pentaho.pac.client.i18n.PacLocalizedMessages;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class TableListCtrl extends ScrollPanel {

  private FlexTable table = null;
  private static final int HEADER_ROW = 0;
  private static final int FIRST_ROW = HEADER_ROW+1;
  private static final int SELECT_COLUMN = 0;
  private static final int FIRST_COLUMN = SELECT_COLUMN+1;
  private static final String BLANK = "&nbsp;"; //$NON-NLS-1$
  private static final String DEFAULT_HEIGHT = "230px"; //$NON-NLS-1$
  private static final PacLocalizedMessages MSGS = PentahoAdminConsole.getLocalizedMessages();
  
  public TableListCtrl( String[] columnHeaderNames )
  {
    this( textToLabel( columnHeaderNames ) );
  }
  
  public TableListCtrl( Widget[] columnHeaderWidgets )
  {
    super();

    setHeight( DEFAULT_HEIGHT );
    table = createTable( columnHeaderWidgets );
    add( table );
  }

  private static Widget[] textToLabel(  String[] columnHeaderNames  ) {
    int len = columnHeaderNames.length;
    Widget[] widgets = new Widget[ columnHeaderNames.length ];
    for ( int ii = 0; ii<len; ++ii ) {
      widgets[ ii ] = new Label( columnHeaderNames[ii] );
    }
    return widgets;
  }
  
  public void setTableStyleName( String styleName ) {
    table.setStyleName( styleName );
  }
  
  public void setTableHeaderStyleName( String styleName ) {
    table.getRowFormatter().setStyleName( 0, styleName );
  }
  
  private FlexTable createTable( Widget[] columnHeaderWidgets ) {
    
    FlexTable tmpTable = new FlexTable();
   
    tmpTable.setCellPadding( 0 );
    tmpTable.setCellSpacing( 0 );
    addTableHeader( tmpTable, columnHeaderWidgets );
    
    return tmpTable;
  }
  
  private void addTableHeader( FlexTable tmpTable, Widget[] columnHeaderWidgets )
  {
    Label l = new Label();
    l.getElement().setInnerHTML( "<span>&nbsp;</span>" ); //$NON-NLS-1$
    l.setTitle( "check to select row" );
    tmpTable.setWidget( HEADER_ROW, SELECT_COLUMN, l );
    for ( int ii=0; ii<columnHeaderWidgets.length; ++ii ) {
      tmpTable.setWidget( HEADER_ROW, ii+FIRST_COLUMN, columnHeaderWidgets[ii] );
    }
  }
  
  public List<Integer> getSelectedIndexes()
  {
    List<Integer> idxs = new ArrayList<Integer>();

    for ( int rowNum=FIRST_ROW; rowNum<table.getRowCount(); ++rowNum ) {
      CheckBox cb = (CheckBox)table.getWidget( rowNum, SELECT_COLUMN );
      if ( cb.isChecked() ) {
        idxs.add( new Integer( rowNum-FIRST_ROW ) );
      }
    }
    
    return idxs;
  }
  
  /**
   * Removes all non-header items from the list
   */
  public void removeAll() {
    // don't delete row 0, it's the header
    for ( int rowNum=table.getRowCount()-1; rowNum>=FIRST_ROW; --rowNum ) {
      table.removeRow( rowNum );
    }
  }
  
  public void remove( int rowNum ) {
    table.removeRow( rowNum+FIRST_ROW );
  }
  
  public void selectAll() {
    for ( int rowNum=FIRST_ROW; rowNum<table.getRowCount(); ++rowNum ) {
      CheckBox cb = getSelectCheckBox( rowNum );
      cb.setChecked( true );
    }
  }
  
  public void unselectAll() {
    for ( int rowNum=FIRST_ROW; rowNum<table.getRowCount(); ++rowNum ) {
      CheckBox cb = getSelectCheckBox( rowNum );
      cb.setChecked( false );
    }
  }
  
  public void select( int rowNum ) {
    CheckBox cb = getSelectCheckBox( rowNum );
    cb.setChecked( true );
  }
  
  public void unselect( int rowNum ) {
    CheckBox cb = getSelectCheckBox( rowNum );
    cb.setChecked( false );
  }

  private CheckBox getSelectCheckBox( int rowNum ) {
    return (CheckBox)table.getWidget( rowNum+FIRST_ROW, SELECT_COLUMN );
  }
  
  public void addRow( Widget[] widgets ) {
    int newRowNum = table.getRowCount();
    table.setWidget( newRowNum, 0, new CheckBox() );
    for ( int ii=0; ii<widgets.length; ++ii ) {
      setCellWidget( newRowNum-FIRST_ROW, ii+1, widgets[ii] );
    }
  }

  public void setCellWidget( int rowNum, int colNum, Widget w ) {
    table.setWidget( rowNum+FIRST_ROW, colNum+SELECT_COLUMN, w );
  }

  public Widget getCellWidget( int rowNum, int colNum ) {
    return table.getWidget( rowNum+FIRST_ROW, colNum+SELECT_COLUMN );
  }

  public void setCellData( int rowNum, int colNum, Widget w ) {
    table.setWidget( rowNum+FIRST_ROW, colNum+SELECT_COLUMN, w );
  }
  
  public int getNumRows() {
    return table.getRowCount() - FIRST_ROW;
  }
  
  public int getNumColumns() {
    return table.getCellCount(FIRST_ROW) - SELECT_COLUMN;
  }
}
