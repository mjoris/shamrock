package be.kuleuven.shamrock.interactivemap;

/*
* REFERRED ICONS ORIGINALLY DESIGNED/PUBLISHED BY Nicolas Mollet
 * UNDER CC 3.0 BY-SA License
* http://mapicons.nicolasmollet.com/
* */
public class TransportationIcon extends Icon {
    public enum Color {
        ORANGE("orange");

        private final String text;
        private Color(final String text) { this.text = text;}
        @Override
        public String toString() {return text;}
    }

    public enum Type {
        ACCESDENIED("accesdenied.png"),
        AIRCRAFTSMALL("aircraftsmall.png"),
        AIRPORT("airport.png"),
        AIRPORT_APRON("airport_apron.png"),
        AIRPORT_RUNWAY("airport_runway.png"),
        AIRPORT_TERMINAL("airport_terminal.png"),
        AMBULANCE("ambulance.png"),
        BARRIER("barrier.png"),
        BOAT("boat.png"),
        BULLDOZER("bulldozer.png"),
        BUS("bus.png"),
        BUSSTOP("busstop.png"),
        CABLECAR("cablecar.png"),
        CAR("car.png"),
        CARACCIDENT("caraccident.png"),
        CARRENTAL("carrental.png"),
        CARWASH("carwash.png"),
        CAR_SHARE("car_share.png"),
        CAUTION("caution.png"),
        CCTV("cctv.png"),
        CLOSEDROAD("closedroad.png"),
        CONSTRUCTION("construction.png"),
        CONVERTIBLE("convertible.png"),
        CROSSINGGUARD("crossingguard.png"),
        CURVELEFT("curveleft.png"),
        CUSTOMS("customs.png"),
        CYCLING("cycling.png"),
        DESCENT("descent.png"),
        DIRECTION_DOWN("direction_down.png"),
        DIRECTION_UTURN("direction_uturn.png"),
        DISABILITY("disability.png"),
        DUCATI_DIAVEL("ducati-diavel.png"),
        E_BIKE_CHARGING("e-bike-charging.png"),
        FALLINGROCKS("fallingrocks.png"),
        FERRY("ferry.png"),
        FILLINGSTATION("fillingstation.png"),
        FLAGMAN("flagman.png"),
        FOOTPRINT("footprint.png"),
        FOURBYFOUR("fourbyfour.png"),
        FUNICOLAR_22X22("funicolar-22x22.png"),
        HARBOR("harbor.png"),
        HELICOPTER("helicopter.png"),
        HELIPAD("helipad.png"),
        HIGHWAY("highway.png"),
        HOTAIRBALOON("hotairbaloon.png"),
        ICY_ROAD("icy_road.png"),
        JEEP("jeep.png"),
        JUNCTION("junction.png"),
        KINGAIR("kingair.png"),
        LEVELCROSSING("levelcrossing.png"),
        MAINROAD("mainroad.png"),
        MAXHEIGHT("maxheight.png"),
        MAXWEIGHT("maxweight.png"),
        MAXWIDTH("maxwidth.png"),
        METANO_ICON("metano-icon.png"),
        MOTORCYCLE("motorcycle.png"),
        OIL_2("oil-2.png"),
        PARKANDRIDE("parkandride.png"),
        PARKING_METER_EXPORT("parking-meter-export.png"),
        PARKINGGARAGE("parkinggarage.png"),
        PARKING_BICYCLE_2("parking_bicycle-2.png"),
        PEDESTRIANCROSSING("pedestriancrossing.png"),
        PICKUP("pickup.png"),
        PICKUP_CAMPER("pickup_camper.png"),
        PLOWTRUCK("plowtruck.png"),
        REPAIR("repair.png"),
        ROAD("road.png"),
        ROADTYPE_GRAVEL("roadtype_gravel.png"),
        SLIPWAY("slipway.png"),
        SOLAR_CRUISE("solar-cruise.png"),
        SPEEDHUMP("speedhump.png"),
        SPEED_50("speed_50.png"),
        SPORTSCAR("sportscar.png"),
        SPORTUTILITYVEHICLE("sportutilityvehicle.png"),
        STARGATE_RAW("stargate-raw.png"),
        STEAMTRAIN("steamtrain.png"),
        STOP("stop.png"),
        TAXI("taxi.png"),
        TAXIBOAT("taxiboat.png"),
        TAXIWAY("taxiway.png"),
        TIDALDIAMOND("tidaldiamond.png"),
        TIRES("tires.png"),
        TOLLSTATION("tollstation.png"),
        TRAFFICCAMERA("trafficcamera.png"),
        TRAFFICLIGHT("trafficlight.png"),
        TRAIN("train.png"),
        TRAMWAY("tramway.png"),
        TRUCK3("truck3.png"),
        TUNNEL("tunnel.png"),
        UNDERGROUND("underground.png"),
        VAN("van.png"),
        VESPA("vespa.png"),
        WATERCRAFT("watercraft.png"),
        WORLDWILDWAY("worldwildway.png");

        private final String text;
        private Type(final String text) { this.text = text;}
        @Override
        public String toString() {return text;}
    }

    public TransportationIcon(LeafletMap linkedMap, Color color, Type type) {
        super(linkedMap, "mollet-transportation/" + color + "/" + type, 16, 37, 0, -29);
    }

}
