package br.unipar.frameworksweb;

// Enum que representa os itens que podem ser colocados na mochila
public enum Item {

    ITEM1("BARRACA", 3, 10),
    ITEM2("FÓSFORO",1, 10),
    ITEM3("GARRAFA",1, 5),
    ITEM4("COMIDA",3, 10),
    ITEM5("COLCHONETE",2, 6),
    ITEM6("FOGAREIRO",2, 8),
    ITEM7("LAMPIÃO",1, 3),
    ITEM8("FACA",1, 10),
    ITEM9("TRALHA PESCA",3, 2),
    ITEM10("ROUPAS",2, 6);

    private String descricao;

    private int peso;
    private int valor;

    private Item(String descricao, int peso, int valor) {
        this.descricao = descricao;
        this.peso = peso;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPeso() {
        return peso;
    }

    public int getImportancia() {
        return valor;
    }
}
