package org.lunding;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestMatrix {

    @Rule
    public ExpectedException illegalArgument = ExpectedException.none();

    private Matrix a;

    @Before
    public void setUp() throws Exception {
        double[][] startData = {
                {1.0, 2.0},
                {3.0, 4.0}};
        a = new MatrixImpl(startData);
    }

    /**
     **************************** Equals ****************************
     */
    @Test
    public void testMatrixFilledWith0ShouldBeEqual(){
        a = new MatrixImpl(2, 2);
        double[][] resultData = {
                {0.0, 0.0},
                {0.0, 0.0}};
        Matrix result = new MatrixImpl(resultData);
        assertTrue("Matrices filled with 0 should be equal", a.equals(result));
    }

    @Test
    public void testMatrixFilledWith1ShouldNotBeEqual(){
        double[][] resultData = {
                {1.0, 1.0},
                {1.0, 1.0}};
        Matrix result = new MatrixImpl(resultData);
        assertFalse("Matrices filled with 0 should be equal", a.equals(result));
    }

    @Test
    public void testMatrixFilledWithArbitraryDataShouldBeEqual(){
        double[][] resultData1 = {
                {3.2, 1.0, 9.4},
                {1.0, 1.0, 1.0},
                {31.1, 3.0, 12.0},
                {2.0, 1.3, 12.0}};
        Matrix result1 = new MatrixImpl(resultData1);
        double[][] resultData2 = {
                {3.2, 1.0, 9.4},
                {1.0, 1.0, 1.0},
                {31.1, 3.0, 12.0},
                {2.0, 1.3, 12.0}};
        Matrix result2 = new MatrixImpl(resultData2);
        assertTrue("Matrices filled with 0 should be equal", result1.equals(result2));
    }

    /**
     **************************** Identity ****************************
     */
    @Test
    public void testIdentitySize2ShouldBe1OnDiagonalAndRest0(){
        double[][] resultData = {
                {1.0, 0.0},
                {0.0, 1.0}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("Inverse matrix should be 1 from top left to bottom right, otherwise 0", result, a.identity(2));
    }

    @Test
    public void testIdentitySize3ShouldBe1OnDiagonalAndRest0(){
        double[][] resultData = {
                {1.0, 0.0, 0.0},
                {0.0, 1.0, 0.0},
                {0.0, 0.0, 1.0}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("Inverse matrix should be 1 from top left to bottom right, otherwise 0", result, a.identity(3));
    }

    /**
     **************************** Transpose ****************************
     */
    @Test
    public void testTranspose1(){
        double[][] startData = {
                {1.0, 2.0}};
        Matrix start = new MatrixImpl(startData);
        double[][] resultData = {
                {1.0},
                {2.0}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("Transpose of (1 2) should be (1) (2)", result, start.transpose());
    }

    @Test
    public void testTranspose2(){
        double[][] startData = {
                {1.0, 2.0},
                {3.0, 4.0},
                {5.0, 6.0}};
        Matrix start = new MatrixImpl(startData);
        double[][] resultData = {
                {1.0, 3.0, 5.0},
                {2.0, 4.0, 6.0}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("Transpose of (1 2)(3 4)(5 6) should be (1 3 5)(2 4 6)", result, start.transpose());
    }

    @Test
    public void testTranspose3(){
        double[][] startData = {
                {1.0, 2.0},
                {3.0, 4.0},
                {5.0, 6.0}};
        Matrix start = new MatrixImpl(startData);
        assertEquals("The ((A)T)T) should just be A", start, start.transpose().transpose());
    }

    /**
     **************************** Plus ****************************
     */
    @Test
    public void testPlus1Fail(){
        double[][] bData = {
                {1.0, 2.0},
                {3.0, 4.0},
                {5.0, 6.0}};
        Matrix b = new MatrixImpl(bData);
        illegalArgument.expect(IllegalArgumentException.class);
        b.plus(a);
    }

    @Test
    public void testPlus2Fail(){
        double[][] bData = {
                {1.0, 2.0, 3.0},
                {3.0, 4.0, 5.0}};
        Matrix b = new MatrixImpl(bData);
        illegalArgument.expect(IllegalArgumentException.class);
        b.plus(a);
    }

    @Test
    public void testPlus1(){
        double[][] bData = {
                {4.0, 3.0},
                {2.0, 1.0}};
        Matrix b = new MatrixImpl(bData);
        double[][] resultData = {
                {5.0, 5.0},
                {5.0, 5.0}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("(1 2) (3 4) + (4 3) (2 1) = (5 5) (5 5)", result, a.plus(b));
    }

    @Test
    public void testPlus2(){
        double[][] aData = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}};
        Matrix a = new MatrixImpl(aData);
        double[][] bData = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}};
        Matrix b = new MatrixImpl(bData);
        double[][] resultData = {
                {2.0, 4.0, 6.0},
                {8.0, 10.0, 12.0},
                {14.0, 16.0, 18.0}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("(1 2 3) (4 5 6) (7 8 9) + (1 2 3) (4 5 6) (7 8 9) " +
                "= (2 4 6) (8 10 12) (14 16 18)", result, a.plus(b));
    }
    /**
     **************************** Minus ****************************
     */

    @Test
    public void testMinus1Fail(){
        double[][] bData = {
                {1.0, 2.0},
                {3.0, 4.0},
                {5.0, 6.0}};
        Matrix b = new MatrixImpl(bData);
        illegalArgument.expect(IllegalArgumentException.class);
        b.minus(a);
    }

    @Test
    public void testMinus2Fail(){
        double[][] bData = {
                {1.0, 2.0, 3.0},
                {3.0, 4.0, 5.0}};
        Matrix b = new MatrixImpl(bData);
        illegalArgument.expect(IllegalArgumentException.class);
        b.minus(a);
    }

    @Test
    public void testMinus1(){
        double[][] bData = {
                {4.0, 3.0},
                {2.0, 1.0}};
        Matrix b = new MatrixImpl(bData);
        double[][] resultData = {
                {-3.0, -1.0},
                {1.0, 3.0}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("(1 2) (3 4) - (4 3) (2 1) = (-3 -1) (1 3)", result, a.minus(b));
    }

    @Test
    public void testMinus2(){
        double[][] aData = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}};
        Matrix a = new MatrixImpl(aData);
        double[][] bData = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}};
        Matrix b = new MatrixImpl(bData);
        double[][] resultData = {
                {0.0, 0.0, 0.0},
                {0.0, 0.0, 0.0},
                {0.0, 0.0, 0.0}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("(1 2 3) (4 5 6) (7 8 9) - (1 2 3) (4 5 6) (7 8 9) " +
                "= (0 0 0) (0 0 0) (0 0 0)", result, a.minus(b));
    }

    /**
     **************************** Times ****************************
     */

    @Test
    public void testTimes1Fail(){
        double[][] bData = {
                {1.0, 2.0},
                {3.0, 4.0},
                {5.0, 6.0}};
        Matrix b = new MatrixImpl(bData);
        illegalArgument.expect(IllegalArgumentException.class);
        a.times(b);
    }

    @Test
    public void testTimes2Fail(){
        double[][] bData = {
                {1.0, 2.0, 3.0},
                {3.0, 4.0, 5.0}};
        Matrix b = new MatrixImpl(bData);
        illegalArgument.expect(IllegalArgumentException.class);
        b.times(a);
    }

    @Test
    public void testTimes1(){
        double[][] bData = {
                {4.0, 5.0},
                {6.0, 7.0}};
        Matrix b = new MatrixImpl(bData);
        double[][] resultData = {
                {16.0, 19.0},
                {36.0, 43.0}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("(1 2) (3 4) * (4 5) (6 7) = (16 19) (36 43)", result, a.times(b));
    }

    @Test
    public void testTimes2(){
        double[][] aData = {
                {3.0, 4.0, 2.0}};
        Matrix a = new MatrixImpl(aData);
        double[][] bData = {
                {13.0, 9.0, 7.0, 15.0},
                {8.0, 7.0, 4.0, 6.0},
                {6.0, 4.0, 0.0, 3.0}};
        Matrix b = new MatrixImpl(bData);
        double[][] resultData = {
                {83.0, 63.0, 37.0, 75.0}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("(3 4 2) * (13 9 7 15) (8 7 4 6) (6 4 0 3) = (83 63 37 75)", result, a.times(b));
    }

    @Test
    public void testTimes3(){
        double[][] aData = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0}};
        Matrix a = new MatrixImpl(aData);
        double[][] bData = {
                {7.0, 8.0},
                {9.0, 10.0},
                {11.0, 12.0}};
        Matrix b = new MatrixImpl(bData);
        double[][] resultData = {
                {58, 64},
                {139, 154}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("(1 2 3) (4 5 6) * (7 8) (9 10) (11 12) " +
                "= (58 64) (139 154)", result, a.times(b));
    }

    /**
     **************************** Solve ****************************
     */
    @Test
    public void testSolve1Fail(){
        double[][] bData = {
                {1.0, 2.0},
                {3.0, 4.0},
                {5.0, 6.0}};
        Matrix b = new MatrixImpl(bData);
        illegalArgument.expect(IllegalArgumentException.class);
        a.solve(b);
    }

    @Test
    public void testSolve2Fail(){
        double[][] bData = {
                {1.0, 2.0},
                {3.0, 4.0}};
        Matrix b = new MatrixImpl(bData);
        illegalArgument.expect(IllegalArgumentException.class);
        a.solve(b);
    }

    @Test
    public void testSolve1(){
        double[][] bData = {
                {-4.0},
                {-6.0}};
        Matrix b = new MatrixImpl(bData);
        double[][] resultData = {
                {2},
                {-3}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("(1 2) (3 4) | (-4 -6) = (1 0) (1 0) | (2 - 3)", result, a.solve(b));
    }

    @Test
    public void testSolve2() {
        double[][] aData = {
                {2.0, 1.0, -1.0},
                {-3.0, -1.0, 2.0},
                {-2.0, 1.0, 2.0}};
        Matrix a = new MatrixImpl(aData);
        double[][] bData = {
                {8.0},
                {-11.0},
                {-3.0}};
        Matrix b = new MatrixImpl(bData);

        double[][] resultData = {
                {2.0},
                {3.0},
                {-1.0}};
        Matrix result = new MatrixImpl(resultData);
        assertEquals("(2 1 -1) (-3 -1 2) (-2 1 2) | (8 -11 -3) = (2 3 -1)", result, a.solve(b));
    }
}