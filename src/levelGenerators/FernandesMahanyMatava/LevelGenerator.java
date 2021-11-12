package levelGenerators.FernandesMahanyMatava;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LevelGenerator implements MarioLevelGenerator {


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
            "--e-" + "\n" +
            "XXXX";

    static String END =
            "----" + "\n" +
            "-F--" + "\n" +
            "-#--" + "\n" +
            "XXXX";

    static HashMap<String, HashMap<String, Float>> weights;

    static {
        weights = new HashMap<>();
        weights.put(START, new HashMap<>(){{
            put(CHUNK_1, 1.0f);
        }});
        weights.put(CHUNK_1, new HashMap<>(){{
            put(CHUNK_2, 0.5f);
            put(CHUNK_3, 0.5f);
        }});
        weights.put(CHUNK_2, new HashMap<>(){{
            put(CHUNK_1, 0.5f);
            put(CHUNK_3, 0.5f);
        }});
        weights.put(CHUNK_3, new HashMap<>(){{
            put(CHUNK_1, 0.45f);
            put(CHUNK_2, 0.45f);
            put(END, 0.1f);
        }});
    }

    List<String> chunks;

    void nextChunk() {
        float value = (float)Math.random();

        HashMap<String, Float> next_weights = weights.get(chunks.get(chunks.size() - 1));

        float acc = 0.0f;

        String sel = next_weights.keySet().iterator().next();
        for(var k : next_weights.keySet()) {
            acc += next_weights.get(k);
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

        while(!this.chunks.get(this.chunks.size() - 1).equals(END)) {
            nextChunk();
        }

        int x = 0;

        for(String ch : this.chunks) {
            int w = ch.split("\n")[0].length();
            int h = ch.split("\n").length;

            if (x + w > model.getWidth()) {
                ch = END;
                w = ch.split("\n")[0].length();
                h = ch.split("\n").length;
                model.copyFromString(x, model.getHeight() - h, 0, 0, w, h, ch);
                break;
            } else {
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
