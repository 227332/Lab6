package it.polito.tdp.sudoku.model;

public class RecursiveSudoku {

	/*
	 * ALTERNATIVA NUMERO 1 -- TROVO UNA SOLA SOLUZIONE 
	 * QUI PER IL CHECK UTILIZZO IL METODO Utils.check(matrix, true) IMPLEMENTATO DA ME
	 * 
	 */

	public int[][] recursiveSudoku(int[][] matrix) {
		return recursiveSudokuSolver(matrix, 0);
	}

	/*
	 * FUNZIONE RICORSIVA: Il parametro level identifica il livello nell'albero
	 * della ricorsione. Nel caso del Sudoku, identifica la posizione nella
	 * matrice (posizione usando un solo indice che va da 0 a NxN-1) in cui vado 
	 * a posizionare un nuovo numero.
	 */
	private int[][] recursiveSudokuSolver(int[][] matrix, int level) {

		// Condizione di terminazione
		if (level == Utils.dim * Utils.dim) {
			// Ho trovato una nuova soluzione!

			
			// Stampo la matrice, giusto per vederla io
			System.out.println("Yeah!");
			Utils.printMatrix(matrix, Utils.dim);

			return matrix;
		}
		
		
		/*
		 * RICORDA: data una matrice m[riga][col]=m[index] con riga e col che vanno da 0 a N-1 e
		 *  index che va da 0 a NxN-1 , la conversione è la seguente:
		 *  - index = riga x N + col , xk percorro completamente tutte le righe precedenti e poi
		 *  					   	  percorro le c celle della riga corrente
		 *  - riga = index / N , ossia la divisione intera tra index e N, xk vedendo la matrice 
		 *  					 come un vettore indicizzato da index, la divisione intera mi dà
		 *  					 il numero di righe che ho completamente riempito. Io sono pertanto
		 *  					 alla riga successiva ma, siccome r parte non da 1 ma da 0, allora
		 *  					 essa è proprio pari al numero di righe completamente riempite
		 *  - col = index % N , ossia il resto della divisione intera tra index e N, xk vedendo  
		 *  					 la matrice come un vettore indicizzato da index, la divisione 
		 *  					 intera mi dà il numero di righe che ho completamente riempito. Io
		 *  					  sono pertanto alla riga successiva e alla colonna data dal numero
		 *  					 indicato nel resto (in realtà il resto mi dovrebbe dare col+1 ma
		 *  					 siccome il vettore parte con index=0 e non 1 mi esce proprio col).
		 */

		// Calcolo la riga e la colonna della matrice in base al livello
		int riga = level / Utils.dim;
		int colonna = level % Utils.dim;

		// Posiziono un numero solo se la casella è vuota (contiene uno 0)
		// Ciò infatti non si verifica sempre, perchè ci sono già dei numeri inseriti 
		// a caso dal SudokuGenerator per creare appunto il Sudoku
		if (matrix[riga][colonna] == 0) {

			// Per ciascun possibile valore che posso mettere nella posizione corrente...
			for (int i = 1; i <= Utils.dim; i++) {

				// ...Aggiungo tale valore nella posizione corrente...
				matrix[riga][colonna] = i;

				// ...Controllo che il numero non sia già  presente né nella stessa riga 
				//    né nella stessa colonna né nello stesso quadrato...
				if (Utils.check(matrix, true)) {
					// Chiama la funzione ricorsiva
					int[][] retMatrix = recursiveSudokuSolver(matrix, level + 1);
					
					//se è una soluzione la restituisco e termino il metodo ricorsivo,
					//altrimenti vado avanti sperando di trovare una soluzione provando
					//a mettere un altro numero in tale posizione
					if (retMatrix != null)
						return retMatrix;
				}

				// Backtracking: rimuovo la regina appena aggiunta
				matrix[riga][colonna] = 0;
			}

		} else {
			// Altrimenti richiamo semplicemente la funzione
			// ricorsiva sul livello successivo
			int[][] retMatrix = recursiveSudokuSolver(matrix, level + 1);
			
			//in realtà se è null ormai non ho altri tentativi da fare perciò restituisco cmq null
			//dunque tale controllo potevo anche evitarlo
			if (retMatrix != null)
				return retMatrix;
		}

		//Ho provato inserendo nella posizione corrente ogni possibile valore ma nessuno è
		//andato bene perchè nessuno di loro ha superato il check
		return null;
	}
}
