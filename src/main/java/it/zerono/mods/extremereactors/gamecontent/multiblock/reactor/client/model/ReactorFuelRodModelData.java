/*
 *
 * ReactorFuelRodModelData.java
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

package it.zerono.mods.extremereactors.gamecontent.multiblock.reactor.client.model;

import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
import it.unimi.dsi.fastutil.shorts.Short2ObjectMap;
import it.zerono.mods.extremereactors.gamecontent.multiblock.reactor.client.ClientFuelRodsLayout;
import it.zerono.mods.zerocore.lib.client.model.data.AbstractModelDataMap;
import it.zerono.mods.zerocore.lib.client.model.data.NamedModelProperty;
import net.minecraftforge.client.model.data.ModelProperty;

import java.util.Objects;

public class ReactorFuelRodModelData
    extends AbstractModelDataMap {

    public static final ModelProperty<Short> REACTANTS_MODEL_KEY = new NamedModelProperty<>("REACTANTS_MODEL_KEY", Objects::nonNull);
    public static final ModelProperty<Boolean> VERTICAL = new NamedModelProperty<>("VERTICAL", Objects::nonNull);
    public static final ModelProperty<Byte> FUEL_LEVEL = new NamedModelProperty<>("FUEL_LEVEL", Objects::nonNull);
    public static final ModelProperty<Byte> WASTE_LEVEL = new NamedModelProperty<>("WASTE_LEVEL", Objects::nonNull);

    public static short createKey(final byte fuelLevel, final byte wasteLevel, final boolean isVertical) {
        return (short)((isVertical ? 0x8000 : 0) | (fuelLevel << 4) | (wasteLevel & 0xf));
    }

    public static ReactorFuelRodModelData from(final ClientFuelRodsLayout.FuelData fuelData) {
        return s_cache.computeIfAbsent(fuelData.getModelKey(), k -> new ReactorFuelRodModelData(fuelData));
    }

    public short getModelKey() {

        final Short v = this.getData(REACTANTS_MODEL_KEY);

        //noinspection AutoUnboxing
        return null != v ? v : (short)0;
    }

    public boolean isVertical() {

        final Boolean v = this.getData(VERTICAL);

        //noinspection AutoUnboxing
        return null != v && v;
    }

    public byte getFuelLevel() {

        final Byte v = this.getData(FUEL_LEVEL);

        //noinspection AutoUnboxing
        return null != v ? v : (byte)0;
    }

    public byte getWasteLevel() {

        final Byte v = this.getData(WASTE_LEVEL);

        //noinspection AutoUnboxing
        return null != v ? v : (byte)0;
    }

    //region internals

    @SuppressWarnings("AutoBoxing")
    private ReactorFuelRodModelData(final ClientFuelRodsLayout.FuelData fuelData) {

        super(4);
        this.addProperty(REACTANTS_MODEL_KEY, (short)(fuelData.getModelKey() & 0xFF));
        this.addProperty(VERTICAL, fuelData.getModelKey() < 0);
        this.addProperty(FUEL_LEVEL, fuelData.getFuelLevel());
        this.addProperty(WASTE_LEVEL, fuelData.getWasteLevel());
    }

    private static final Short2ObjectMap<ReactorFuelRodModelData> s_cache = new Short2ObjectArrayMap<>(512);

    //endregion
}
