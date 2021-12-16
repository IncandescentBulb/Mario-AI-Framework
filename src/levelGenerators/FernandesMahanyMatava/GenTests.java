package levelGenerators.FernandesMahanyMatava;

import engine.core.MarioLevelGenerator;

import java.util.ArrayList;
import java.util.Hashtable;

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
        //System.out.println("Simple test");
        //WeightFactor simple1 = new SimpleFactor(1.0f); ///Uncomment SimpleFactor class below before this
        //w = simple1.getWeight(gen);
        //System.out.println("simple1 test (1.0: returns " + w);
        */
        System.out.println("SCF Tests");
        /*
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
        /*
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
        */
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
        System.out.println("STF Tests");
        /*
        WeightFactor stfPrior = new SpecificTagFactor(Tag.TAG_B, 2.1f);
        w = stfPrior.getWeight(gen);
        System.out.println("stfPrior test (2.1): returns " + w);

        WeightFactor stfPriorNot = new SpecificTagFactor(Tag.TAG_C, 2.1f);
        w = stfPriorNot.getWeight(gen);
        System.out.println("stfPriorNot test (0): returns " + w);

        WeightFactor stfPriorNot2 = new SpecificTagFactor(Tag.TAG_A, 2.1f);
        w = stfPriorNot2.getWeight(gen);
        System.out.println("stfPriorNot2 test (0): returns " + w);

        WeightFactor stfAll1 = new SpecificTagFactor(Tag.TAG_A, 2.2f, "All");
        w = stfAll1.getWeight(gen);
        System.out.println("stfAll1 test (2.2): returns " + w);

        WeightFactor stfAll2 = new SpecificTagFactor(Tag.TAG_B, 2.2f, "All");
        w = stfAll2.getWeight(gen);
        System.out.println("stfAll2 test (2.2): returns " + w);

        WeightFactor stfAll3 = new SpecificTagFactor(Tag.TAG_C, 2.2f, "All");
        w = stfAll3.getWeight(gen);
        System.out.println("stfAll3 test (2.2): returns " + w);

        WeightFactor stfAllNot = new SpecificTagFactor(Tag.TAG_D, 2.2f, "All");
        w = stfAllNot.getWeight(gen);
        System.out.println("stfAllNot test (0): returns " + w);

        WeightFactor stfLastX1 = new SpecificTagFactor(Tag.TAG_C, 2.4f, "LastX", 2);
        w = stfLastX1.getWeight(gen);
        System.out.println("stfLastX1 test (2.4): returns " + w);

        WeightFactor stfLastX2 = new SpecificTagFactor(Tag.TAG_B, 2.4f, "LastX", 2);
        w = stfLastX2.getWeight(gen);
        System.out.println("stfLastX2 test (2.4): returns " + w);

        WeightFactor stfLastXNot = new SpecificTagFactor(Tag.TAG_A, 2.4f, "LastX", 2);
        w = stfLastXNot.getWeight(gen);
        System.out.println("stfLastXNot test (0): returns " + w);

        WeightFactor stfLastXAll = new SpecificTagFactor(Tag.TAG_A, 2.4f, "LastX", 5);
        w = stfLastXAll.getWeight(gen);
        System.out.println("stfLastXAll test (2.4): returns " + w);

        WeightFactor stfLastXAllNot = new SpecificTagFactor(Tag.TAG_D, 2.4f, "LastX", 5);
        w = stfLastXAllNot.getWeight(gen);
        System.out.println("stfLastXAllNot test (0): returns " + w);
        */
        System.out.println("TTF Tests");
        LevelGenerator gen2 = new levelGenerators.FernandesMahanyMatava.LevelGenerator();
        gen2.chunks = new ArrayList<>();
        gen2.chunks.add("TEST_B");
        gen2.chunks.add("TEST_A");
        gen2.chunks.add("TEST_B");
        /*

        WeightFactor ttfA = new TotalTagFactor(Tag.TAG_A, 3.0f, 1);
        w = ttfA.getWeight(gen2);
        System.out.println("ttfA test (3.0): returns " + w);

        WeightFactor ttfB = new TotalTagFactor(Tag.TAG_B, 3.0f, 2);
        w = ttfB.getWeight(gen2);
        System.out.println("ttfB test (3.0): returns " + w);

        WeightFactor ttfBMinus = new TotalTagFactor(Tag.TAG_B, 3.0f, 1);
        w = ttfBMinus.getWeight(gen2);
        System.out.println("ttfBMinus test (3.0): returns " + w);

        WeightFactor ttfANot = new TotalTagFactor(Tag.TAG_A, 3.0f, 2);
        w = ttfANot.getWeight(gen2);
        System.out.println("ttfANot test (0.0): returns " + w);

        WeightFactor ttfBNot = new TotalTagFactor(Tag.TAG_B, 3.0f, 3);
        w = ttfBNot.getWeight(gen2);
        System.out.println("ttfBNot test (0.0): returns " + w);

        WeightFactor ttfLessA = new TotalTagFactor(Tag.TAG_A, 3.1f, 2, true);
        w = ttfLessA.getWeight(gen2);
        System.out.println("ttfLessA test (3.1): returns " + w);

        WeightFactor ttfLessB = new TotalTagFactor(Tag.TAG_B, 3.1f, 3, true);
        w = ttfLessB.getWeight(gen2);
        System.out.println("ttfLessB test (3.1): returns " + w);

        WeightFactor ttfLessANot = new TotalTagFactor(Tag.TAG_A, 3.1f, 1, true);
        w = ttfLessANot.getWeight(gen2);
        System.out.println("ttfLessANot test (0.0): returns " + w);

        WeightFactor ttfLessBNot = new TotalTagFactor(Tag.TAG_B, 3.1f, 2, true);
        w = ttfLessBNot.getWeight(gen2);
        System.out.println("ttfLessBNot test (0.0): returns " + w);
        */
        Hashtable<String, Integer> ht1 = new Hashtable<String, Integer>();
        ht1.put("One", 1);
        ht1.put("Two", 2);
        ht1.put("Zero", 0);
        ht1.put("-One", -1);
        gen2.setParameters(ht1);

        System.out.println("PMF Tests");

        WeightFactor pmfOne = new ParamMultFactor("One", 3.2f);
        w = pmfOne.getWeight(gen2);
        System.out.println("pmfOne test (3.2): returns " + w);

        WeightFactor pmfTwo = new ParamMultFactor("Two", 1.6f);
        w = pmfTwo.getWeight(gen2);
        System.out.println("pmfTwo test (3.2): returns " + w);

        WeightFactor pmfNegOne = new ParamMultFactor("-One", -3.2f);
        w = pmfNegOne.getWeight(gen2);
        System.out.println("pmfNegOne test (3.2): returns " + w);

        WeightFactor pmfNot = new ParamMultFactor("Not", -1.0f);
        w = pmfNot.getWeight(gen2);
        System.out.println("pmfNot test (0): returns " + w);

        WeightFactor pmfZero = new ParamMultFactor("Zero", -1.0f);
        w = pmfZero.getWeight(gen2);
        System.out.println("pmfZero test (0): returns " + w);
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