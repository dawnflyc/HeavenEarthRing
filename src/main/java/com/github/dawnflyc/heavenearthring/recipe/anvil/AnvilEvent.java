package com.github.dawnflyc.heavenearthring.recipe.anvil;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = HeavenEarthRing.MOD_ID)
public class AnvilEvent {

    private static final Logger LOGGER = LogManager.getLogger();

    public static Set<AnvilUpdateRecipe> anvilUpdateRecipes=new HashSet<>();

    public static void AddAnvilRecipe(AnvilUpdateRecipe anvilUpdateRecipe){
        anvilUpdateRecipes.add(anvilUpdateRecipe);
    }

    @SubscribeEvent
    public static void AnvilUpdateEvent(AnvilUpdateEvent event){
        AnvilIO.AnvilOutput out = null;
        for (AnvilUpdateRecipe anvilUpdateRecipe : anvilUpdateRecipes) {
            AnvilIO.AnvilOutput output= anvilUpdateRecipe.anvilUpdate(new AnvilIO.AnvilInput(event.getLeft(),event.getRight(),event.getName()));
            if (output!=null){
                out=output;
                break;
            }
        }
        if (out!=null && out.getOutput()!=null){
            event.setOutput(out.getOutput());
            event.setCost(out.getCost());
            event.setMaterialCost(out.getMaterialCost());
            event.setResult(Event.Result.ALLOW);
        }

    }


}