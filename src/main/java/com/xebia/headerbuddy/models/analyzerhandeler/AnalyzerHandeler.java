package com.xebia.headerbuddy.models.analyzerhandeler;

import com.xebia.headerbuddy.models.entities.Evalue;

import java.util.Set;

public abstract class AnalyzerHandeler {

    public abstract Set<Evalue> detectMissingHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> toCompareValues);

    public abstract Set<Evalue> detectHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> toCompareValues);
}
