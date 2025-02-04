package mcjty.lostcities.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketReturnProfileToClient {

    private final ResourceKey<Level> dimension;
    private final String profile;

    public PacketReturnProfileToClient(FriendlyByteBuf buf) {
        dimension = ResourceKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
        profile = buf.readUtf(32767);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceLocation(dimension.location());
        buf.writeUtf(profile);
    }

    public PacketReturnProfileToClient(ResourceKey<Level> dimension, String profileName) {
        this.dimension = dimension;
        this.profile = profileName;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // @todo 1.14
//            WorldTypeTools.setProfileFromServer(dimension, profile);
        });
        ctx.get().setPacketHandled(true);
    }
}
