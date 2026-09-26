package org.fourmur.wathe_extended_rus_improved.client.render.entity;

import org.fourmur.wathe_extended_rus_improved.WatheExtendedRusImproved;
import org.fourmur.wathe_extended_rus_improved.client.entity.PlayerBerserkModeEntity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public final class PlayerBerserkModeModel extends DefaultedEntityGeoModel<PlayerBerserkModeEntity> {
    public static final Identifier MODEL = WatheExtendedRusImproved.id("berserk");

	public PlayerBerserkModeModel() {
		super(MODEL);
	}
}
