package testingmod.items

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import testingmod.Testingmod

class ModItemGroups {

    public fun init() {
        Testingmod.logger.info("Registering items for {}", Testingmod.modid)
    }

    private val TESTINGMOD_ITEMS = Registry.register(Registries.ITEM_GROUP,
        Identifier.of(Testingmod.modid, "testingmod_items"),
        FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.testingmod.testingmod_items"))
            .icon { -> ItemStack(ModItems().HEALER) }
            .entries { context, entries ->
                entries.add(ModItems().HEALER)
                entries.add(ModItems().KILLER)
            }
            .build())
}