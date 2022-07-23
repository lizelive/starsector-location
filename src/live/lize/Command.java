package live.lize;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.intel.deciv.DecivTracker;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.MarketCMD;
import org.jetbrains.annotations.NotNull;
import org.lazywizard.console.BaseCommand;
import org.lazywizard.console.Console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

enum Industry {
    PopulationAndInfrastructure,
    Farming,
    Mining,
    Refining,
    LightIndustry,
    TechMining,
    HeavyIndustry,
    OrbitalWorks,
    FuelProduction,
    Commerce,
    MilitaryBase,
    HighCommand
}

class ProductionPotential  implements  Cloneable{
    @Override
    public String toString() {
        return "ProductionPotential{" +
                "rare_ore=" + rare_ore +
                ", ore=" + ore +
                ", organics=" + organics +
                ", food=" + food +
                ", volatiles=" + volatiles +
                ", metal=" + metal +
                ", transplutonics=" + transplutonics +
                ", fuel=" + fuel +
                ", supplies=" + supplies +
                ", heavy_machinery=" + heavy_machinery +
                ", heavy_armaments=" + heavy_armaments +
                ", ship_hulls=" + ship_hulls +
                ", domestic_goods=" + domestic_goods +
                ", luxury_goods=" + luxury_goods +
                ", recreational_drugs=" + recreational_drugs +
                ", harvested_organs=" + harvested_organs +
                '}';
    }

    private static final int MAX_COLONY_SIZE = 6;
    private static final ProductionPotential ZERO = new ProductionPotential();

    public int rare_ore;
    public int ore;
    public int organics;
    public int food;
    public int volatiles;

    public int metal;
    public int transplutonics;
    public int fuel;
    public int supplies;
    public int heavy_machinery;
    public int heavy_armaments;
    public int ship_hulls;
    public int domestic_goods;
    public int luxury_goods;
    public int recreational_drugs;
    public int harvested_organs;


    // zero produciton
    public ProductionPotential() {

    }

    public static ProductionPotential max(ProductionPotential a, ProductionPotential b) {
        ProductionPotential out = new ProductionPotential();
        out.rare_ore = Math.max(a.rare_ore, b.rare_ore);
        out.ore = Math.max(a.ore, b.ore);
        out.organics = Math.max(a.organics, b.organics);
        out.food = Math.max(a.food, b.food);
        out.volatiles = Math.max(a.volatiles, b.volatiles);
        out.metal = Math.max(a.metal, b.metal);
        out.transplutonics = Math.max(a.transplutonics, b.transplutonics);
        out.fuel = Math.max(a.fuel, b.fuel);
        out.supplies = Math.max(a.supplies, b.supplies);
        out.heavy_machinery = Math.max(a.heavy_machinery, b.heavy_machinery);
        out.heavy_armaments = Math.max(a.heavy_armaments, b.heavy_armaments);
        out.ship_hulls = Math.max(a.ship_hulls, b.ship_hulls);
        out.domestic_goods = Math.max(a.domestic_goods, b.domestic_goods);
        out.luxury_goods = Math.max(a.luxury_goods, b.luxury_goods);
        out.recreational_drugs = Math.max(a.recreational_drugs, b.recreational_drugs);
        out.harvested_organs = Math.max(a.harvested_organs, b.harvested_organs);
        return out;
    }

    public void subtract(ProductionPotential other) {
        rare_ore = rare_ore - other.rare_ore;
        ore = ore - other.ore;
        organics = organics - other.organics;
        food = food - other.food;
        volatiles = volatiles - other.volatiles;
        metal = metal - other.metal;
        transplutonics = transplutonics - other.transplutonics;
        fuel = fuel - other.fuel;
        supplies = supplies - other.supplies;
        heavy_machinery = heavy_machinery - other.heavy_machinery;
        heavy_armaments = heavy_armaments - other.heavy_armaments;
        ship_hulls = ship_hulls - other.ship_hulls;
        domestic_goods = domestic_goods - other.domestic_goods;
        luxury_goods = luxury_goods - other.luxury_goods;
        recreational_drugs = recreational_drugs - other.recreational_drugs;
        harvested_organs = harvested_organs - other.harvested_organs;
    }

