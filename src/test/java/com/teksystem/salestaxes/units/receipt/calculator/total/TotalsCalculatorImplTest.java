package com.teksystem.salestaxes.units.receipt.calculator.total;

import com.teksystem.salestaxes.model.items.Item;
import com.teksystem.salestaxes.model.items.NonTaxableImportedItem;
import com.teksystem.salestaxes.model.items.TaxableImportedItem;
import com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierImpl;
import com.teksystem.salestaxes.receipt.calculator.total.TotalsCalculator;
import com.teksystem.salestaxes.receipt.calculator.total.TotalsCalculatorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import com.teksystem.salestaxes.utils.Pair;
import org.junit.Test;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierHelper.addItemsTo;
import static com.teksystem.salestaxes.units.model.items.ItemMockHelper.mockItem;
import static com.teksystem.salestaxes.units.receipt.calculator.TestHelper.createTaxApplier;
import static com.teksystem.salestaxes.utils.CustomDecimalFormatter.format;
import static java.util.Collections.EMPTY_LIST;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TotalsCalculatorImplTest {

    @Test
    public void itShouldCalculateTotalOfSalesTaxes() {
        final TaxApplierImpl taxApplier = createTaxApplier(10.0, 5.0);
        addItemsTo(taxApplier,
                mockItem("imported box chocolates", 10.00, NonTaxableImportedItem.class),
                mockItem("imported bottle of perfume", 47.50, TaxableImportedItem.class)
        );
        final TotalsCalculator totalsCalculator = createTotalCalculator(taxApplier.getTaxedItems());

        assertThat(totalsCalculator.calculateTotalOfSalesTaxes().doubleValue(), is(7.63));
    }

    @Test
    public void itShouldCalculateTotalOfItemsIncludingTaxes() {
        final TaxApplierImpl taxApplier = createTaxApplier(10.0, 5.0);
        addItemsTo(taxApplier,
                mockItem("imported box chocolates", 10.00, NonTaxableImportedItem.class),
                mockItem("imported bottle of perfume", 47.50, TaxableImportedItem.class)
        );
        final TotalsCalculator totalsCalculator = createTotalCalculator(taxApplier.getTaxedItems());

        assertThat(format(totalsCalculator.calculateTotalOfTaxedItems()), is("65.13"));
    }

    @Test
    public void itShouldReturnZeroForSalesTaxesOfNoItem() {
        assertThat(createTotalCalculator(EMPTY_LIST).calculateTotalOfSalesTaxes().doubleValue(), is(0.0));
    }

    @Test
    public void itShouldReturnZeroAsTotalOfNoItem() {
        assertThat(createTotalCalculator(EMPTY_LIST).calculateTotalOfTaxedItems().doubleValue(), is(0.0));
    }

    private static TotalsCalculator createTotalCalculator(Iterable<Pair<Item, BigDecimal>> taxedItems) {
        return new TotalsCalculatorImpl(taxedItems);
    }
}