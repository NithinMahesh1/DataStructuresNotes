package evaluator.arith;

import language.Operand;
import language.Operator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions.
 *
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;
	protected Operand<Integer> op0;
	
	/**
	 * Constructs an {@link ArithPostfixEvaluator}
	 */
	public ArithPostfixEvaluator() {
		this.stack = new LinkedStack<Operand<Integer>>(); //TODO initialize your LinkedStack
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
		// TODO use all of the things you've built so far to 
		// implement the algorithm for postfix expression evaluation
		
		ArithPostfixParser parser = new ArithPostfixParser(expr);
		for (Token<Integer> token : parser) {
			Type type = token.getType();
			switch(type){ 
			case OPERAND:
				//TODO What do we do when we see an operand?
				stack.push(token.getOperand());
				break;
			case OPERATOR:
				//TODO What do we do when we see an operator?
				Operator<Integer> operand = token.getOperator();
				if(operand.getNumberOfArguments() == 2) {
					if(stack.isEmpty() == true) {
						throw new IllegalPostfixExpressionException();
					}
					Operand<Integer> op1 = stack.top();
					stack.pop();
					operand.setOperand(1, op1);
					operand.performOperation();
					stack.push(op1);
					Operand<Integer> op2 = stack.top();
					stack.pop();
					operand.setOperand(0, op2);
					operand.performOperation();
					stack.push(op2);
				}
				if(operand.getNumberOfArguments() == 1) {
					if(stack.isEmpty() == true) {
						throw new IllegalPostfixExpressionException();
					}
					Operand<Integer> op3 = stack.top();
					stack.pop();
					operand.setOperand(0, op3);
					operand.performOperation();
					stack.push(op3);
				}
				if(operand.getNumberOfArguments() == 0) {
					throw new IllegalPostfixExpressionException();
				} 
				
				break;
			default:
				throw new IllegalStateException("Parser returned an invalid Type: " + type);
			}			
		}
		
		//TODO What do we return?
		return null;
	}

}
