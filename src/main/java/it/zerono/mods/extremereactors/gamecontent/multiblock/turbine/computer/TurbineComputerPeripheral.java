/*
 *
 * TurbineComputerPeripheral.java
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

package it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.computer;

import it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.MultiblockTurbine;
import it.zerono.mods.extremereactors.gamecontent.multiblock.turbine.part.TurbineComputerPortEntity;
import it.zerono.mods.zerocore.lib.compat.computer.ComputerMethod;
import it.zerono.mods.zerocore.lib.compat.computer.MultiblockComputerPeripheral;
import net.minecraftforge.common.util.NonNullConsumer;

public class TurbineComputerPeripheral
        extends MultiblockComputerPeripheral<MultiblockTurbine, TurbineComputerPortEntity> {

    public TurbineComputerPeripheral(final TurbineComputerPortEntity computerPort) {
        super(computerPort);
    }

    //region MultiblockComputerPeripheral

    /**
     * Get the name of this ComputerPeripheral
     *
     * @return the name
     */
    @Override
    public String getPeripheralStaticName() {
        return "extremereactor-turbineComputerPort";
    }

    /**
     * Collect the methods provided by this ComputerPeripheral
     *
     * @param methodConsumer pass your methods to this Consumer
     */
    @Override
    public void populateMethods(final NonNullConsumer<ComputerMethod<MultiblockComputerPeripheral<MultiblockTurbine, TurbineComputerPortEntity>>> methodConsumer) {

        super.populateMethods(methodConsumer);

        methodConsumer.accept(new ComputerMethod<>("getVariant", wrapControllerValue(c -> c.getVariant().getName()))); //NEW
//
//        methodConsumer.accept(new ComputerMethod<>("getEnergyStored", wrapControllerValue(c -> c.getEnergyStored(c.getOutputEnergySystem(), null))));
//
//        methodConsumer.accept(new ComputerMethod<>("getEnergyStoredAsText", wrapControllerValue(c -> { //NEW
//
//            final EnergySystem sys = c.getOutputEnergySystem();
//
//            return CodeHelper.formatAsHumanReadableNumber(c.getEnergyStored(sys, null), sys.getUnit());
//        })));
//
//        methodConsumer.accept(new ComputerMethod<>("getNumberOfControlRods", wrapControllerValue(IReactorReader::getFuelRodsCount)));
//
//        methodConsumer.accept(new ComputerMethod<>("getActive", wrapControllerValue(IMachineReader::isMachineActive)));
//
//        methodConsumer.accept(new ComputerMethod<>("getFuelTemperature", wrapControllerValue(c -> c.getFuelHeatValue().get())));
//
//        methodConsumer.accept(new ComputerMethod<>("getCasingTemperature", wrapControllerValue(c -> c.getReactorHeatValue().get())));
//
//        methodConsumer.accept(new ComputerMethod<>("getFuelAmount", wrapControllerValue(IReactorReader::getFuelAmount)));
//
//        methodConsumer.accept(new ComputerMethod<>("getWasteAmount", wrapControllerValue(IReactorReader::getWasteAmount)));
//
//        methodConsumer.accept(new ComputerMethod<>("getFuelAmountMax", wrapControllerValue(IReactorReader::getCapacity)));
//
//        methodConsumer.accept(new ComputerMethod<>("getControlRodName", wrapControllerValue(controlRodByIndex(ReactorControlRodEntity::getName)), 1));
//
//        methodConsumer.accept(new ComputerMethod<>("getControlRodLevel", wrapControllerValue(controlRodByIndex(ReactorControlRodEntity::getInsertionRatio)), 1));
//
//        methodConsumer.accept(new ComputerMethod<>("getEnergyProducedLastTick", wrapControllerValue(c ->
//                c.getOperationalMode().isPassive() ? c.getUiStats().getAmountGeneratedLastTick() : 0.0)));
//
//        methodConsumer.accept(new ComputerMethod<>("getHotFluidProducedLastTick", wrapControllerValue(c ->
//                c.getOperationalMode().isActive() ? c.getUiStats().getAmountGeneratedLastTick() : 0.0)));
//
//        methodConsumer.accept(new ComputerMethod<>("isActivelyCooled", wrapControllerValue(c -> c.getOperationalMode().isActive())));
//
//        methodConsumer.accept(new ComputerMethod<>("getCoolantAmount", wrapControllerValue(c -> 0)));
////        methodConsumer.accept(new ComputerMethod<>("getCoolantAmount", ReactorComputerPeripheral::getCoolantAmount));
//
//        methodConsumer.accept(new ComputerMethod<>("getCoolantAmountMax", wrapControllerValue(c -> 0)));
////        methodConsumer.accept(new ComputerMethod<>("getCoolantAmountMax", ReactorComputerPeripheral::getCoolantAmountMax));
//
//        methodConsumer.accept(new ComputerMethod<>("getCoolantType", wrapControllerValue(c -> null)));
////        methodConsumer.accept(new ComputerMethod<>("getCoolantType", ReactorComputerPeripheral::getCoolantType));
//
//        methodConsumer.accept(new ComputerMethod<>("getHotFluidAmount", wrapControllerValue(c -> 0)));
////        methodConsumer.accept(new ComputerMethod<>("getHotFluidAmount", ReactorComputerPeripheral::getHotFluidAmount));
//
//        methodConsumer.accept(new ComputerMethod<>("getHotFluidAmountMax", wrapControllerValue(c -> 0)));
////        methodConsumer.accept(new ComputerMethod<>("getHotFluidAmountMax", ReactorComputerPeripheral::getHotFluidAmountMax));
//
//        methodConsumer.accept(new ComputerMethod<>("getHotFluidType", wrapControllerValue(c -> null)));
////        methodConsumer.accept(new ComputerMethod<>("getHotFluidType", ReactorComputerPeripheral::getHotFluidType));
//
//        methodConsumer.accept(new ComputerMethod<>("getFuelReactivity", wrapControllerValue(c -> c.getFuelFertility() * 100.0f)));
//
//        methodConsumer.accept(new ComputerMethod<>("getFuelConsumedLastTick", wrapControllerValue(c -> c.getUiStats().getFuelConsumedLastTick())));
//
//        methodConsumer.accept(new ComputerMethod<>("getControlRodLocation", wrapControllerValue((c, arguments) ->
//                CodeHelper.optionalMap(c.getMinimumCoord(),
//                        c.getControlRodByIndex(LuaHelper.getIntFromArgs(arguments, 0)).map(ReactorControlRodEntity::getWorldPosition),
//                        (minCoords, rodCoords) -> rodCoords.subtract(minCoords)
//                ).orElse(null)), 1));
//
//        methodConsumer.accept(new ComputerMethod<>("getEnergyCapacity", wrapControllerValue(c -> c.getCapacity(c.getOutputEnergySystem(), null))));
//
//        methodConsumer.accept(new ComputerMethod<>("getControlRodsLevels", wrapControllerValue(c -> {
//
//            final int controlRodsCount = c.getControlRodsCount();
//            final Map<Integer, Integer> levels = Maps.newHashMapWithExpectedSize(controlRodsCount);
//
//            for (int idx = 0; idx < controlRodsCount; ++idx) {
//                levels.put(idx, c.getControlRodByIndex(idx).map(rod -> (int)rod.getInsertionRatio()).orElse(-1));
//            }
//
//            //return new Object[] { levels };
//            return levels;
//        })));
//
//        methodConsumer.accept(new ComputerMethod<>("setControlRodsLevels", wrapControllerAction((c, arguments) -> {
//
//            if (!(arguments[0] instanceof Map)) {
//                return;
//            }
//
//            @SuppressWarnings("rawtypes")
//            final Map levels = (Map)arguments[0];
//            final int controlRodsCount = c.getControlRodsCount();
//
//            if (controlRodsCount != levels.size()) {
//                throw new IllegalArgumentException("Invalid levels count in a call to setControlRodsLevels()");
//            }
//
//            final int[] newLevels = new int[controlRodsCount];
//
//            for (int idx = 0; idx < newLevels.length; ++idx) {
//
//                double value;
//
//                if (levels.containsKey((double)idx)) {
//                    value = (double)levels.get((double)idx);
//                } else if (levels.containsKey(idx)) {
//                    value = (double)levels.get(idx);
//                } else {
//                    throw new IllegalArgumentException("Invalid table key in a call to setControlRodsLevels()");
//                }
//
//                newLevels[idx] = (int)Math.round(value);
//
//                if (newLevels[idx] < 0 || newLevels[idx] > 100) {
//                    LuaHelper.raiseIllegalArgumentRange(idx, 0, 100);
//                }
//            }
//
//            for (int idx = 0; idx < newLevels.length; ++idx) {
//
//                final int newLevel = newLevels[idx];
//
//                c.getControlRodByIndex(idx).ifPresent(rod -> ReactorControlRodEntity.setInsertionRatio(rod, newLevel));
//            }
//
//        }), 1, true));
//
//        methodConsumer.accept(new ComputerMethod<>("getEnergyStats", wrapControllerValue(c -> {
//
//            final Map<String, Object> stats = Maps.newHashMap();
//            final EnergySystem sys = c.getOutputEnergySystem();
//
//            stats.put("energyStored", c.getEnergyStored(sys, null));
//            stats.put("energyCapacity", c.getCapacity(sys, null));
//            stats.put("energyProducedLastTick", c.getUiStats().getAmountGeneratedLastTick());
//            stats.put("energySystem", sys.getUnit()); //NEW
//
//            return stats;
//
//        })));
//
//        methodConsumer.accept(new ComputerMethod<>("getFuelStats", wrapControllerValue(c -> {
//
//            final Map<String, Object> stats = Maps.newHashMap();
//
//            stats.put("fuelAmount", c.getFuelAmount());
//            stats.put("fuelCapacity", c.getCapacity());
//            stats.put("fuelTemperature", c.getFuelHeatValue().get());
//            stats.put("fuelConsumedLastTick", c.getUiStats().getFuelConsumedLastTick());
//            stats.put("fuelReactivity", c.getFuelFertility() * 100.0f);
//            stats.put("wasteAmount", c.getWasteAmount());
//
//            return stats;
//
//        })));
//
//        methodConsumer.accept(new ComputerMethod<>("getHotFluidStats", wrapControllerValue(c -> {
//
//            final Map<String, Object> stats = Maps.newHashMap();
//            final IFluidContainer container = c.getFluidContainer();
//
//            //TODO fix
////            final Fluid fluidType = c.getCoolantContainer().getVaporType();
////
////            stats.put("fluidType", null == fluidType ? null : fluidType.getName());
//            stats.put("fluidType", ""/*container.getGas().map(fluid -> )*/);
//            stats.put("fluidAmount", container.getGasAmount());
//            stats.put("fluidCapacity", container.getCapacity());
//            stats.put("fluidProducedLastTick", c.getOperationalMode().isPassive() ? 0.0f : c.getUiStats().getAmountGeneratedLastTick());
//
//            return stats;
//
//        })));
//
//        methodConsumer.accept(new ComputerMethod<>("getCoolantFluidStats", wrapControllerValue(c -> {
//
//            final Map<String, Object> stats = Maps.newHashMap();
//            final IFluidContainer container = c.getFluidContainer();
//
//            //TODO fix
////            final Fluid fluidType = c.getCoolantContainer().getVaporType();
////
////            stats.put("fluidType", null == fluidType ? null : fluidType.getName());
//            stats.put("fluidType", ""/*container.getLiquid().map(fluid -> )*/);
//            stats.put("fluidAmount", container.getLiquidAmount());
//            stats.put("fluidCapacity", container.getCapacity());
//
//            return stats;
//
//        })));
//
//        methodConsumer.accept(new ComputerMethod<>("setActive", wrapControllerAction((c, arguments) ->
//                c.setMachineActive(LuaHelper.getBooleanFromArgs(arguments, 0))), 1, true));
//
//        methodConsumer.accept(new ComputerMethod<>("setControlRodLevel", wrapControllerAction((c, arguments) ->
//                c.getControlRodByIndex(LuaHelper.getIntFromArgs(arguments, 0))
//                        .ifPresent(rod -> ReactorControlRodEntity.setInsertionRatio(rod, LuaHelper.getIntFromArgs(arguments, 1, 0, 100)))), 2, true));
//
//        methodConsumer.accept(new ComputerMethod<>("setAllControlRodLevels", wrapControllerAction((c, arguments) ->
//                c.setControlRodsInsertionRatio(LuaHelper.getIntFromArgs(arguments, 0, 0, 100))), 1, true));
//
//        methodConsumer.accept(new ComputerMethod<>("setControlRodName", wrapControllerAction((c, arguments) ->
//                c.getControlRodByIndex(LuaHelper.getIntFromArgs(arguments, 0))
//                        .ifPresent(rod -> ReactorControlRodEntity.setName(rod, LuaHelper.getStringFromArgs(arguments, 1)))), 2, true));
//
//        methodConsumer.accept(new ComputerMethod<>("doEjectWaste", wrapControllerAction((c, arguments) -> c.ejectWaste()), 0, true));
//
//        methodConsumer.accept(new ComputerMethod<>("doEjectFuel", wrapControllerAction((c, arguments) -> c.ejectFuel()), 0, true));
    }

    //endregion
    //region method wrappers and helpers

