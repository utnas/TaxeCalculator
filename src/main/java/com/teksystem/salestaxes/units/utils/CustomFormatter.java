package com.teksystem.salestaxes.units.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CustomFormatter {
    public final static MathContext MATH_CONTEXT = new MathContext(3, RoundingMode.HALF_UP);

    public static DecimalFormat getCustomDecimalFormat() {
        final DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        decimalFormat.setMinimumIntegerDigits(1);
        decimalFormat.setMaximumIntegerDigits(9);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setDecimalSeparatorAlwaysShown(true);
        return decimalFormat;
    }

    public static Double format(final BigDecimal calculatedRate) {
        return Double.valueOf(getCustomDecimalFormat().format(calculatedRate));
    }

    public static Double format(final Double calculatedRate) {
        return Double.valueOf(getCustomDecimalFormat().format(calculatedRate));
    }
}