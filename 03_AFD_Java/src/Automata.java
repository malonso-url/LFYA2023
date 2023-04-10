import java.util.Scanner;

/**
 * 
 */

/**
 * @author MAAG
 *
 */
public class Automata {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		System.out.println("Ingrese el programa");
		Scanner in = new Scanner(System.in);
		String program = in.nextLine() + " ";
		
		int index = 0;
		int actual_state = 0;
		int[] final_states = {0,1,2,4};
		
		String command = "";
		
		while (index < program.length() ) {
			char lexeme = program.charAt(index);
			String symbol = identify_SET(lexeme);
			
			if (symbol.equals("")) {
				symbol = identify_TERMINAL(lexeme);
			}
			
			if (symbol.equals("")) {
				System.out.println("Simbolo no reconocido");
				break;
			}
				
			
			switch (actual_state) {
			
				case 0:{
					
					
					switch (symbol)
					{
						case "DIGITO":{
							actual_state = 1;
							command += lexeme;
						}break;
						
						case "LETRA":{
							actual_state = 4;
							command += lexeme;
						}break;
						
						case "=":{
							actual_state = 2;
							command += lexeme;
						}break;
						
						case ":":{
							actual_state = 3;
							command += lexeme;
						}break;
						
						case "BLANK_SPACE":{
							actual_state = 0;
							command = "";
						}break;
						
						default:{
							System.out.println("Simbolo no reconocido");
						}break;
					
					}
					
					
				}break;
				
				case 1:{
					
					
					switch (symbol)
					{
						case "DIGITO":{
							actual_state = 1;
							command += lexeme;
						}break;
						
						case "LETRA":{
							actual_state = 4;
							command += lexeme;
						}break;
						
						case "BLANK_SPACE":{
							System.out.println("TOKEN 1");
							actual_state = 0;
							command = "";
						}break;
						
						default:{
							System.out.println("Se esperaba " + " LETRA " + " DIGITO ");
						}break;
					
					}
					
					
				}break;
				
				case 2:{
					
					switch (symbol)
					{
						
						case "BLANK_SPACE":{
							if (command.equals(":="))
								System.out.println("TOKEN 3");
							else 
								System.out.println("TOKEN 2");
							actual_state = 0;
							command = "";
						}break;
						
						default:{
							System.out.println("Simbolo no reconocido");
						}break;
					
					}
					
				}break;
				
				case 3:{
					
					switch (symbol)
					{
						
						case "=":{
							actual_state = 2;
							command += lexeme;
						}break;
						
						default:{
							System.out.println("Se esperaba " + " = ");
						}break;
					
					}
					
				}break;
				
				case 4:{
					
					switch (symbol)
					{
						case "DIGITO":{
							actual_state = 4;
							command += lexeme;
						}break;
						
						case "LETRA":{
							actual_state = 4;
							command += lexeme;
						}break;
						
						case "BLANK_SPACE":{
							System.out.println(RESERVADAS(command));
							actual_state = 0;
							command = "";
						}break;
						
						default:{
							System.out.println("Se esperaba " + " DIGITO " + " LETRA ");
						}break;
					
					}
					
				}break;
			
			}
			
			
			index++;
			
		}
		
		if (isFinalState(actual_state, final_states) && index == program.length()) {
			System.out.println("PROGRAMA CORRECTO");
		} else {
			System.out.println("FALLO EN EL PROGRAMA");
		}

	}
	
	static boolean isFinalState(int state, int[] final_states) {
		for (int i = 0; i < final_states.length; i++) {
			if (final_states[i] == state)
				return true;
		}
		return false;
	}
	
	static String identify_TERMINAL(char lexeme) {
		if (lexeme == '=')
			return "=";
		
		if (lexeme == ':')
			return ":";
		
		if (lexeme == ' ')
			return "BLANK_SPACE";
		
		return "";
	}
	
	static String identify_SET(char lexeme) {
		
		int lexeme_value = (int)lexeme;
		
		//SET LETRA
		int LETRA1_INFERIOR = (int)'A';
		int LETRA1_SUPERIOR = (int)'Z';
		int LETRA2_INFERIOR = (int)'a';
		int LETRA2_SUPERIOR	= (int)'z';
		int LETRA3_ONLY = (int)'_';
		if (lexeme_value >= LETRA1_INFERIOR && lexeme_value <= LETRA1_SUPERIOR)
			return "LETRA";
		
		if (lexeme_value >= LETRA2_INFERIOR && lexeme_value <= LETRA2_SUPERIOR)
			return "LETRA";
		
		if (lexeme_value == LETRA3_ONLY)
			return "LETRA";
		
		//SET DIGITO
		int DIGITO1_INFERIOR = (int)'0';
		int DIGITO1_SUPERIOR = (int)'9';
		if (lexeme_value >= DIGITO1_INFERIOR && lexeme_value <= DIGITO1_SUPERIOR)
			return "DIGITO";
		
		return "";
		
	}
	
	static String RESERVADAS(String command) {
		if (command.equalsIgnoreCase("PROGRAM"))
			return "TOKEN 5";
		
		if (command.equalsIgnoreCase("INCLUDE"))
			return "TOKEN 6";
		
		if (command.equalsIgnoreCase("CONST"))
			return "TOKEN 7";
		
		if (command.equalsIgnoreCase("TYPE"))
			return "TOKEN 8";
		
		return "TOKEN 4";
	}


}
