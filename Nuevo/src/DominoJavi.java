
public class DominoJavi {
	/**
	 * Aleatoriamente quita una ficha de las fichas del dominó
	 * @param fichas - todas las fichas del dominó con las que jugamos
	 * @return La ficha quitada
	 */
	public static String quitarUna(String[] fichas) {
		int posicion=-1;
		String ficha = "";
		
		do {
			posicion = ((int) (Math.random()*28));
			ficha = fichas[posicion];					
		} while (fichas[posicion].equals("##"));
			
		fichas[posicion] = new String("##");
		
		return ficha;
	}
	
	/**
	 * De las fichas del dominó le quita siete y las devuelve
	 * @param fichas - todas las fichas del dominó
	 * @return 7 fichas para un jugador
	 */
	public static String[] repartirJugador(String[] fichas) {
		String[] jugador = new String[7];
		
		for(int i=0; i<7; i++) {
			jugador[i] = quitarUna(fichas);
		}
		
		return jugador;
	}
	
	/**
	 * Pintar fichas: pintar un array de fichas
	 * @param fichas -> String[]
	 */
	public static void pintarFichas(String[] fichas) {
		
		for(int i=0;i<fichas.length;i++)
			System.out.print(fichas[i] + " ");
	}
	
	/**
	 * Poner ficha: un jugador intenta poner una ficha en el tablero
	 * @param jugador -> las fichas de un jugador
	 * @param juego --> la cabeza y la cola de la mesa de dominó (donde puedo poner fichas)
	 * @return Booleano si se ha podido poner una ficha o no
	 */
	public static boolean ponerFicha(String[] jugador, int[] juego) {
		boolean exito = false;
		
		//Recorremos las fichas del jugador una por una
		//Comprobamos que la ficha del jugador coincida con la cabeza o la cola, o con -1
		int cabezaj, colaj;
		for(int i=0; i < jugador.length; i++) {
			//Convertimos el char de la ficha a su valor entero
			cabezaj = Character.getNumericValue(jugador[i].charAt(0));
			colaj = Character.getNumericValue(jugador[i].charAt(1));
			
			//Compruebo si puedo poner cada parte de la ficha en la cabeza o cola de la partida
			if (cabezaj == juego[0]) {
				//Pongo mi ficha por la cabeza
				juego[0] = colaj;
				exito = true;
			} else if (cabezaj == juego[1] ) {
				//Pongo mi ficha por la cabeza
				juego[1] = colaj;
				exito = true;
			} else if (colaj == juego[0]) {
				//Pongo mi ficha por la cola
				juego[0] = cabezaj;
				exito = true;
			} else if (colaj == juego[1])  {
				//Pongo mi ficha por la cola
				juego[1] = cabezaj;
				exito = true;
			} else if (juego[0] == -1 && juego[1] == -1) {
				//Pongo mi ficha al empezar el juego
				juego[0] = cabezaj;
				juego[1] = colaj;
				exito = true;
			} else {
				//No puedo poner ficha
			}
			
			//Quito mi ficha si la he podido poner
			if (exito) {
				//99 indica que es una ficha que ya he puesto y no existe
				jugador[i] = new String("99");
				//Para que si puede poner una ficha no intente poner más
				return true;
			}
		}
		
		return exito;
	}
	
	/**
	 *  contarFichas, cuenta las fichas que le quedan a un jugador
	 *  @param jugador -> las fichas de un jugador
	 *  @return El número de fichas que le quedan a un jugador, es decir, las distintas de 99
	 */
	public static int contarFichas(String[] jugador) {
		int cont=0;
		for(int i=0; i<jugador.length;i++) {
			if (!jugador[i].equals("99"))
				cont++;
		}
		
		return cont;
	}
	
	/**
	 *  valorFichas, cuenta el valor de las fichas que le quedan a un jugador
	 *  @param jugador -> las fichas de un jugador
	 *  @return El valor de las fichas que le quedan a un jugador, sumando el primero número y el segundo
	 *  de cada ficha
	 */
	public static int valorFichas(String[] jugador) {
		int cont=0;
		for(int i=0; i<jugador.length;i++) {
			if (!jugador[i].equals("99")) {
				//Sumamos el valor numérico de los dos números de la ficha
				cont += Character.getNumericValue(jugador[i].charAt(0));
				cont += Character.getNumericValue(jugador[i].charAt(1));
			}
		}
		
		return cont;
	}	
	
