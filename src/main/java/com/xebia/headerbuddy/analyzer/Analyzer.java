package com.xebia.headerbuddy.analyzer;

import com.xebia.headerbuddy.models.entities.Evalue;
import java.util.Set;

public interface Analyzer {

    Set<Evalue> analyze(Set<Evalue> toAnalyze, Set<Evalue> toCompare);
}