    public void min(ProductionPotential other) {
        rare_ore = Math.min(rare_ore, other.rare_ore);
        ore = Math.min(ore, other.ore);
        organics = Math.min(organics, other.organics);
        food = Math.min(food, other.food);
        volatiles = Math.min(volatiles, other.volatiles);
        metal = Math.min(metal, other.metal);
        transplutonics = Math.min(transplutonics, other.transplutonics);
        fuel = Math.min(fuel, other.fuel);
        supplies = Math.min(supplies, other.supplies);
        heavy_machinery = Math.min(heavy_machinery, other.heavy_machinery);
        heavy_armaments = Math.min(heavy_armaments, other.heavy_armaments);
        ship_hulls = Math.min(ship_hulls, other.ship_hulls);
        domestic_goods = Math.min(domestic_goods, other.domestic_goods);
        luxury_goods = Math.min(luxury_goods, other.luxury_goods);
        recreational_drugs = Math.min(recreational_drugs, other.recreational_drugs);
        harvested_organs = Math.min(harvested_organs, other.harvested_organs);
    }

    public int total() {
        return rare_ore +
                ore +
                organics +
                food +
                volatiles +
                metal +
                transplutonics +
                fuel +
                supplies +
                heavy_machinery +
                heavy_armaments +
                ship_hulls +
                domestic_goods +
                luxury_goods +
                recreational_drugs +
                harvested_organs;
    }

    public void max(ProductionPotential other) {
        rare_ore = Math.max(rare_ore, other.rare_ore);
        ore = Math.max(ore, other.ore);
        organics = Math.max(organics, other.organics);
        food = Math.max(food, other.food);
        volatiles = Math.max(volatiles, other.volatiles);
        metal = Math.max(metal, other.metal);
        transplutonics = Math.max(transplutonics, other.transplutonics);
        fuel = Math.max(fuel, other.fuel);
        supplies = Math.max(supplies, other.supplies);
        heavy_machinery = Math.max(heavy_machinery, other.heavy_machinery);
        heavy_armaments = Math.max(heavy_armaments, other.heavy_armaments);
        ship_hulls = Math.max(ship_hulls, other.ship_hulls);
        domestic_goods = Math.max(domestic_goods, other.domestic_goods);
        luxury_goods = Math.max(luxury_goods, other.luxury_goods);
        recreational_drugs = Math.max(recreational_drugs, other.recreational_drugs);
        harvested_organs = Math.max(harvested_organs, other.harvested_organs);
    }

    public void clip() {
        max(ZERO);
    }

    // get demand
    public static ProductionPotential demand(Industry industry, boolean use_ai) {
        int size = MAX_COLONY_SIZE;
        int modifier = use_ai ? -1 : 0;
        int base = size + modifier;
        ProductionPotential demand = new ProductionPotential();


        // access modifiers
        /*

        nearby = 0.4?
        lowgravity = 0.1
        alpha = 0.2?
        freeport=0.25
        size = 0.15?
        megaport = 0.8
        megaport_improvement = 0.2
        megaport_alpha = 0.2
        waystation = 0.1 + 0.2
        waystation_alpha = 0.2
        FULLERENE = 0.3

         */

        // NOT_A_GAS_GIANT, NOT_EXTREME_WEATHER, NOT_EXTREME_TECTONIC_ACTIVITY

        if (industry == null) {
            // fuel refining
            demand.volatiles = base;


            // heavy industry
            demand.metal = base;
            demand.transplutonics = base - 2;

            // refining
            demand.rare_ore = base;
            demand.ore = base + 2;
            demand.heavy_machinery = base - 2;


            // heavy batteries
            demand.heavy_armaments = base - 2;

            // Military Base
            demand.supplies = base + 2;
            demand.ship_hulls = base + 2;
            demand.fuel = base + 2;

            // mining
            demand.recreational_drugs = base;

            // population
            demand.harvested_organs = base - 3;
            demand.food = base - 1;
            demand.luxury_goods = base - 3;
            demand.domestic_goods = base - 1;

            // light industry
            demand.organics = base;


            // Orbital Fusion Lamp
            demand.volatiles = Math.max(10 + modifier, demand.volatiles);
            // Hypershunt Tap
            demand.transplutonics = Math.max(10 + modifier, demand.transplutonics);


            demand.clip();
        }

        if (industry == Industry.PopulationAndInfrastructure) {
            // Population & Infrastructure
            demand.food = base - 1;
            demand.domestic_goods = base - 1;
            demand.luxury_goods = base - 3;
            demand.recreational_drugs = base - 2;
            demand.harvested_organs = base - 3;
            demand.supplies = 2;
            demand.organics = base - 1;
        }


        demand.clip();
        return demand;
        // Mining


    }

