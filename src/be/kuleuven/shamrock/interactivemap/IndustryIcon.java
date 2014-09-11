package be.kuleuven.shamrock.interactivemap;

/*
* REFERRED ICONS ORIGINALLY DESIGNED/PUBLISHED BY Nicolas Mollet
 * UNDER CC 3.0 BY-SA License
* http://mapicons.nicolasmollet.com/
* */
public class IndustryIcon extends Icon {
    public enum Color {
        CERULEAN_BLUE("cerulean");

        private final String text;
        private Color(final String text) { this.text = text;}
        @Override
        public String toString() {return text;}
    }

    public enum Type {
        AIRCRAFTCARRIER("aircraftcarrier.png"),
        AIR_FIXWING("air_fixwing.png"),
        ARMY("army.png"),
        BATTLESHIP_3("battleship-3.png"),
        BOATCRANE("boatcrane.png"),
        BOMBER_2("bomber-2.png"),
        BREWERY1("brewery1.png"),
        BUNKER_2_2("bunker-2-2.png"),
        BUNKER("bunker.png"),
        CHANNELCHANGE("channelchange.png"),
        CHEMISTRY_2("chemistry-2.png"),
        CONSTRUCTION("construction.png"),
        CONSTRUCTIONCRANE("constructioncrane.png"),
        DAM("dam.png"),
        DATABASE("database.png"),
        DEEPSEAFISHING("deepseafishing.png"),
        EXPERT("expert.png"),
        FACTORY("factory.png"),
        FISHING("fishing.png"),
        FISHINGBOAT("fishingboat.png"),
        FOODCAN("foodcan.png"),
        FOUNDRY_2("foundry-2.png"),
        FREQCHG("freqchg.png"),
        GAS_CYLINDER1("gas_cylinder1.png"),
        GEOTHERMAL_SITE("geothermal-site.png"),
        GLAZER("glazer.png"),
        HIRETOOLS("hiretools.png"),
        IOBRIDGE("iobridge.png"),
        JEEP("jeep.png"),
        JETFIGHTER("jetfighter.png"),
        KITCHEN("kitchen.png"),
        LABORATORY("laboratory.png"),
        LANDFILL("landfill.png"),
        LIGHTHOUSE_2("lighthouse-2.png"),
        LINEDOWN("linedown.png"),
        LOCK("lock.png"),
        MASTCRANE1("mastcrane1.png"),
        METRONETWORK("metronetwork.png"),
        MINE("mine.png"),
        MISSILE_2("missile-2.png"),
        MOBILEPHONETOWER("mobilephonetower.png"),
        MUSEUM_INDUSTRY("museum_industry.png"),
        NO_NUKE_EXPORT("no-nuke-export.png"),
        OBSERVATORY("observatory.png"),
        OILPUMPJACK("oilpumpjack.png"),
        OILRIG2("oilrig2.png"),
        OUTLET2("outlet2.png"),
        PLASTERING_ICON("plastering-icon.png"),
        POND("pond.png"),
        POWERLINEPOLE("powerlinepole.png"),
        POWEROUTAGE("poweroutage.png"),
        POWERPLANT("powerplant.png"),
        POWERSUBSTATION("powersubstation.png"),
        RADAR("radar.png"),
        RADIO_STATION_2("radio-station-2.png"),
        RECYCLE("recycle.png"),
        SATELLITE_DISH("satellite-dish.png"),
        SAWMILL_2("sawmill-2.png"),
        SEPTIC_TANK("septic_tank.png"),
        SHIPWRECK("shipwreck.png"),
        SOLARENERGY("solarenergy.png"),
        SPACEPORT_2("spaceport-2.png"),
        SPLICE("splice.png"),
        SUBMARINE_2("submarine-2.png"),
        SURVEYING_2("surveying-2.png"),
        WACO("waco.png"),
        WAREHOUSE_2("warehouse-2.png"),
        WATERTOWER("watertower.png"),
        WINDTURBINE("windturbine.png");

        private final String text;
        private Type(final String text) { this.text = text;}
        @Override
        public String toString() {return text;}
    }

    public IndustryIcon(LeafletMap linkedMap, Color color, Type type) {
        super(linkedMap, "mollet-industry/" + color + "/" + type, 16, 37, 0, -29);
    }

}
