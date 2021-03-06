package com.teksystem.salestaxes.receipt.calculator.tax;

import com.teksystem.salestaxes.model.items.Item;
import com.teksystem.salestaxes.utils.Pair;

import java.math.BigDecimal;
import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

public class TaxApplierHelper {

    public static Collection<Pair<Item, BigDecimal>> addItemsTo(final TaxApplier taxApplier, final Item... items) {
        for (final Item item : items) {
            taxApplier.applyTaxOn(item);
        }
        return unmodifiableCollection(taxApplier.getTaxedItems());
    }
}