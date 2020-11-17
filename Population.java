import java.util.Random;

public class Population {

    private final int genesPerPop;
    private final Crosstype crosstype;
    private final float mutationChance;
    private Individual[] individuals;
    private int popSize;

    /**
     * Representation of a population of pseudo-randomly generated individuals
     *
     * @param popSize        set the size of this population
     * @param genesPerPop    sets the gene size of each individual in the pool
     * @param crosstype      the crosstype to be used by this population
     * @param mutationChance chance for an individual to mutate at birth
     */
    public Population(int popSize, int genesPerPop, Crosstype crosstype, float mutationChance) {
        this.individuals = new Individual[popSize];
        this.genesPerPop = genesPerPop;
        this.crosstype = crosstype;
        this.mutationChance = mutationChance;
        this.popSize = popSize;
        for (int i = 0; i < popSize; i++)
            this.individuals[i] = new Individual(genesPerPop);
    }

    /**
     * Representation of a population of pre-computed individuals
     *
     * @param individuals    an array of individuals
     * @param crosstype      the crosstype to be used by this population
     * @param mutationChance chance for an individual to mutate at birth
     */
    public Population(Individual[] individuals, Crosstype crosstype, float mutationChance) {
        assert individuals.length > 0;
        this.individuals = individuals;
        this.genesPerPop = individuals[0].getGenes().length;
        this.crosstype = crosstype;
        this.mutationChance = mutationChance;
    }

    /**
     * Creates a new population using this generation's individuals
     *
     * @return the newly generated population
     */
    public void generateNewPopulation() {
        if (this.crosstype == Crosstype.ROULETTE) {
            this.roulette();
        } else {
            this.tournoi();
        }
    }

    /**
     * Takes 2 individuals and create 2 children using their genes
     *
     * @param firstParent  the first selected individual
     * @param secondParent the second selected individual
     * @param crosspoint   index of the crosspoint
     * @return an array of 2 individuals
     */
    public Individual reproduceIndividuals(Individual firstParent, Individual secondParent, int crosspoint) {
        int[] childGenes = new int[genesPerPop];

        // Get childGenes from first parent
        for (int i = 0; i < crosspoint; i++) {
            childGenes[i] = firstParent.getGenes()[i];
        }

        // Get childGenes from second parent
        for (int i = crosspoint; i < genesPerPop; i++) {
            childGenes[i] = secondParent.getGenes()[i];
        }

//        DEBUG parents and child
//        System.out.println("crosspoint: '" + crosspoint + "'");
//        for(int i=0; i < genesPerPop; i++){
//            System.out.println("firstParent.getGenes()[" + i + "]: '" + firstParent.getGenes()[i] + "'");
//        }
//        System.out.println("-----------");
//        for(int i=0; i < genesPerPop; i++){
//            System.out.println("secondParent.getGenes()["+ i + "]: '" + secondParent.getGenes()[i] + "'");
//        }
//        System.out.println("-----------");
//        for(int i=0; i < genesPerPop; i++){
//            System.out.println("childGenes[" + i + "]: '" + childGenes[i] + "'");
//        }
//        System.out.println("------------------------");

        Individual offsprings = new Individual(childGenes);
        return offsprings;
    }

    public void tournoi() {
        // Create childs
        Individual[] childs;
        Individual[] parents;
        childs = new Individual[individuals.length];
        parents = new Individual[2];

        // For all childs
        for(int i = 0; i < individuals.length; i += 2){
            // Get parents in individuals
            for(int j = 0; j < 2; j++){
                int nbIndTour = (new Random().nextInt(individuals.length)+1);
                int choice = 0;
                Individual[] selection;
                selection = new Individual[nbIndTour];

                for(int k = 0; k < nbIndTour; k++){
                    choice = new Random().nextInt(individuals.length);
//                    System.out.println("kase: '" + kase + "'");
                    selection[k] = individuals[choice];
//                    System.out.println("individuals[kase]: '" + individuals[kase] + "'");
                }

                Individual alpha = selection[0];
                for(int k = 0; k < nbIndTour; k++){
                    if(alpha.computeFitness() <= selection[k].computeFitness()){
                        alpha = selection[k];
                    }
                }
                parents[j] = alpha;
            }
            // Generate child and append
            int CrossPointRand = new Random().nextInt(genesPerPop);
            childs[i] = this.reproduceIndividuals(parents[0], parents[1], CrossPointRand);
            if (i < individuals.length - 1) {
                childs[i + 1] = this.reproduceIndividuals(parents[1], parents[0], CrossPointRand);
            }
        }
        // Affect childs to new population
        individuals = childs;
    }


    public void roulette() {
        // Create childs
        Individual[] childs;
        Individual[] parents;
        childs = new Individual[individuals.length];
        parents = new Individual[2];

        // FitnessSum
        int fitnessSum = 0;
        for (int i = 0; i < individuals.length; i++) {
            fitnessSum += individuals[i].computeFitness();
        }
        System.out.println("fitnessSum: '" + fitnessSum + "'");

        // Sort individual
        Individual tmp;
        int is_changed = 1;

        while (is_changed != 0) {
            is_changed = 0;
            for (int i = 0; i < individuals.length - 1; i++) {
                if (individuals[i].computeFitness() > individuals[i + 1].computeFitness()) {
                    tmp = individuals[i + 1];
                    individuals[i + 1] = individuals[i];
                    individuals[i] = tmp;
                    is_changed++;
                }
            }
        }

        // For all childs
        for (int i = 0; i < individuals.length; i += 2) {
            System.out.println("child number : '" + i + "'");
            // Get parents
            for (int j = 0; j < 2; j++) {
                // Calculate rand with fitness
                if (fitnessSum == 0) {
                    fitnessSum = 1;
                }
                int rand = (new Random().nextInt(fitnessSum));
                System.out.println("individuals rand: '" + rand + "'");

                int k = 0;
                int count = 0;
                while (count < rand) {
                    count += individuals[k].computeFitness();
                    k++;
                }
                if (k == 0) {
                    k = 1;
                }
//                DEBUG
//                System.out.println("j: '" + j + "'");
//                System.out.println("k: '" + k + "'");
//                System.out.println("individuals[j - 1]: '" + individuals[j - 1] + "'");

                // Save two parents
                parents[j] = individuals[k - 1];
                System.out.println("parents[" + j + "]: '" + parents[j] + "'");
            }
            // Generate child and append
            int CrossPointRand = new Random().nextInt(genesPerPop);
            childs[i] = this.reproduceIndividuals(parents[0], parents[1], CrossPointRand);
            if (i < individuals.length - 1) {
                childs[i + 1] = this.reproduceIndividuals(parents[1], parents[0], CrossPointRand);
            }
        }

        // Affect childs to new population
        individuals = childs;
    }


    @Override
    public String toString() {
        String ret = "Population: ";
        for (int i = 0; i < individuals.length; i++) {
            ret += "Individual: '" + i + "'\n";
            ret += individuals[i];
            ret += "\n";
        }

        ret += "\ngenesPerPop: '" + genesPerPop + "'";
        return ret;
    }
}
