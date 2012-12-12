package com.github.Icyene.bytecode.introspection.internal.pools;

import com.github.Icyene.bytecode.introspection.util.ByteStream;
import com.github.Icyene.bytecode.introspection.util.Bytes;
import com.github.Icyene.bytecode.introspection.internal.members.Exception;

import java.util.LinkedList;

public class ExceptionPool extends LinkedList<Exception> {

    public ExceptionPool(ByteStream stream) {
        short size = stream.readShort();
        for (int i = 0; i != size; i++)
            add(new Exception(stream));
    }

    public byte[] getBytes() {
        byte[] raw = Bytes.toByteArray((short) size());
        for (Exception e : this)
            raw = Bytes.concat(raw, e.getBytes());
        return raw;
    }
}
