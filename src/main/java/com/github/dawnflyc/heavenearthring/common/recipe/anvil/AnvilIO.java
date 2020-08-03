package com.github.dawnflyc.heavenearthring.common.recipe.anvil;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class AnvilIO {

    /**
     * 铁砧输入
     */
    public static class AnvilInput {
        /**
         * 左边物品
         */
        private ItemStack left;
        /**
         * 右边物品
         */
        private ItemStack right;
        /**
         * 名称
         */
        private String name;

        /**
         * 铁砧输出，不参与合成则返回null
         *
         * @param left
         * @param right
         * @param name
         */
        public AnvilInput(ItemStack left, ItemStack right, String name) {
            this.left = left;
            this.right = right;
            this.name = name;
        }

        public ItemStack getLeft() {
            return left;
        }

        public void setLeft(ItemStack left) {
            this.left = left;
        }

        public ItemStack getRight() {
            return right;
        }

        public void setRight(ItemStack right) {
            this.right = right;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 铁砧输出
     */
    public static class AnvilOutput {
        /**
         * 输出物品
         */
        @Nonnull
        private ItemStack output;
        /**
         * 花费经验
         * 附魔一次，nbt从1开始，nbt花费的二次方+1递增
         * 实际花费 nbt花费+4
         */
        private int cost;
        /**
         * 材料物品，右边物品消耗
         */
        @Nonnull
        private int materialCost;

        public AnvilOutput(ItemStack output, int cost, int materialCost) {
            this.output = output;
            this.cost = cost;
            this.materialCost = materialCost;
        }

        public AnvilOutput(@Nonnull ItemStack output, int materialCost) {
            this.output = output;
            int nbt = output.getTag().getInt("RepairCost");
            this.cost = nbt + 4;
            output.getTag().putInt("RepairCost", nbt * 2 + 1);
            this.materialCost = materialCost;
        }

        public ItemStack getOutput() {
            return output;
        }

        public void setOutput(ItemStack output) {
            this.output = output;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getMaterialCost() {
            return materialCost;
        }

        public void setMaterialCost(int materialCost) {
            this.materialCost = materialCost;
        }

    }
}
