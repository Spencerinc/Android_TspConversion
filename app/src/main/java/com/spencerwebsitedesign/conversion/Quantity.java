package com.spencerwebsitedesign.conversion;

import java.text.DecimalFormat;

/**
 * Created by cspencer1 on 2/9/2016.
 */
public class Quantity {

    final double value;
    final Unit unit; // enumerated type - a constant key to enumerate a value

    public static enum Unit {
        tsp(1.0d), tbs(0.3333d), cup(0.0208d), oz(0.1666d), pint(0.0104d), quart(0.0052d), gallon(0.0013d),
        pound(0.0125d), ml(4.9289d), liter(0.0049d), mg(5687.5d), kg(0.0057d);

        // Define that tsp is base unit of measure. That's why we set it as 1.0
        final static Unit baseUnit = tsp;

        final double byBaseUnit;

        private Unit(double inTsp){
            this.byBaseUnit = inTsp;
        }

        public double toBaseUnit(double value){
            return value / byBaseUnit;
        }

        public double fromBaseUnit(double value){
            return value * byBaseUnit;
        }
    }

    // Constructor
    public Quantity(double value, Unit unit){

        super();
        this.value = value;
        this.unit = unit;
    }

    public Quantity to(Unit newUnit){
        Unit oldUnit = this.unit;
        return new Quantity(newUnit.fromBaseUnit(oldUnit.toBaseUnit(value)), newUnit);
    }

    @Override
    public String toString(){

        // Format it to 4 decimal places
        DecimalFormat df = new DecimalFormat("#.0000");
        return df.format(value) + " " +unit.name();
    }
}