    public boolean at_least(ProductionPotential other) {
        return rare_ore >= other.rare_ore
                && ore >= other.ore
                && organics >= other.organics
                && food >= other.food
                && volatiles >= other.volatiles
                && metal >= other.metal
                && transplutonics >= other.transplutonics
                && fuel >= other.fuel
                && supplies >= other.supplies
                && heavy_machinery >= other.heavy_machinery
                && heavy_armaments >= other.heavy_armaments
                && ship_hulls >= other.ship_hulls
                && domestic_goods >= other.domestic_goods
                && luxury_goods >= other.luxury_goods
                && recreational_drugs >= other.recreational_drugs
                && harvested_organs >= other.harvested_organs;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public ProductionPotential(PlanetInfo planet) {
        int size = MAX_COLONY_SIZE;

        // using max ai cores and improvement
        // jk just admin mod
        int admin_modifier = 1;

        int base = size + admin_modifier;

        if (planet.rare_ore > 0) {
            rare_ore = base + (planet.rare_ore - 1) - 2;
            // Autonomous Mantle Bore
            if (!planet.habitable)
                rare_ore += 3;
        }

        if (planet.ore > 0) {
            ore = base + (planet.ore - 1);
            // Autonomous Mantle Bore
            if (!planet.habitable)
                ore += 3;
        }

        if (planet.organics > 0) {
            organics = base + (planet.organics - 1);

            if (!planet.habitable)
                organics += 3;
        }

        if (planet.volatiles > 0) {
            volatiles = base + (planet.volatiles - 1) - 2;
            // Plasma Dynamo
            if (planet.gas_giant)
                volatiles += 3;
        }

        if (planet.food > 0) {
            food = base + (planet.food - 1);

            // Soil Nanites
            if (planet.rare_ore == 0 && planet.volatiles == 0)
                food += 2;
        }

        // civ
        harvested_organs = base - 5;

        // refinery
        transplutonics = base - 2;
        metal = base;

        // fuel production
        fuel = base - 2;

        // Catalytic Core
        if (planet.atmosphere == 0) {
            transplutonics += 3;
            metal += 3;
        }

        // Synchrotron Core
        if (planet.atmosphere == 0) {
            fuel += 3;
        }

        // orbital works
        heavy_machinery = base - 2 ;
        supplies = base - 2;
        heavy_armaments = base - 2;
        ship_hulls = base - 2;

        // todo nanoforge should not be installed in habitable but it can be
        if (!planet.habitable) {
            heavy_machinery += 3;
            supplies += 3;
            heavy_armaments += 3;
            ship_hulls += 3;
        }

        // light industry
        recreational_drugs = base - 2;
        luxury_goods = base - 2;
        domestic_goods = base;

        if (planet.habitable) {
            luxury_goods += 2;
            domestic_goods += 2;
            recreational_drugs += 2;
        }

        if(planet.cryosanctum){
            harvested_organs = base + 3;
        }


        clip();
    }
}

class PlanetInfo {

