/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nina
 */
public class MonthData {
    private String month;
    private Number value;

    public MonthData(String month, Number value) {
        this.month = month;
        this.value = value;
    }

    public String getMonth() {
        return month;
    }

    public Number getValue() {
        return value;
    }
}

