import jdk.jshell.spi.ExecutionControl;

import java.util.Date;
import java.util.Random;

public class Individual {

    private final int[] genes;
    private int fitnessScore;

    /**
     * Representation of an individual with pseudo-randomly generated genes
     *
     * @param numberOfGenes sets the number of genes of this individual
     */
    public Individual(int numberOfGenes) {
        this.genes = new int[numberOfGenes];
        Random rand = new Random();
        rand.setSeed(new Date().getTime());
        for (int i = 0; i < numberOfGenes; i++)
            this.genes[i] = rand.nextInt(2);
        //this.computeFitnessScore()
    }

    /**
     * Representation of an individual with pre-computed genes
     *
     * @param genes an array of genes
     */
    public Individual(int[] genes) {
        this.genes = genes;
        //this.computeFitnessScore()
    }

    /**
     * Mutation of one individual with 1% chance
     */
    public void mutation(){
        // 1% chance for mutation
        if (new Random().nextInt(100) == 0){
            // Set random gene
            int i = new Random().nextInt(this.genes.length);
            if (this.genes[i] == 1){
                this.genes[i] = 0;
            } else {
                this.genes[i] = 1;
            }
        }
    }

    /**
     * Self compute the fitness score
     */
    public void computeFitnessScore() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method computeFitnessScore has not been implemented yet.");
    }

    /**
     * Selects a bit in the genes array and flips it to either 1 or 0
     *
     * @param geneIndex index of the gene that needs to be flipped
     */
    public void geneFlip(int geneIndex) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method computeFitnessScore has not been implemented yet.");
    }

    public int[] getGenes() {
        return genes;
    }

    public int computeFitness() {
        fitnessScore = 0;
        int j = 0;
        for (int i = this.genes.length - 1; 0 <= i; i--) {
            if (genes[i] == 1) {
                fitnessScore += Math.pow(2, j);
            }
            j++;
        }
        return fitnessScore;
    }

    @Override
    public String toString() {
        String ret = "Genes: '";
        for (int i = 0; i < genes.length; i++) {
            ret += genes[i];
        }
        ret += "'";

        ret += "\nFitness: '" + computeFitness() + "'\n";

        return ret;
    }
}
