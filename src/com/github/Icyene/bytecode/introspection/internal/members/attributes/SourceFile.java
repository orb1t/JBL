package com.github.Icyene.bytecode.introspection.internal.members.attributes;

import com.github.Icyene.bytecode.introspection.internal.members.Attribute;
import com.github.Icyene.bytecode.introspection.internal.members.Constant;
import com.github.Icyene.bytecode.introspection.internal.pools.ConstantPool;
import com.github.Icyene.bytecode.introspection.util.ByteStream;
import com.github.Icyene.bytecode.introspection.util.Bytes;

import static com.github.Icyene.bytecode.introspection.internal.metadata.Opcode.TAG_UTF_STRING;

/**
 * Source file attribute, defines what file a class was compiled from. Source file name doesn't have to be true.
 */
public class SourceFile extends Attribute {
    private Constant sourceIndex;

    /**
     * Constructs a SourceFile attribute.
     * @param stream stream containing encoded data.
     * @param name the name, "SourceFile".
     * @param pool the associated constant pool.
     */
    public SourceFile(ByteStream stream, Constant name, ConstantPool pool) {
        super(name, stream.readInt());
        sourceIndex = pool.get(stream.readShort());
    }

    /**
     * Public no-args constructor for extending classes. Should not be used directly.
     */
    public SourceFile(){}

    /**
     * {@inheritDoc}
     */
    public byte[] getBytes() {
        byte[] bytes = Bytes.toByteArray((short) sourceIndex.getIndex());
        length = bytes.length;
        return Bytes.prepend(bytes, super.getBytes());
    }

    /**
     * Fetches the name of the file.
     * @return the name.
     */
    public String getSourceFile() {
        return sourceIndex.getStringValue();
    }

    /**
     * Sets the name of the file.
     * @param newSource the new name.
     */
    public void setSourceFile(String newSource) {
        sourceIndex.getOwner().set(sourceIndex.getIndex(), (sourceIndex = new Constant(sourceIndex.getIndex(), TAG_UTF_STRING, newSource.getBytes())));
    }
}
