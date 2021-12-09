package levelGenerators.FernandesMahanyMatava;

import engine.core.MarioLevelGenerator;

import java.util.ArrayList;

public class GenTests {
    public static void main(String[] args) {
        System.out.println("Start of GenTests");
        float w = -2.0f;
        LevelGenerator gen = new levelGenerators.FernandesMahanyMatava.LevelGenerator();
        gen.chunks = new ArrayList<>();
        gen.chunks.add("TEST_A");
        gen.chunks.add("TEST_B");
        gen.chunks.add("TEST_C");
        /*
        //WeightFactor simple1 = new SimpleFactor(1.0f); ///Uncomment SimpleFactor class below before this
        //w = simple1.getWeight(gen);
        //System.out.println("simple1 test (1.0: returns " + w);

        WeightFactor scfPrior = new SpecificChunkFactor("TEST_B", 1.1f);
        w = scfPrior.getWeight(gen);
        System.out.println("scfPrior test (1.1): returns " + w);

        WeightFactor scfPriorNot = new SpecificChunkFactor("TEST_C", 1.1f);
        w = scfPriorNot.getWeight(gen);
        System.out.println("scfPriorNot test (0): returns " + w);

        WeightFactor scfPriorNot2 = new SpecificChunkFactor("TEST_A", 1.1f);
        w = scfPriorNot2.getWeight(gen);
        System.out.println("scfPriorNot2 test (0): returns " + w);

        WeightFactor scfAll1 = new SpecificChunkFactor("TEST_A", 1.2f, "All");
        w = scfAll1.getWeight(gen);
        System.out.println("scfAll1 test (1.2): returns " + w);

        WeightFactor scfAll2 = new SpecificChunkFactor("TEST_B", 1.2f, "All");
        w = scfAll2.getWeight(gen);
        System.out.println("scfAll2 test (1.2): returns " + w);

        WeightFactor scfAll3 = new SpecificChunkFactor("TEST_C", 1.2f, "All");
        w = scfAll3.getWeight(gen);
        System.out.println("scfAll3 test (1.2): returns " + w);

        WeightFactor scfAllNot = new SpecificChunkFactor("BRICKS_1", 1.2f, "All");
        w = scfAllNot.getWeight(gen);
        System.out.println("scfAllNot test (0): returns " + w);
        */
        WeightFactor scfFirstX1 = new SpecificChunkFactor("TEST_A", 1.3f, "FirstX", 2);
        w = scfFirstX1.getWeight(gen);
        System.out.println("scfFirstX1 test (1.3): returns " + w);

        WeightFactor scfFirstX2 = new SpecificChunkFactor("TEST_B", 1.3f, "FirstX", 2);
        w = scfFirstX2.getWeight(gen);
        System.out.println("scfFirstX2 test (1.3): returns " + w);

        WeightFactor scfFirstXNot = new SpecificChunkFactor("TEST_C", 1.3f, "FirstX", 2);
        w = scfFirstXNot.getWeight(gen);
        System.out.println("scfFirstXNot test (0): returns " + w);

        WeightFactor scfFirstXAll = new SpecificChunkFactor("TEST_C", 1.3f, "FirstX", 5);
        w = scfFirstXAll.getWeight(gen);
        System.out.println("scfFirstXAll test (1.3): returns " + w);

        WeightFactor scfFirstXAllNot = new SpecificChunkFactor("BRICKS_1", 1.3f, "FirstX", 5);
        w = scfFirstXAllNot.getWeight(gen);
        System.out.println("scfFirstXAllNot test (0): returns " + w);
        /*
        WeightFactor scfLastX1 = new SpecificChunkFactor("TEST_C", 1.4f, "LastX", 2);
        w = scfLastX1.getWeight(gen);
        System.out.println("scfLastX1 test (1.4): returns " + w);

        WeightFactor scfLastX2 = new SpecificChunkFactor("TEST_B", 1.4f, "LastX", 2);
        w = scfLastX2.getWeight(gen);
        System.out.println("scfLastX2 test (1.4): returns " + w);

        WeightFactor scfLastXNot = new SpecificChunkFactor("TEST_A", 1.4f, "LastX", 2);
        w = scfLastXNot.getWeight(gen);
        System.out.println("scfLastXNot test (0): returns " + w);

        WeightFactor scfLastXAll = new SpecificChunkFactor("TEST_A", 1.4f, "LastX", 5);
        w = scfLastXAll.getWeight(gen);
        System.out.println("scfLastXAll test (1.4): returns " + w);

        WeightFactor scfLastXAllNot = new SpecificChunkFactor("BRICKS_1", 1.4f, "LastX", 5);
        w = scfLastXAllNot.getWeight(gen);
        System.out.println("scfLastXAllNot test (0): returns " + w);
        */

        System.out.println("End of GenTests");
    }
}
/*
class SimpleFactor implements WeightFactor{
    float held = -1.0f;

    SimpleFactor(float toHold){
        held = toHold;
    }

    @Override
    public float getWeight(LevelGenerator generator) {
        return(held);
    }
}*/