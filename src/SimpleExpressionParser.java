import java.util.function.*;

/**
 * SimpleExpressionParser Class
 */
public class SimpleExpressionParser implements ExpressionParser {
	/*
	 * Grammar:
	 * S -> A | P
	 * A -> A+M | A-M | M
	 * M -> M*E | M/E | E
	 * E -> P^E | P
	 * P -> (S) | L | V
	 * L -> <float>
	 * V -> x
	 */

	/**
	 * Parses using the given grammar
	 * @param str the string to parse into an expression tree
	 * @return Expression representing the expression
	 * @throws ExpressionParseException
	 */
	public Expression parse (String str) throws ExpressionParseException {
		str = str.replaceAll(" ", "");
		Expression expression = parseStartExpression(str);
		if (expression == null) {
			throw new ExpressionParseException("Cannot parse expression: " + str);
		}

		return new SingleChildExpression(expression, true); // Add a start Expression at the beginning for the extra newline
	}

	/**
	 * Parses the start expression into additive or parenthetical
	 * @param str Remaining String to be Parsed
	 * @return Parsed exception
	 * @throws ExpressionParseException
	 */
	protected Expression parseStartExpression (String str) throws ExpressionParseException {

		// Check if it's a parenthetical expression, could ignore, but grammar requires it
		if(!str.equals("")&& str.charAt(0) == '('){
			int parenCounter = 1;
			for(int i = 1; i < str.length();i++){
				if(str.charAt(i) == '('){
					parenCounter++;
				} else if(str.charAt(i) == ')'){
					parenCounter--;
				}
				if(parenCounter == 0 && i != str.length()-1){
					break;
				}

				if(parenCounter == 0 && i == str.length()-1 && str.charAt(i)== ')'){
					return parseParentheticalExpression(str);
				}
			}
		}

		// Otherwise start from additive expression
		return parseAdditiveExpression(str);
	}

	/**
	 * Parse the Additive Expression
	 * @param str Remaining String to be Parsed
	 * @return Parsed expression
	 * @throws ExpressionParseException
	 */
	protected Expression parseAdditiveExpression (String str) throws ExpressionParseException{

		// Count of open Parenthesis
		int parenCounter = 0;

		// If given an empty string, throw exception
		if(str.equals("")){
			throw new ExpressionParseException("Invalid String Exception");
		}

		// Loops through the string
		for(int i = 0; i < str.length(); i++){

			// Increment or decrement the counter based on open or closed parenthesis
			if(str.charAt(i) == '('){
				parenCounter++;
			} else if(str.charAt(i) == ')'){
				parenCounter--;
			}

			// If the current char is outside of all parenthesis
			if(parenCounter == 0){

				// Check if the operator matches an additive expression
				if(str.charAt(i) == '+'){
					return new DoubleChildExpression (parseStartExpression(str.substring(0, i)),
							parseStartExpression(str.substring(i+1)), str.charAt(i));

				} else if(str.charAt(i) == '-'){
					if(i - 1 == 0){
						continue;
					} else if(i+1 < str.length()){
						if(str.charAt(i+1) == '.'){
							continue;
						}
						try {
							parseLiteralExpression(str.substring(i, i+2));
						} catch(ExpressionParseException e){ // not a literal
							throw new ExpressionParseException("Invalid String Exception");
						}
						continue;
					}
					return new DoubleChildExpression (parseStartExpression(str.substring(0, i)),
							parseStartExpression(str.substring(i+1)), str.charAt(i));
				}
			}
		}

		// If it isn't an additive expression, try the multiplicative expression
		return parseMultiplicativeExpression(str);
	}

	/**
	 * Parse the Multiplicative Expression
	 * @param str Remaining String to be Parsed
	 * @return Parsed expression
	 * @throws ExpressionParseException
	 */
	protected Expression parseMultiplicativeExpression (String str) throws ExpressionParseException{
		// Count of open Parenthesis
		int parenCounter = 0;

		// If given an empty string, throw exception
		if(str.equals("")){
			throw new ExpressionParseException("Invalid String Exception");
		}

		// Loops through the string
		for(int i = 0; i < str.length(); i++){

			// Increment or decrement the counter based on open or closed parenthesis
			if(str.charAt(i) == '('){
				parenCounter++;
			} else if(str.charAt(i) == ')'){
				parenCounter--;
			}

			// If the current char is outside of all parenthesis
			if(parenCounter == 0){

				// Check if the operator matches an multiplicative expression
				if(str.charAt(i) == '*' || str.charAt(i) == '/'){
					return new DoubleChildExpression (parseStartExpression(str.substring(0, i)),
							parseStartExpression(str.substring(i+1)), str.charAt(i));

				}
			}
		}

		// If it isn't an additive expression, try the Exponential expression
		return parseExponentialExpression(str);
	}

