package de.bluecolored.nbtlibtest.libs;

import de.bluecolored.nbtlibtest.Chunk;
import de.bluecolored.nbtlibtest.NBTLibrary;
import dev.dewy.nbt.Nbt;
import dev.dewy.nbt.tags.collection.CompoundTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitBufNbtLibrary implements NBTLibrary {

    private final Nbt nbt = new Nbt();
    @Override
    public Chunk loadChunk(InputStream in) throws IOException {
        CompoundTag data = nbt.fromStream(new DataInputStream(in));
        return new ChunkImpl(
                data.getInt("DataVersion").getValue(),
                data.getInt("xPos").getValue(),
                data.getInt("yPos").getValue(),
                data.getInt("zPos").getValue(),
                data.getLong("InhabitedTime").getValue(),
                data.getString("Status").getValue()
        );
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChunkImpl implements Chunk {
        private int dataVersion;
        private int xPos;
        private int yPos;
        private int zPos;
        private long inhabitedTime;
        private String status;
    }
}
