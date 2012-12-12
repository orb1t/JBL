package com.github.Icyene.bytecode.introspection.internal.pools;

import com.github.Icyene.bytecode.introspection.internal.members.Interface;
import com.github.Icyene.bytecode.introspection.util.ByteStream;
import com.github.Icyene.bytecode.introspection.util.Bytes;

import java.util.LinkedList;

public class InterfacePool extends LinkedList<Interface> {
    public InterfacePool(ByteStream stream, ConstantPool pool) {
        short size = stream.readShort();
        if (size <= 0) return;
        for (int i = 0; i != size; i++)
            add(new Interface(pool, stream.read(4)));
    }

    public byte[] getBytes() {
        byte[] raw = Bytes.toByteArray((short) size());
        for (Interface cpi : this)
            raw = Bytes.concat(raw, cpi.getBytes());
        return raw;
    }

    public int getSizeInBytes() {
        return size() << 1; //Everything is u2
    }
}
