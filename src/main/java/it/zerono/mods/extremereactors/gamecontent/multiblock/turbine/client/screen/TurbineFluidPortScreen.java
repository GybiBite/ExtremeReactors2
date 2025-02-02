/*
 *
 * TurbineFluidPortScreen.java
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

package it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.client.screen;

import it.zerono.mods.extremereactors.CommonLocations;
import it.zerono.mods.extremereactors.gamecontent.multiblock.common.client.screen.AbstractFluidPortScreen;
import it.zerono.mods.extremereactors.gamecontent.multiblock.common.container.FluidPortContainer;
import it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.MultiblockTurbine;
import it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.part.TurbineFluidPortEntity;
import it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.variant.IMultiblockTurbineVariant;
import it.zerono.mods.zerocore.base.client.screen.control.MachineStatusIndicator;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class TurbineFluidPortScreen
        extends AbstractFluidPortScreen<MultiblockTurbine, IMultiblockTurbineVariant, TurbineFluidPortEntity> {

    public TurbineFluidPortScreen(FluidPortContainer<MultiblockTurbine, IMultiblockTurbineVariant, TurbineFluidPortEntity> container,
                                  Inventory inventory, Component title) {
        super(container, inventory, title, CommonLocations.TURBINE.buildWithSuffix("part-forgefluidport"));
    }

    //region AbstractMultiblockScreen

    @Override
    protected MachineStatusIndicator createStatusIndicator(FluidPortContainer<MultiblockTurbine, IMultiblockTurbineVariant, TurbineFluidPortEntity> container) {
        return this.createTurbineStatusIndicator(container.ACTIVE);
    }

    //endregion
}
