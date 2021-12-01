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


    static String COIN_BOX=
            "!S" + "\n" +
            "--" + "\n" +
            "--" + "\n" +
            "XX" + "\n" +
            "XX";

    static String PIPE_1 =
            "----" + "\n" +
            "----" + "\n" +
            "-tt-" + "\n" +
            "XXXX" + "\n" +
            "XXXX";

    static String PIPE_6 =
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "XXXX" + "\n" +
            "XXXX";

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

    interface WeightFactor {
        float getWeight(LevelGenerator generator);
    }

    static class PrevChunkFactor implements WeightFactor {
        String chunk;
        float weight;

        PrevChunkFactor(String chunk, float weight) {
            this.chunk = chunk;
            this.weight = weight;
        }

        @Override
        public float getWeight(LevelGenerator generator) {
            return generator.chunks.size() < 2 ?
                    0.0f
                    : (generator.chunks.get(generator.chunks.size() - 2).equals(this.chunk) ?
                        this.weight
                        : 0.0f);
        }
    }

    static class Chunk {
        String tiles;

        /**
         * List of possible next chunks, and their weights
         * The WeightFactors are summed to calculate the total weight for that next chunk
         */
        HashMap<String, List<WeightFactor>> weights;

        Chunk(String tiles) {
            this.tiles = tiles;
            this.weights = new HashMap<>();
        }

        HashMap<String, Float> calculateWeights(LevelGenerator generator) {
            HashMap<String, Float> calc = new HashMap<>();

            this.weights.forEach((k, weightFactors) -> {
                float v = (float)weightFactors.stream().mapToDouble(wf -> wf.getWeight(generator)).sum();
                calc.put(k, v);
            });

            return calc;
        }

        void addWeight(String chunk, WeightFactor weight) {
            // there's definitely a function that does this but I haven't used java in a while so idr what it is
            if(!this.weights.containsKey(chunk)) {
                this.weights.put(chunk, new ArrayList<>());
            }
            this.weights.get(chunk).add(weight);
        }

        void addWeight(String chunk, float weight) {
            this.addWeight(chunk, (prevChunks) -> weight);
        }

        int getWidth() {
            return this.tiles.split("\n")[0].length();
        }

        int getHeight() {
            return this.tiles.split("\n").length;
        }
    }

    static HashMap<String, Chunk> allChunks;

    // this runs once when this class is loaded
    static {

        allChunks = new HashMap<>();

        allChunks.put("START", new Chunk("""
            -------
            --M----
            XXXXXXX
            XXXXXXX
            """));
        allChunks.get("START").addWeight("BRICKS_1", 0.75f);
        allChunks.get("START").addWeight("BRICKS_2", 0.75f);
        allChunks.get("START").addWeight("FOUR_QUESTION_BLOCKS", 1.25f);
        allChunks.get("START").addWeight("PIT", 1.25f);
        allChunks.get("START").addWeight("ENEMIES_1", 1.0f);
        allChunks.get("START").addWeight("PIPE_2", 0.5f);


        allChunks.put("BRICKS_1", new Chunk("""
            -SS@S-----
            ----------
            ----------
            ------k-gg
            XXXXXXXXXX
            XXXXXXXXXX
            """));
        allChunks.get("BRICKS_1").addWeight("START_HIGH_SECTION", 0.1f);
        allChunks.get("BRICKS_1").addWeight("BRICKS_2", 0.1f);
        allChunks.get("BRICKS_1").addWeight("FOUR_QUESTION_BLOCKS", 0.2f);
        allChunks.get("BRICKS_1").addWeight("PIT", 0.25f);
        allChunks.get("BRICKS_1").addWeight("ENEMIES_1", 0.25f);
        allChunks.get("BRICKS_1").addWeight("PIPE_2", 0.1f);

        // some examples of things you can do
//        allChunks.get("BRICKS_1").addWeight("END_STAIRS", new PrevChunkFactor("ENEMIES_1", 100.0f));
//        allChunks.get("BRICKS_1").addWeight("END_STAIRS", new PrevChunkFactor("BRICKS_2", 100.0f));
//        allChunks.get("BRICKS_1").addWeight("END_STAIRS", generator -> {
//            var nChunks = generator.chunks.size();
//            return nChunks > 2 ? 100.0f : 0.0f;
//        });

        allChunks.put("BRICKS_2", new Chunk("""
            ------!---
            ----------
            ----------
            --!--SSS--
            ----------
            ----------
            ---g--g---
            XXXXXXXXXX
            XXXXXXXXXX
            """));
        allChunks.get("BRICKS_2").addWeight("START_HIGH_SECTION", 0.1f);
        allChunks.get("BRICKS_2").addWeight("BRICKS_1", 0.1f);
        allChunks.get("BRICKS_2").addWeight("FOUR_QUESTION_BLOCKS", 0.1f);
        allChunks.get("BRICKS_2").addWeight("PIT", 0.3f);
        allChunks.get("BRICKS_2").addWeight("ENEMIES_1", 0.3f);
        allChunks.get("BRICKS_2").addWeight("PIPE_2", 0.1f);

        allChunks.put("FOUR_QUESTION_BLOCKS", new Chunk("""
            -----@-----
            -----------
            -----------
            -----------
            --!--!--!--
            -----------
            -----------
            --k--------
            XXXXXXXXXXX
            XXXXXXXXXXX
            """));
        allChunks.get("FOUR_QUESTION_BLOCKS").addWeight("START_HIGH_SECTION", 0.1f);
        allChunks.get("FOUR_QUESTION_BLOCKS").addWeight("BRICKS_1", 0.15f);
        allChunks.get("FOUR_QUESTION_BLOCKS").addWeight("BRICKS_2", 0.15f);
        allChunks.get("FOUR_QUESTION_BLOCKS").addWeight("PIT", 0.25f);
        allChunks.get("FOUR_QUESTION_BLOCKS").addWeight("ENEMIES_1", 0.25f);
        allChunks.get("FOUR_QUESTION_BLOCKS").addWeight("PIPE_2", 0.1f);

        allChunks.put("PIT", new Chunk("""
            -------
            XX---XX
            XX---XX
            """));
        allChunks.get("PIT").addWeight("START_HIGH_SECTION", 0.1f);
        allChunks.get("PIT").addWeight("BRICKS_1", 0.15f);
        allChunks.get("PIT").addWeight("BRICKS_2", 0.15f);
        allChunks.get("PIT").addWeight("FOUR_QUESTION_BLOCKS", 0.25f);
        allChunks.get("PIT").addWeight("ENEMIES_1", 0.25f);
        allChunks.get("PIT").addWeight("PIPE_2", 0.1f);

        allChunks.put("ENEMIES_1", new Chunk("""
            -r-g-g-
            XXXXXXX
            XXXXXXX
            """));
        allChunks.get("ENEMIES_1").addWeight("START_HIGH_SECTION", 0.1f);
        allChunks.get("ENEMIES_1").addWeight("BRICKS_1", 0.15f);
        allChunks.get("ENEMIES_1").addWeight("BRICKS_2", 0.15f);
        allChunks.get("ENEMIES_1").addWeight("FOUR_QUESTION_BLOCKS", 0.25f);
        allChunks.get("ENEMIES_1").addWeight("PIT", 0.25f);
        allChunks.get("ENEMIES_1").addWeight("PIPE_2", 0.1f);

        allChunks.put("PIPE_2", new Chunk("""
            ----
            -tt-
            -tt-
            XXXX
            XXXX
            """));
        allChunks.get("PIPE_2").addWeight("PIPE_INBETWEEN_1", 0.5f);
        allChunks.get("PIPE_2").addWeight("PIPE_INBETWEEN_2", 0.5f);

        allChunks.put("PIPE_3", new Chunk("""
            -tt-
            -tt-
            -tt-
            XXXX
            XXXX
            """));
        allChunks.get("PIPE_3").addWeight("PIPE_INBETWEEN_1", 0.5f);
        allChunks.get("PIPE_3").addWeight("PIPE_INBETWEEN_2", 0.5f);

        allChunks.put("PIPE_4", new Chunk("""
            -tt-
            -tt-
            -tt-
            -tt-
            XXXX
            XXXX
            """));
        allChunks.get("PIPE_4").addWeight("PIPE_INBETWEEN_1", 0.5f);
        allChunks.get("PIPE_4").addWeight("PIPE_INBETWEEN_2", 0.5f);

        allChunks.put("PIPE_5", new Chunk("""
            -tt-
            -tt-
            -tt-
            -tt-
            XXXX
            XXXX
            """));
        allChunks.get("PIPE_5").addWeight("PIPE_INBETWEEN_1", 0.5f);
        allChunks.get("PIPE_5").addWeight("PIPE_INBETWEEN_2", 0.5f);

        allChunks.put("PIPE_INBETWEEN_1", new Chunk("""
            ----
            ----
            -g-g
            XXXX
            XXXX
            """));
        allChunks.get("PIPE_INBETWEEN_1").addWeight("PIPE_3", 0.25f);
        allChunks.get("PIPE_INBETWEEN_1").addWeight("PIPE_4", 0.25f);
        allChunks.get("PIPE_INBETWEEN_1").addWeight("PIPE_5", 0.20f);
        allChunks.get("PIPE_INBETWEEN_1").addWeight("START_HIGH_SECTION", 0.05f);
        allChunks.get("PIPE_INBETWEEN_1").addWeight("BRICKS_1", 0.05f);
        allChunks.get("PIPE_INBETWEEN_1").addWeight("BRICKS_2", 0.05f);
        allChunks.get("PIPE_INBETWEEN_1").addWeight("FOUR_QUESTION_BLOCKS", 0.05f);
        allChunks.get("PIPE_INBETWEEN_1").addWeight("PIT", 0.05f);
        allChunks.get("PIPE_INBETWEEN_1").addWeight("ENEMIES_1", 0.05f);

        allChunks.put("PIPE_INBETWEEN_2", new Chunk("""
            ----
            ----
            -kg-
            XXXX
            XXXX
            """));
        allChunks.get("PIPE_INBETWEEN_2").addWeight("PIPE_3", 0.25f);
        allChunks.get("PIPE_INBETWEEN_2").addWeight("PIPE_4", 0.25f);
        allChunks.get("PIPE_INBETWEEN_2").addWeight("PIPE_5", 0.20f);
        allChunks.get("PIPE_INBETWEEN_2").addWeight("START_HIGH_SECTION", 0.05f);
        allChunks.get("PIPE_INBETWEEN_2").addWeight("BRICKS_1", 0.05f);
        allChunks.get("PIPE_INBETWEEN_2").addWeight("BRICKS_2", 0.05f);
        allChunks.get("PIPE_INBETWEEN_2").addWeight("FOUR_QUESTION_BLOCKS", 0.05f);
        allChunks.get("PIPE_INBETWEEN_2").addWeight("PIT", 0.05f);
        allChunks.get("PIPE_INBETWEEN_2").addWeight("ENEMIES_1", 0.05f);

        allChunks.put("START_HIGH_SECTION", new Chunk("""
            -------g----
            ---SSSSSSSSS
            ------------
            ------------
            --g---------
            S@S-----1---
            ------------
            ------------
            ------------
            XXXXXXXXX---
            XXXXXXXXX---
            """));
        allChunks.get("START_HIGH_SECTION").addWeight("HIGH_SECTION_ENEMY_PIT", 0.4f);
        allChunks.get("START_HIGH_SECTION").addWeight("HIGH_SECTION_GAP", 0.4f);
        allChunks.get("START_HIGH_SECTION").addWeight("HIGH_SECTION_END", 0.2f);

        allChunks.put("HIGH_SECTION_ENEMY_PIT", new Chunk("""
            --k--------r
            SSSS-g-ggkSS
            ---SSSSSSSS-
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            """));
        allChunks.get("HIGH_SECTION_ENEMY_PIT").addWeight("HIGH_SECTION_GAP", 0.6f);
        allChunks.get("HIGH_SECTION_ENEMY_PIT").addWeight("HIGH_SECTION_END", 0.4f);

        allChunks.put("HIGH_SECTION_GAP", new Chunk("""
            -----------g
            SS-----SSSSS
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            """));
        allChunks.get("HIGH_SECTION_GAP").addWeight("HIGH_SECTION_GAP", 0.6f);
        allChunks.get("HIGH_SECTION_GAP").addWeight("HIGH_SECTION_END", 0.4f);

        allChunks.put("HIGH_SECTION_END", new Chunk("""
            ------------
            SS----------
            ----S-------
            ------------
            ------------
            -------CSS--
            ------------
            ------------
            -----------k
            ---XXXXXXXXX
            ---XXXXXXXXX
            """));
        allChunks.get("HIGH_SECTION_END").addWeight("BRICKS_1", 0.1f);
        allChunks.get("HIGH_SECTION_END").addWeight("BRICKS_2", 0.2f);
        allChunks.get("HIGH_SECTION_END").addWeight("FOUR_QUESTION_BLOCKS", 0.2f);
        allChunks.get("HIGH_SECTION_END").addWeight("PIT", 0.2f);
        allChunks.get("HIGH_SECTION_END").addWeight("ENEMIES_1", 0.2f);

        allChunks.put("END_STAIRS", new Chunk("""
            --------##-------
            -------###-------
            ------####-------
            -----#####-------
            ----######-------
            ---#######-------
            --########-------
            -#########-------
            XXXXXXXXXXXXXXXXX
            XXXXXXXXXXXXXXXXX
            """));
        allChunks.get("END_STAIRS").addWeight("END", 1.0f);

        allChunks.put("END", new Chunk("""
            ----
            -F--
            -#--
            XXXX
            XXXX
            """));

    }

    /**
     * List of chunks in the level so far.
     */
    List<String> chunks;

    /**
     * Add a random chunk to the chunks List, based on the weight map of the previous chunk
     */
    void nextChunk() {

        String lastChunk = chunks.get(chunks.size() - 1);
//        HashMap<String, Float> nextWeights = weight_maps.get(lastChunk);
        HashMap<String, Float> nextWeights = allChunks.get(lastChunk).calculateWeights(this);

        float sumOfWeights = nextWeights.values().stream().reduce(Float::sum).get();

        // random [0.0, 1.0) * sumOfWeights
        float value = (float)Math.random() * sumOfWeights;

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

        this.chunks.add("START");

        // keep adding chunks until it generates an END chunk
        // TODO: maybe should add a length limit here in case it gets stuck in a loop
        int chunkCount = 0;
        //TODO: do total lengths of chunks, in tiles, rather than number of chunks
        float minSize = 15;
        float maxSize = 18;
        while(!this.chunks.get(this.chunks.size() - 1).equals("END")) {
            float value = (float)Math.random();
            float endWeight = (chunkCount-minSize)/(maxSize-minSize);
//            System.out.println("endweight: " + endWeight + ", rand value: " + value);
            if(endWeight > value){
                this.chunks.add("END_STAIRS");
                this.chunks.add("END");
            }else {
                nextChunk();
            }
            chunkCount++;
        }

        // build the level out of the chunks

        int x = 0;
        for(String ch : this.chunks) {
            int w = allChunks.get(ch).tiles.split("\n")[0].length();
            int h = allChunks.get(ch).tiles.split("\n").length;

            if (x + w > model.getWidth()) {
                // if the level is too long, change this chunk to an END and stop building more chunks
                ch = "END";
                w = allChunks.get(ch).tiles.split("\n")[0].length();
                h = allChunks.get(ch).tiles.split("\n").length;
                model.copyFromString(x, model.getHeight() - h, 0, 0, w, h, allChunks.get(ch).tiles);
                break;
            } else {
                // copy the chunk into the level
                // note the targetY is set up to align chunks with the bottom of the screen, regardless of chunk height
                model.copyFromString(x, model.getHeight() - h, 0, 0, w, h, allChunks.get(ch).tiles);
                x += w;
            }
        }

        System.out.println(model.getMap().replace("\0", "-"));

        return model.getMap();
    }

    @Override
    public String getGeneratorName() {
        return "FernandesMahanyMatava Generator";
    }
}
