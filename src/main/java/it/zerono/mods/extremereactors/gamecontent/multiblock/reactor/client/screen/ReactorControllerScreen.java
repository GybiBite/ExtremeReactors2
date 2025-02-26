/*
 *
 * ReactorControllerScreen.java
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

package it.zerono.mods.extremereactors.gamecontent.multiblock.reactor.client.screen;

import it.zerono.mods.extremereactors.CommonLocations;
import it.zerono.mods.extremereactors.gamecontent.CommonConstants;
import it.zerono.mods.extremereactors.gamecontent.compat.patchouli.PatchouliCompat;
import it.zerono.mods.extremereactors.gamecontent.multiblock.common.client.screen.CommonMultiblockScreen;
import it.zerono.mods.extremereactors.gamecontent.multiblock.reactor.MultiblockReactor;
import it.zerono.mods.extremereactors.gamecontent.multiblock.reactor.client.screen.control.ReactorControllerPanel;
import it.zerono.mods.extremereactors.gamecontent.multiblock.reactor.container.ReactorControllerContainer;
import it.zerono.mods.extremereactors.gamecontent.multiblock.reactor.part.ReactorControllerEntity;
import it.zerono.mods.extremereactors.gamecontent.multiblock.reactor.variant.ReactorVariant;
import it.zerono.mods.zerocore.base.client.screen.ClientBaseHelper;
import it.zerono.mods.zerocore.base.client.screen.control.MachineStatusIndicator;
import it.zerono.mods.zerocore.lib.item.inventory.PlayerInventoryUsage;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ReactorControllerScreen
        extends CommonMultiblockScreen<MultiblockReactor, ReactorControllerEntity, ReactorControllerContainer> {

    public ReactorControllerScreen(final ReactorControllerContainer container,
                                   final Inventory inventory, final Component title) {

        super(container, inventory, PlayerInventoryUsage.None, title,
                mainTextureFromVariant(container.getTileEntity().getMultiblockVariant().orElse(ReactorVariant.Basic)));

        this.addPatchouliHelpButton(PatchouliCompat.HANDBOOK_ID, CommonLocations.REACTOR.buildWithSuffix("part-controller"), 1);
        this.addRecipesButton(this::displayReactorRecipes, "gui.bigreactors.show_recipes.tooltip.title");

        this.setContentBounds(14, 0);

        this._controllerPanel = new ReactorControllerPanel(this, this.getContentWidth(), this.getContentHeight(), container,
                this::onActiveStateChanged, this::onWasteEjectionChanged, this::onVoidReactants, this::onScram,
                ClientBaseHelper::setButtonSpritesAndOverlayForState);
    }

    //region CommonMultiblockScreen

    @Override
    protected MachineStatusIndicator createStatusIndicator(ReactorControllerContainer container) {
        return this.createReactorStatusIndicator(container.active());
    }

    @Override
    protected void onScreenCreate() {

        super.onScreenCreate();
        this.addControl(this._controllerPanel);
    }

    //region internals

    private void onActiveStateChanged(boolean active) {
        if (!this.isDataUpdateInProgress()) {
            this.sendCommandToServer(active ? CommonConstants.COMMAND_ACTIVATE : CommonConstants.COMMAND_DEACTIVATE);
        }
    }

    private void onWasteEjectionChanged(boolean active) {
        this.sendCommandToServer(active ? ReactorControllerEntity.COMMAND_WASTE_AUTOMATIC :
                ReactorControllerEntity.COMMAND_WASTE_MANUAL);
    }

    private void onScram() {
        this.sendCommandToServer(ReactorControllerEntity.COMMAND_SCRAM);
    }

    private void onVoidReactants() {
        this.sendCommandToServer(ReactorControllerEntity.COMMAND_VOID_REACTANTS);
    }

    private final ReactorControllerPanel _controllerPanel;

    //endregion
}
