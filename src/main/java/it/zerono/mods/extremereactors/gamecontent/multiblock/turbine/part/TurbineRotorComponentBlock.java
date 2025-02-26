/*
 *
 * TurbineRotorComponentBlock.java
 *
 * This file is part of Extreme Reactors 2 by ZeroNoRyouki, a Minecraft mod.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * DO NOT REMOVE OR EDIT THIS HEADER
 *
 */

package it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.part;

import it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.ITurbinePartType;
import it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.MultiblockTurbine;
import it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.rotor.RotorBladeState;
import it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.rotor.RotorComponentType;
import it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.rotor.RotorShaftState;
import it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.variant.IMultiblockTurbineVariant;
import it.zerono.mods.zerocore.base.multiblock.part.GenericDeviceBlock;
import it.zerono.mods.zerocore.base.multiblock.part.INeverCauseRenderingSkip;
import it.zerono.mods.zerocore.lib.CodeHelper;
import it.zerono.mods.zerocore.lib.block.INeighborChangeListener;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.List;

public abstract class TurbineRotorComponentBlock
        extends GenericDeviceBlock<MultiblockTurbine, ITurbinePartType>
        implements INeighborChangeListener.Notifier, INeverCauseRenderingSkip {

    public static final EnumProperty<RotorShaftState> ROTOR_SHAFT_STATE = EnumProperty.create("state", RotorShaftState.class);
    public static final EnumProperty<RotorBladeState> ROTOR_BLADE_STATE = EnumProperty.create("state", RotorBladeState.class);

    public static TurbineRotorComponentBlock shaft(final MultiblockPartProperties<ITurbinePartType> properties) {
        return new TurbineRotorComponentBlock(properties) {

            @Override
            public boolean isShaft() {
                return true;
            }

            @Override
            public boolean isBlade() {
                return false;
            }

            @Override
            public RotorComponentType getComponentType() {
                return RotorComponentType.Shaft;
            }

            @Override
            protected void buildBlockState(final StateDefinition.Builder<Block, BlockState> builder) {

                super.buildBlockState(builder);
                builder.add(ROTOR_SHAFT_STATE);
            }

            @Override
            protected BlockState buildDefaultState(BlockState state) {
                return super.buildDefaultState(state).setValue(ROTOR_SHAFT_STATE, RotorShaftState.getDefault());
            }
        };
    }

    public static TurbineRotorComponentBlock blade(final MultiblockPartProperties<ITurbinePartType> properties) {
        return new TurbineRotorComponentBlock(properties) {

            @Override
            public boolean isShaft() {
                return false;
            }

            @Override
            public boolean isBlade() {
                return true;
            }

            @Override
            public RotorComponentType getComponentType() {
                return RotorComponentType.Blade;
            }

            @Override
            protected void buildBlockState(final StateDefinition.Builder<Block, BlockState> builder) {

                super.buildBlockState(builder);
                builder.add(ROTOR_BLADE_STATE);
            }

            @Override
            protected BlockState buildDefaultState(BlockState state) {
                return super.buildDefaultState(state).setValue(ROTOR_BLADE_STATE, RotorBladeState.getDefault());
            }
        };
    }

    public abstract boolean isShaft();

    public abstract boolean isBlade();

    public abstract RotorComponentType getComponentType();

    //region Block

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return this == adjacentBlockState.getBlock();
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {

        super.appendHoverText(stack, context, tooltip, flag);

        if (this.isBlade()) {

            if (null == this._bladeTooltipInfo) {

                this._bladeTooltipInfo = this.getMultiblockVariant()
                        .filter(v -> v instanceof IMultiblockTurbineVariant)
                        .map(v -> (IMultiblockTurbineVariant) v)
                        .map(variant -> (Component) Component.translatable("gui.bigreactors.turbine.controller.rotorstatus.tooltip.value2",
                                String.format(ChatFormatting.DARK_AQUA + "" + ChatFormatting.BOLD + "%d", variant.getBaseFluidPerBlade()))
                                .setStyle(Style.EMPTY
                                        .withColor(ChatFormatting.GRAY)
                                        .withItalic(true))
                        )
                        .orElse(CodeHelper.TEXT_EMPTY_LINE);
            }

            tooltip.add(this._bladeTooltipInfo);
        }
    }

    //endregion
    //region internals

    protected TurbineRotorComponentBlock(final MultiblockPartProperties<ITurbinePartType> properties) {
        super(properties);
    }

    private Component _bladeTooltipInfo;

    //endregion
}
