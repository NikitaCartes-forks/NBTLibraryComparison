package de.bluecolored.nbtlibtest.libs;

import de.bluecolored.nbtlibtest.Chunk;
import de.bluecolored.nbtlibtest.NBTLibrary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class VanillaLibrary implements NBTLibrary {

    @Override
    public Chunk loadChunk(InputStream in) throws IOException {
        CompoundTag data = NbtIo.read(new DataInputStream(in), NbtAccounter.unlimitedHeap());
        return new ChunkyLibrary.ChunkImpl(
                data.getInt("DataVersion"),
                data.getInt("xPos"),
                data.getInt("yPos"),
                data.getInt("zPos"),
                data.getLong("InhabitedTime"),
                data.getString("Status")
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
