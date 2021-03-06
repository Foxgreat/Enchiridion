package joshie.enchiridion.gui.book.features.recipe;

import joshie.enchiridion.api.recipe.IItemStack;
import joshie.enchiridion.helpers.ItemListHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WrappedStack implements IItemStack {
    protected static final Random RAND = new Random();
    protected NonNullList<ItemStack> permutations = NonNullList.create();
    protected boolean hasPermutations = false;
    @Nonnull
    protected ItemStack stack;
    private int ticker = 0;
    private double x;
    private double y;
    private float scale;

    public WrappedStack(Object object, double x, double y, float scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;

        if (object == null) stack = ItemStack.EMPTY;
        else {
            if (object instanceof String) {
                object = OreDictionary.getOres((String) object);
            }
            if (object instanceof Ingredient) {
                if (object == Ingredient.EMPTY) {
                    permutations.add(ItemStack.EMPTY);
                } else {
                    Collections.addAll(permutations, ((Ingredient) object).getMatchingStacks());
                }
            } else if (object instanceof ItemStack) {
                stack = ((ItemStack) object).copy();
                if (stack.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
                    permutations.addAll(ItemListHelper.items().stream().filter(aStack -> aStack.getItem() == stack.getItem()).collect(Collectors.toList()));
                } else permutations.add(stack);
            } else if (object instanceof List) {
                Object first = ((List) object).get(0);
                if (first instanceof Ingredient) {
                    List<Ingredient> ingredients = new ArrayList<Ingredient>((List) object);
                    for (Ingredient stacky : ingredients) {
                        Collections.addAll(permutations, stacky.getMatchingStacks());
                    }
                } else if (first instanceof ItemStack) {
                    List<ItemStack> stacks = new ArrayList<ItemStack>((List) object);
                    for (ItemStack stacky : stacks) {
                        if (stacky.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
                            permutations.addAll(ItemListHelper.items().stream().filter(aStack -> aStack.getItem() == stacky.getItem()).collect(Collectors.toList()));
                        } else permutations.add(stacky);
                    }
                }
            }

            hasPermutations = permutations.size() > 1;
            stack = permutations.get(RAND.nextInt(permutations.size()));
        }
    }

    @Override
    @Nonnull
    public ItemStack getItemStack() {
        return stack;
    }

    @Override
    public void onDisplayTick() {
        if (hasPermutations) {
            ticker--;

            if (ticker <= 0) {
                ticker = 100;
                stack = permutations.get(RAND.nextInt(permutations.size()));
            }
        }
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public float getScale() {
        return scale;
    }
}