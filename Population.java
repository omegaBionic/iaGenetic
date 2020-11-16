import jdk.jshell.spi.ExecutionControl;

import java.util.Arrays;
import java.util.Random;

public class Population {

    private Individual[] individuals;
    private int genesPerPop;
    private Crosstype crosstype;
    private float mutationChance;
    private int popSize;

    /**
     * Representation of a population of pseudo-randomly generated individuals
     * @param popSize set the size of this population
     * @param genesPerPop sets the gene size of each individual in the pool
     * @param crosstype the crosstype to be used by this population
     * @param mutationChance chance for an individual to mutate at birth
     */
    public Population(int popSize, int genesPerPop, Crosstype crosstype, float mutationChance)
    {
        this.individuals = new Individual[popSize];
        this.genesPerPop = genesPerPop;
        this.crosstype = crosstype;
        this.mutationChance = mutationChance;
        this.popSize = popSize;
        for(int i=0; i<popSize; i++)
            this.individuals[i] = new Individual(genesPerPop);
    }

    /**
     * Representation of a population of pre-computed individuals
     * @param individuals an array of individuals
     * @param crosstype the crosstype to be used by this population
     * @param mutationChance chance for an individual to mutate at birth
     */
    public Population(Individual[] individuals, Crosstype crosstype, float mutationChance)
    {
        assert individuals.length > 0;
        this.individuals = individuals;
        this.genesPerPop = individuals[0].getGenes().length;
        this.crosstype = crosstype;
        this.mutationChance = mutationChance;
    }

    /**
     * Creates a new population using this generation's individuals
     * @return the newly generated population
     */
    public void generateNewPopulation() {
        if(this.crosstype == Crosstype.ROULETTE)
        {
            this.roulette();
        }
        else{
            // TODO
        }
    }

    /**
     * Takes 2 individuals and create 2 children using their genes
     * @param firstParent the first selected individual
     * @param secondParent the second selected individual
     * @param crosspoint index of the crosspoint
     * @return an array of 2 individuals
     */
    public Individual[] reproduceIndividuals(Individual firstParent, Individual secondParent, int crosspoint)
            throws ExecutionControl.NotImplementedException
    {
        Individual[] offsprings = new Individual[2];

//        int[] firstChildGenes = new int[genesPerPop];
//        int[] secondChildGenes = new int[genesPerPop];
//        ToDo compute the genes
//        ToDo compute a possible mutation of a gene
//        offsprings[0] = new Individual(firstChildGenes);
//        offsprings[1] = new Individual(secondChildGenes);

        return offsprings;
    }

    public void roulette(){
        // Create childs
        Individual[] childs;
        Individual[] parents;
        childs = new Individual[individuals.length];
        parents = new Individual[2];

        // FitnessSum
        int fitnessSum = 0;
        for(int i = 0; i < individuals.length; i++){
            fitnessSum += individuals[i].computeFitness();
        }
        System.out.println("fitnessSum: '" + fitnessSum + "'");

        // Sort individual
        Individual tmp;
        int is_changed = 1;

        while(is_changed != 0) {
            is_changed = 0;
            for (int i = 0; i < individuals.length-1; i++) {
                if (individuals[i].computeFitness() > individuals[i + 1].computeFitness()) {
                    tmp = individuals[i + 1];
                    individuals[i + 1] = individuals[i];
                    individuals[i] = tmp;
                    is_changed++;
                }
            }
        }
    }


    @Override
    public String toString()
    {
        String ret = "Population: ";
        for(int i = 0; i < individuals.length; i++){
            ret += "Individual: '" + i + "'\n";
            ret += individuals[i];
            ret += "\n";
        }

        ret += "\ngenesPerPop: '" + genesPerPop + "'";
        return ret;
    }
}
