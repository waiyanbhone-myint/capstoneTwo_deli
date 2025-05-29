package com.ps.signature;

import com.ps.model.*;

public class PhillyCheeseSteak extends Sandwich {
    public PhillyCheeseSteak() {
        super(BreadType.WHITE, Size.MEDIUM_8, true); // 8" white bread toasted

        addTopping(new Premium("Steak", ToppingCategory.MEAT));
        addTopping(new Premium("American Cheese", ToppingCategory.CHEESE));
        addTopping(new Regular("Peppers"));
        addTopping(new Regular("Mayo"));
    }

    @Override
    public String getDetails() {
        return "Signature Sandwich: Philly Cheese Steak\n" + super.getDetails();
    }
}
