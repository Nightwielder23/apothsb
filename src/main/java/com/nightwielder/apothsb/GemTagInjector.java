package com.nightwielder.apothsb;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class GemTagInjector {
    private static final ResourceLocation GEM_ID = new ResourceLocation("apotheosis", "gem");
    private static final TagKey<Item> GEM_TAG = ItemTags.create(GEM_ID);

    @SubscribeEvent
    public void onTagsUpdated(TagsUpdatedEvent event) {
        if (!Registry.ITEM.containsKey(GEM_ID)) {
            return;
        }
        Item gem = Registry.ITEM.get(GEM_ID);

        Holder<Item> rawHolder = Registry.ITEM.getResourceKey(gem)
                .flatMap(Registry.ITEM::getHolder)
                .orElse(null);
        if (!(rawHolder instanceof Holder.Reference)) {
            return;
        }
        @SuppressWarnings("unchecked")
        Holder.Reference<Item> holder = (Holder.Reference<Item>) rawHolder;
        if (holder.is(GEM_TAG)) {
            return;
        }

        injectTag(holder, GEM_TAG);
    }

    private static void injectTag(Holder.Reference<Item> holder, TagKey<Item> tag) {
        Field tagsField = findTagsField();
        if (tagsField == null) {
            return;
        }
        try {
            tagsField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Set<TagKey<Item>> current = (Set<TagKey<Item>>) tagsField.get(holder);
            Set<TagKey<Item>> updated = new HashSet<>(current);
            if (updated.add(tag)) {
                tagsField.set(holder, Set.copyOf(updated));
            }
        } catch (ReflectiveOperationException ignored) {
        }
    }

    private static Field findTagsField() {
        for (Field f : Holder.Reference.class.getDeclaredFields()) {
            if (Set.class.isAssignableFrom(f.getType())) {
                return f;
            }
        }
        return null;
    }
}
