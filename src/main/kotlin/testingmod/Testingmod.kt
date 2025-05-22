package testingmod

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory
import testingmod.items.ModItemGroups
import testingmod.items.ModItems

object Testingmod : ModInitializer {
    public val logger = LoggerFactory.getLogger("testingmod")
	public val modid = "testingmod"

	override fun onInitialize() {
		ModItems().init()
		// ModItemGroups().init()
	}
}