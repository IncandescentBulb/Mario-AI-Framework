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
            put(CHUNK_1, 0.45f);
            put(CHUNK_2, 0.45f);
            put(END, 0.1f);
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
        while(!this.chunks.get(this.chunks.size() - 1).equals(END)) {
            nextChunk();
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
