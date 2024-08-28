package br.unipar.frameworksweb;

import java.util.Arrays;
import java.util.Random;

public class MetaEvolutivo {

    private static double[] vetor_variancia = new double[10];
    private static Individuo[] populacao = new Individuo[10];
    private static Individuo[] populacao2 = new Individuo[10];
    private static Individuo[] populacaoNova = new Individuo[10];

    private static int laps = 10;
    private static double soma_aptidoes = 0;

    public static void main(String[] args) {
        inicializarPopulacao();
        for (int i = 0; i < 1000; i++) {
            System.out.println("Era: " + i);
            executarEra();

            for (int j = 0; j < laps; j++) {
                System.out.println("Individuo: " + j + " - " + populacaoNova[j].itens_mochila());
            }
        }
    }

    private static void executarEra() {
        calcularDesvioPadrao();
        selecionarEPerturbarIndividuos();
        ordenarECombinarPopulacoes();
    }

    private static void inicializarPopulacao() {
        for (int i = 0; i < laps; i++) {
            Individuo individuo = new Individuo();
            individuo.popularIndividuo();
            populacao[i] = individuo;
        }
    }

    private static void calcularDesvioPadrao() {
        for (int i = 0; i < laps; i++) {
            double sum = 0;
            double sumOfSquares = 0;
            int n = laps;

            for (int j = 0; j < n; j++) {
                int dp = populacao[j].getItem_mochila()[i].getVai_na_mochila();
                sum += dp;
                sumOfSquares += dp * dp;
//                System.out.println("Individuo: " + j + " Item: " + i + " vai?: " + dp);
            }

            double mean = sum / n;
            double variance = (sumOfSquares / n) - (mean * mean);
            double stdDeviation = Math.sqrt(variance);

//            System.out.println("Desvio padrão do Item " + i + ": " + stdDeviation);

            vetor_variancia[i] = stdDeviation;
        }

        System.out.println("Vetor de variância: " + Arrays.toString(vetor_variancia));
    }

    private static void selecionarEPerturbarIndividuos() {
        soma_aptidoes = 0;
        for (int i = 0; i < laps; i++) {
            soma_aptidoes += populacao[i].getAptidao();
        }
//        System.out.println("Soma das aptidões: " + soma_aptidoes);
        for (int j = 0; j < laps; j++) {
            double selecao = new Random().nextDouble(soma_aptidoes);
            System.out.println("Seleção: " + selecao);

            Individuo selecionado = new Individuo();
            double soma_parcial = 0;
            for (int i = 0; i < laps; i++) {
                soma_parcial += populacao[i].getAptidao();
                if (soma_parcial >= selecao) {
                    selecionado = populacao[i];
                    break;
                }
            }
//            System.out.println("Soma Parcial: " + soma_parcial);
//            System.out.println("Selecionado: " + selecionado.getAptidao() + " - " + selecionado.itens_mochila());

            populacao2[j] = perturbarIndividuo(selecionado.clone(), vetor_variancia);
//            System.out.println("Individuo perturbado OLHAR AQUI::::::::: " + populacao2[j].itens_mochila());
        }
    }

    private static void ordenarECombinarPopulacoes() {
        System.out.println("População 1:" + populacao[0].getAptidao() + " - População 1:" + populacao2[9].getAptidao());

        for (int i = 0; i < laps; i++) {
            System.out.println("individuo: " + i + " - " + populacao[i].getAptidao());

        }

        Arrays.sort(populacao, (o1, o2) -> Double.compare(o2.getAptidao(), o1.getAptidao()));
        Arrays.sort(populacao2, (p1, p2) -> Double.compare(p2.getAptidao(), p1.getAptidao()));

        for (int i = 0; i < laps; i++) {
            System.out.println("individuo: " + i + " - " + populacao[i].getAptidao());

        }

        System.out.println("População 1:" + populacao[0].getAptidao() + " - População 1:" + populacao2[9].getAptidao());

        System.arraycopy(populacao, 0, populacaoNova, 0, 5);
        System.arraycopy(populacao2, 0, populacaoNova, 5, 5);

//        for (int i = 0; i < laps; i++) {
//            System.out.println("População Nova: " + populacaoNova[i].itens_mochila());
//        }

        // Atualiza a população e começa tudo de novo
        populacao = populacaoNova;
    }

    private static Individuo perturbarIndividuo(Individuo selecionado, double[] vetor_variancia) {
//        System.out.println("Perturbando individuo: " + selecionado.itens_mochila());
        for (int i = 0; i < laps; i++) {
            double rand = new Random().nextDouble(0.5);

            if (rand < vetor_variancia[i]) {
                if (selecionado.getItem_mochila()[i].getVai_na_mochila() == 1) {
                    selecionado.getItem_mochila()[i].setVai_na_mochila(0);
                } else {
                    selecionado.getItem_mochila()[i].setVai_na_mochila(1);
                }
            }
        }
//        System.out.println("Individuo perturbado: " + selecionado.itens_mochila());
        return selecionado;
    }
}