package levelGenerators.FernandesMahanyMatava;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Container that holds all of the Chunks
 */
public class ChunkReg {

    static final HashMap<String, Chunk> CHUNKS;

    // this runs once when this class is loaded
    static {

        CHUNKS = new HashMap<>();

        // TODO: might want to move all the addWeights after the HashMap is filled otherwise we won't be able to use tags here in the future
        //       (or if we make this deferred somehow it could allow the layout to stay the same?)

        CHUNKS.put("START", new Chunk("""
            -------
            --M----
            XXXXXXX
            XXXXXXX
            """));
        // TODO: this repetition is kind of gross, could refactor addWeight to be chainable
        CHUNKS.get("START").addWeight("BRICKS_1", 0.75f);
        CHUNKS.get("START").addWeight("BRICKS_2", 0.75f);
        CHUNKS.get("START").addWeight("FOUR_QUESTION_BLOCKS", 1.25f);
        CHUNKS.get("START").addWeight("PIT", 1.25f);
        CHUNKS.get("START").addWeight("ENEMIES_1", 1.0f);
        CHUNKS.get("START").addWeight("PIPE_2", 0.5f);
        CHUNKS.get("START").tags.add(Tag.START);
        CHUNKS.put("BRICKS_1", new Chunk("""
            -SS@S-----
            ----------
            ----------
            ------k-gg
            XXXXXXXXXX
            XXXXXXXXXX
            """));
        CHUNKS.get("BRICKS_1").addWeight("START_HIGH_SECTION", 0.5f);
        CHUNKS.get("BRICKS_1").addWeight("BRICKS_2", 0.5f);
        CHUNKS.get("BRICKS_1").addWeight("FOUR_QUESTION_BLOCKS", 1.0f);
        CHUNKS.get("BRICKS_1").addWeight("PIT", 1.25f);
        CHUNKS.get("BRICKS_1").addWeight("ENEMIES_1", 1.25f);
        CHUNKS.get("BRICKS_1").addWeight("PIPE_2", 0.5f);
        CHUNKS.get("BRICKS_1").tags.add(Tag.ENEMY);
        CHUNKS.get("BRICKS_1").tags.add(Tag.GOOMBA);
        CHUNKS.get("BRICKS_1").tags.add(Tag.KOOPA);
        // some examples of things you can do
        //        allChunks.get("BRICKS_1").addWeight("END_STAIRS", new PrevChunkFactor("ENEMIES_1", 100.0f));
        //        allChunks.get("BRICKS_1").addWeight("END_STAIRS", new PrevChunkFactor("BRICKS_2", 100.0f));
        //        allChunks.get("BRICKS_1").addWeight("END_STAIRS", generator -> {
        //            var nChunks = generator.chunks.size();
        //            return nChunks > 2 ? 100.0f : 0.0f;
        //        });

        CHUNKS.put("BRICKS_2", new Chunk("""
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
        CHUNKS.get("BRICKS_2").addWeight("START_HIGH_SECTION", 0.5f);
        CHUNKS.get("BRICKS_2").addWeight("BRICKS_1", 0.5f);
        CHUNKS.get("BRICKS_2").addWeight("FOUR_QUESTION_BLOCKS", 0.5f);
        CHUNKS.get("BRICKS_2").addWeight("PIT", 0.3f);
        CHUNKS.get("BRICKS_2").addWeight("ENEMIES_1", 0.3f);
        CHUNKS.get("BRICKS_2").addWeight("PIPE_2", 0.5f);

        CHUNKS.get("BRICKS_2").tags.add(Tag.ENEMY);
        CHUNKS.get("BRICKS_2").tags.add(Tag.GOOMBA);
        CHUNKS.put("FOUR_QUESTION_BLOCKS", new Chunk("""
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
        CHUNKS.get("FOUR_QUESTION_BLOCKS").addWeight("START_HIGH_SECTION", 0.5f);
        CHUNKS.get("FOUR_QUESTION_BLOCKS").addWeight("BRICKS_1", 0.15f);
        CHUNKS.get("FOUR_QUESTION_BLOCKS").addWeight("BRICKS_2", 0.15f);
        CHUNKS.get("FOUR_QUESTION_BLOCKS").addWeight("PIT", 1.25f);
        CHUNKS.get("FOUR_QUESTION_BLOCKS").addWeight("ENEMIES_1", 1.25f);
        CHUNKS.get("FOUR_QUESTION_BLOCKS").addWeight("PIPE_2", 0.5f);

        CHUNKS.get("FOUR_QUESTION_BLOCKS").tags.add(Tag.ENEMY);
        CHUNKS.get("FOUR_QUESTION_BLOCKS").tags.add(Tag.KOOPA);

        CHUNKS.put("PIT", new Chunk("""
            -------
            XX---XX
            XX---XX
            """));
        CHUNKS.get("PIT").addWeight("START_HIGH_SECTION", 0.5f);
        CHUNKS.get("PIT").addWeight("BRICKS_1", 0.15f);
        CHUNKS.get("PIT").addWeight("BRICKS_2", 0.15f);
        CHUNKS.get("PIT").addWeight("FOUR_QUESTION_BLOCKS", 0.25f);
        CHUNKS.get("PIT").addWeight("ENEMIES_1", 1.25f);
        CHUNKS.get("PIT").addWeight("PIPE_2", 0.5f);

        CHUNKS.get("PIT").tags.add(Tag.PIT);

        CHUNKS.put("ENEMIES_1", new Chunk("""
            -r-g-g-
            XXXXXXX
            XXXXXXX
            """));
        CHUNKS.get("ENEMIES_1").addWeight("START_HIGH_SECTION", 0.5f);
        CHUNKS.get("ENEMIES_1").addWeight("BRICKS_1", 0.15f);
        CHUNKS.get("ENEMIES_1").addWeight("BRICKS_2", 0.15f);
        CHUNKS.get("ENEMIES_1").addWeight("FOUR_QUESTION_BLOCKS", 0.25f);
        CHUNKS.get("ENEMIES_1").addWeight("PIT", 1.25f);
        CHUNKS.get("ENEMIES_1").addWeight("PIPE_2", 0.5f);

        CHUNKS.get("ENEMIES_1").tags.add(Tag.ENEMY);
        CHUNKS.get("ENEMIES_1").tags.add(Tag.KOOPA);
        CHUNKS.get("ENEMIES_1").tags.add(Tag.GOOMBA);

        CHUNKS.put("PIPE_2", new Chunk("""
            ----
            -tt-
            -tt-
            XXXX
            XXXX
            """));
        CHUNKS.get("PIPE_2").addWeight("PIPE_INBETWEEN_1", 0.5f);
        CHUNKS.get("PIPE_2").addWeight("PIPE_INBETWEEN_2", 0.5f);

        CHUNKS.get("PIPE_2").tags.add(Tag.PIPE);

        CHUNKS.put("PIPE_3", new Chunk("""
            -tt-
            -tt-
            -tt-
            XXXX
            XXXX
            """));
        CHUNKS.get("PIPE_3").addWeight("PIPE_INBETWEEN_1", 0.5f);
        CHUNKS.get("PIPE_3").addWeight("PIPE_INBETWEEN_2", 0.5f);

        CHUNKS.get("PIPE_3").tags.add(Tag.PIPE);

        CHUNKS.put("PIPE_4", new Chunk("""
            -tt-
            -tt-
            -tt-
            -tt-
            XXXX
            XXXX
            """));
        CHUNKS.get("PIPE_4").addWeight("PIPE_INBETWEEN_1", 0.5f);
        CHUNKS.get("PIPE_4").addWeight("PIPE_INBETWEEN_2", 0.5f);

        CHUNKS.get("PIPE_4").tags.add(Tag.PIPE);

        CHUNKS.put("PIPE_5", new Chunk("""
            -tt-
            -tt-
            -tt-
            -tt-
            XXXX
            XXXX
            """));
        CHUNKS.get("PIPE_5").addWeight("PIPE_INBETWEEN_1", 0.5f);
        CHUNKS.get("PIPE_5").addWeight("PIPE_INBETWEEN_2", 0.5f);

        CHUNKS.get("PIPE_2").tags.add(Tag.PIPE);

        CHUNKS.put("PIPE_INBETWEEN_1", new Chunk("""
            ----
            ----
            -g-g
            XXXX
            XXXX
            """));
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("PIPE_3", 2.0f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("PIPE_3", new SpecificChunkFactor("PIPE_2", 1.0f, "LastX", 5));
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("PIPE_4", 1.0f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("PIPE_4", new SpecificChunkFactor("PIPE_2", 1.0f, "LastX", 5));
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("PIPE_5", 1.0f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("PIPE_5", new SpecificChunkFactor("PIPE_2", 1.0f, "LastX", 5));
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("START_HIGH_SECTION", 0.5f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("BRICKS_1", 0.5f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("BRICKS_2", 0.5f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("FOUR_QUESTION_BLOCKS", 0.5f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("PIT", 0.5f);
        CHUNKS.get("PIPE_INBETWEEN_1").addWeight("ENEMIES_1", 0.5f);

        CHUNKS.get("PIPE_INBETWEEN_1").tags.add(Tag.ENEMY);
        CHUNKS.get("PIPE_INBETWEEN_1").tags.add(Tag.GOOMBA);

        CHUNKS.put("PIPE_INBETWEEN_2", new Chunk("""
            ----
            ----
            -kg-
            XXXX
            XXXX
            """));
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("PIPE_3", 1.0f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("PIPE_3", new SpecificChunkFactor("PIPE_2", 1.0f, "LastX", 5));
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("PIPE_4", 1.0f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("PIPE_4", new SpecificChunkFactor("PIPE_2", 1.0f, "LastX", 5));
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("PIPE_5", 1.0f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("PIPE_5", new SpecificChunkFactor("PIPE_2", 1.0f, "LastX", 5));
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("START_HIGH_SECTION", 0.5f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("BRICKS_1", 0.5f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("BRICKS_2", 0.5f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("FOUR_QUESTION_BLOCKS", 0.5f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("PIT", 0.5f);
        CHUNKS.get("PIPE_INBETWEEN_2").addWeight("ENEMIES_1", 0.5f);

        CHUNKS.get("PIPE_INBETWEEN_2").tags.add(Tag.ENEMY);
        CHUNKS.get("PIPE_INBETWEEN_2").tags.add(Tag.KOOPA);
        CHUNKS.get("PIPE_INBETWEEN_2").tags.add(Tag.GOOMBA);

        CHUNKS.put("START_HIGH_SECTION", new Chunk("""
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
        CHUNKS.get("START_HIGH_SECTION").addWeight("HIGH_SECTION_ENEMY_PIT", 0.5f);
        CHUNKS.get("START_HIGH_SECTION").addWeight("HIGH_SECTION_GAP", 0.5f);
        CHUNKS.get("START_HIGH_SECTION").addWeight("HIGH_SECTION_GAP2", 0.5f);
        CHUNKS.get("START_HIGH_SECTION").addWeight("HIGH_SECTION_END", 1.0f);

        CHUNKS.get("START_HIGH_SECTION").tags.add(Tag.ENEMY);
        CHUNKS.get("START_HIGH_SECTION").tags.add(Tag.GOOMBA);
        CHUNKS.get("START_HIGH_SECTION").tags.add(Tag.PIT);

        CHUNKS.put("HIGH_SECTION_ENEMY_PIT", new Chunk("""
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
        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").addWeight("HIGH_SECTION_GAP", 0.0f);
        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").addWeight("HIGH_SECTION_GAP", new SpecificChunkFactor("START_HIGH_SECTION", 0.6f, "LastX", 5));
        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").addWeight("HIGH_SECTION_GAP2", 0.0f);
        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").addWeight("HIGH_SECTION_GAP2", new SpecificChunkFactor("START_HIGH_SECTION", 0.6f, "LastX", 5));
        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").addWeight("HIGH_SECTION_ENEMY_PIT", 0.0f);
        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").addWeight("HIGH_SECTION_ENEMY_PIT", new SpecificChunkFactor("START_HIGH_SECTION", 0.3f, "LastX", 5));
        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").addWeight("HIGH_SECTION_END", 1.0f);

        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").tags.add(Tag.ENEMY);
        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").tags.add(Tag.GOOMBA);
        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").tags.add(Tag.KOOPA);
        CHUNKS.get("HIGH_SECTION_ENEMY_PIT").tags.add(Tag.PIT);

        CHUNKS.put("HIGH_SECTION_GAP", new Chunk("""
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
        CHUNKS.get("HIGH_SECTION_GAP").addWeight("HIGH_SECTION_GAP", 0.0f);
        CHUNKS.get("HIGH_SECTION_GAP").addWeight("HIGH_SECTION_GAP", new SpecificChunkFactor("START_HIGH_SECTION", 0.3f, "LastX", 5));
        CHUNKS.get("HIGH_SECTION_GAP").addWeight("HIGH_SECTION_GAP2", 0.0f);
        CHUNKS.get("HIGH_SECTION_GAP").addWeight("HIGH_SECTION_GAP2", new SpecificChunkFactor("START_HIGH_SECTION", 0.3f, "LastX", 5));
        CHUNKS.get("HIGH_SECTION_GAP").addWeight("HIGH_SECTION_ENEMY_PIT", 0.0f);
        CHUNKS.get("HIGH_SECTION_GAP").addWeight("HIGH_SECTION_ENEMY_PIT", new SpecificChunkFactor("START_HIGH_SECTION", 0.6f, "LastX", 5));
        CHUNKS.get("HIGH_SECTION_GAP").addWeight("HIGH_SECTION_END", 1.0f);

        CHUNKS.get("HIGH_SECTION_GAP").tags.add(Tag.ENEMY);
        CHUNKS.get("HIGH_SECTION_GAP").tags.add(Tag.GOOMBA);
        CHUNKS.get("HIGH_SECTION_GAP").tags.add(Tag.PIT);

        CHUNKS.put("HIGH_SECTION_GAP2", new Chunk("""
            ------------
            S-----------
            ----SSSSS---
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            ------------
            """));
        CHUNKS.get("HIGH_SECTION_GAP2").addWeight("HIGH_SECTION_GAP", 0.0f);
        CHUNKS.get("HIGH_SECTION_GAP2").addWeight("HIGH_SECTION_GAP", new SpecificChunkFactor("START_HIGH_SECTION", 0.3f, "LastX", 5));
        CHUNKS.get("HIGH_SECTION_GAP2").addWeight("HIGH_SECTION_GAP2", 0.0f);
        CHUNKS.get("HIGH_SECTION_GAP2").addWeight("HIGH_SECTION_GAP2", new SpecificChunkFactor("START_HIGH_SECTION", 0.3f, "LastX", 5));
        CHUNKS.get("HIGH_SECTION_GAP2").addWeight("HIGH_SECTION_ENEMY_PIT", 0.0f);
        CHUNKS.get("HIGH_SECTION_GAP2").addWeight("HIGH_SECTION_ENEMY_PIT", new SpecificChunkFactor("START_HIGH_SECTION", 0.6f, "LastX", 5));
        CHUNKS.get("HIGH_SECTION_GAP2").addWeight("HIGH_SECTION_END", 1.0f);

        CHUNKS.get("HIGH_SECTION_GAP2").tags.add(Tag.PIT);

        CHUNKS.put("HIGH_SECTION_END", new Chunk("""
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
        CHUNKS.get("HIGH_SECTION_END").addWeight("BRICKS_1", 0.5f);
        CHUNKS.get("HIGH_SECTION_END").addWeight("BRICKS_2", 0.2f);
        CHUNKS.get("HIGH_SECTION_END").addWeight("FOUR_QUESTION_BLOCKS", 0.2f);
        CHUNKS.get("HIGH_SECTION_END").addWeight("PIT", 0.2f);
        CHUNKS.get("HIGH_SECTION_END").addWeight("ENEMIES_1", 0.2f);

        CHUNKS.get("HIGH_SECTION_END").tags.add(Tag.ENEMY);
        CHUNKS.get("HIGH_SECTION_END").tags.add(Tag.KOOPA);
        CHUNKS.get("HIGH_SECTION_END").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_1", new Chunk("""
            -%%%%-
            --||--
            --||--
            """));

        CHUNKS.get("MUSHROOM_1").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_1").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_1_HARD", new Chunk("""
            ---g--
            -%%%%-
            --||--
            --||--
            """));

        CHUNKS.get("MUSHROOM_1").tags.add(Tag.ENEMY);
        CHUNKS.get("MUSHROOM_1").tags.add(Tag.GOOMBA);
        CHUNKS.get("MUSHROOM_1").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_1").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_2", new Chunk("""
            ----ooor--
            ---%%%%%--
            ----|||---
            ----|||---
            ----|||---
            -%%%%%%%%-
            --||||||--
            --||||||--
            --||||||--
            --||||||--
            --||||||--
            """));

        CHUNKS.get("MUSHROOM_2").tags.add(Tag.ENEMY);
        CHUNKS.get("MUSHROOM_2").tags.add(Tag.KOOPA);
        CHUNKS.get("MUSHROOM_2").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_2").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_2_HARD", new Chunk("""
            ---rooo---
            --%%%%%---
            ---|||--o-
            ---|||--o-
            ---|||--G-
            -%%%%%%%%-
            --||||||--
            --||||||--
            --||||||--
            --||||||--
            --||||||--
            """));

        CHUNKS.get("MUSHROOM_2_HARD").tags.add(Tag.ENEMY);
        CHUNKS.get("MUSHROOM_2_HARD").tags.add(Tag.GOOMBA);
        CHUNKS.get("MUSHROOM_2_HARD").tags.add(Tag.KOOPA);
        CHUNKS.get("MUSHROOM_2_HARD").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_2_HARD").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_3", new Chunk("""
            -----
            -----
            -----
            %%%%%
            -|||-
            -|||-
            -|||-
            -|||-
            -|||-
            -|||-
            """));

        CHUNKS.get("MUSHROOM_3").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_3").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_3_HARD", new Chunk("""
            ---g-
            %%%%%
            -|||-
            -|||-
            -|||-
            -|||-
            -|||-
            -|||-
            """));

        CHUNKS.get("MUSHROOM_3_HARD").tags.add(Tag.ENEMY);
        CHUNKS.get("MUSHROOM_3_HARD").tags.add(Tag.GOOMBA);
        CHUNKS.get("MUSHROOM_3_HARD").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_3_HARD").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_4", new Chunk("""
            ----g-g
            %%%%%%%
            -|||||-
            -|||||-
            -|||||-
            -|||||-
            -|||||-
            -|||||-
            -|||||-
            -|||||-
            -|||||-
            -|||||-
            """));

        CHUNKS.get("MUSHROOM_4").tags.add(Tag.ENEMY);
        CHUNKS.get("MUSHROOM_4").tags.add(Tag.GOOMBA);
        CHUNKS.get("MUSHROOM_4").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_4").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_5", new Chunk("""
            -%%%-
            --|--
            --|--
            """));

        CHUNKS.get("MUSHROOM_5").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_5").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_5_HARD", new Chunk("""
            ---g-
            -%%%-
            --|--
            --|--
            """));

        CHUNKS.get("MUSHROOM_5_HARD").tags.add(Tag.ENEMY);
        CHUNKS.get("MUSHROOM_5_HARD").tags.add(Tag.GOOMBA);
        CHUNKS.get("MUSHROOM_5_HARD").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_5_HARD").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_6", new Chunk("""
            --oooo-
            --%%%%-
            ---||--
            ---||--
            ---||--
            -U-||--
            ---||--
            ---||--
            ---||--
            -%%%%%-
            --|||--
            """));

        CHUNKS.get("MUSHROOM_6").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_6").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_7", new Chunk("""
            %%%%%-
            -|||--
            """));

        CHUNKS.get("MUSHROOM_7").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_7").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_7_HARD", new Chunk("""
            --K---
            ------
            ------
            ------
            ------
            %%%%%-
            -|||--
            """));

        CHUNKS.get("MUSHROOM_7").tags.add(Tag.ENEMY);
        CHUNKS.get("MUSHROOM_7").tags.add(Tag.KOOPA);
        CHUNKS.get("MUSHROOM_7").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_7").tags.add(Tag.PIT);
        CHUNKS.get("MUSHROOM_7").tags.add(Tag.WINGED);

        CHUNKS.put("MUSHROOM_8", new Chunk("""
            %%%
            -|-
            -|-
            -|-
            -|-
            -|-
            """));

        CHUNKS.get("MUSHROOM_8").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_8").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_9", new Chunk("""
            -----
            -----
            -----
            -----
            -----
            -----
            ---r-
            -ooo-
            -%%%-
            --|--
            """));

        CHUNKS.get("MUSHROOM_9").tags.add(Tag.ENEMY);
        CHUNKS.get("MUSHROOM_9").tags.add(Tag.KOOPA);
        CHUNKS.get("MUSHROOM_9").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_9").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_10", new Chunk("""
            ---ooo-
            -------
            ---%%%-
            ----|--
            ----|--
            ----|--
            -%%%|--
            --|-|--
            --|-|--
            --|-|--
            """));

        CHUNKS.get("MUSHROOM_10").tags.add(Tag.MUSHROOM_BLOCKS);
        CHUNKS.get("MUSHROOM_10").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_GAP_1", new Chunk("""
            -oo--
            -----
            -----
            -SSS-
            -----
            -----
            -----
            -----
            -----
            -----
            """));

        CHUNKS.get("MUSHROOM_GAP_1").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_GAP_2", new Chunk("""
            -------
            ---SS--
            ---S---
            ---S---
            --SS---
            -------
            -------
            """));

        CHUNKS.get("MUSHROOM_GAP_2").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_GAP_2_HARD", new Chunk("""
            ---!---
            -------
            ----K--
            -------
            --SSS--
            -------
            -------
            """));

        CHUNKS.get("MUSHROOM_GAP_2_HARD").tags.add(Tag.ENEMY);
        CHUNKS.get("MUSHROOM_GAP_2_HARD").tags.add(Tag.KOOPA);
        CHUNKS.get("MUSHROOM_GAP_2_HARD").tags.add(Tag.WINGED);
        CHUNKS.get("MUSHROOM_GAP_2_HARD").tags.add(Tag.PIT);

        CHUNKS.put("MUSHROOM_GAP_3", new Chunk("""
            -oooo-
            -SSSS-
            ------
            ------
            ------
            ------
            ------
            ------
            ------
            ------
            """));

        CHUNKS.get("MUSHROOM_GAP_3").tags.add(Tag.PIT);

        // TODO: this is probably the worst thing ever right now
        for(int i = 0; i < 10; i++){
            for(int h = 0; h < 2; h++) {
                var key = "MUSHROOM_" + (i + 1);
                if(h == 1) {
                    key += "_HARD";
                    if(!CHUNKS.containsKey(key)) {
                        continue;
                    }
                }

                var diff_factor = new ParamMultFactor("difficulty", 0.15f);

                CHUNKS.get(key).addWeight("MUSHROOM_1", 1.0f).addWeight("MUSHROOM_1", new SpecificChunkFactor("MUSHROOM_1", -0.8f, "LastX", 3));
                CHUNKS.get(key).addWeight("MUSHROOM_1_HARD", 0.10f)
                        .addWeight("MUSHROOM_1_HARD", diff_factor); // TODO: factor in difficulty setting
                CHUNKS.get(key).addWeight("MUSHROOM_2", 1.0f)
                        .addWeight("MUSHROOM_2", new SpecificChunkFactor("MUSHROOM_2", -0.8f, "LastX", 3))
                        .addWeight("MUSHROOM_2_HARD", new SpecificChunkFactor("MUSHROOM_2_HARD", -0.8f, "LastX", 3));
                CHUNKS.get(key).addWeight("MUSHROOM_2_HARD", 0.25f) // TODO: factor in difficulty setting
                        .addWeight("MUSHROOM_2", new SpecificChunkFactor("MUSHROOM_2", -0.2f, "LastX", 3))
                        .addWeight("MUSHROOM_2_HARD", new SpecificChunkFactor("MUSHROOM_2_HARD", -0.2f, "LastX", 3))
                        .addWeight("MUSHROOM_2_HARD", diff_factor);
                if (!Arrays.asList(7, 9).contains(i + 1)) {
                    CHUNKS.get(key).addWeight("MUSHROOM_3", 1.0f).addWeight("MUSHROOM_3", new SpecificChunkFactor("MUSHROOM_3", -0.8f, "LastX", 3));
                    CHUNKS.get(key).addWeight("MUSHROOM_3_HARD", 0.25f)
                            .addWeight("MUSHROOM_3_HARD", diff_factor); // TODO: factor in difficulty setting
                }
                if (!Arrays.asList(1, 5, 7, 9).contains(i + 1)) {
                    CHUNKS.get(key).addWeight("MUSHROOM_4", 1.0f).addWeight("MUSHROOM_4", new SpecificChunkFactor("MUSHROOM_4", -0.8f, "LastX", 3));
                    CHUNKS.get(key).addWeight("MUSHROOM_5", 0.5f)
                            .addWeight("MUSHROOM_5", new SpecificChunkFactor("MUSHROOM_5", -0.8f, "LastX", 3))
                            .addWeight("MUSHROOM_5_HARD", new SpecificChunkFactor("MUSHROOM_5_HARD", -0.8f, "LastX", 3))
                            .addWeight("MUSHROOM_5_HARD", diff_factor);
                    CHUNKS.get(key).addWeight("MUSHROOM_5_HARD", 0.1f)
                            .addWeight("MUSHROOM_5", new SpecificChunkFactor("MUSHROOM_5", -0.8f, "LastX", 3))
                            .addWeight("MUSHROOM_5_HARD", new SpecificChunkFactor("MUSHROOM_5_HARD", -0.8f, "LastX", 3))
                            .addWeight("MUSHROOM_5_HARD", diff_factor);
                }
                CHUNKS.get(key).addWeight("MUSHROOM_6", 1.0f).addWeight("MUSHROOM_6", new SpecificChunkFactor("MUSHROOM_6", -0.8f, "LastX", 3));
                CHUNKS.get(key).addWeight("MUSHROOM_7", 1.0f).addWeight("MUSHROOM_7", new SpecificChunkFactor("MUSHROOM_7", -0.8f, "LastX", 3));
                CHUNKS.get(key).addWeight("MUSHROOM_7_HARD", 0.1f)
                        .addWeight("MUSHROOM_7_HARD", diff_factor); // TODO: factor in difficulty setting
                CHUNKS.get(key).addWeight("MUSHROOM_8", 1.0f).addWeight("MUSHROOM_8", new SpecificChunkFactor("MUSHROOM_8", -0.8f, "LastX", 3));
                CHUNKS.get(key).addWeight("MUSHROOM_9", 1.0f).addWeight("MUSHROOM_9", new SpecificChunkFactor("MUSHROOM_9", -0.8f, "LastX", 3));
                CHUNKS.get(key).addWeight("MUSHROOM_10", 1.0f).addWeight("MUSHROOM_10", new SpecificChunkFactor("MUSHROOM_10", -0.8f, "LastX", 3));

                CHUNKS.get(key).addWeight(key, -0.8f);

                CHUNKS.get(key).addWeight("MUSHROOM_GAP_2", 0.25f);
                CHUNKS.get(key).addWeight("MUSHROOM_GAP_2_HARD", 0.05f)
                        .addWeight("MUSHROOM_GAP_2_HARD", diff_factor); // TODO: factor in difficulty setting
                if (!Arrays.asList(1, 7, 9).contains(i + 1)) {
                    CHUNKS.get(key).addWeight("MUSHROOM_GAP_1", 0.25f);
                    CHUNKS.get(key).addWeight("MUSHROOM_GAP_3", 0.25f);
                }
            }
        }

        for(int i = 0; i < 3; i++){
            for(int h = 0; h < 2; h++) {
                var key = "MUSHROOM_GAP_" + (i + 1);
                if (h == 1) {
                    key += "_HARD";
                    if (!CHUNKS.containsKey(key)) {
                        continue;
                    }
                }

                var diff_factor = new ParamMultFactor("difficulty", 0.15f);

                CHUNKS.get(key).addWeight("MUSHROOM_1", 1.0f).addWeight("MUSHROOM_1", new SpecificChunkFactor("MUSHROOM_1", -0.8f, "LastX", 3));
                CHUNKS.get(key).addWeight("MUSHROOM_1_HARD", 0.1f)
                        .addWeight("MUSHROOM_1_HARD", diff_factor); // TODO: factor in difficulty setting
                CHUNKS.get(key).addWeight("MUSHROOM_2", 1.0f).addWeight("MUSHROOM_2", new SpecificChunkFactor("MUSHROOM_2", -0.8f, "LastX", 3));
                CHUNKS.get(key).addWeight("MUSHROOM_2_HARD", 0.1f)
                        .addWeight("MUSHROOM_2_HARD", diff_factor); // TODO: factor in difficulty setting
//                if (!Arrays.asList(7, 9).contains(i + 1)) {
                    CHUNKS.get(key).addWeight("MUSHROOM_3", 1.0f).addWeight("MUSHROOM_3", new SpecificChunkFactor("MUSHROOM_3", -0.8f, "LastX", 3));
                    CHUNKS.get(key).addWeight("MUSHROOM_3_HARD", 0.1f)
                            .addWeight("MUSHROOM_3_HARD", diff_factor); // TODO: factor in difficulty setting
//                }
                if (!Arrays.asList(2, 3).contains(i + 1)) {
                    CHUNKS.get(key).addWeight("MUSHROOM_4", 1.0f).addWeight("MUSHROOM_4", new SpecificChunkFactor("MUSHROOM_4", -0.8f, "LastX", 3));
                    CHUNKS.get(key).addWeight("MUSHROOM_5", 1.0f).addWeight("MUSHROOM_5", new SpecificChunkFactor("MUSHROOM_5", -0.8f, "LastX", 3));
                }
                CHUNKS.get(key).addWeight("MUSHROOM_6", 1.0f).addWeight("MUSHROOM_6", new SpecificChunkFactor("MUSHROOM_6", -0.8f, "LastX", 3));
                CHUNKS.get(key).addWeight("MUSHROOM_7", 1.0f)
                        .addWeight("MUSHROOM_7", new SpecificChunkFactor("MUSHROOM_7", -0.8f, "LastX", 3))
                        .addWeight("MUSHROOM_7_HARD", new SpecificChunkFactor("MUSHROOM_7_HARD", -0.8f, "LastX", 3))
                        .addWeight("MUSHROOM_7_HARD", diff_factor);
                CHUNKS.get(key).addWeight("MUSHROOM_7_HARD", 0.1f) // TODO: factor in difficulty setting
                        .addWeight("MUSHROOM_7", new SpecificChunkFactor("MUSHROOM_7", -0.05f, "LastX", 3))
                        .addWeight("MUSHROOM_7_HARD", new SpecificChunkFactor("MUSHROOM_7_HARD", -0.05f, "LastX", 3))
                        .addWeight("MUSHROOM_7_HARD", diff_factor);
                CHUNKS.get(key).addWeight("MUSHROOM_8", 1.0f).addWeight("MUSHROOM_8", new SpecificChunkFactor("MUSHROOM_8", -0.8f, "LastX", 3));
                CHUNKS.get(key).addWeight("MUSHROOM_9", 1.0f).addWeight("MUSHROOM_9", new SpecificChunkFactor("MUSHROOM_9", -0.8f, "LastX", 3));
                CHUNKS.get(key).addWeight("MUSHROOM_10", 1.0f).addWeight("MUSHROOM_10", new SpecificChunkFactor("MUSHROOM_10", -0.8f, "LastX", 3));

                CHUNKS.get(key).addWeight(key, -0.8f);

                CHUNKS.get(key).addWeight("MUSHROOM_GAP_2", 0.25f);
                CHUNKS.get(key).addWeight("MUSHROOM_GAP_2_HARD", 0.1f)
                        .addWeight("MUSHROOM_GAP_2_HARD", diff_factor); // TODO: factor in difficulty setting
                if (!Arrays.asList(1, 7, 9).contains(i + 1)) {
                    CHUNKS.get(key).addWeight("MUSHROOM_GAP_1", 0.25f);
                    CHUNKS.get(key).addWeight("MUSHROOM_GAP_3", 0.25f);
                }
            }
        }

        CHUNKS.put("", new Chunk("""
            ----------LSSS------
            --------------------
            ------g-------------
            ------#-------------
            -----##---2-----#---
            ----###---------#---
            ---####---------##--
            --#####----kk---##--
            XXXXXXXXXXXXXXXXXXXX
            XXXXXXXXXXXXXXXXXXXX
            """));

        CHUNKS.put("", new Chunk("""
            --QQQQQ--
            ---------
            ---------
            ----k----
            --?QQQQ--
            ---------
            ---------
            ---------
            XXXXXXXXX
            XXXXXXXXX
            """));

        CHUNKS.put("", new Chunk("""
            ---SSCSS-----
            -------------
            -------------
            -------------
            -QQQQ--QQQ---
            -------------
            -------------
            ----------ggg
            XXXXXXXXXXXXX
            XXXXXXXXXXXXX
            """));

        CHUNKS.put("", new Chunk("""
            -SSSS-
            ------
            ------
            ------
            ------
            ------
            ------
            ------
            X----X
            X----X
            """));

        CHUNKS.put("", new Chunk("""
            -g-------
            -TT------
            -TT------
            -TT------
            -TT------
            XXXX---XX
            XXXX---XX
            """));

        CHUNKS.put("", new Chunk("""
            USSS
            ----
            ----
            ----
            ----
            -tt-
            -tt-
            -tt-
            XXXX
            XXXX
            """));

        CHUNKS.put("", new Chunk("""
            -SSQSSS?SSS--
            -------------
            -------------
            ---K---------
            -SSSSSSSSSSS-
            -------------
            -------------
            ---------K---
            XXXXXXXXXXXXX
            XXXXXXXXXXXXX
            """));

        CHUNKS.put("", new Chunk("""
            --k-------
            -SQS--SQS-
            ----------
            ----------
            ----------
            -SQS--S?S-
            ----------
            ----------
            ------ggg-
            XXXXXXXXXX
            XXXXXXXXXX
            """));

        CHUNKS.put("", new Chunk("""
            ----k--
            -SCSSS-
            ---K---
            -------
            -K---K-
            XXXXXXX
            XXXXXXX
            """));

        CHUNKS.put("END_STAIRS", new Chunk("""
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
        CHUNKS.get("END_STAIRS").addWeight("END", 1.0f);

        CHUNKS.get("END_STAIRS").tags.add(Tag.END_STAIRS);

        CHUNKS.put("END_STAIRS_HARD_1", new Chunk("""
            ---------k-------
            --------##-------
            -------###-------
            -----k####-------
            -----#####-------
            ----######-------
            ---#######-------
            --########-------
            -#########-------
            XXXXXXXXXXXXXXXXX
            XXXXXXXXXXXXXXXXX
            """));
        CHUNKS.get("END_STAIRS_HARD_1").addWeight("END", 1.0f);

        CHUNKS.get("END_STAIRS_HARD_1").tags.add(Tag.END_STAIRS);

        CHUNKS.put("END_STAIRS_HARD_2", new Chunk("""
            -----------------
            ---------#-------
            -------#-#-------
            -------#-#-------
            -----#-#-#-------
            -----#-#-#-------
            ---#-#-#-#-------
            ---#-#-#-#-------
            -#-#-#-#-#-------
            XX-X-X-X-XXXXXXXX
            XX-X-X-X-XXXXXXXX
            """));
        CHUNKS.get("END_STAIRS_HARD_2").addWeight("END", 1.0f);

        CHUNKS.get("END_STAIRS_HARD_2").tags.add(Tag.END_STAIRS);

        CHUNKS.put("END", new Chunk("""
            ----
            -F--
            -#--
            XXXX
            XXXX
            """));

        CHUNKS.get("END").tags.add(Tag.FLAG);

        CHUNKS.put("TEST_A", new Chunk(""));
        CHUNKS.get("TEST_A").tags.add(Tag.TAG_A);
        CHUNKS.put("TEST_B", new Chunk(""));
        CHUNKS.get("TEST_B").tags.add(Tag.TAG_B);
        CHUNKS.put("TEST_C", new Chunk(""));
        CHUNKS.get("TEST_C").tags.add(Tag.TAG_C);

        for (String k : CHUNKS.keySet()) {
            Chunk c = CHUNKS.get(k);
            for (String wck : c.weights.keySet()) {
                if(CHUNKS.get(wck).tags.contains(Tag.ENEMY)) {
                    c.addWeight(wck, -0.25f);
                    c.addWeight(wck, new ParamMultFactor("difficulty", 0.25f));
                }
            }
        }

        for (String k : CHUNKS.keySet()) {
            Chunk c = CHUNKS.get(k);
            for (String wck : c.weights.keySet()) {
                if(CHUNKS.get(wck).tags.contains(Tag.PIPE)) {
                    c.addWeight(wck, -0.25f);
                    c.addWeight(wck, new ParamCompFactor("theme", "Equal", 2, 1.0f));
                }
            }
        }

    }

}