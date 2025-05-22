package testingmod.items

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import testingmod.Testingmod
import testingmod.items.custom.HealerItem
import testingmod.items.custom.KillerItem

class ModItems {
    val HEALER = registerItem("healer", HealerItem(Item.Settings()
        .maxDamage(40)
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Testingmod.modid, "healer")))
    ))
    val KILLER = registerItem("killer", KillerItem(Item.Settings()
        .maxDamage(40)
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Testingmod.modid, "killer")))
    ))

    private fun registerItem(name: String, item: Item): Item {
         return Registry.register(Registries.ITEM, Identifier.of(Testingmod.modid, name), item)
    }
    public fun init() {
        Testingmod.logger.info("Registering items for {}", Testingmod.modid)
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register { entries ->
            entries.add(KILLER)
            entries.add(HEALER)
        }
    }
}