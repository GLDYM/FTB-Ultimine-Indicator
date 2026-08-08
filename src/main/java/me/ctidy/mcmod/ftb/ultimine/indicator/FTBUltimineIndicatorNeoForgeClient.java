/*
 * Copyright (c) 2025, Tidy-Bear.
 *
 * This file is part of "FTB Ultimine Indicator".
 *
 * "FTB Ultimine Indicator" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "FTB Ultimine Indicator" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "FTB Ultimine Indicator".  If not, see <https://www.gnu.org/licenses/>.
 */

package me.ctidy.mcmod.ftb.ultimine.indicator;

import me.ctidy.mcmod.ftb.ultimine.indicator.client.ClientHandler;
import me.ctidy.mcmod.ftb.ultimine.indicator.config.FTBUltimineIndicatorClientConfig;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = FTBUltimineIndicatorNeoForge.MOD_ID, dist = Dist.CLIENT)
public final class FTBUltimineIndicatorNeoForgeClient {

    public FTBUltimineIndicatorNeoForgeClient(IEventBus modEventBus) {
        FTBUltimineIndicatorClientConfig.init();

        modEventBus.addListener(this::onReloadListenerRegister);
        NeoForge.EVENT_BUS.addListener(this::onHudRender);
    }

    private void onReloadListenerRegister(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(ClientHandler::reloadResources);
    }

    private void onHudRender(RenderGuiLayerEvent.Post event) {
        if (!VanillaGuiLayers.CROSSHAIR.equals(event.getName())) {
            return;
        }
        ClientHandler.renderHud(event.getGuiGraphics(), Minecraft.getInstance().getWindow(), event.getPartialTick().getGameTimeDeltaPartialTick(false));
    }

}
