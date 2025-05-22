package testingmod.items.custom

import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand

class KillerItem(settings: Settings) : Item(settings) {
    override fun useOnEntity(stack: ItemStack?, user: PlayerEntity?, entity: LivingEntity?, hand: Hand?): ActionResult? {
        val entityHealth = entity!!.health
        if (!user!!.world.isClient) {
            stack!!.damage(entityHealth.toInt(), user.world as ServerWorld, user as ServerPlayerEntity) { item ->
                user.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND)
            }
            entity.kill(user.world as ServerWorld)
            user.world.playSound(null, user.x, user.y, user.z, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS)
            return ActionResult.SUCCESS_SERVER
        }
        return ActionResult.PASS
    }
}