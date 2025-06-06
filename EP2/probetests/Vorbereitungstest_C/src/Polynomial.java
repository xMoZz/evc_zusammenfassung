/**
 * This polynomial is an expression of one variable in the form a⋅x⁰+b⋅x¹+c⋅x²+d⋅x³+e⋅x⁴ ... .
 * More generally, it is a sum of multiple monomials of the form c⋅xᵈ where 'c' is the constant
 * coefficient, 'x' is the variable and 'd' the constant degree of the monomial. 'c' and 'd'
 * can take on any value (also a negative value or 0).
 */
//
// TODO: Complete the methods in 'Polynomial'.
//       You can define further classes and methods if needed.
//       Do NOT use the Java-Collection framework in 'Polynomial' or any other class.
//
public class Polynomial {

    //TODO: additional variables, constructors and methods must be private.
    private ConstConstTreeMap monominals;


    /**
     * Initializes this polynomial using the specified map in which in each key-value pair
     * represents one monomial. The degree of a monomial (key of 'IntConst') is associated with
     * the coefficient of the monomial (value of 'IntConst'). Later changes of 'map' will
     * not affect 'this'.
     *
     * @param map, the map from which this polynomial is constructed, map != null,
     *             'map' is not empty.
     */
    public Polynomial(ConstConstTreeMap map) {
        if (map == null) return;
        this.monominals = map;
    }

    /**
     * Returns a new polynomial representing the sum of 'this' and 'p'.
     *
     * @param p the second summand, p != null.
     * @return a new polynomial representing the sum of 'this' and 'p'.
     */
    public Polynomial plus(Polynomial p) {
        //TODO: implement method.
        IntConstQueue q = new IntConstQueue();
        p.monominals.addAllKeysTo(q);

        ConstConstTreeMap result = new ConstConstTreeMap(monominals);


        for (int i = 0; i < q.size(); i++) {
            IntConst current_key = q.poll();
            if (this.monominals.get(current_key) == null) {
                result.put(current_key, p.monominals.get(current_key));
            } else {
                result.put(current_key, p.monominals.get(current_key).plus(this.monominals.get(current_key)));
            }
        }
        return new Polynomial(result);
    }

    /**
     * Returns a string representation of this polynomial in which the terms appear ordered
     * according to their degree and there is at most one term with a specific degree (terms of
     * the same degree are represented by their sum). The terms are separated by " + ".
     * Monomials with coefficient of 0 do not appear in the string representation
     * (if all monomials are 0 an empty string is returned).
     * Example: "-2x^3 + 1x^4 + -3x^5"
     *
     * @return the string representation of this polynomial.
     */
    public String toString() {
        String result = "";

        IntConstQueue q = new IntConstQueue();
        this.monominals.addAllKeysTo(q);

        for (int i = 0; i < q.size(); i++) {
            IntConst currentDegree = q.poll();

            if (!(this.monominals.get(currentDegree).isEqual(new IntConst(0))))
                result += ("" + this.monominals.get(currentDegree) + "x^" + currentDegree + " + ");
        }
        IntConst currentDegree = q.poll();

        if (!(this.monominals.get(currentDegree).isEqual(new IntConst(0)))) {
            result += ("" + this.monominals.get(currentDegree) + "x^" + currentDegree);
        }
        return result;
    }
}

// TODO: define further classes, if needed (either here or in a separate file).