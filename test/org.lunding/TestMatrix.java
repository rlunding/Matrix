package org.lunding;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMatrix {

    private Matrix m;

    @Before
    public void setUp() throws Exception {
        m = new MatrixImpl(2, 2);
    }

    @Test
    public void testMatrixFilledWith0ShouldBeEqual(){
        double[][] resultData = {
                {0.0, 0.0},
                {0.0, 0.0}};
        MatrixImpl result = new MatrixImpl(resultData);
        assertTrue("Matrices filled with 0 should be equal", m.equals(result));
    }

    @Test
    public void testMatrixFilledWith1ShouldNotBeEqual(){
        double[][] resultData = {
                {1.0, 1.0},
                {1.0, 1.0}};
        MatrixImpl result = new MatrixImpl(resultData);
        assertFalse("Matrices filled with 0 should be equal", m.equals(result));
    }

    @Test
    public void testMatrixFilledWithArbitraryDataShouldBeEqual(){
        double[][] resultData1 = {
                {3.2, 1.0, 9.4},
                {1.0, 1.0, 1.0},
                {31.1, 3.0, 12.0},
                {2.0, 1.3, 12.0}};
        MatrixImpl result1 = new MatrixImpl(resultData1);
        double[][] resultData2 = {
                {3.2, 1.0, 9.4},
                {1.0, 1.0, 1.0},
                {31.1, 3.0, 12.0},
                {2.0, 1.3, 12.0}};
        MatrixImpl result2 = new MatrixImpl(resultData2);
        assertTrue("Matrices filled with 0 should be equal", result1.equals(result2));
    }

    @Test
    public void testIdentitySize2ShouldBe1OnDiagonalAndRest0(){
        double[][] resultData = {
                {1.0, 0.0},
                {0.0, 1.0}};
        MatrixImpl result = new MatrixImpl(resultData);
        assertEquals("Inverse matrix should be 1 from top left to bottom right, otherwise 0", result, m.identity(2));
    }

    @Test
    public void testIdentitySize3ShouldBe1OnDiagonalAndRest0(){
        double[][] resultData = {
                {1.0, 0.0, 0.0},
                {0.0, 1.0, 0.0},
                {0.0, 0.0, 1.0}};
        MatrixImpl result = new MatrixImpl(resultData);
        assertEquals("Inverse matrix should be 1 from top left to bottom right, otherwise 0", result, m.identity(3));
    }
}