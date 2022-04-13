
import java.util.Scanner;
/* Department for checking the Matrix class: Defining class instances (metrixes)
   and operations of addition, subtraction and multiplication. */
public class MatrixTest
{
    static Scanner input = new Scanner(System.in);

    public static void main( String[] args )
    {
        System.out.println("Enter Matrix size: ");
        int n = input.nextInt();

        Matrix M1= generateMatrix(n,1);
        Matrix M2= generateMatrix(n,2);
        Matrix M3;
        System.out.println("M1:");
        System.out.println(M1);
        System.out.println("M2:");
        System.out.println(M2);

        System.out.println("M1 + M2:");
        M3 = Matrix.matrixAdd(M1,M2);
        System.out.println(M3);
        System.out.println("M1 - M2:");
        M3 = Matrix.matrixSub(M1,M2);
        System.out.println(M3);
        System.out.println("M1 * M2:");
        M3 = Matrix.matrixMult(M1,M2);
        System.out.println(M3);


    } // main

    // Static method for receiving matrix data
    static Matrix generateMatrix(int n,int k)
    {


        Matrix mat = new Matrix(n);

        System.out.printf("Enter the n,n entries of  matrix %d now:\n",k);

        for (int i = 0 ; i < n; i++ ) {
            for(int j=0 ; j < n; j++){
                mat.setIJ(i,j, input.nextDouble());
            }
        }
        return mat;
    } // generateMatrix

} // MatrixTest
