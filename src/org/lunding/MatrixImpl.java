package org.lunding;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * Created by Lunding on 26/01/15.
 */
public class MatrixImpl implements Matrix, Cloneable{

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
        if (this.columns != b.getRows()) {
            throw new IllegalArgumentException("Illegal matrix dimensions");
        }
        MatrixImpl c = new MatrixImpl(this.rows, b.getColumns());
        for (int i = 0; i < c.rows; i++) {
            for (int j = 0; j < c.columns; j++) {
                for (int k = 0; k < this.columns; k++) {
                    c.data[i][j] += this.data[i][k] * b.getData(k, j);
                }
            }
        }
        return c;
    }

    @Override
    public Matrix solve(Matrix input) {
        if (input == null){
            throw new IllegalArgumentException("input b, must not be null");
        }
        if (rows != columns || input.getRows() != columns || input.getColumns() != 1) {
            throw new IllegalArgumentException("Illegal matrix dimensions");
        }

        MatrixImpl a = (MatrixImpl) this.clone();
        MatrixImpl b = new MatrixImpl(input.getRows(), input.getColumns());
        for (int i = 0; i < b.getRows(); i++){
            for (int j = 0; j < b.getColumns(); j++){
                b.data[i][j] = input.getData(i, j);
            }
        }

        for (int i = 0; i < columns; i++) {
            int max = i;
            for (int j = i + 1; j < columns; j++) {
                if (Math.abs(this.data[j][i]) > Math.abs(this.data[max][i])) {
                    max = j;
                }
            }
            a.swap(i, max);
            b.swap(i, max);

            if (a.data[i][i] == 0.0) {
                throw new RuntimeException("Matrix is singular.");
            }
            for (int j = i + 1; j < columns; j++) {
                b.data[j][0] -= b.data[i][0] * a.data[j][i] / a.data[i][i];
            }
            for (int j = i + 1; j < columns; j++) {
                double m = a.data[j][i] / a.data[i][i];
                for (int k = i + 1; k < columns; k++) {
                    a.data[j][k] -= a.data[i][k] * m;
                }
                a.data[j][i] = 0.0;
            }
        }

        MatrixImpl x = new MatrixImpl(columns, 1);
        for (int i = columns - 1; i >= 0; i--) {
            double t = 0.0;
            for (int j = i + 1; j < columns; j++) {
                t += a.data[i][j] * x.data[j][0];
            }
            x.data[i][0] = round((b.data[i][0] - t) / a.data[i][i]);
        }
        return x;
    }

    private double round(double a){
        return Math.round(a * 1000.0) / 1000.0;
    }

    private void swap(int i, int j){
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
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

    @Override
    public Matrix clone() {
        MatrixImpl result = new MatrixImpl(data.clone());
        return result;
    }
}
