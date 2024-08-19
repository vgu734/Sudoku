package com.vgu734.sudoku.model;

public class Cell {
    private Integer value;
    private boolean readOnly;
    
    public Cell() {
    }

    public Cell(Integer value, boolean readOnly) {
        this.value = value;
        this.readOnly = readOnly;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        if (!readOnly) {
            this.value = value;
        }
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}
