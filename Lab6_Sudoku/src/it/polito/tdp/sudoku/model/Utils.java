package it.polito.tdp.sudoku.model;

import java.util.Arrays;

public class Utils {

	final static int dim = 9;
	final static int dim3 = 3;

	/*
	 * Metodo per il controllo della griglia Sudoku
	 */
	static boolean check(int[][] matrix, boolean partial) {

		//per ogni colonna...
		for (int i = 0; i < dim; i++) {

			// Ad ogni ciclo calcolo 3 array:
			// (uno per la riga, uno per la colonna ed uno per il quadrato)
			int[] row = new int[dim];
			int[] square = new int[dim];
			int[] column = matrix[i].clone();

			for (int j = 0; j < dim; j++) {
				row[j] = matrix[j][i];//vettore contenente la colonna i 
				square[j] = matrix[(i / dim3) * dim3 + j / dim3][i * dim3 % dim + j % dim3];
			}

			if (!(validate(column) && validate(row) && validate(square)))
				return false;

		}
		return true;
	}

	/*
	 * Valido un array di una soluzione parziale (ossia che può contenere degli 0)
	 */
	private static boolean validate(int[] check) {

		// Ordino l'array
		Arrays.sort(check);
		for (int i = 0; i < check.length - 1; i++) {
			// Controllo che non ci siano elementi duplicati
			if (check[i] != 0 && check[i] == check[i + 1])//WOW,Ecco perchè prima ordina il vettore!
				return false;
		}
		return true;
	}

	/*
	 * Stampa la griglia di numeri sulla console.
	 */
	static void printMatrix(int[][] matrix, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] != 0)
					System.out.print(matrix[i][j] + " ");
				else
					System.out.print("  ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

}
