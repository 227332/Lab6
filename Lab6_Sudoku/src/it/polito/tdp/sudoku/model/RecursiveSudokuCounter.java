package it.polito.tdp.sudoku.model;

public class RecursiveSudokuCounter {

	/*
	 * VERSIONE NUMERO 3 -- TROVO TUTTE LE SOLUZIONI
	 * Questa non andava bene in questo esercizio, perchè qui ne vuole solo una da visualizzare
	 * nel Controller
	 * 
	 */
	
	static int solutionsCounter = 0;

	public int recursiveSudokuCounter(int[][] matrix) {
		//qui il metodo ricorsivo è un metodo void
		recursiveSudokuSolver(matrix, 0);
		return solutionsCounter;
	}

	/*
	 * FUNZIONE RICORSIVA: Il parametro level identifica il livello nell'albero
	 * della ricorsione. Nel caso del Sudoku, identifica la posizione della
	 * matrice in cui vado a posizionare un nuovo numero.
	 */
	private void recursiveSudokuSolver(int[][] matrix, int level) {

		// Condizione di terminazione
		if (level == Utils.dim * Utils.dim) {

			// Ho trovato una nuova soluzione! Perciò incremento il contatore...
			solutionsCounter++;
			
			// ...e stampo la matrice (così facendo stampo tutte le soluzioni a mano 
			//a mano che le trovo)
			System.out.println("Yeah!");
			Utils.printMatrix(matrix, Utils.dim);

			return;
		}

		// Calcolo la riga e la colonna della matrice in base al livello
		int riga = level / Utils.dim;
		int colonna = level % Utils.dim;

		// Posiziono un numero solo se la casella è vuota (contiene uno 0)
		if (matrix[riga][colonna] == 0) {

			// Per ciascuna colonna
			for (int i = 1; i <= Utils.dim; i++) {

				// Prova ad aggiungere un nuovo numero sulla riga corrente
				matrix[riga][colonna] = i;

				// Controlla se il numero non è già  presente
				if (Utils.check(matrix, true)) {
					// Chiama la funzione ricorsiva
					recursiveSudokuSolver(matrix, level + 1);
				}

				// Backtracking: rimuovo il numero appena aggiunto
				matrix[riga][colonna] = 0;
			}

		} else {
			
			// Altrimenti richiamo semplicemente la funzione
			// ricorsiva sul livello successivo
			recursiveSudokuSolver(matrix, level + 1);
		}
	}
}