//    private static BiFunction<MultiblockReactor, Object[], Object> controlRodByIndex(final BiConsumer<ReactorControlRodEntity, Object[]> code) {
//        return (reactor, arguments) -> {
//            reactor.getControlRodByIndex(LuaHelper.getIntFromArgs(arguments, 0)).ifPresent(rod -> code.accept(rod, arguments));
//            return null;
//        };
//    }


    //endregion
    //region Methods

//    public static Object[] getCoolantAmount(@Nonnull final ReactorComputerPeripheral peripheral, @Nonnull final Object[] arguments) throws Exception {
//        return new Object[] { getReactorControllerOrFail(peripheral).getCoolantContainer().getCoolantAmount() };
//    }
//
//    public static Object[] getCoolantAmountMax(@Nonnull final ReactorComputerPeripheral peripheral, @Nonnull final Object[] arguments) throws Exception {
//        return new Object[] { getReactorControllerOrFail(peripheral).getCoolantContainer().getCapacity() };
//    }
//
//    public static Object[] getCoolantType(@Nonnull final ReactorComputerPeripheral peripheral, @Nonnull final Object[] arguments) throws Exception {
//
//        final Fluid fluidType = getReactorControllerOrFail(peripheral).getCoolantContainer().getCoolantType();
//
//        return new Object[] { null == fluidType ? null : fluidType.getName() };
//    }
//
//    public static Object[] getHotFluidAmount(@Nonnull final ReactorComputerPeripheral peripheral, @Nonnull final Object[] arguments) throws Exception {
//        return new Object[] { getReactorControllerOrFail(peripheral).getCoolantContainer().getVaporAmount() };
//    }
//
//    public static Object[] getHotFluidAmountMax(@Nonnull final ReactorComputerPeripheral peripheral, @Nonnull final Object[] arguments) throws Exception {
//        return new Object[] { getReactorControllerOrFail(peripheral).getCoolantContainer().getCapacity() };
//    }
//
//    public static Object[] getHotFluidType(@Nonnull final ReactorComputerPeripheral peripheral, @Nonnull final Object[] arguments) throws Exception {
//
//        final Fluid fluidType = getReactorControllerOrFail(peripheral).getCoolantContainer().getVaporType();
//
//        return new Object[] { null == fluidType ? null : fluidType.getName() };
//    }
//

    //endregion
}

