package levelGenerators.FernandesMahanyMatava;

import java.util.Arrays;
import java.util.List;

interface WeightFactor {
    float getWeight(LevelGenerator generator);
}

class PrevChunkFactor implements WeightFactor {
    String chunk;
    float weight;

    PrevChunkFactor(String chunk, float weight) {
        this.chunk = chunk;
        this.weight = weight;
    }

    @Override
    public float getWeight(LevelGenerator generator) {
        float w;
        //TODO: add conditions that are checked to modify weights
        if(generator.chunks.size() < 2){
            w= 0.0f;
        } else if(generator.chunks.get(generator.chunks.size() - 2).equals(this.chunk)){
            w= this.weight;
        }else{
            w= 0.0f;
        }
        return w;
        /*return generator.chunks.size() < 2 ?
                0.0f
                : (generator.chunks.get(generator.chunks.size() - 2).equals(this.chunk) ?
                this.weight
                : 0.0f);*/
    }
}

/**
 * Applies a weight if a specific chunk is within a certain section of the level
 */
class SpecificChunkFactor implements WeightFactor {
    String chunkName; //name of the chunk to look for
    float weight; // weight to apply if chunk is found
    String within; // specifies a set of chunks to look at

    // TODO: this should be an enum, it would remove the need to check for valid input most of the time
    //       though I feel like it would just be better to have these be separate classes anyway, they
    //           already have separate constructors.
    static List<String> withinOptions = Arrays.asList("Prior", "All", "FirstX", "LastX");
    /* Valid values for within
    * Prior: The second-last chunk currently in the level
    * All: All chunks within the level
    * FirstX: the first X chunks in the level: requires setting X
    * LastX: the last X chunks in the level: requires setting X
    *
    * not yet implemented
    * notFirstX: all except the first X chunks in level: requires setting X
    * notLastX: all except the first X chunks in level: requires setting X
    * Xth:? Xth chunk in level (can do with FirstX with X and FirstX with X-1 and opposite weight)
    * XthLast:? Xth-last chunk in level (can do with LastX with X and LastX with X-1 and opposite weight)
    * */
    int X; // A value tied to some versions of this.within

    SpecificChunkFactor(String chunkName, float weight) {// will function the same as PrevChunkFactor
        this.chunkName = chunkName;
        this.weight = weight;
        this.within = "Prior";
        this.X = -1;
    }

    SpecificChunkFactor(String chunkName, float weight, String within) {// for non-X withins
        /*if(!validWithin(within)){//
            //SHOULD return error, but not necessary for now
        }*/
        this.chunkName = chunkName;
        this.weight = weight;
        this.within = within;
        this.X = -1;
        if (this.within.equals("FirstX") || this.within.equals("LastX")){ //if within uses X
            // wrong constructor
            this.X = 1; //SHOULD be error, but can default for now
        }

    }

    SpecificChunkFactor(String chunkName, float weight, String within, int X) {// for withins that use X
        /*if(!validWithin(within)){//
            //SHOULD return error, but not necessary for now
        }*/
        this.chunkName = chunkName;
        this.weight = weight;
        this.within = within;
        this.X = X;
        //if (this.within.equals("Prior") || this.within.equals("All")){ //if within does not use X
          //wrong constructor
          //SHOULD be error, but can do nothing for now
        //}
    }


    boolean validWithin(String within){ // Is the given string an implemented Within option?
        int size = withinOptions.size();
        int i;
        for (i = 0; i < size; i++){// for each Within option
            if (within.equals(withinOptions.get(i))){
                return true;
            }
        }
        return false;
    }

    @Override
    public float getWeight(LevelGenerator generator) {
        int i, j;
        if(within.equals("Prior")) {
            return generator.chunks.size() < 2 ?
                    0.0f
                    : (generator.chunks.get(generator.chunks.size() - 2).equals(this.chunkName) ?
                    this.weight
                    : 0.0f);
        } else if (within.equals("All")){
            for(i = 0; i < generator.chunks.size(); i++){ // iterate through entire level
                if (generator.chunks.get(i).equals(this.chunkName)){// when the chunk is found
                     return this.weight;
                }
            }
            return 0.0f;
        } else if (within.equals("FirstX")){
            if (X > generator.chunks.size()){ // if X is too long for current level
                X = generator.chunks.size(); //set X to length; will function like "All"
            }
            for(i = 0; i < X; i++){ // iterate through first X chunks of level
                if (generator.chunks.get(i).equals(this.chunkName)){
                    return this.weight;
                }
            }
            return 0.0f;
        } else if (within.equals("LastX")){
            if (X > generator.chunks.size()){ // if X is too long for current level
                X = generator.chunks.size(); //set X to length; will function like "All"
            }
            j = generator.chunks.size() - 1 - X;
            for(i = generator.chunks.size() - 1; i > j; i--){
                if (generator.chunks.get(i).equals(this.chunkName)){
                    return this.weight;
                }
            }
            return 0.0f;
        }
        // should have error report here
        return 0.0f; //this factor was made invalid, should not affect weight
    }


}

