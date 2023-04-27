package nmd.autoplant.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin
{
    @Shadow public int age;
    @Inject(at = @At("HEAD"), method = "tick", cancellable = false)
    public void injected(CallbackInfo cir) {
        ItemStack stack = ((ItemEntity)(Object)this).getItem();

        if (!stack.isEmpty() && stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof BushBlock bushBlock) {
            Level level = ((ItemEntity)(Object)this).getLevel();
            BlockPos pos = ((ItemEntity)(Object)this).blockPosition();
            BlockState state = bushBlock.defaultBlockState();

            if(this.age >= 3000 && level.getBlockState(pos).getMaterial().isReplaceable() && bushBlock.canSurvive(state, level, pos)) {
                level.setBlock(pos, state, 2);
                ((ItemEntity)(Object)this).discard();
            }
        }
    }
}