	/**
	 * esGanador, cuenta las fichas de cuatro jugadores y devuelve el que menos tenga y de menos valor
	 * @param j1 - fichas del jugador1
	 * @param j2 - fichas del jugador2
	 * @param j3 - fichas del jugador3
	 * @param j4 - fichas del jugador4
	 * @return La posición, 0,1,2,3 que indica el jugador que ha ganado
	 */
	public static int esGanador(String[] j1, String[] j2, String[] j3, String[] j4) {
		String[][] fichasRestantes = {j1,j2,j3,j4};
		
		//Voy calculando el menor número de piezas para saber al ganador cuántas fichas le quedan
		int menor=7;
		int aux=0;
		for(int i=0; i<fichasRestantes.length;i++) {
			aux = contarFichas(fichasRestantes[i]);	//Cuenta las fichas de cada jugador	
			if( aux < menor) {				
				menor = aux;
			}
		}

		//Si hay empate de número de fichas entre dos jugadores entonces tengo que
		//ver el valor de las piezas que le quedan a cada uno y coger el menor
		int menorValor=15;
		int posicion=-1;
		for(int i=0; i<fichasRestantes.length;i++) {
			if (contarFichas(fichasRestantes[i]) == menor) {  //Si coinciden con el de menor fichas
				aux = valorFichas(fichasRestantes[i]);		  //Miro el valor de las fichas		
				if(aux < menorValor) {
					menorValor = aux;
					posicion = i;							  //La i me indica qué jugador es
				}
			}
		}
		
		return posicion;
	}
	
	/**
	 * @param args - argumentos del main (ninguno)
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Cabeza y cola del dominó, por donde se puede poner una ficha
		int[] juego = {-1,-1};
		
		//Fichas del dominó
		String[] fichas = new String[28];	
		//Generación de las fichas del jugador
		int k = 0;
		for(int i=0; i<7; i++) {
			for(int j=i; j<7; j++) {
				fichas[k] = new String(String.valueOf(i)+String.valueOf(j));
				k++;
			}
		}
	
		pintarFichas(fichas);
		
		//Primer jugador
		String[] jugador1 = repartirJugador(fichas);		
		String[] jugador2 = repartirJugador(fichas);
		String[] jugador3 = repartirJugador(fichas);
		String[] jugador4 = repartirJugador(fichas);	
		
		System.out.println();
		System.out.println("Jugador1");
		pintarFichas(jugador1);
		System.out.println();
		System.out.println("Jugador2");
		pintarFichas(jugador2);
		System.out.println();
		System.out.println("Jugador3");
		pintarFichas(jugador3);
		System.out.println();
		System.out.println("Jugador4");
		pintarFichas(jugador4);	
		System.out.println();
		
		//pintarFichas(fichas);
		System.out.println("JUEGO --------------");
		
		boolean ponerFichas1, ponerFichas2, ponerFichas3, ponerFichas4;
		boolean ponerFichas=true;
		while (ponerFichas) {
			
			System.out.print("J1 ");
			pintarFichas(jugador1);
			System.out.print("- J2 ");
			pintarFichas(jugador2);		
			System.out.print("- J3 ");
			pintarFichas(jugador3);		
			System.out.print("- J4 ");
			pintarFichas(jugador4);
			System.out.println();
			
			//Pone cada jugador una ficha, y si no le quedan se sale y los demás no ponen
			ponerFichas1 = ponerFicha(jugador1,juego);
			if (contarFichas(jugador1) == 0) {
				break;
			} 			
			ponerFichas2 = ponerFicha(jugador2,juego);
			if (contarFichas(jugador2) == 0) {
				break;
			}
			ponerFichas3 = ponerFicha(jugador3,juego);
			if (contarFichas(jugador3) == 0) {
				break;
			}
			ponerFichas4 = ponerFicha(jugador4,juego);
			if (contarFichas(jugador4) == 0) {
				break;
			}
			
			//Si se pone alguna ficha se sigue jugando a no ser que alguien se quede sin fichas
			ponerFichas = ponerFichas1 || ponerFichas2 || ponerFichas3 || ponerFichas4;			
		}
		
		//Pinto la última tirada
		System.out.print("J1 ");
		pintarFichas(jugador1);
		System.out.print("- J2 ");
		pintarFichas(jugador2);		
		System.out.print("- J3 ");
		pintarFichas(jugador3);		
		System.out.print("- J4 ");
		pintarFichas(jugador4);
		System.out.println();
		
		System.out.println(" - Tablero: " + juego[0]+ juego[1]);
		
		//Gana el que menos piezas tenga. Si alguno ha puesto todas ya se ha salido antes del bucle
		//También tengo que ver si dos jugadores se quedan con el mismo número de piezas cuál de 
		//ellos sus piezas suman menos cantidad
		int posicion = esGanador(jugador1,jugador2,jugador3,jugador4);
		
		//La posición me indica que jugador tiene menos piezas, y si hay varios el de menos valor
		switch (posicion) {
			case 0: System.out.println("Ha ganado Jugador1"); break;
			case 1: System.out.println("Ha ganado Jugador2"); break;
			case 2: System.out.println("Ha ganado Jugador3"); break;
			case 3: System.out.println("Ha ganado Jugador4"); break;
		}	
		
	}
}
