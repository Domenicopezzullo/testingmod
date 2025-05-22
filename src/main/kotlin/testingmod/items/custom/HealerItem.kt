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
import net.minecraft.world.World

class HealerItem(settings: Settings) : Item(settings) {
    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): ActionResult? {
        if (user!!.health == user.maxHealth) return ActionResult.PASS
        if (user.world.isClient) return ActionResult.PASS
        val stack = user.getStackInHand(hand)
        val missingHealth = user.maxHealth - user.health
        val remainingDurability = stack.maxDamage - stack.damage
        val healAmount = minOf(missingHealth, remainingDurability.toFloat())
        user.world.playSound(null, user.x, user.y, user.z, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS)
        user.heal(healAmount)
        stack.damage(healAmount.toInt(), user.world as ServerWorld, user as ServerPlayerEntity) { item ->
            user.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND)
        }
        return ActionResult.SUCCESS_SERVER
    }

    override fun useOnEntity(stack: ItemStack?, user: PlayerEntity?, entity: LivingEntity?, hand: Hand?): ActionResult? {
        if (entity!!.health == entity.maxHealth) return ActionResult.PASS
        if (user!!.world.isClient) return ActionResult.PASS
        var missingHealth = entity.maxHealth - entity.health
        if (stack!!.damage < entity.maxHealth) missingHealth = stack.damage.toFloat()
        entity.heal(missingHealth)
        user.world.playSound(null, user.x, user.y, user.z, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS)
        stack.damage(missingHealth.toInt(), user.world as ServerWorld, user as ServerPlayerEntity) { item ->
            user.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND)
        }
        return ActionResult.SUCCESS_SERVER
    }
}