    public float hazard;
    public float accessibility;

    // 0 is normal +2 is very hot
    public int heat;

    // 0 is none, 1
    public int rare_ore;
    public int ore;
    public int organics;
    public int food;
    public int volatiles;

    public int ruins;

    boolean solar_array;

    boolean cryosanctum;

    int atmosphere;
    boolean habitable;

    boolean gas_giant;
    String name;

    @Override
    public String toString() {
        return "PlanetInfo{" +
                "hazard=" + hazard +
                ", accessibility=" + accessibility +
                ", heat=" + heat +
                ", rare_ore=" + rare_ore +
                ", ore=" + ore +
                ", organics=" + organics +
                ", food=" + food +
                ", volatiles=" + volatiles +
                ", solar_array=" + solar_array +
                ", cryosanctum=" + cryosanctum +
                ", atmosphere=" + atmosphere +
                ", habitable=" + habitable +
                ", gas_giant=" + gas_giant +
                ", name='" + name + '\'' +
                '}';
    }

    public PlanetInfo(PlanetAPI planet) {
        name = planet.getName();
        solar_array = planet.hasCondition(Conditions.SOLAR_ARRAY);

        atmosphere = 2;
        if (planet.hasCondition(Conditions.NO_ATMOSPHERE))
            atmosphere = 0;

        if (planet.hasCondition(Conditions.THIN_ATMOSPHERE))
            atmosphere = 1;

        if (planet.hasCondition(Conditions.DENSE_ATMOSPHERE))
            atmosphere = 3;

        habitable = planet.hasCondition(Conditions.HABITABLE);
        gas_giant = planet.isGasGiant();
        MarketAPI market = planet.getMarket();
            if(market != null ) {
            hazard = market.getHazardValue();
            accessibility = market.getAccessibilityMod().flatBonus;
            cryosanctum = market.hasIndustry(Industries.CRYOSANCTUM);
        }
        if (planet.hasCondition(Conditions.FARMLAND_POOR)) {
            food = 1;
        }
        if (planet.hasCondition(Conditions.FARMLAND_ADEQUATE)) {
            food = 2;
        }
        if (planet.hasCondition(Conditions.FARMLAND_RICH)) {
            food = 3;
        }
        if (planet.hasCondition(Conditions.FARMLAND_BOUNTIFUL)) {
            food = 4;
        }


        if (planet.hasCondition(Conditions.RUINS_SCATTERED)){
            ruins = 1;
        }
        if (planet.hasCondition(Conditions.RUINS_WIDESPREAD)){
            ruins = 2;
        }
        if (planet.hasCondition(Conditions.RUINS_EXTENSIVE)){
            ruins = 3;
        }
        if (planet.hasCondition(Conditions.RUINS_VAST)){
            ruins = 4;
        }

        if (planet.hasCondition(Conditions.VERY_COLD)) {
            heat = -2;
        }
        if (planet.hasCondition(Conditions.COLD)) {
            heat = -1;
        }
        if (planet.hasCondition(Conditions.HOT)) {
            heat = 1;
        }
        if (planet.hasCondition(Conditions.VERY_HOT)) {
            heat = 2;
        }

        if (planet.hasCondition(Conditions.ORGANICS_TRACE)) {
            organics = 1;
        }
        if (planet.hasCondition(Conditions.ORGANICS_COMMON)) {
            organics = 2;
        }
        if (planet.hasCondition(Conditions.ORGANICS_ABUNDANT)) {
            organics = 3;
        }
        if (planet.hasCondition(Conditions.ORGANICS_PLENTIFUL)) {
            organics = 4;
        }


        if (planet.hasCondition(Conditions.VOLATILES_TRACE)) {
            volatiles = 1;
        }
        if (planet.hasCondition(Conditions.VOLATILES_DIFFUSE)) {
            volatiles = 2;
        }
        if (planet.hasCondition(Conditions.VOLATILES_ABUNDANT)) {
            volatiles = 3;
        }
        if (planet.hasCondition(Conditions.VOLATILES_PLENTIFUL)) {
            volatiles = 4;
        }

        if (planet.hasCondition(Conditions.ORE_SPARSE)) {
            ore = 1;
        }
        if (planet.hasCondition(Conditions.ORE_MODERATE)) {
            ore = 2;
        }
        if (planet.hasCondition(Conditions.ORE_ABUNDANT)) {
            ore = 3;
        }
        if (planet.hasCondition(Conditions.ORE_RICH)) {
            ore = 4;
        }
        if (planet.hasCondition(Conditions.ORE_ULTRARICH)) {
            ore = 5;
        }


        if (planet.hasCondition(Conditions.RARE_ORE_SPARSE)) {
            rare_ore = 1;
        }
        if (planet.hasCondition(Conditions.RARE_ORE_MODERATE)) {
            rare_ore = 2;
        }
        if (planet.hasCondition(Conditions.RARE_ORE_ABUNDANT)) {
            rare_ore = 3;
        }
        if (planet.hasCondition(Conditions.RARE_ORE_RICH)) {
            rare_ore = 4;
        }
        if (planet.hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
            rare_ore = 5;
        }
    }

}

class PlanetReport {
    public PlanetInfo info;
    public ProductionPotential production;