class SpecificTagFactor implements WeightFactor {
    String tagName; //name of tag to look for
    float weight; // weight to apply if tag is found
    String within; // specifies a set of chunks to look at

    static List<String> withinOptions = Arrays.asList("Prior", "All", "FirstX", "LastX");
    /* Valid values for within
     * Prior: The second-last chunk currently in the level
     * All: All chunks within the level
     * FirstX: the first X chunks in the level: requires setting X
     * LastX: the last X chunks in the level: requires setting X
     *
     * not yet implemented
     * notFirstX: all except the first X chunks in level: requires setting X
     * notLastX: all except the first X chunks in level: requires setting X
     * Xth:? Xth chunk in level (can do with FirstX with X and FirstX with X-1 and opposite weight)
     * XthLast:? Xth-last chunk in level (can do with LastX with X and LastX with X-1 and opposite weight)
     * */
    int X; // A value tied to some versions of this.within

    SpecificTagFactor(String tagName, float weight) {// will function as PrevChunkFactor for tags
        this.tagName = tagName;
        this.weight = weight;
        this.within = "Prior";
        this.X = -1;
    }

    SpecificTagFactor(String tagName, float weight, String within) {// for non-X withins
        /*if(!validWithin(within)){//
            //SHOULD return error, but not necessary for now
        }*/
        this.tagName = tagName;
        this.weight = weight;
        this.within = within;
        this.X = -1;
        if (this.within.equals("FirstX") || this.within.equals("LastX")){ //if within uses X
            // wrong constructor
            this.X = 1; //SHOULD be error, but can default for now
        }

    }

    SpecificTagFactor(String tagName, float weight, String within, int X) {// for withins that use X
        /*if(!validWithin(within)){//
            //SHOULD return error, but not necessary for now
        }*/
        this.tagName = tagName;
        this.weight = weight;
        this.within = within;
        this.X = X;
        //if (this.within.equals("Prior") || this.within.equals("All")){ //if within does not use X
        //wrong constructor
        //SHOULD be error, but can do nothing for now
        //}
    }


    boolean validWithin(String within){ // Is the given string an implemented Within option?
        int size = withinOptions.size();
        int i;
        for (i = 0; i < size; i++){// for each Within option
            if (within.equals(withinOptions.get(i))){
                return true;
            }
        }
        return false;
    }

    @Override
    public float getWeight(LevelGenerator generator) {
        int i, j;
        if(within.equals("Prior")) {
            //return generator.chunks.size() < 2 ?
            //        0.0f
            //        : (generator.chunks.get(generator.chunks.size() - 2).equals(this.tagName) ?
            //        this.weight
            //        : 0.0f);
            if (generator.chunks.size() >= 2){
                if(checkTag(generator.chunks.get(generator.chunks.size() - 2), tagName)){
                    return this.weight;
                }
            }
            return 0.0f;
        } else if (within.equals("All")){
            for(i = 0; i < generator.chunks.size(); i++){ // iterate through entire level
                if (checkTag(generator.chunks.get(i), tagName)){// when the chunk is found
                    return this.weight;
                }
            }
            return 0.0f;
        } else if (within.equals("FirstX")){
            if (X > generator.chunks.size()){ // if X is too long for current level
                X = generator.chunks.size(); //set X to length; will function like "All"
            }
            for(i = 0; i < X; i++){ // iterate through first X chunks of level
                if (checkTag(generator.chunks.get(i), tagName)){
                    return this.weight;
                }
            }
            return 0.0f;
        } else if (within.equals("LastX")){
            if (X > generator.chunks.size()){ // if X is too long for current level
                X = generator.chunks.size(); //set X to length; will function like "All"
            }
            j = generator.chunks.size() - 1 - X;
            for(i = generator.chunks.size() - 1; i > j; i--){
                if (checkTag(generator.chunks.get(i), tagName)){
                    return this.weight;
                }
            }
            return 0.0f;
        }
        // should have error report here
        return 0.0f; //this factor was made invalid, should not affect weight
    }

    boolean checkTag(String chunkName, String tag){
        if (ChunkReg.CHUNKS.containsKey(chunkName)){//if chunk exists
            return ChunkReg.CHUNKS.get(chunkName).hasTag(tag);
        }
        return false;
    }

}