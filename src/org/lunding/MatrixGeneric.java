package org.lunding;

/**
 * Created by Lunding on 26/01/15.
 */
public class MatrixGeneric<T> {

    private final T[][] t;
    private final int rows;
    private final int columns;

    public MatrixGeneric(int rows, int columns){
        this.t = (T[][]) new Object[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }
}
