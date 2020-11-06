import jdk.jshell.spi.ExecutionControl;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Individual {

    private int[] genes;
    private int fitnessScore;

    /**
     * Representation of an individual with pseudo-randomly generated genes
     * @param numberOfGenes sets the number of genes of this individual
     */
    public Individual(int numberOfGenes)
    {
        this.genes = new int[numberOfGenes];
        Random rand = new Random();
        rand.setSeed(new Date().getTime());
        for(int i=0; i<numberOfGenes; i++)
            this.genes[i] = rand.nextInt(2);
        //this.computeFitnessScore()
    }

    /**
     * Representation of an individual with pre-computed genes
     * @param genes an array of genes
     */
    public Individual(int[] genes)
    {
        this.genes = genes;
        //this.computeFitnessScore()
    }

    /**
     * Self compute the fitness score
     */
    public void computeFitnessScore() throws ExecutionControl.NotImplementedException
    {
        throw new ExecutionControl.NotImplementedException("Method computeFitnessScore has not been implemented yet.");
    }

    /**
     * Selects a bit in the genes array and flips it to either 1 or 0
     * @param geneIndex index of the gene that needs to be flipped
     */
    public void geneFlip(int geneIndex) throws ExecutionControl.NotImplementedException
    {
        throw new ExecutionControl.NotImplementedException("Method computeFitnessScore has not been implemented yet.");
    }

    public int[] getGenes()
    {
        return genes;
    }

    @Override
    public String toString()
    {
        return "Individual{" +
                "genes=" + Arrays.toString(genes) +
                '}';
    }
}
