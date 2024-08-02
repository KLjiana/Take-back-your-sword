package com.kljiana.tbys.mixin;

import com.kljiana.tbys.TBYS;
import net.minecraft.world.item.BowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BowItem.class)
public class BowCancel {
    @ModifyConstant(method = "releaseUsing", constant = @Constant(doubleValue = 0.1D, ordinal = 0))
    public double cancelBow(double constant){
        return 1.0D;
    }
}
