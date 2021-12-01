package levelGenerators.FernandesMahanyMatava;

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