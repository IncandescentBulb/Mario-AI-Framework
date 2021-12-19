package levelGenerators.FernandesMahanyMatava;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class LevelGenerator implements MarioLevelGenerator {

    /**
     * List of chunks in the level so far.
     */
    List<String> chunks;

    Hashtable<String, Integer> parameters;

    /**
     * Set the input parameters.
     */
    public void setParameters(Hashtable<String, Integer> param){
        parameters = param;
    }

    /**
     * Add a random chunk to the chunks List, based on the weight map of the previous chunk
     */
    void nextChunk() {

        String lastChunk = chunks.get(chunks.size() - 1);
        HashMap<String, Float> nextWeights = ChunkReg.CHUNKS.get(lastChunk).calculateWeights(this);
        System.out.println(nextWeights);

        float sumOfWeights = nextWeights.values().stream().reduce(Float::sum).get();

        // random [0.0, sumOfWeights)
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

        // set to "START" for non-mushroom level
        // set to "MUSHROOM_7" for mushroom level
        this.chunks.add(parameters.getOrDefault("theme", 0).equals(1) ? "MUSHROOM_7" : "START");

        // keep adding chunks until it generates an END chunk
        // TODO: maybe should add a length limit here in case it gets stuck in a loop
        int chunkCount = 0;
        //TODO: do total lengths of chunks, in tiles, rather than number of chunks
        float minSize = 15;
        float maxSize = 18;

        float minSizeTiles = parameters.getOrDefault("min-width", 80);
        float maxSizeTiles = model.getWidth()-5;
        int map_length = ChunkReg.CHUNKS.get(this.chunks.get(0)).getWidth();
        while(!this.chunks.get(this.chunks.size() - 1).equals("END")) {
            float value = (float)Math.random();
            //float endWeight = (chunkCount-minSize)/(maxSize-minSize);
            float endWeight = (map_length-minSizeTiles)/(maxSizeTiles-minSizeTiles);
//            System.out.println("endweight: " + endWeight + ", rand value: " + value);
            if(endWeight > value){
                this.chunks.add("END_STAIRS");
                this.chunks.add("END");
            }else {
                nextChunk();
            }
            chunkCount++;
            map_length+= ChunkReg.CHUNKS.get(this.chunks.get(this.chunks.size() - 1)).getWidth();//.split("\n")[0].length();
        }

        // build the level out of the chunks

        int x = 0;
        for(String ch : this.chunks) {
            int w = ChunkReg.CHUNKS.get(ch).tiles.split("\n")[0].length();
            int h = ChunkReg.CHUNKS.get(ch).tiles.split("\n").length;

            if (x + w >= model.getWidth()-4) {
                // if the level is too long, change this chunk to an END and stop building more chunks
                ch = "END";
                w = ChunkReg.CHUNKS.get(ch).tiles.split("\n")[0].length();
                h = ChunkReg.CHUNKS.get(ch).tiles.split("\n").length;
                model.copyFromString(x, model.getHeight() - h, 0, 0, w, h, ChunkReg.CHUNKS.get(ch).tiles);
                break;
            } else {
                // copy the chunk into the level
                // note the targetY is set up to align chunks with the bottom of the screen, regardless of chunk height
                model.copyFromString(x, model.getHeight() - h, 0, 0, w, h, ChunkReg.CHUNKS.get(ch).tiles);
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
