package org.lunding;



/**
 * Implementation of the Matrix interface.
 * Created by Lunding on 26/01/15.
 */
public class MatrixImpl implements Matrix, Cloneable{

    private static final int ROUND_PARAMETER = 1000;
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
    public Matrix times(double scalar) {
        MatrixImpl result = new MatrixImpl(rows, columns);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.columns; j++) {
                result.data[i][j] = data[i][j] * scalar;
            }
        }
        return result;
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

    private void swap(int i, int j){
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private double round(double a){
        return Math.round(a * ROUND_PARAMETER) / ROUND_PARAMETER;
    }

    @Override
    public double determinant() {
        if (rows != columns) {
            throw new IllegalArgumentException("Illegal matrix dimensions");
        }
        return determinant(data.clone(), rows);
    }

    private double determinant(double[][] a, int n){
        if(n == 1) {
            return  a[0][0];
        }
        if (n == 2) {
            return a[0][0]*a[1][1] - a[1][0]*a[0][1];
        }
        double det = 0;
        for(int i = 0; i < n; i++) {

            double[][] m = new double[n-1][];
            for(int k = 0; k < (n-1); k++) {
                m[k] = new double[n-1];
            }
            for(int j = 1; j < n; j++) {
                int j2=0;
                for(int k = 0; k < n; k++) {
                    if(k == i){
                        continue;
                    }
                    m[j - 1][j2] = a[j][k];
                    j2++;
                }
            }
            det += Math.pow(-1.0, 1.0 + i + 1.0) * a[0][i] * determinant(m, n-1);
        }
        return det;
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
    public Matrix clone(){
        return new MatrixImpl(data.clone());
    }
}
