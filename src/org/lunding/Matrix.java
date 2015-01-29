package org.lunding;

/**
 * Write something
 * Created by Lunding on 26/01/15.
 */
public interface Matrix {

    public Matrix identity(int n);
    public Matrix transpose();
    public Matrix inverse();
    public Matrix plus(Matrix b);
    public Matrix minus(Matrix b);
    public Matrix times(Matrix b);
    public Matrix times(double scalar);
    public Matrix solve(Matrix b);
    public double determinant();
    public int getRows();
    public int getColumns();
    public double getData(int row, int column);
}
