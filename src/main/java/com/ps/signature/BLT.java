package com.ps.signature;

import com.ps.model.*;

public class BLT extends Sandwich {
    public BLT() {
        super(BreadType.WHITE, Size.MEDIUM_8, true); // 8" white bread toasted

        addTopping(new Premium("Bacon", ToppingCategory.MEAT));
        addTopping(new Premium("Cheddar", ToppingCategory.CHEESE));
        addTopping(new Regular("Lettuce"));
        addTopping(new Regular("Tomato"));
        addTopping(new Regular("Ranch"));
    }

    @Override
    public String getDetails() {
        return "Signature Sandwich: BLT\n" + super.getDetails();
    }
}
