package br.unipar.frameworksweb;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Random;

@Getter
@Setter
public class Individuo implements Cloneable {
    // Vetor que representa os itens que podem ser colocados na mochila
    private Item_Mochila[] item_mochila = new Item_Mochila[10];

    private static int C = 1;

    // Variável que representa a aptidão do indivíduo
    private double aptidao = 0;

    public static void main(String[] args) {
        Individuo individuo = new Individuo();
        individuo.popularIndividuo();
    }

    public void popularIndividuo() {
        Random random = new Random();
        int soma_peso = 0;
        int excesso_peso = 0;

        // Preenche o vetor de itens da mochila
        for (int i = 0; i < item_mochila.length; i++) {
            item_mochila[i] = new Item_Mochila();
            item_mochila[i].setItem(Item.valueOf("ITEM" + (i + 1)));

            item_mochila[i].setVai_na_mochila(random.nextInt(2));
            soma_peso += item_mochila[i].getItem().getPeso();

//            System.out.println("Item: " + item_mochila[i].getItem().getDescricao() + " vai na mochila? " + item_mochila[i].getVai_na_mochila());


            if(soma_peso > 12) {
                excesso_peso = soma_peso - 12;
            }
//            System.out.println("soma de peso: " + soma_peso);
//            System.out.println("excesso de peso: " + excesso_peso);

            //se o item vai na mochila, calcula a aptidao
            if(item_mochila[i].getVai_na_mochila() == 1) {
                aptidao += (item_mochila[i].getItem().getImportancia()/ item_mochila[i].getItem().getPeso()) - (C * excesso_peso);
                System.out.println("Item: " + item_mochila[i].getItem().getDescricao() + " vai na mochila - " + aptidao);
            }
        }

        // Se a aptidão for negativa, atribui 0.1
        if(aptidao < 0.0) {
            aptidao = 0.1;
        }
//        System.out.println("mochila aptidao: " + aptidao);
//        System.out.println("individuo: " + itens_mochila());
    }

    public String itens_mochila() {
        StringBuilder itens = new StringBuilder();
        for (Item_Mochila item : item_mochila) {
            if(item.getVai_na_mochila() == 1)
                itens.append(item.getItem().getDescricao()).append(", ");
            else
                itens.append("-".repeat(item.getItem().getDescricao().length())).append(", ");
        }
        if(itens.length() == 0) {
            return "Mochila vazia";
        }
        return itens.toString().substring(0, itens.length() - 2);
    }

    @Override
    public Individuo clone() {
        try {
            Individuo cloned = (Individuo) super.clone();
            cloned.item_mochila = Arrays.copyOf(this.item_mochila, this.item_mochila.length);
            for (int i = 0; i < this.item_mochila.length; i++) {
                cloned.item_mochila[i] = this.item_mochila[i].clone();
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
