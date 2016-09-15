package com.spring.nikita.help_model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by nikita on 14.09.16.
 */
public class HelpCartEditor {

    @NotBlank
    private int bougthQuantity;

    public HelpCartEditor(){}

    public int getBougthQuantity() {
        return bougthQuantity;
    }

    public void setBougthQuantity(int bougthQuantity) {
        this.bougthQuantity = bougthQuantity;
    }
}
