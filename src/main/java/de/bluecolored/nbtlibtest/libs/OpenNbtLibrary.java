package de.bluecolored.nbtlibtest.libs;

import com.github.steveice10.opennbt.NBTIO;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.opennbt.tag.builtin.IntTag;
import com.github.steveice10.opennbt.tag.builtin.LongTag;
import com.github.steveice10.opennbt.tag.builtin.StringTag;
import de.bluecolored.nbtlibtest.Chunk;
import de.bluecolored.nbtlibtest.NBTLibrary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

public class OpenNbtLibrary implements NBTLibrary {

    @Override
    public Chunk loadChunk(InputStream in) throws IOException {
        CompoundTag data = (CompoundTag) NBTIO.readTag(in);
        return new ChunkImpl(
                data.<IntTag>get("DataVersion").getValue(),
                data.<IntTag>get("xPos").getValue(),
                data.<IntTag>get("yPos").getValue(),
                data.<IntTag>get("zPos").getValue(),
                data.<LongTag>get("InhabitedTime").getValue(),
                data.<StringTag>get("Status").getValue()
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
