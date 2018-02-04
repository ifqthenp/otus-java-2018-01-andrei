package com.otus.hw_01;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class RandomizedMatrix
{
    private AbstractRealDistribution distribution;

    public RandomizedMatrix(AbstractRealDistribution distribution, long seed)
    {
        this.distribution = distribution;
        distribution.reseedRandomGenerator(seed);
    }

    public RandomizedMatrix()
    {
        this(new UniformRealDistribution(-1, 1), 0L);
    }

    public void fillMatrix(RealMatrix matrix)
    {
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            matrix.setRow(i, distribution.sample(matrix.getColumnDimension()));
        }
    }

    public RealMatrix getMatrix(int numRows, int numCols)
    {
        RealMatrix output = new BlockRealMatrix(numRows, numCols);
        for (int i = 0; i < numRows; i++) {
            output.setRow(i, distribution.sample(numCols));
        }
        return output;
    }

    public void fillVector(RealVector vector)
    {
        for (int i = 0; i < vector.getDimension(); i++) {
            vector.setEntry(i, distribution.sample());
        }
    }

    public RealVector getVector(int dim)
    {
        return new ArrayRealVector(distribution.sample(dim));
    }

    public static void main(String[] args)
    {
        // build new randomized matrix
        int numRows = 3;
        int numCols = 4;
        long seed = 0L;
        RandomizedMatrix rndMatrix = new RandomizedMatrix(new NormalDistribution(0, 1), seed);
        RealMatrix matrix = rndMatrix.getMatrix(numRows, numCols);

        // get json representation of the matrix
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(matrix);

        System.out.println("Json representation of matrix:");
        System.out.println(json);
    }
}
