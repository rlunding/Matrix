package org.lunding;

public class Main {

    public static void main(String[] args) {
	    new GUI();
        double[][] aData = {{1, 2, 3}, {4, 5, 6}};
        Matrix a = new MatrixImpl(aData);
        double[][] bData = {{1, 2},{4, 5}};
        Matrix b = new MatrixImpl(bData);
        double[][] cData = {{1, 0, 2, 3},{4, 7, 6, 4}, {5, 2, 1, 0}};
        Matrix c = new MatrixImpl(cData);
        double[][] dData = {{2, 0, 2}, {4, 5, 0}};
        Matrix d = new MatrixImpl(dData);

        try {
            System.out.println("1)\n" + a.plus(b));
        } catch (IllegalArgumentException e){
            System.out.println("1)\n Undefined\n");
        }
        System.out.println("2)\n" + a.plus(d));
        System.out.println("3)\n" + d.plus(a.times(3)));
        System.out.println("4)\n" + a.times(c));
        System.out.println("5)\n" + b.times(a));

        try {
            System.out.println("6)\n" + a.times(d));
        } catch (IllegalArgumentException e){
            System.out.println("6)\n Undefined\n");
        }
        System.out.println("7)\n" + a.times(c).transpose());
        System.out.println("8)\n" + a.transpose().times(a));
        System.out.println("9)\n" + a.times(a.transpose()));
        System.out.println("10)\n" + c.transpose().times(a.transpose()));
        System.out.println("11)\n" + a.times(a.transpose()).minus(b));
        System.out.println("12)\n" + b.times(3).times(b));

        aData = new double[][]{{2, 0, 0}, {0, 3, 0}, {0, 0, 1}};
        a = new MatrixImpl(aData);
        System.out.println("Opgave 8)\n" + a.times(a).times(a).times(a).times(a).times(a));
    }
}
