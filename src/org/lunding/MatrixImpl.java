package org.lunding;

import java.util.Arrays;

/**
 * Created by Lunding on 26/01/15.
 */
public class MatrixImpl implements Matrix{

    private final double[][] data;
    private final int rows;
    private final int columns;

    public MatrixImpl(int rows, int columns){
        this.data = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public MatrixImpl(double[][] data){
        rows = data.length;
        columns = data[0].length;
        this.data = data;
    }

    @Override
    public Matrix identity(int n) {
        MatrixImpl m = new MatrixImpl(n, n);
        for (int i = 0; i < n; i++){
            m.data[i][i] = 1;
        }
        return m;
    }

    @Override
    public Matrix transpose() {
        MatrixImpl m = new MatrixImpl(columns, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                m.data[j][i] = this.data[i][j];
            }
        }
        return m;
    }

    @Override
    public Matrix inverse() {
        return null;
    }

    @Override
    public Matrix show() {
        return null;
    }

    @Override
    public Matrix plus(Matrix b) {
        if (this.rows != b.getRows() || this.columns != b.getColumns()) {
            throw new IllegalArgumentException("Illegal matrix dimensions");
        }
        MatrixImpl c = new MatrixImpl(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                c.data[i][j] = this.data[i][j] + b.getData(i, j);
            }
        }
        return c;
    }

    @Override
    public Matrix minus(Matrix b) {
        if (this.rows != b.getRows() || this.columns != b.getColumns()) {
            throw new IllegalArgumentException("Illegal matrix dimensions");
        }
        MatrixImpl c = new MatrixImpl(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                c.data[i][j] = this.data[i][j] - b.getData(i, j);
            }
        }
        return c;
    }

    @Override
    public Matrix times(Matrix b) {
        return null;
    }

    @Override
    public Matrix solve(Matrix b) {
        return null;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public double getData(int row, int column) {
        return this.data[row][column];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatrixImpl matrix = (MatrixImpl) o;

        if (columns != matrix.columns) return false;
        if (rows != matrix.rows) return false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (this.data[i][j] != matrix.data[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = rows;
        result = 31 * result + columns;
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                result += data[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
}
