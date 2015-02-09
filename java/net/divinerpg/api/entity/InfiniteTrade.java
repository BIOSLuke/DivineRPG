package net.divinerpg.api.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;

public class InfiniteTrade extends MerchantRecipe {

	public InfiniteTrade(ItemStack buy, ItemStack sell) {
		super(buy, sell);
	}
	
	@Override
	public boolean isRecipeDisabled() {
		return false;
	}

}
