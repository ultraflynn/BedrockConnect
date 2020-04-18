package com.pyratron.pugmatt.bedrockconnect.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nukkitx.nbt.NbtUtils;
import com.nukkitx.nbt.stream.NBTInputStream;
import com.nukkitx.nbt.tag.CompoundTag;
import com.nukkitx.nbt.tag.ListTag;
import com.pyratron.pugmatt.bedrockconnect.BedrockConnect;
import io.netty.buffer.ByteBuf;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// https://github.com/DragonetMC/DragonProxy/blob/rewrite/proxy/src/main/java/org/dragonet/proxy/util/PaletteManager.java
// Author: lukeeey

public class PaletteManager {

    private ArrayList<RuntimeEntry> entries;

    private ByteBuf cachedPalette;

    public ListTag<CompoundTag> CACHED_PALLETE;

    public PaletteManager() {
        InputStream stream = BedrockConnect.class.getClassLoader().getResourceAsStream("tables/runtime_block_states.dat");
        if (stream == null) {
            throw new AssertionError("Unable to locate block state nbt");
        }

        Map<String, Integer> blockIdToIdentifier = new HashMap<>();
        ListTag<CompoundTag> tag;

        NBTInputStream nbtInputStream = NbtUtils.createReader(stream);
        try {
            tag = (ListTag<CompoundTag>) nbtInputStream.readTag();
            nbtInputStream.close();
        } catch (Exception ex) {
            System.out.println("Failed to receive blocks palette");
            throw new AssertionError(ex);
        }

        CACHED_PALLETE = tag;
    }

    private static class RuntimeEntry {
        @JsonProperty("name")
        private String name;
        @JsonProperty("id")
        private int id;
        @JsonProperty("data")
        private int data;

        public RuntimeEntry() {
        }

        public RuntimeEntry(String name, int id, int data) {
            this.id = id;
            this.name = name;
            this.data = data;
        }
    }
}
