package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.entities.Eprofile;
import com.xebia.headerbuddy.models.entities.Evalue;
import java.util.HashSet;
import java.util.Set;

public class ProtocolHandler {

    private static String usedProtocol;

    public static Set<Evalue> getEvaluesByProtocol(Set<String> urls, Iterable<Eprofile> profiles, Iterable<Evalue> values) {
        Set<Evalue> correctEvalues = new HashSet<Evalue>();

        //Trim all the urls to just the protocols that are being used.
        Set<String> protocols = convertToProtocolSet(urls);

        //Define what protocol needs to be used for the analayzer
        String finalProtocol = getFinalProtocol(protocols, profiles);

        //Add all values by correct protocol profile.
        for (Evalue value : values) {
            for (Eprofile profile : value.getHeader().getProfiles()) {
                if (profile.getName().equals(finalProtocol)) {
                    correctEvalues.add(value);
                }
            }
        }

        return correctEvalues;
    }

    private static String getFinalProtocol(Set<String> protocols, Iterable<Eprofile> profiles) {
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
