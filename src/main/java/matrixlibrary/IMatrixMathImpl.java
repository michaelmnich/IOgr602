package matrixlibrary;

public class IMatrixMathImpl implements IMatrixMath {
    public IMatrix inverseMatrix(IMatrix m1) {
        //  http://mathworld.wolfram.com/MatrixInverse.html
        double det = 0;
        try {
            det = m1.determinant();
            if (Math.abs(det) < 1e-12)
                return null;
        } catch (InvalidDimensionException e) {
            return null;
        }
        if (m1.getHeight() > 3)
            return null;    //Zaimplementowano do macierzy 2x2, 3x3
        if (m1.getHeight() == 1)
            return m1;
        if (m1.getHeight() == 2) {
            double[][] result = new double[2][2];
            result[0][0] = m1.getMatrixValue(1, 1);
            result[1][1] = m1.getMatrixValue(0, 0);
            result[0][1] = -m1.getMatrixValue(0, 1);
            result[1][0] = -m1.getMatrixValue(1, 0);
            return scalarMultiplication(new IMatrixImpl(result), 1 / det);
        } else {
            double[][] result = new double[3][3];
            result[0][0] = m1.getMatrixValue(1, 1) * m1.getMatrixValue(2, 2) - m1.getMatrixValue(1, 2) * m1.getMatrixValue(2, 1);
            result[1][0] = m1.getMatrixValue(0, 2) * m1.getMatrixValue(2, 1) - m1.getMatrixValue(0, 1) * m1.getMatrixValue(2, 2);
            result[2][0] = m1.getMatrixValue(0, 1) * m1.getMatrixValue(1, 2) - m1.getMatrixValue(0, 2) * m1.getMatrixValue(1, 1);
            result[0][1] = m1.getMatrixValue(1, 2) * m1.getMatrixValue(2, 0) - m1.getMatrixValue(1, 0) * m1.getMatrixValue(2, 2);
            result[1][1] = m1.getMatrixValue(0, 0) * m1.getMatrixValue(2, 2) - m1.getMatrixValue(0, 2) * m1.getMatrixValue(2, 0);
            result[2][1] = m1.getMatrixValue(0, 2) * m1.getMatrixValue(1, 0) - m1.getMatrixValue(0, 0) * m1.getMatrixValue(1, 2);
            result[0][2] = m1.getMatrixValue(1, 0) * m1.getMatrixValue(2, 1) - m1.getMatrixValue(1, 1) * m1.getMatrixValue(2, 0);
            result[1][2] = m1.getMatrixValue(0, 1) * m1.getMatrixValue(2, 0) - m1.getMatrixValue(0, 0) * m1.getMatrixValue(2, 1);
            result[2][2] = m1.getMatrixValue(0, 0) * m1.getMatrixValue(1, 1) - m1.getMatrixValue(0, 1) * m1.getMatrixValue(1, 0);
            return scalarMultiplication(new IMatrixImpl(result), 1 / det);
        }
    }

    public IMatrix matrixAddition(IMatrix m1, IMatrix m2) throws InvalidDimensionException {
        if (m1.getHeight() != m2.getHeight() || m1.getWidth() != m2.getWidth())
            throw new InvalidDimensionException();
        double[][] m3 = new double[m1.getWidth()][m1.getHeight()];
        for (int i = 0; i < m1.getWidth(); i++) {
            for (int j = 0; j < m1.getHeight(); j++) {
                m3[i][j] = m1.getMatrixValue(i, j) + m2.getMatrixValue(i, j);
            }
        }
        IMatrix result = new IMatrixImpl();
        result.setMatrixValues(m3);
        return result;
    }

    public IMatrix matrixMultiplication(IMatrix m1, IMatrix m2) {
        // TODO: 05/06/2016 Implement
        return null;
    }

    public IMatrix matrixSubtracting(IMatrix m1, IMatrix m2) throws InvalidDimensionException {
        if (m1.getHeight() != m2.getHeight() || m1.getWidth() != m2.getWidth())
            throw new InvalidDimensionException();
        double[][] m3 = new double[m1.getWidth()][m1.getHeight()];
        for (int i = 0; i < m1.getWidth(); i++) {
            for (int j = 0; j < m1.getHeight(); j++) {
                m3[i][j] = m1.getMatrixValue(i, j) - m2.getMatrixValue(i, j);
            }
        }
        IMatrix result = new IMatrixImpl();
        result.setMatrixValues(m3);
        return result;
    }

    public IMatrix matrixTransposition(IMatrix m1) {
        // TODO: 05/06/2016 Implement
        return null;
    }

    public IMatrix scalarMultiplication(IMatrix m1, double scalar) {
        double[][] m3 = new double[m1.getWidth()][m1.getHeight()];
        for (int i = 0; i < m1.getWidth(); i++) {
            for (int j = 0; j < m1.getHeight(); j++) {
                m3[i][j] = m1.getMatrixValue(i, j) * scalar;
            }
        }
        return new IMatrixImpl(m3);
    }
}
