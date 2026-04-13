package com.nightwielder.apothsb;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class GemTagInjector {
    private static final String APOTHEOSIS = "apotheosis";
    private static final String SOPHISTICATED_BACKPACKS = "sophisticatedbackpacks";
    private static final ResourceLocation GEM_ID = new ResourceLocation(APOTHEOSIS, "gem");
    private static final TagKey<Item> GEM_TAG = ItemTags.create(GEM_ID);

    @SubscribeEvent
    public void onTagsUpdated(TagsUpdatedEvent event) {
        ModList mods = ModList.get();
        if (mods == null || !mods.isLoaded(APOTHEOSIS) || !mods.isLoaded(SOPHISTICATED_BACKPACKS)) {
            return;
        }

        if (!BuiltInRegistries.ITEM.containsKey(GEM_ID)) {
            return;
        }
        Item gem = BuiltInRegistries.ITEM.get(GEM_ID);
        if (gem == null) {
            return;
        }

        Holder.Reference<Item> holder = BuiltInRegistries.ITEM.getResourceKey(gem)
                .flatMap(BuiltInRegistries.ITEM::getHolder)
                .orElse(null);
        if (holder == null || holder.is(GEM_TAG)) {
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
