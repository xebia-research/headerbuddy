package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.entities.Eprofile;
import com.xebia.headerbuddy.models.entities.Evalue;
import java.util.HashSet;
import java.util.Set;

public class ProtocolHandler {

    private static String usedProtocol;

    public static Set<Evalue> getEvaluesByProtocol(Set<String> urls, String profileParameter, Iterable<Eprofile> profiles, Iterable<Evalue> values) {

        //Trim all the urls to just the protocols that are being used.
        Set<String> protocols = convertToProtocolSet(urls);

        //Define what protocol needs to be used for the analayzer
        getFinalProtocol(profileParameter, protocols, profiles);

        // Retrieve relevant values
        Set<Evalue> relevantValues = getRelevantValuesByProfile(values);

        return relevantValues;
    }

    private static void getFinalProtocol(String profile, Set<String> protocols, Iterable<Eprofile> profiles) {

        switch (profile.toLowerCase()) {
            case "mobile":
                usedProtocol = "mobile";
                break;
            default:
                usedProtocol = getWebProtocol(protocols, profiles);
        }
    }

    private static String getWebProtocol(Set<String> protocols, Iterable<Eprofile> profiles) {
        Set<String> foundProtocols = new HashSet<String>();

        //Check if the database has knowledge over the found protocols.
        for (String protocol : protocols) {
            for (Eprofile profile : profiles) {
                if (profile.getName().equals(protocol) && !foundProtocols.contains(protocol)) {
                    foundProtocols.add(protocol);
                }
            }
        }

        //Define what protocol needs to be used.
        String protocol;

        if (foundProtocols.contains("https")) {
            protocol = "https";
        } else {
            protocol = "http";
        }

        usedProtocol = protocol;
        return protocol;
    }

    private static Set<Evalue> getRelevantValuesByProfile(Iterable<Evalue> values) {
        Set<Evalue> relevantValues = new HashSet<Evalue>();

        //Add all values by correct protocol profile.
        for (Evalue value : values) {
            for (Eprofile profile : value.getHeader().getProfiles()) {
                if (profile.getName().equals(usedProtocol)) {
                    relevantValues.add(value);
                }
            }
        }

        return relevantValues;
    }

    private static Set<String> convertToProtocolSet(Set<String> stringUrls) {
        Set<String> protocols = new HashSet<String>();
        for (String stringUrl : stringUrls) {
            String trimmedToProtocol = stringUrl.split(":")[0];
            protocols.add(trimmedToProtocol);
        }
        return protocols;
    }

    public static String getUsedProtocol() {
        return usedProtocol;
    }
}
