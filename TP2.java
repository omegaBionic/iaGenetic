/**
 * Le but du TP est de trouver la valeur maximale associée à un nombre entier constitué
 * de N bits. Par exemple, si mon entier est constitué de 4 bits, sa valeur maximale est 15. S’il
 * est constitué de 2 bits, la valeur maximale est 3, etc.
 * <p>
 * Pour résoudre ce problème, vous allez devoir générer une population de Y individus,
 * faire se reproduire les individus entre eux à l’aide du système de la roulette afin de regénérer
 * une nouvelle population de Y individus, puis recommencer et ce jusqu’à atteindre la
 * convergence. La détection de la convergence se fait visuellement.
 * <p>
 * Une fois cela fait, vous devrez implémenter la sélection par tournoi et, pour les plus
 * ambitieux, implémenter la détection de la convergence et la mutation de gène. Pour rappel, la
 * mutation d’un gène correspond à un bit flip (0 -> 1 ou 1 -> 0). La convergence se calcule en
 * regardant la valeur maximale trouvée à la fin d’une génération et en la comparant à la
 * génération précédente. Si trop de générations successives possèdent la même valeur
 * maximale, c’est que la convergence a été atteinte.
 * <p>
 * L’intégralité du code est documentée, libre à vous de vous baser dessus ou non. Le
 * choix du langage reste votre.
 */

public class TP2 {

    public static void main(String[] args) {
        int convergenceSize = 1;
        int popSize = 50;
        int genesPerPop = 4;
        //Crosstype crosstype = Crosstype.ROULETTE;
        Crosstype crosstype = Crosstype.TOURNOI;
        float mutationChance = 0.05f;

        // Initial population
        Population pop = new Population(popSize, genesPerPop, crosstype, mutationChance, convergenceSize);
        //System.out.println(pop);

        for (int epoch = 0; epoch < 50; epoch++) {
            pop.generateNewPopulation();
            if (pop.isConverged()){
                System.out.println("Convergence has been achieved at: '" + epoch + "'");
                break;
            }
        }
        System.out.println(pop);
    }
}
