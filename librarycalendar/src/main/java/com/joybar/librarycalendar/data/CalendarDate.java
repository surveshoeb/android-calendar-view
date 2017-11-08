package com.joybar.librarycalendar.data;

/**
 * Created by joybar on 2/24/16.
 */
public class CalendarDate {

    private Lunar lunar = new Lunar();//Lunar calendar
    private Solar solar = new Solar();//Gregorian calendar
    private boolean isInThisMonth; //Whether in the month
    private boolean isSelect;//Is selected?
    private boolean unSelectable;
    private boolean inRange;


    public CalendarDate(int year, int month, int day, boolean isInThisMonth, boolean isSelect, Lunar lunar) {
        this.isInThisMonth = isInThisMonth;
        this.isSelect = isSelect;
        this.lunar = lunar;
    }


    public CalendarDate(boolean isInThisMonth, boolean isSelect, Solar solar, Lunar lunar) {
        this.isInThisMonth = isInThisMonth;
        this.isSelect = isSelect;
        this.solar = solar;
        this.lunar = lunar;
    }

    public boolean isInThisMonth() {
        return isInThisMonth;
    }


    public void setIsInThisMonth(boolean isInThisMonth) {
        this.isInThisMonth = isInThisMonth;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public Solar getSolar() {
        return solar;
    }

    public void setSolar(Solar solar) {
        this.solar = solar;
    }

    public void setInThisMonth(boolean inThisMonth) {
        isInThisMonth = inThisMonth;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public Lunar getLunar() {
        return lunar;
    }

    public void setLunar(Lunar lunar) {
        this.lunar = lunar;
    }

    public void setUnSelectable(boolean unSelectable) { this.unSelectable = unSelectable; }
    public boolean isUnSelectable() { return unSelectable; }

    public boolean isInRange() {
        return inRange;
    }
    public void setInRange(boolean inRange) {
        this.inRange = inRange;
    }
}
