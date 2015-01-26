package org.lunding;

/**
 * Created by Lunding on 26/01/15.
 */
public interface Matrix {

    public Matrix identity(int n);
    public Matrix transpose();
    public Matrix inverse();
    public Matrix show();
    public Matrix plus(Matrix b);
    public Matrix minus(Matrix b);
    public Matrix times(Matrix b);
    public Matrix solve(Matrix b);

    public boolean equal();

}