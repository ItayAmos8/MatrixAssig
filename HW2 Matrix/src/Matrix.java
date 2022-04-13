import static java.lang.reflect.Array.*;

public class Matrix {
    public double[][] mat;
    public int N, size;
    public double x;
    public int numofrow;

    public Matrix(int n) {//initialize the matrix with 0
        mat = new double[n][n];
        size = getLength(mat);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = 0;
            }
        }
        numofrow = n;
    }

    public int getN() {//return the dimension of matrix mat
        N = size * size;
        return N;
    }

    public double getIJ(int i, int j) {
        x = mat[i][j];
        return x;
    }

    public void setIJ(int i, int j, double value) {
        mat[i][j] = value;
    }

    public String toString() {
        String numin = "";
        for (int i = 0; i < numofrow; i++) {
            for (int j = 0; j < numofrow; j++) {
                numin += mat[i][j] + "        ";

            }
            numin += "\n";
        }
        return numin;
    }

    public static Matrix matrixAdd(Matrix M1, Matrix M2) {
        Matrix addmat = new Matrix(M1.numofrow);
        for (int i = 0; i < M1.numofrow; i++) {
            for (int j = 0; j < M1.numofrow; j++) {
                addmat.mat[i][j] = M1.getIJ(i, j) + M2.getIJ(i, j);
            }
        }
        return addmat;
    }

    public static Matrix matrixSub(Matrix M1, Matrix M2) {
        Matrix submat = new Matrix(M1.numofrow);

        for (int i = 0; i < M1.numofrow; i++) {
            for (int j = 0; j < M1.numofrow; j++) {
                try{
                    submat.mat[i][j] = M1.mat[i][j] - M2.mat[i][j];
                }
                catch (Exception e){
                    System.out.println(j);
                    e.printStackTrace();
                }

            }
        }
        return submat;
    }

    public static Matrix matrixMult(Matrix M1, Matrix M2) {
        Matrix result = new Matrix(M1.numofrow);
        int len = M1.numofrow / 2;

        if (M1.numofrow == 1) {
            result.mat[0][0] = M1.mat[0][0] * M2.mat[0][0];
            return result;
        }

        Matrix M1_11 = new Matrix(len);
        Matrix M1_12 = new Matrix(len);
        Matrix M1_21 = new Matrix(len);
        Matrix M1_22 = new Matrix(len);

        Matrix M2_11 = new Matrix(len);
        Matrix M2_12 = new Matrix(len);
        Matrix M2_21 = new Matrix(len);
        Matrix M2_22 = new Matrix(len);

        M1_11.split(M1, M1_11, 0, 0);
        M1_12.split(M1,M1_12,0,len);
        M1_21.split(M1,M1_21,len,0);
        M1_22.split(M1,M1_22,len,len);

        System.out.println("M2: " + M2);
        System.out.println("M2_11: " + M2_11);
        System.out.println("M2_12: " + M2_12);
        System.out.println("M2_21: " + M2_21);
        System.out.println("M2_22: " + M2_22);
        M2_11.split(M2, M2_11, 0, 0);
        M2_12.split(M2,M2_12,0,len);
        M2_21.split(M2,M2_21,len,0);
        M2_22.split(M2,M2_22,len,len);

        Matrix m1 = matrixMult(matrixAdd(M1_11, M1_22), matrixAdd(M2_11, M2_22));
        Matrix m2 = matrixMult(matrixAdd(M1_21, M1_22), M2_11);
        Matrix m3 = matrixMult(M1_11, matrixSub(M2_12, M2_22));
        Matrix m4 = matrixMult(M1_22, matrixSub(M2_21, M2_11));
        Matrix m5 = matrixMult(matrixAdd(M1_11, M1_12), M2_22);
        Matrix m6 = matrixMult(matrixSub(M1_21, M1_11), matrixAdd(M2_11, M2_12));
        Matrix m7 = matrixMult(matrixSub(M1_12, M1_22), matrixAdd(M2_21, M2_22));

        System.out.println("m1_11 " +  M1_11.toString());
        System.out.println("m1_12 " + M1_12.toString());
        System.out.println("m2_22 " + M2_22.toString());
        System.out.println("m7 " + m7.toString());
        System.out.println("m7+5 " + matrixAdd(m5, m7).toString());
        System.out.println("m1+4 " + matrixAdd(m1, m4).toString());

        Matrix c11 = matrixAdd(matrixSub(matrixAdd(m1, m4),m5), m7);
        Matrix c12 = matrixAdd(m3, m5);
        Matrix c21 = matrixAdd(m2, m4);
        Matrix c22 = matrixAdd(matrixSub(m1, m2), matrixAdd(m3, m6));

        for (int i = 0; i < M1.numofrow / 2; i++) {
            for (int j = 0; j < M1.numofrow / 2; j++) {
                result.mat[i][j] = c11.getIJ(i, j);
                result.mat[i][M2.numofrow / 2 + j] = c12.getIJ(i, j);
                result.mat[M1.numofrow / 2 + i][j] = c21.getIJ(i, j);
                result.mat[M2.numofrow / 2 + i][M2.numofrow / 2 + j] = c22.getIJ(i, j);
            }
        }
        M1.numofrow /= 2;
        return result;
    }


    public void split(Matrix P, Matrix C, int iB, int jB) {
        // Outer loop for rows
        for (int i1 = 0, i2 = iB; i1 < C.numofrow; i1++, i2++) {
            // Inner loop for columns
            for (int j1 = 0, j2 = jB; j1 < C.numofrow; j1++, j2++) {
                C.mat[i1][j1] = P.mat[i2][j2];
            }
        }
    }

}


