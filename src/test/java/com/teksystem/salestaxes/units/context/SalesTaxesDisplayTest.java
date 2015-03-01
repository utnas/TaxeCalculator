package com.teksystem.salestaxes.units.context;

import com.teksystem.salestaxes.units.model.NonTaxableItem;
import com.teksystem.salestaxes.units.visitor.TaxVisitorImpl;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SalesTaxesDisplayTest {

    @Test
    public void itShouldDisplayZeroAsTaxTotalForNonItem() {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));

        assertThat(new SalesTaxesDisplay(taxApplier.getTaxedItems()).displayBill(), is("Sales Taxes: 0.0\nTotal: 0.0"));
    }

    @Test
    public void itShouldDisplayZeroAsTaxTotalForNonTaxableItem() {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));
        taxApplier.applyTaxOn(new NonTaxableItem("chocolate bar", 0.85));

        assertThat(new SalesTaxesDisplay(taxApplier.getTaxedItems()).displayBill(), is("1 chocolate bar: 0.85\nSales Taxes: 0.0\nTotal: 0.85"));
    }

    @Test
    public void itShouldDisplayTaxTotalForTaxableItem() {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));
        taxApplier.applyTaxOn(new NonTaxableItem("chocolate bar", 0.85));

        final SalesTaxesDisplay salesTaxesDisplay = new SalesTaxesDisplay(taxApplier.getTaxedItems());
        assertThat(salesTaxesDisplay.displayBill(), is("1 chocolate bar: 0.85\nSales Taxes: 0.0\nTotal: 0.85"));
    }
}