	/**
	 * Parse the Exponential Expression
	 * @param str Remaining String to be Parsed
	 * @return Parsed expression
	 * @throws ExpressionParseException
	 */
	protected Expression parseExponentialExpression (String str) throws ExpressionParseException{
		// Count of open Parenthesis
		int parenCounter = 0;

		// If given an empty string, throw exception
		if(str.equals("")){
			throw new ExpressionParseException("Invalid String Exception");
		}

		// Loops through the string
		for(int i = 0; i < str.length(); i++){

			// Increment or decrement the counter based on open or closed parenthesis
			if(str.charAt(i) == '('){
				parenCounter++;
			} else if(str.charAt(i) == ')'){
				parenCounter--;
			}

			// If the current char is outside of all parenthesis
			if(parenCounter == 0){

				// Check if the operator matches an multiplicative expression
				if(str.charAt(i) == '^'){
					return new DoubleChildExpression (parseStartExpression(str.substring(0, i)),
							parseStartExpression(str.substring(i+1)), '^');
				}
			}
		}

		// If it isn't an additive expression, try the Exponential expression
		return parseParentheticalExpression(str);
	}

	/**
	 * Parse the Parenthetical Expression
	 * @param str Remaining String to be Parsed
	 * @return Parsed expression
	 * @throws ExpressionParseException
	 */
	protected Expression parseParentheticalExpression (String str) throws ExpressionParseException{

		if(str.equals("")){ // Empty string means an exception
			throw new ExpressionParseException("Invalid String Exception");

		} else if(str.length() >= 3 && str.charAt(0) == '(' && str.charAt(str.length()-1) == ')'){ // Checks if this is a Parenthetical Expression
			return new SingleChildExpression(parseStartExpression(str.substring(1, str.length()-1)));

		} else if(str.equals("x")){ // Check if this is a variable expression
			return parseVariableExpression(str);

		} else { // Otherwise it's a literal expression
			return parseLiteralExpression(str);
		}
	}

	/**
	 * Parse Variable Expression
	 * @param str Expression
	 * @return new NoChildExpression
	 */
	protected Expression parseVariableExpression (String str) {
		return new NoChildExpression();
	}

	/**
	 * Parse Literal Expression
	 * @param str
	 * @return
	 * @throws ExpressionParseException
	 */
	protected Expression parseLiteralExpression (String str) throws ExpressionParseException {
		// From https://stackoverflow.com/questions/3543729/how-to-check-that-a-string-is-parseable-to-a-double/22936891:
		final String Digits     = "(\\p{Digit}+)";
		final String HexDigits  = "(\\p{XDigit}+)";
		// an exponent is 'e' or 'E' followed by an optionally 
		// signed decimal integer.
		final String Exp        = "[eE][+-]?"+Digits;
		final String fpRegex    =
		    ("[\\x00-\\x20]*"+ // Optional leading "whitespace"
		    "[+-]?(" +         // Optional sign character
		    "NaN|" +           // "NaN" string
		    "Infinity|" +      // "Infinity" string

		    // A decimal floating-point string representing a finite positive
		    // number without a leading sign has at most five basic pieces:
		    // Digits . Digits ExponentPart FloatTypeSuffix
		    // 
		    // Since this method allows integer-only strings as input
		    // in addition to strings of floating-point literals, the
		    // two sub-patterns below are simplifications of the grammar
		    // productions from the Java Language Specification, 2nd 
		    // edition, section 3.10.2.

		    // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
		    "((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|"+

		    // . Digits ExponentPart_opt FloatTypeSuffix_opt
		    "(\\.("+Digits+")("+Exp+")?)|"+

		    // Hexadecimal strings
		    "((" +
		    // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
		    "(0[xX]" + HexDigits + "(\\.)?)|" +

		    // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
		    "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

		    ")[pP][+-]?" + Digits + "))" +
		    "[fFdD]?))" +
		    "[\\x00-\\x20]*");// Optional trailing "whitespace"

		if (str.matches(fpRegex)) {
			return new NoChildExpression(str);
		}
		throw new ExpressionParseException("Invalid String Exception");
	}
}
