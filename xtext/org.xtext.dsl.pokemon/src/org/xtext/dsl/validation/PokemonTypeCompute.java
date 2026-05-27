package org.xtext.dsl.validation;

import org.eclipse.xtext.EcoreUtil2;
import org.xtext.dsl.pokemon.*;

public class PokemonTypeCompute {

    /**
     * The entry point: returns the PokemonType for any expression.
     * This handles the recursive "Complex Expression" requirement.
     */
    public PokemonType typeOf(Exp e) {
    	if (e == null) {
    		return PokemonType.VOID;
    	}
    	
        if (e instanceof NumVal) {
            return getNumericContextType(e, false);
        } else if (e instanceof FloatVal) {
            return getNumericContextType(e, true);
        } else if (e instanceof BoolVal) {
        	return PokemonType.BOOLEAN;
        } else if (e instanceof LvlRef) {
            return PokemonType.LEVEL_INT;
        } else if (e instanceof BadgeRef) {
        	return PokemonType.COUNT_INT;
        } else if (e instanceof Addition) {
            return computeArithmeticType(((Addition) e).getLeft(), ((Addition) e).getRight());
        } else if (e instanceof Multiplication) {
            return computeArithmeticType(((Multiplication) e).getLeft(), ((Multiplication) e).getRight());
        } else if (e instanceof Comparison) {
        	return computeComparison((Comparison) e);
        } else if (e instanceof Equal) {
        	return computeEqual((Equal) e);
        } else if (e instanceof And) {
        	return computeBooleanType(((And) e).getLeft(), ((And) e).getRight());
        } else if (e instanceof Or) {
        	return computeBooleanType(((Or) e).getLeft(), ((Or) e).getRight());
        }
        return PokemonType.VOID;
    }

    /**
     * Determines Nominal Type based on context.
     * If the number is inside a Player, it's Money. If inside PokemonDef, it's a Stat.
     */
    private PokemonType getNumericContextType(Exp e, boolean isFloat) {
        if (EcoreUtil2.getContainerOfType(e, Player.class) != null) {
            return isFloat ? PokemonType.VOID : PokemonType.MONEY_INT;
        }
        if (EcoreUtil2.getContainerOfType(e, PokemonDef.class) != null) {
            return isFloat ? PokemonType.STAT_FLOAT : PokemonType.STAT_INT;
        }
        if (EcoreUtil2.getContainerOfType(e, Trainer.class) != null) {
        	return isFloat ? PokemonType.VOID : PokemonType.LEVEL_INT;
        }
        return PokemonType.VOID;
    }

    /**
     * Logic for combining types (Int + Float = Float, etc.)
     */
    private PokemonType computeArithmeticType(Exp left, Exp right) {
        PokemonType leftT = typeOf(left);
        PokemonType rightT = typeOf(right);

        if (leftT.equals(PokemonType.VOID) || rightT.equals(PokemonType.VOID)) {
            return PokemonType.VOID;
        }

        if (!leftT.getDomain().equals(rightT.getDomain()) &&
            !leftT.getDomain().equals(PokemonType.DOMAIN_LEVEL) &&
            !rightT.getDomain().equals(PokemonType.DOMAIN_LEVEL)) {
            return PokemonType.VOID;
        }

        boolean resultIsFloat = leftT.isFloat() || rightT.isFloat();

        String resultDomain;

        if (leftT.getDomain().equals(PokemonType.DOMAIN_LEVEL)) {
            resultDomain = rightT.getDomain();
        } else {
            resultDomain = leftT.getDomain();
        }

        PokemonType result = new PokemonType(resultDomain, resultIsFloat);
        return rejectDecimalMoney(result);
    }

    private PokemonType rejectDecimalMoney(PokemonType type) {
        if (type.getDomain().equals(PokemonType.DOMAIN_MONEY) && type.isFloat()) {
            return PokemonType.VOID;
        }
        return type;
    }
    
    private PokemonType computeComparison(Comparison compare) {
    	if (compare.getRight() == null) {
    		return typeOf(compare.getLeft());
    	}
    	
    	PokemonType leftType = typeOf(compare.getLeft());
    	PokemonType rightType = typeOf(compare.getRight());
    	
    	if (isNumeric(leftType) && isNumeric(rightType)) {
    		return PokemonType.BOOLEAN;
    	}
    	return PokemonType.VOID;
    }
    
    private PokemonType computeEqual(Equal equal) {
    	if (equal.getRight() == null) {
    		return typeOf(equal.getLeft());
    	}
    	
    	PokemonType leftType = typeOf(equal.getLeft());
    	PokemonType rightType = typeOf(equal.getRight());
    	
    	if (leftType.equals(rightType)) {
    		return PokemonType.BOOLEAN;
    	}
    	return PokemonType.VOID;
    }
    
    private PokemonType computeBooleanType(Exp left, Exp right) {
    	if (right == null) {
    		return typeOf(left);
    	}
    	
    	PokemonType leftType = typeOf(left);
    	PokemonType rightType = typeOf(right);
    	
    	if (PokemonType.BOOLEAN.equals(leftType) && PokemonType.BOOLEAN.equals(rightType)) {
    		return PokemonType.BOOLEAN;
    	}
    	return PokemonType.VOID;
    }
    
    private boolean isNumeric(PokemonType type) {
    	return type.getDomain().equals(PokemonType.DOMAIN_STAT) 
    			|| type.getDomain().equals(PokemonType.DOMAIN_LEVEL) 
    			|| type.getDomain().equals(PokemonType.DOMAIN_MONEY)
    			|| type.getDomain().equals(PokemonType.DOMAIN_COUNT);
    }
}