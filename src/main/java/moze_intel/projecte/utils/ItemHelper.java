package moze_intel.projecte.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Helpers for Inventories, ItemStacks, Items, and the Ore Dictionary
 * Notice: Please try to keep methods tidy and alphabetically ordered. Thanks!
 */
public final class ItemHelper
{
	/**
	 * @return True if the only aspect these stacks differ by is stack size, false if item, meta, or nbt differ.
	 */
	public static boolean areItemStacksEqual(ItemStack stack1, ItemStack stack2)
	{
		return ItemStack.areItemStacksEqual(getNormalizedStack(stack1), getNormalizedStack(stack2));
	}

	public static void compactInventory(IItemHandlerModifiable inventory)
	{
		List<ItemStack> temp = new ArrayList<>();
		for (int i = 0; i < inventory.getSlots(); i++)
		{
			if (!inventory.getStackInSlot(i).isEmpty())
			{
				temp.add(inventory.getStackInSlot(i));
				inventory.setStackInSlot(i, ItemStack.EMPTY);
			}
		}

		for (ItemStack s : temp)
		{
			ItemHandlerHelper.insertItemStacked(inventory, s, false);
		}
	}

	/**
	 * Compacts and sorts list of items, without regard for stack sizes
	 */
	public static void compactItemListNoStacksize(List<ItemStack> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			ItemStack s = list.get(i);
			if (!s.isEmpty())
			{
				for (int j = i + 1; j < list.size(); j++)
				{
					ItemStack s1 = list.get(j);
					if (ItemHandlerHelper.canItemStacksStack(s, s1))
					{
						s.grow(s1.getCount());
						list.set(j, ItemStack.EMPTY);
					}
				}
			}
		}

		list.removeIf(ItemStack::isEmpty);
		list.sort(Comparators.ITEMSTACK_ASCENDING);
	}

	public static boolean containsItemStack(List<ItemStack> list, ItemStack toSearch)
	{
		for (ItemStack stack : list) {
			if (stack.isEmpty()) {
				continue;
			}

			if (stack.getItem() == toSearch.getItem()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns an ItemStack with stacksize 1.
	 */
	public static ItemStack getNormalizedStack(ItemStack stack)
	{
		ItemStack result = stack.copy();
		result.setCount(1);
		return result;
	}

	public static String getOreDictionaryName(ItemStack stack)
	{
		if (stack.isEmpty())
		{
			return "Unknown";
		}
		int[] oreIds = OreDictionary.getOreIDs(stack);

		if (oreIds.length == 0)
		{
			return "Unknown";
		}

		return OreDictionary.getOreName(oreIds[0]);
	}

	public static boolean hasSpace(NonNullList<ItemStack> inv, ItemStack stack)
	{
		for (ItemStack invStack : inv)
		{
			if (invStack.isEmpty())
			{
				return true;
			}

			if (areItemStacksEqual(stack, invStack) && invStack.getCount() < invStack.getMaxStackSize())
			{
				return true;
			}
		}

		return false;
	}

	public static IItemHandlerModifiable immutableCopy(IItemHandler toCopy)
	{
		final List<ItemStack> list = new ArrayList<>(toCopy.getSlots());
		for (int i = 0; i < toCopy.getSlots(); i++)
		{
			list.add(toCopy.getStackInSlot(i));
		}

		return new IItemHandlerModifiable()
		{
			@Override
			public void setStackInSlot(int slot, @Nonnull ItemStack stack) {}

			@Override
			public int getSlots() {
				return list.size();
			}

			@Nonnull
			@Override
			public ItemStack getStackInSlot(int slot) {
				return list.get(slot);
			}

			@Nonnull
			@Override
			public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
				return stack;
			}

			@Nonnull
			@Override
			public ItemStack extractItem(int slot, int amount, boolean simulate) {
				return ItemStack.EMPTY;
			}

			@Override
			public int getSlotLimit(int slot)
			{
				return getStackInSlot(slot).getMaxStackSize();
			}
		};
	}

	public static boolean isDamageable(ItemStack stack)
	{
		return stack.isDamageable();
	}

	public static boolean isOre(IBlockState state)
	{
		if (Item.getItemFromBlock(state.getBlock()) == Items.AIR)
		{
			return false;
		}
		String oreDictName = getOreDictionaryName(new ItemStack(state.getBlock(), 1));
		return oreDictName.startsWith("ore") || oreDictName.startsWith("denseore");
	}

	public static IBlockState stackToState(ItemStack stack)
	{
		if (stack.getItem() instanceof ItemBlock)
		{
			return ((ItemBlock) stack.getItem()).getBlock().getDefaultState();
		}
		else
		{
			return null;
		}
	}
}
