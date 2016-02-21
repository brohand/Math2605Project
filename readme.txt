README FILE

MATH 2605: Calculus III for CS
Aaron Andrews, Jinsong Han, Patrick Tam

This is the README file for the MATH 2605 Project. All files for this project are written in Java.
The README file will follow the procedures for running and executing each section of the rubric for the project.

The driver of each class can be executed through the command line interface. Be sure to compile all files at once, and ignore any compiler warnings.

Part One: Hilbert Matrix
The driver for this part is named "Driver.java".
1. LU-decomposition. To test LU decomposition, run the driver, and select "Test Matrix Functions," and then "LU Decomposition of a Matrix."
Please place a .dat file containing the matrix to be decomposed using LU into the project root (the same directory where this README file is located),
and then enter the full file name including the .dat extension into the console. The program will then output the original matrix, L, U, and the error LU-H.
2. QR-factorization. To test QR factorization, run the driver, and select "Test Matrix Functions," and then option 1 or 2, depending on whether you
are testing Householders or Givens. Please place a .dat file containing the matrix to be decomposed using LU into the project root 
(the same directory where this README file is located), and then enter the full file name including the .dat extension into the console. 
The program will then output the original matrix, Q, R, and the error QR-H.
3. Solving Ax=b. To test this, run the driver, and select "Test Matrix Functions," and then option 3, 4, or 6 depending on whether you want to solve 
using Householders, Givens, or LU. Please place a .dat file containing an augmented matrix (Matrix A with Vector B appended to the end) into the project root 
(the same directory where this README file is located), and then enter the full file name including the .dat extension into the console. 
The program will then output A, x, and b (in the equation Ax=b) and the error Ax-b.
4. Hilbert Matrix. To test this, run the driver, and select either option 1 or 2, depending on whether you want to run a QR visualization or LU visualization.
The program will output the results for Hilbert matrices of size 2 to 20.
5. Plots and discussion. This can be found within the "docs" folder of the project. For the answers to the specific questions, see the "discussion" section of the report.

Part Two: Convolutional Codes
The driver for this part is named "ConvolutionalCodes.java"
1. Encoding Problem. To test the encoding function, run the driver, and select option 1 ("Encoding"). Enter the length of the stream that you want to 
generate. The program will print the generated stream, as well as the generated encoded convolutional code y.
2. Iterative Methods for Ax=b. To test the iterative methods, run the driver, and select option 3. Select the method that you'd like to test (or both).
Enter your error tolerance into the command line, or enter -1 for the default error tolerance (10^-8). Next, please enter an augmented matrix into a .dat file 
named "iterative.dat", and place it into the project root. Next, enter a guess vector into a .dat file named "guess.dat" into the project root. Make sure that
the guess vector is entered in vertically, like:

0.
0.
0.
0.

Once all has been completed, enter any letter to continue. The iterative methods will run and then output their solution vector, as well as how many iterations it took.
3. Decoding Problem. To test the decoding function, run the driver, and select option 2. Enter the stream that you would like to decode. Choose the iterative 
method that you would like to use to decode, and then enter the error tolerance (-1 for default value of 10^-8). The entered Y stream will then attempt to be
decoded. Note that the entered Y stream must be even in order to proceed.
4. Discussion. This can be found within the "docs" folder of the project. For the answers to the questions specifically, see the "discussion" section of the report.
