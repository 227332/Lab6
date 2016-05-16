package it.polito.tdp.sudoku.model;

public class RecursiveSudoku2 {

	/*
	 * VERSIONE NUMERO 2 -- TROVO UNA SOLA SOLUZIONE
	 * QUI PER IL CHECK UTILIZZO IL METODO FORNITO DALLA CLASSE SudokuGenerator
	 */

	SudokuGenerator sg;

	public int[][] recursiveSudoku2(int[][] matrix) {
		//Creo un solo oggetto SudokuGenerator solo perché così poi posso sfruttare il suo metodo
		//legalMove che cui, passandogli la matrice che modifico ricorsivamente, mi dice se la
		//mossa che voglio fare rispetta i vincoli o meno
		sg = new SudokuGenerator();
		
		//chiamo la funzione ricorsiva che mi troverà una soluzione
		
		return recursiveSudokuSolver2(matrix, 0);
	}

	/*
	 * FUNZIONE RICORSIVA: Il parametro level identifica il livello nell'albero
	 * della ricorsione. Nel caso del Sudoku, identifica la posizione della
	 * matrice in cui vado a posizionare un nuovo numero.
	 */
	private int[][] recursiveSudokuSolver2(int[][] matrix, int level) {

		// Condizione di terminazione
		if (level == Utils.dim * Utils.dim) {

			// Ho trovato una nuova soluzione!

			// stampo la matrice, giusto per vederla io
			System.out.println("Yeah!");
			Utils.printMatrix(matrix, Utils.dim);


			return matrix;
		}

		// Calcolo la riga e la colonna della matrice in base al livello
		int riga = level / Utils.dim;
		int colonna = level % Utils.dim;

		// Posiziono un numero solo se la casella è vuota (contiene uno 0)
		// Ciò infatti non si verifica sempre, perchè ci sono già dei numeri inseriti 
		// a caso dal SudokuGenerator per creare appunto il Sudoku
		if (matrix[riga][colonna] == 0) {

			// Per ciascun possibile numero
			for (int i = 1; i <= Utils.dim; i++) {
				
				// Controlla se inserire l'i-esimo numero è corretto, sfruttando il metodo della
				//classe SudokuGenerator
				if (sg.legalMove(matrix, riga, colonna, i)) {

					// Aggiungi un nuovo numero sulla posizione corrente
					matrix[riga][colonna] = i;

					// Chiama la funzione ricorsiva
					int[][] retMatrix = recursiveSudokuSolver2(matrix, level + 1);
					
					//se è una soluzione la restituisco e termino il metodo ricorsivo,
					//altrimenti vado avanti sperando di trovare una soluzione provando
					//a mettere un altro numero in tale posizione
					if (retMatrix != null)
						return retMatrix;
					

					// Backtracking: rimuovo il numero appena aggiunto
					matrix[riga][colonna] = 0;
				}
			}

		} else {

			// Altrimenti richiamo semplicemente la funzione
			// ricorsiva sul livello successivo
			int[][] retMatrix = recursiveSudokuSolver2(matrix, level + 1);
			
			if (retMatrix != null)
				return retMatrix;
		}
		return null;
	}

}