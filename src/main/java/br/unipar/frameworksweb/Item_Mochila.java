package br.unipar.frameworksweb;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item_Mochila implements Cloneable {

    private int vai_na_mochila = 0;
    private Item item;
    private double aptidao = 0;

    @Override
    public Item_Mochila clone() {
        try {
            return (Item_Mochila) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
