package com.xebia.headerbuddy;

import com.xebia.headerbuddy.models.entities.Ecategory;
import com.xebia.headerbuddy.models.entities.Eheader;
import com.xebia.headerbuddy.models.entities.Eprofile;
import com.xebia.headerbuddy.models.entities.Evalue;
import com.xebia.headerbuddy.models.entities.repositories.EcategoryRepository;
import com.xebia.headerbuddy.models.entities.repositories.EprofileRepository;
import com.xebia.headerbuddy.models.entities.repositories.EvalueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class HeaderbuddyApplication implements CommandLineRunner {

    @Autowired
    EcategoryRepository categoryRepository;
    @Autowired
    EvalueRepository valueRepository;
    @Autowired
    EprofileRepository profileRepository;

    public static void main(String[] args) {
        SpringApplication.run(HeaderbuddyApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {

        //========================================
        //========== Creating lists ==============
        //========================================
        List<Ecategory> categories = new ArrayList<>();
        List<Eprofile> profiles = new ArrayList<>();
        List<Evalue> values = new ArrayList<>();

        //========================================
        //========== Creating entities ===========
        //========================================

        //========== Headers ===============
        Eheader cspHeader = new Eheader("Content-Security-Policy");
        Eheader xframeHeader = new Eheader("X-Frame-Options");
        Eheader serverHeader = new Eheader("Server");
        Eheader referrerHeader = new Eheader("Referrer-Policy");

        //========== Profiles with associated headers ===============
        Eprofile browserProfile = new Eprofile("browser", new HashSet<Eheader>(){{
            add(cspHeader);
            add(xframeHeader);
            add(serverHeader);
            add(referrerHeader);
        }});

        Eprofile mobileProfile = new Eprofile("mobile", new HashSet<Eheader>(){{
            add(cspHeader);
            add(serverHeader);
            add(referrerHeader);
        }});

        profiles.addAll(Arrays.asList(browserProfile, mobileProfile));

        //========== Categories ===========
        Ecategory doCategory = new Ecategory("do");
        Ecategory dontCategory = new Ecategory("dont");
        Ecategory recommendationCategory = new Ecategory("recommendation");

        categories.addAll(Arrays.asList(doCategory, dontCategory, recommendationCategory));

        //========== Header values with associated header and category ===============
        Evalue cspDefaultsrc = new Evalue("default-src", "Use the server as fallback for the other fetch directives", doCategory, cspHeader);
        Evalue xframeDeny = new Evalue("DENY", "Completely deny loading in frames", doCategory, xframeHeader);
        Evalue xframeSameorigin = new Evalue("SAMEORIGIN", "Only load frames from the same page origin", doCategory, xframeHeader);
        Evalue xframeAllowfrom = new Evalue("ALLOW-FROM", "Only load frames from whitelisted sources", doCategory, xframeHeader);
        Evalue serverAny = new Evalue("*", "Hide this information to not make it too easy for attackers", dontCategory, serverHeader);
        Evalue referrerStrictOrigin = new Evalue("strict-origin", "Only set refrerer link for HTTPS connections", doCategory, referrerHeader);
        Evalue referrerUnsafeUrl = new Evalue("unsafe-url", "Always set referer url", recommendationCategory, referrerHeader);

        values.addAll(Arrays.asList(cspDefaultsrc, xframeDeny, xframeSameorigin, xframeAllowfrom, serverAny, referrerStrictOrigin, referrerUnsafeUrl));

        //========================================
        //=========== Saving the data ============
        //========================================

        for(Eprofile profile : profiles)
            profileRepository.save(profile);

        for(Ecategory categorie : categories)
            categoryRepository.save(categorie);

        for(Evalue value : values)
            valueRepository.save(value);
    }
}
