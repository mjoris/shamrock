package be.kuleuven.shamrock.interactivemap;

import netscape.javascript.JSObject;

import java.util.List;

/**
* Created by Joris on 18/08/2014.
*/
public class LeafletObject {
    protected String jsVariableName;
    protected LeafletMap linkedMap = null;

    private static int varCounter = 0;

    LeafletObject(LeafletMap linkedMap) {
        this.jsVariableName = "v"+(varCounter++);
        this.linkedMap = linkedMap;
    }

    protected void create(String command, Object ... argsAndOptions) {
        String script = "var "+ this.jsVariableName + " = " + "L." + command + "(" + parseArgsAndOptions(argsAndOptions) + ");";
        this.linkedMap.doScript(script);
    }

    protected void executeMethod(String command, Object ... argsAndOptions) {
        this.linkedMap.doScript(this.jsVariableName + "."+ command + "(" + parseArgsAndOptions(argsAndOptions) + ");");
    }

    protected Object tryGetMethod(String command, Object ... argsAndOptions) {
        return this.linkedMap.tryScript(this.jsVariableName + "."+ command + "(" + parseArgsAndOptions(argsAndOptions) + ");");
    }


    @Override
    public String toString() {
        return this.jsVariableName;
    }

    // *** JAVASCRIPT PARSING CODE

    private static void singleQuoteEncode(String s) {
        s.replace("'", "\\'");
        s.replace("\\", "\\\\");
        Object[] test = new String[5];
    }

    private static String parseArg(Object o)  {
        //System.out.println("############"+o.getClass());
        if (o instanceof String) {
            String s = (String)(o);
            singleQuoteEncode(s);
            return "'" + s + "'";
        } else if (o instanceof List) {
            String res = "";
            for (Object o1 : (List)(o)) {
                if (res.length() != 0) {
                    res += ", ";
                }
                res += parseArg(o1);
            }

            return "[" + res + "]";
        }
        else return o.toString(); // e.g. numbers, bare code, JsOption, JsVariable, LatLng, ...
    }

    private static String parseArgsAndOptions(Object[] argsAndOptions) {
        String args = "";
        String options = "";
        for (Object o : argsAndOptions) {
            if (o instanceof JsOption) {
                if (options.length() != 0) {
                    options += ", ";
                }
                options += o.toString();
            } else {
                if (args.length() != 0) {
                    args += ", ";
                }
                args += parseArg(o);
            }
        }
        if (options.length() != 0) {
            if (args.length() != 0)
                args += ", ";
            args += ("{" + options + "}");
        }
        return args;
    }

    // *** CLASSES ONLY USED TO MODEL JS/LEAFLET OPTIONS AND ARGUMENTS

    static class BareCode {
        private String s;
        public BareCode(String s) {
            this.s = s;
        }
        @Override
        public String toString() {
            return s;
        }
    }

    static class Point {
        int x;
        int y;
        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "[" + this.x +  ", " + this.y + "]";
        }
    }

    static class Bounds {
        double lat1;
        double lng1;
        double lat2;
        double lng2;
        public Bounds (double lat1, double lng1, double lat2, double lng2) {
            this.lat1 = lat1;
            this.lng1 = lng1;
            this.lat2 = lat2;
            this.lng2 = lng2;
        }
        @Override
        public String toString() {
            return "[[" + lat1 +  ", " + lng1 + "], [" + lat2 +  ", " + lng2 + "]]";
        }
    }

    static class JsOption {
        private String label;
        private Object arg;
        public JsOption(String label, Object arg) {
            this.label = label;
            this.arg = arg;
        }
        @Override
        public String toString() {
            return label + ": " + parseArg(arg);
        }
    }

}