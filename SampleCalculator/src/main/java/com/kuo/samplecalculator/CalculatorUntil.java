package com.kuo.samplecalculator;

/**
 * Created by Kuo on 2015/11/3.
 */
public class CalculatorUntil {

    private Integer id = -1;
    private Integer color = -1;
    private Integer background = -1;

    @Override
    public boolean equals(Object o) {
        if (o instanceof CalculatorUntil) {
            return this.id==((CalculatorUntil) o).getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public void setBackground(Integer background) {
        this.background = background;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getColor() {
        return color;
    }

    public Integer getId() {
        return id;
    }

    public Integer getBackground() {
        return background;
    }
}
