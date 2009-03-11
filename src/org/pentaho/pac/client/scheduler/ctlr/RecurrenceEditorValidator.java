/*
 * Copyright 2005-2008 Pentaho Corporation.  All rights reserved. 
 * This program is free software; you can redistribute it and/or modify it under the 
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software 
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this 
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html 
 * or from the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright 2008 - 2009 Pentaho Corporation.  All rights reserved.
 *
 * Created  
 * @author Steven Barkdull
 */
package org.pentaho.pac.client.scheduler.ctlr;

import org.pentaho.gwt.widgets.client.controls.schededitor.RecurrenceEditor;
import org.pentaho.gwt.widgets.client.utils.StringUtils;
import org.pentaho.gwt.widgets.client.utils.TimeUtil;
import org.pentaho.pac.client.PentahoAdminConsole;
import org.pentaho.pac.client.i18n.PacLocalizedMessages;

/**
 * 
 * @author Steven Barkdull
 *
 */
public class RecurrenceEditorValidator implements IUiValidator {
  private static final PacLocalizedMessages MSGS = PentahoAdminConsole.getLocalizedMessages();
  
  private RecurrenceEditor recurrenceEditor = null;
  private DateRangeEditorValidator dateRangeEditorValidator = null;
  private static final String MUST_BE_A_NUMBER = MSGS.mustBeIntegerRange();
  
  public RecurrenceEditorValidator( RecurrenceEditor recurrenceEditor ) {
    this.recurrenceEditor = recurrenceEditor; 
    this.dateRangeEditorValidator = new DateRangeEditorValidator( recurrenceEditor.getDateRangeEditor() );
  }
  
  public boolean isValid() {
    boolean isValid = true;
    switch ( recurrenceEditor.getTemporalState() ) {
      case SECONDS:
        RecurrenceEditor.SecondlyRecurrenceEditor sEd = recurrenceEditor.getSecondlyEditor();
        String seconds = sEd.getValue();
        if ( !StringUtils.isPositiveInteger( seconds ) 
            || ( Integer.parseInt( seconds ) <= 0 ) ) {
          isValid = false;
        }
        if ( Integer.parseInt( seconds ) > TimeUtil.MAX_SECOND_BY_MILLISEC ) {
          isValid = false;
        }
        if ( !isValid ) {
          sEd.setValueError( MSGS.mustBeSecondsRange( Integer.toString( TimeUtil.MAX_SECOND_BY_MILLISEC ) ) );
        }
        break;
      case MINUTES:
        RecurrenceEditor.MinutelyRecurrenceEditor mEd = recurrenceEditor.getMinutelyEditor();
        String minutes = mEd.getValue();
        if ( !StringUtils.isPositiveInteger( minutes ) 
            || ( Integer.parseInt( minutes ) <= 0 ) ) {
          isValid = false;
        }
        if ( Integer.parseInt( minutes ) > TimeUtil.MAX_MINUTE_BY_MILLISEC ) {
          isValid = false;
        }
        if ( !isValid ) {
          mEd.setValueError( MSGS.mustBeMinutesRange( Integer.toString( TimeUtil.MAX_MINUTE_BY_MILLISEC ) ) );
        }
        break;
      case HOURS:
        RecurrenceEditor.HourlyRecurrenceEditor hEd = recurrenceEditor.getHourlyEditor();
        String hours = hEd.getValue();
        if ( !StringUtils.isPositiveInteger( hours ) 
            || ( Integer.parseInt( hours ) <= 0 ) ) {
          isValid = false;
        }
        if ( Integer.parseInt( hours ) > TimeUtil.MAX_HOUR_BY_MILLISEC ) {
          isValid = false;
        }
        if ( !isValid ) {
          hEd.setValueError( MSGS.mustBeHoursRange( Integer.toString( TimeUtil.MAX_HOUR_BY_MILLISEC ) ) );
        }
        break;
      case DAILY:
        RecurrenceEditor.DailyRecurrenceEditor dEd = recurrenceEditor.getDailyEditor();
        if ( dEd.isEveryNDays() ) {
          String days = dEd.getRepeatValue();
          if ( !StringUtils.isPositiveInteger( days ) 
              || ( Integer.parseInt( days ) <= 0 ) ) {
            isValid = false;
            dEd.setRepeatError( MSGS.days( MUST_BE_A_NUMBER ) );
          }
        }
        break;
      case WEEKLY:
        RecurrenceEditor.WeeklyRecurrenceEditor wEd = recurrenceEditor.getWeeklyEditor();
        if ( wEd.getNumCheckedDays() < 1 ) {
          isValid = false;
          wEd.setEveryDayOnError( MSGS.oneOrMoreMustBeChecked() );
        }
        break;
      case MONTHLY:
        RecurrenceEditor.MonthlyRecurrenceEditor monthlyEd = recurrenceEditor.getMonthlyEditor();
        if ( monthlyEd.isDayNOfMonth() ) {
          String dayNOfMonth = monthlyEd.getDayOfMonth();
          if ( !StringUtils.isPositiveInteger( dayNOfMonth ) 
              || !TimeUtil.isDayOfMonth( Integer.parseInt( dayNOfMonth ) ) ) {
            isValid = false;
            monthlyEd.setDayNOfMonthError( MSGS.dayOfMonthMustBeBetween() );
          }
        }
        break;
      case YEARLY:
        RecurrenceEditor.YearlyRecurrenceEditor yearlyEd = recurrenceEditor.getYearlyEditor();
        if ( yearlyEd.isEveryMonthOnNthDay() ) {
          String dayNOfMonth = yearlyEd.getDayOfMonth();
          if ( !StringUtils.isPositiveInteger( dayNOfMonth ) 
              || !TimeUtil.isDayOfMonth( Integer.parseInt( dayNOfMonth ) ) ) {
            isValid = false;
            yearlyEd.setDayOfMonthError( MSGS.dayOfMonthMustBeBetween() );
          }
        }
        break;
      default:
        throw new RuntimeException( MSGS.unrecognizedSchedType( recurrenceEditor.getTemporalState().toString() ) );
    }
    isValid &= dateRangeEditorValidator.isValid();
    return isValid;
  }

  public void clear() {
    recurrenceEditor.getSecondlyEditor().setValueError( null );
    recurrenceEditor.getMinutelyEditor().setValueError( null );
    recurrenceEditor.getHourlyEditor().setValueError( null );
    recurrenceEditor.getDailyEditor().setRepeatError( null );
    recurrenceEditor.getWeeklyEditor().setEveryDayOnError( null );
    recurrenceEditor.getMonthlyEditor().setDayNOfMonthError( null );
    recurrenceEditor.getYearlyEditor().setDayOfMonthError( null );
    dateRangeEditorValidator.clear();
  }
}
