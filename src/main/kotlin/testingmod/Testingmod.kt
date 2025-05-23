package testingmod

import net.fabricmc.api.ModInitializer
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import testingmod.items.ModItemGroups
import testingmod.items.ModItems
import testingmod.items.custom.HealerItem

object Testingmod : ModInitializer {
    public val logger = LoggerFactory.getLogger("testingmod")
	public val modid = "testingmod"

	override fun onInitialize() {
		ModItems.init()
		ModItemGroups.init()
	}
}
