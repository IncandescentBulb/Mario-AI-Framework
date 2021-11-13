package levelGenerators.FernandesMahanyMatava;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LevelGenerator implements MarioLevelGenerator {

    /// Chunk data
    /// each row is assumed to be the same width
    /// chunks are aligned by the bottom row if they have different heights
    /// no two chunks can be exactly the same

    static String BLANK =
            "----" + "\n" +
            "----" + "\n" +
            "----" + "\n" +
            "----";

    static String START =
            "----" + "\n" +
            "----" + "\n" +
            "----" + "\n" +
            "XXXX";

    static String CHUNK_1 =
            "----" + "\n" +
            "-!S-" + "\n" +
            "----" + "\n" +
            "XXXX";

    static String CHUNK_2 =
            "----" + "\n" +
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "XXXX";

    static String CHUNK_3 =
            "----" + "\n" +
            "----" + "\n" +
            "--g-" + "\n" +
            "XXXX";

    static String END =
            "----" + "\n" +
            "-F--" + "\n" +
            "-#--" + "\n" +
            "XXXX";

    static String END_STAIRS =
            "-------##-------" + "\n" +
            "------###-------" + "\n" +
            "-----####-------" + "\n" +
            "----#####-------" + "\n" +
            "---######-------" + "\n" +
            "--#######-------" + "\n" +
            "-########-------" + "\n" +
            "#########-------" + "\n" +
            "XXXXXXXXXXXXXXXX" + "\n" +
            "XXXXXXXXXXXXXXXX";

    static String PYRAMID_FLOOR =
            "---#--#---" + "\n" +
            "--##--##--" + "\n" +
            "-###--###-" + "\n" +
            "####--####" + "\n" +
            "XXXXXXXXXX" + "\n" +
            "XXXXXXXXXX";

    static String PYRAMID_PIT =
            "---##--#---" + "\n" +
            "--###--##--" + "\n" +
            "-####--###-" + "\n" +
            "#####--####" + "\n" +
            "XXXXX--XXXX" + "\n" +
            "XXXXX--XXXX";

    static String PIT =
            "-------" + "\n" +
            "XX---XX" + "\n" +
            "XX---XX";

    static String ENEMIES_1 =
            "-r-g-g-" + "\n" +
            "XXXXXXX" + "\n" +
            "XXXXXXX";

    static String FOUR_QUESTION_BLOCKS =
            "-----@-----\n" +
            "-----------\n" +
            "-----------\n" +
            "-----------\n" +
            "--!--!--!--\n" +
            "-----------\n" +
            "-----------\n" +
            "--k--------\n" +
            "XXXXXXXXXXX\n" +
            "XXXXXXXXXXX";

    static String START_HIGH_SECTION =
            "-------g----\n" +
            "---SSSSSSSSS\n" +
            "------------\n" +
            "------------\n" +
            "--g---------\n" +
            "S@S---------\n" +
            "------------\n" +
            "------------\n" +
            "------------\n" +
            "XXXXXXXXX---\n" +
            "XXXXXXXXX---";

    static String HIGH_SECTION_ENEMY_PIT =
            "--k---------\n" +
            "SSSS-g--g-SS\n" +
            "---SSSSSSSS-\n" +
            "------------\n" +
            "------------\n" +
            "------------\n" +
            "------------\n" +
            "------------\n" +
            "------------\n" +
            "------------\n" +
            "------------";

    static String HIGH_SECTION_GAP =
            "-----------g\n" +
            "SS------SSSS\n" +
            "------------\n" +
            "------------\n" +
            "------------\n" +
            "------------\n" +
            "------------\n" +
            "------------\n" +
            "------------\n" +
            "------------\n" +
            "------------";

    static String HIGH_SECTION_END =
            "------------\n" +
            "SS----------\n" +
            "----S-------\n" +
            "------------\n" +
            "------------\n" +
            "-------CSS--\n" +
            "------------\n" +
            "------------\n" +
            "-----------k\n" +
            "---XXXXXXXXX\n" +
            "---XXXXXXXXX";

    static String BRICKS_1 =
            "-SS@S-----\n" +
            "----------\n" +
            "----------\n" +
            "------k-gg\n" +
            "XXXXXXXXXX\n" +
            "XXXXXXXXXX";

    static String BRICKS_2 =
            "------!---\n" +
            "----------\n" +
            "----------\n" +
            "--!--SSS--\n" +
            "----------\n" +
            "----------\n" +
            "---g--g---\n" +
            "XXXXXXXXXX\n" +
            "XXXXXXXXXX";

    static String RAISED_START =
            "-------\n" +
            "-------\n" +
            "-----SS\n" +
            "-----SS\n" +
            "XX---XX\n" +
            "XX---XX";

    static String RAISED_ENEMIES_1 =
            "------\n" +
            "-r-gg-\n" +
            "SSSSSS\n" +
            "SSSSSS\n" +
            "XXXXXX\n" +
            "XXXXXX";

    static String RAISED_ENEMIES_2 =
            "------\n" +
            "-g--k-\n" +
            "SSSSSS\n" +
            "SSSSSS\n" +
            "XXXXXX\n" +
            "XXXXXX";

    static String RAISED_BRICKS_1 =
            "---@--\n" +
            "------\n" +
            "------\n" +
            "------\n" +
            "--SSS-\n" +
            "------\n" +
            "------\n" +
            "------\n" +
            "SSSSSS\n" +
            "SSSSSS\n" +
            "XXXXXX\n" +
            "XXXXXX";

    static String RAISED_BRICKS_2 =
            "-----g----\n" +
            "----SSS---\n" +
            "----------\n" +
            "----k-----\n" +
            "--SCS-@---\n" +
            "----------\n" +
            "----------\n" +
            "-------g--\n" +
            "SSSSSSSSSS\n" +
            "SSSSSSSSSS\n" +
            "XXXXXXXXXX\n" +
            "XXXXXXXXXX";

    static String RAISED_PIT =
            "------\n" +
            "------\n" +
            "SS---S\n" +
            "SS---S\n" +
            "XX---X\n" +
            "XX---X";

    static String RAISED_END =
            "------\n" +
            "-g----\n" +
            "SSSS--\n" +
            "SSSSS-\n" +
            "XXXXXX\n" +
            "XXXXXX";

    /**
     * Each chunk has a list of possible next chunks, and their weights.
     * The weight Floats are assumed to sum to 1.0 for any chunk.
     */
    static HashMap<String, HashMap<String, Float>> weight_maps;

    // this runs once when this class is loaded
    static {
        weight_maps = new HashMap<>();

        weight_maps.put(START, new HashMap<>(){{
            put(CHUNK_1, 1.0f);
        }});

        // read: after CHUNK_1, there's a 50% change of getting CHUNK_2 or CHUNK_3
        weight_maps.put(CHUNK_1, new HashMap<>(){{
            put(CHUNK_2, 0.5f);
            put(CHUNK_3, 0.5f);
        }});

        weight_maps.put(CHUNK_2, new HashMap<>(){{
            put(CHUNK_1, 0.5f);
            put(CHUNK_3, 0.5f);
        }});

        weight_maps.put(CHUNK_3, new HashMap<>(){{
            put(CHUNK_1, 0.6f);
            put(CHUNK_2, 0.4f);
            /*put(CHUNK_1, 0.45f);
            put(CHUNK_2, 0.45f);
            //put(END, 0.1f);*/
        }});

        // new

        weight_maps.put(START, new HashMap<>(){{
            put(START_HIGH_SECTION, 0.1f);
            put(BRICKS_1, 0.1f);
            put(BRICKS_2, 0.2f);
            put(FOUR_QUESTION_BLOCKS, 0.2f);
            put(PIT, 0.2f);
            put(ENEMIES_1, 0.2f);
        }});

        weight_maps.put(BRICKS_1, new HashMap<>(){{
            put(START_HIGH_SECTION, 0.1f);
            put(BRICKS_2, 0.2f);
            put(FOUR_QUESTION_BLOCKS, 0.2f);
            put(PIT, 0.25f);
            put(ENEMIES_1, 0.25f);
        }});

        weight_maps.put(BRICKS_2, new HashMap<>(){{
            put(START_HIGH_SECTION, 0.1f);
            put(BRICKS_1, 0.1f);
            put(FOUR_QUESTION_BLOCKS, 0.2f);
            put(PIT, 0.3f);
            put(ENEMIES_1, 0.3f);
        }});

        weight_maps.put(FOUR_QUESTION_BLOCKS, new HashMap<>(){{
            put(START_HIGH_SECTION, 0.1f);
            put(BRICKS_1, 0.15f);
            put(BRICKS_2, 0.25f);
            put(PIT, 0.25f);
            put(ENEMIES_1, 0.25f);
        }});

        weight_maps.put(PIT, new HashMap<>(){{
            put(START_HIGH_SECTION, 0.1f);
            put(BRICKS_1, 0.15f);
            put(BRICKS_2, 0.25f);
            put(FOUR_QUESTION_BLOCKS, 0.25f);
            put(ENEMIES_1, 0.25f);
        }});

        weight_maps.put(ENEMIES_1, new HashMap<>(){{
            put(START_HIGH_SECTION, 0.1f);
            put(BRICKS_1, 0.15f);
            put(BRICKS_2, 0.25f);
            put(FOUR_QUESTION_BLOCKS, 0.25f);
            put(PIT, 0.25f);
        }});

        weight_maps.put(START_HIGH_SECTION, new HashMap<>(){{
            put(HIGH_SECTION_ENEMY_PIT, 0.4f);
            put(HIGH_SECTION_GAP, 0.4f);
            put(HIGH_SECTION_END, 0.2f);
        }});

        weight_maps.put(HIGH_SECTION_ENEMY_PIT, new HashMap<>(){{
            put(HIGH_SECTION_GAP, 0.6f);
            put(HIGH_SECTION_END, 0.4f);
        }});

        weight_maps.put(HIGH_SECTION_GAP, new HashMap<>(){{
            put(HIGH_SECTION_ENEMY_PIT, 0.6f);
            put(HIGH_SECTION_END, 0.4f);
        }});

        weight_maps.put(HIGH_SECTION_END, new HashMap<>(){{
            put(BRICKS_1, 0.1f);
            put(BRICKS_2, 0.2f);
            put(FOUR_QUESTION_BLOCKS, 0.2f);
            put(PIT, 0.2f);
            put(ENEMIES_1, 0.2f);
        }});

    }

    /**
     * List of chunks in the level so far.
     */
    List<String> chunks;

    /**
     * Add a random chunk to the chunks List, based on the weight map of the previous chunk
     */
    void nextChunk() {
        // random [0.0, 1.0)
        float value = (float)Math.random();

        String lastChunk = chunks.get(chunks.size() - 1);
        HashMap<String, Float> nextWeights = weight_maps.get(lastChunk);

        // by default, choose the first possible level
        String sel = nextWeights.keySet().iterator().next();

        // you can think about the algorithm like this
        // the nextWeights map is like a bar with sections of different sizes
        // this accumulator+loop below basically picks a random position on the bar, and that's the chunk we use

        float acc = 0.0f;

        // loop through all possible next chunks
        for(var k : nextWeights.keySet()) {
            // add the candidate's weight to the accumulator
            acc += nextWeights.get(k);

            // if we hit the target value, select this chunk
            if (acc >= value) {
                sel = k;
                break;
            }
        }

        this.chunks.add(sel);
    }

    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
        this.chunks = new ArrayList<>();

        this.chunks.add(START);

        // keep adding chunks until it generates an END chunk
        // TODO: maybe should add a length limit here in case it gets stuck in a loop
        int chunkCount = 0;
        //TODO: do total lengths of chunks, in tiles, rather than number of chunks
        float minSize = 20;
        float maxSize = 100;
        while(!this.chunks.get(this.chunks.size() - 1).equals(END)) {
            float value = (float)Math.random();
            float endWeight = (chunkCount-minSize)/(maxSize-minSize);
            System.out.println("endweight: " + endWeight + ", rand value: " + value);
            if(endWeight > value){
                this.chunks.add(END);
            }else {
                nextChunk();
            }
            chunkCount++;
        }

        // build the level out of the chunks

        int x = 0;
        for(String ch : this.chunks) {
            int w = ch.split("\n")[0].length();
            int h = ch.split("\n").length;

            if (x + w > model.getWidth()) {
                // if the level is too long, change this chunk to an END and stop building more chunks
                ch = END;
                w = ch.split("\n")[0].length();
                h = ch.split("\n").length;
                model.copyFromString(x, model.getHeight() - h, 0, 0, w, h, ch);
                break;
            } else {
                // copy the chunk into the level
                // note the targetY is set up to align chunks with the bottom of the screen, regardless of chunk height
                model.copyFromString(x, model.getHeight() - h, 0, 0, w, h, ch);
                x += w;
            }
        }

        return model.getMap();
    }

    @Override
    public String getGeneratorName() {
        return "FernandesMahanyMatava Generator";
    }
}
