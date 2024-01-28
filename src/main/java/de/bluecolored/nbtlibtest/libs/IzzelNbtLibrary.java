package de.bluecolored.nbtlibtest.libs;

import de.bluecolored.nbtlibtest.Chunk;
import de.bluecolored.nbtlibtest.NBTLibrary;
import io.izzel.nbt.CompoundTag;
import io.izzel.nbt.util.NbtReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

public class IzzelNbtLibrary implements NBTLibrary {

    @Override
    public Chunk loadChunk(InputStream in) throws IOException {

        CompoundTag data = new NbtReader(in).toCompoundTag();
        return new ChunkImpl(
                data.getIntOrDefault("DataVersion"),
                data.getIntOrDefault("xPos"),
                data.getIntOrDefault("yPos"),
                data.getIntOrDefault("zPos"),
                data.getLongOrDefault("InhabitedTime"),
                data.getStringOrDefault("Status")
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
