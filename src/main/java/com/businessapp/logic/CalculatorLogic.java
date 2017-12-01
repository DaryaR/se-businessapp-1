package com.businessapp.logic;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * ********************************************************************************
 * Local implementation class (unfinished) of calculator logic.
 * <p>
 * Instance is invoked with public interface method nextToken( Token tok ) passing
 * an input token that is created at the UI from a key event. Input tokens are defined
 * in CalculatorIntf and comprised of digits [0-9,.], numeric operators [+,-,*,/,VAT]
 * and control tokens [\backspace,=,C,CE,K_1000].
 * <p>
 * Outputs are are passed through display properties:<p>
 * 	- CalculatorIntf.DISPLAY for numbers and<p>
 * 	- CalculatorIntf.SIDEAREA for VAT calculations.
 * <p>
 * Method(s):
 *	- public void nextToken( Token tok );	;process next token from UI controller
 * 
 */
class CalculatorLogic implements CalculatorLogicIntf {

	private StringBuffer dsb = new StringBuffer();

	/**
	 * Local constructor.
	 */
	CalculatorLogic() {
		nextToken( Token.K_C );		// reset buffers
	}

	/**
     * Process next token from UI controller. Tokens are defined in CalculatorIntf.java
     * <p>
     * Outputs are are passed through display properties:
     * 	- CalculatorIntf.DISPLAY for numbers and
     * 	- CalculatorIntf.SIDEAREA for VAT calculations.
     * <p>
     * @param tok the next Token passed from the UI, CalculatorViewController.
     */
	public void nextToken( Token tok ) {
                     
		String d = tok==Token.K_DOT? "." : CalculatorLogicIntf.KeyLabels[ tok.ordinal() ];
		try {
			switch( tok ) {
			case K_0: case K_1: case K_2: case K_3: case K_4:
			case K_5: case K_6: case K_7: case K_8: case K_9:
				appendBuffer( d );
				break;

			case K_1000:
				nextToken( Token.K_0 );
				nextToken( Token.K_0 );
				nextToken( Token.K_0 );
				break;

			case K_DIV:
                        case K_MUL:
                        case K_PLUS:
                        case K_MIN:
                            if (dsb.charAt(dsb.length()-1) == '*') break;
                            if (dsb.charAt(dsb.length()-1) == '+') break;
                            if (dsb.charAt(dsb.length()-1) == '-') break;
                            if (dsb.charAt(dsb.length()-1) == '/') break;
                            appendBuffer(d);
                            break;

                        case K_EQ:
                            calculate(dsb.toString());
                   
                            break;

			case K_VAT:
				/*CalculatorLogicIntf.SIDEAREA.set(
					"Brutto:  1,000.00\n" +
					VAT_RATE + "% MwSt:  159.66\n" +
					"Netto:  840.34"
				); */
                                String displayValue = dsb.toString();
                                
                                DecimalFormat f = new DecimalFormat("0.00");
                                double displayDoubleValue = Double.parseDouble(displayValue);
                                //MwSt. = (Brutto-Preis / (100 + Mehrwertsteuersatz) ) * Mehrwertsteuersatz
                                double vat = (displayDoubleValue / (100 + VAT_RATE) * VAT_RATE );
                                double netto = (displayDoubleValue - vat);                            
                                CalculatorLogicIntf.SIDEAREA.set(
                                        
                                        "Brutto:" + displayValue + "\n" + 
					VAT_RATE + "% MwSt: " + f.format(vat) + "\n" +
					"Netto: " + f.format(netto)
				);
				break;

			case K_DOT:
				appendBuffer( d );
				break;

			case K_BACK:
				dsb.setLength( Math.max( 0, dsb.length() - 1 ) );
				break;

			case K_C:
				CalculatorLogicIntf.SIDEAREA.set( "" );
			case K_CE:
				dsb.delete( 0,  dsb.length() );
				break;

			default:
			}
			String display = dsb.length()==0? "0" : dsb.toString();
			CalculatorLogicIntf.DISPLAY.set( display );

		} catch( ArithmeticException e ) {
			CalculatorLogicIntf.DISPLAY.set( e.getMessage() );
		}
	}


	/*
	 * Private method(s).
	 */
	private void appendBuffer( String d ) {
		if( dsb.length() <= DISPLAY_MAXDIGITS ) {
			dsb.append( d );
		}
	}
        
        
        public double calculate(String expression) {
                if (expression == null) return Double.MIN_VALUE;
                expression = expression.trim();
                if (expression.equals("")) return Double.MIN_VALUE;     
                StringTokenizer str = new StringTokenizer(expression, "+-*/^%", true);
                List tokens = new ArrayList(str.countTokens());
                while (str.hasMoreTokens())
                    tokens.add(str.nextToken().trim());
                while (tokens.indexOf("%") > -1) {
                    for (int n = 0; n < tokens.size(); n++)
                        calculate("%", tokens, n);
                }
                while (tokens.indexOf("^") > -1) {
                    for (int n = 0; n < tokens.size(); n++)
                        calculate("^", tokens, n);
                }
                while (tokens.indexOf("*") > -1) {
                    for (int n = 0; n < tokens.size(); n++)
                        calculate("*", tokens, n);
                }
                while (tokens.indexOf("/") > -1) {
                    for (int n = 0; n < tokens.size(); n++)
                        calculate("/", tokens, n);
                }
                while (tokens.indexOf("-") > -1) {
                    for (int n = 0; n < tokens.size(); n++)
                        calculate("-", tokens, n);
                }
                while (tokens.indexOf("+") > -1) {
                    for (int n = 0; n < tokens.size(); n++)
                        calculate("+", tokens, n);
                }
                if (tokens.size() != 1) return Double.MIN_VALUE;
                
                return toDouble((String) tokens.get(0),  Double.MIN_VALUE);  
        }

        private void calculate(String calcType, List tokens, int n) {
            String token = (String) tokens.get(n);
            if (!token.equals(calcType)) return;
            double l, r, res;
            int s, e;
            if (n - 1 == -1) {
                s = 0;
                l = 1;
            } else {
                s = n - 1;
                l = toDouble((String) tokens.get(n - 1), 1);
            }
            if (n + 1 == tokens.size()) {
                e = tokens.size() - 1;
                r = 1;
            } else {
                e = n + 1;
                r = toDouble((String) tokens.get(n + 1), 1);
            }
            if (calcType.equals("%"))
                res = Math.sqrt(r);
            else if (calcType.equals("^"))
                res = Math.pow(l, r);
            else if (calcType.equals("*"))
                res = l * r;
            else if (calcType.equals("/"))
                res = l / r;
            else if (calcType.equals("-"))
                res = l - r;
            else if (calcType.equals("+"))
                res = l + r;
            else
                res = 0;
            tokens.add(e + 1, String.valueOf(res));
            for (int i = e; i >= s; i--)
                tokens.remove(i);
            n = n + (e - s);

        }

        private double toDouble(String number, double defaultNumber) {
            try {
                return Double.parseDouble(number);
            } catch (NumberFormatException e) {}
            return defaultNumber;
        } 
}