    public PlanetReport(PlanetInfo info, ProductionPotential production) {
        this.info = info;
        this.production = production;
    }

    @Override
    public String toString() {
        return "PlanetReport{" +
                "info=" + info +
                ", production=" + production +
                '}';
    }
}

class SystemReport {
    public SystemReport(ProductionPotential production_potential, boolean has_gate, boolean has_comm) {
        this.production_potential = production_potential;
        this.has_gate = has_gate;
        this.has_comm = has_comm;
    }

    public String name;
    public ProductionPotential production_potential;
    public  boolean has_gate;
    public  boolean has_comm;
    public ArrayList<PlanetReport> planets;

    @Override
    public String toString() {
        return "SystemReport{" +
                "name='" + name + '\'' +
                ", production_potential=" + production_potential +
                ", has_gate=" + has_gate +
                ", has_comm=" + has_comm +
                ", planets=" + planets +
                '}';
    }
}
public class Command implements BaseCommand {
    @Override
    public CommandResult runCommand(@NotNull String s, @NotNull BaseCommand.CommandContext commandContext) {
        boolean use_ai = false;
        Console.showMessage("uwu dats " + s);
        boolean nuke = s.contains("nuke");
        SectorAPI sector = Global.getSector();


        if (nuke){
            Console.showMessage("oh noes time for war crime");

            for (MarketAPI market : Global.getSector().getEconomy().getMarketsCopy()){


                FactionAPI faction =  market.getFaction();
                if (faction != null && ! (faction.isNeutralFaction() || faction.isPlayerFaction())) {
                    try {
                        //Console.showMessage("die "+ market.getName());
                        DecivTracker.decivilize(market, true);
                        // MarketCMD.addBombardVisual(market.getPrimaryEntity());
                        sector.getCampaignUI().getCurrentInteractionDialog().dismiss();
                    } catch (Exception e) {

                    }
                }
            }
        }

        HashMap<String, Integer> allConditions = new HashMap<String, Integer>();

        ProductionPotential maxNeeded = ProductionPotential.demand(null, use_ai);

        Console.showIndentedMessage("max needed", maxNeeded, 4);
        ArrayList<PlanetReport> planetReports = new ArrayList<>();
        float best = Float.NEGATIVE_INFINITY;
        best = 0;

        for (StarSystemAPI starSystem :
                sector.getStarSystems()) {

            // only systems with gates
            // if(starSystem.getEntitiesWithTag(Tags.GATE).isEmpty()) continue;


            List<PlanetAPI> planets = starSystem.getPlanets();
            String systemName = starSystem.getName();

            /*
            for (SectorEntityToken entity: starSystem.getAllEntities()
                 ) {
                CargoAPI cargo = entity.getCargo();
                if(cargo != null && !cargo.isEmpty()) {
                    Console.showMessage(entity.getId());
                    Console.showMessage(cargo);
                    for (CargoStackAPI stack:
                         cargo.getStacksCopy()) {
                        Console.showMessage(stack.getCommodityId() + "\t" + stack.getSize());
                    }
                }
            }
            */

            ProductionPotential systemPotential = new ProductionPotential();

            float score = 0;

            // Console.showMessage(starSystem.getName() + " has " + planets.size() + " planets");
            for (PlanetAPI planet : planets
            ) {
                if(planet.isStar() || planet.isNormalStar()) continue;

                MarketAPI market =  planet.getMarket();

                FactionAPI faction =  planet.getFaction();
                if (faction != null && ! (faction.isNeutralFaction() || faction.isPlayerFaction())){

                    if (nuke) {
                    }
                    else{
                        score -= 1e6;
                    }

                    // sharing is not caring

                    continue;
                }

                PlanetInfo info = new PlanetInfo(planet);

                ProductionPotential potential = new ProductionPotential(info);
                systemPotential.max(potential);

                PlanetReport report = new PlanetReport(info, potential);
                planetReports.add(report);

                /*
                MarketAPI market =  planet.getMarket();
                if(market == null){
                    // idk not this tho.
                    continue;
                }
                String planetFullName = planet.getFullName();
                Console.showMessage("PlanetFullName=" + planetFullName);
                Console.showMessage("Cargo=" + planet.getCargo().getSpaceUsed());
                Console.showMessage("Hazard=" + market.getHazardValue());
                Console.showMessage("Accessibility=" + market.getAccessibilityMod().getFlatBonus());
                try {
                    for (MarketConditionAPI condition :
                            market.getConditions()
                    ) {
                        String conditionId = condition.getId();
                        if (condition.isPlanetary()) {
                            Console.showMessage("Condition=" + conditionId);
                            Integer count = 0;
                            if (allConditions.containsKey(conditionId)) {
                                count = allConditions.get(conditionId);
                            }
                            allConditions.put(conditionId, count + 1);
                        }
                    }
                }catch (ArrayIndexOutOfBoundsException e) {
                    // aparently the hiddden world fails this
                }

*/
            }
            boolean system_has_gate = !starSystem.getEntitiesWithTag(Tags.GATE).isEmpty();
            boolean system_has_comm_relay = !starSystem.getEntitiesWithTag(Tags.COMM_RELAY).isEmpty();

            SystemReport report = new SystemReport(systemPotential, system_has_gate, system_has_comm_relay);
            report.planets = planetReports;
            report.name = systemName;

            boolean good_enough = systemPotential.at_least(maxNeeded);


            try {
                //(ProductionPotential)maxNeeded.clone();
                ProductionPotential shortages = (ProductionPotential)maxNeeded.clone();
                shortages.subtract(systemPotential);
                shortages.clip();
                score -= 16 * shortages.total();

            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }




            for (PlanetReport planetReport: planetReports
                 ) {
                // add points for planets
                score +=  Math.pow(2,planetReport.info.ruins)*(1 + planetReport.info.accessibility)/planetReport.info.hazard;
            }
            score += systemPotential.total();

            if(system_has_comm_relay)
                score += 100;

            if(system_has_gate)
                score *= 2;

            score /= starSystem.getLocation().lengthSquared();

            if(best < score) {
                best = score;
                Console.showMessage(systemName +"\t"+good_enough+ "\tscore: "+ score);
                //Console.showIndentedMessage(systemName + "\tscore: "+ score, report, 4);
            }

        }
        /*
        Console.showMessage("all conditions: ");
        for (Map.Entry<String, Integer> kv: allConditions.entrySet()
        ) {
            Console.showMessage(kv.getKey() +"\t"+kv.getValue());
        }*/
        return CommandResult.SUCCESS;
    }
}
