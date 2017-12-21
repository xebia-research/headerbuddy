package com.xebia.headerbuddy.models.analyzerhandeler;

import com.xebia.headerbuddy.models.entities.Evalue;

import java.util.Set;

public class DoHandeler extends AnalyzerHandeler {
    @Override
    public Set<Evalue> detectMissingHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> toCompareValues) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Evalue> detectHeaders(Set<Evalue> toAnalyseValues, Set<Evalue> toCompareValues) {
        throw new UnsupportedOperationException();
    }
}

