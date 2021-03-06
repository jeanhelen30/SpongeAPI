/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.data.properties;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.Property;

import java.util.Set;

import javax.annotation.Nullable;

public class DisplacementProperty extends AbstractProperty<String, Set<BlockType>> {

    /**
     * Creates a {@link HarvestingProperty} with a specific set of {@link BlockType}s.
     *
     * @param value The harvestable block types
     */
    public DisplacementProperty(@Nullable Set<BlockType> value) {
        super(value == null ? ImmutableSet.<BlockType>of() : ImmutableSet.copyOf(value));
    }

    /**
     * Creates a {@link HarvestingProperty} with a specific set of {@link BlockType}s.
     *
     * @param value The harvestable block types
     * @param op The operator to use when comparing against other properties
     */
    public DisplacementProperty(@Nullable Set<BlockType> value, Operator op) {
        super(value == null ? ImmutableSet.<BlockType>of() : ImmutableSet.copyOf(value), op);
    }

    @Override
    public int compareTo(Property<?, ?> o) {
        if (o instanceof DisplacementProperty) {
            DisplacementProperty property = (DisplacementProperty) o;
            Set<BlockType> set = Sets.newHashSet(property.getValue());
            for (BlockType displaceable : this.getValue()) {
                if (set.contains(displaceable)) {
                    set.remove(displaceable);
                } else {
                    return 1;
                }
            }
            return set.size() > 0 ? -set.size() : 0;
        }
        return this.getClass().getName().compareTo(o.getClass().getName());
    }
}
