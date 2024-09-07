package minecraft.example.minecraft.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerMovementMixin {

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void onMove(Vec3d movement, CallbackInfo info) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (isInRestrictedArea(player.getPos())) {
            info.cancel();
        }
    }
    private boolean isInRestrictedArea(Vec3d pos) {
        return pos.x > 100 && pos.x < 200 && pos.z > 100 && pos.z < 200;
    }
}