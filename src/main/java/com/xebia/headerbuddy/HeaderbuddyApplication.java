package com.xebia.headerbuddy;

import com.xebia.headerbuddy.models.entities.Ecategory;
import com.xebia.headerbuddy.models.entities.Eheader;
import com.xebia.headerbuddy.models.entities.Eprofile;
import com.xebia.headerbuddy.models.entities.Evalue;
import com.xebia.headerbuddy.models.entities.repositories.EcategoryRepository;
import com.xebia.headerbuddy.models.entities.repositories.EheaderRepository;
import com.xebia.headerbuddy.models.entities.repositories.EprofileRepository;
import com.xebia.headerbuddy.models.entities.repositories.EvalueRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
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
    @Autowired
    EheaderRepository headerRepository;

    public static void main(String[] args) {
        SpringApplication.run(HeaderbuddyApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        //Creating entities
        Ecategory doCategory = new Ecategory("do");
        Ecategory dontCategory = new Ecategory("dont");
        Ecategory recommendationCategory = new Ecategory("recommendation");

        Eheader cspHeader = new Eheader("Content-Security-Policy");
        Eheader xframeHeader = new Eheader("X-Frame-Options");
        Eheader serverHeader = new Eheader("Server");
        Eheader referrerHeader = new Eheader("Referrer-Policy");

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

        Evalue cspDefaultsrc = new Evalue("default-src", "Use the server as fallback for the other fetch directives", doCategory, cspHeader);
        Evalue xframeDeny = new Evalue("DENY", "Completely deny loading in frames", doCategory, xframeHeader);
        Evalue xframeSameorigin = new Evalue("SAMEORIGIN", "Only load frames from the same page origin", doCategory, xframeHeader);
        Evalue xframeAllowfrom = new Evalue("ALLOW-FROM", "Only load frames from whitelisted sources", doCategory, xframeHeader);
        Evalue serverAny = new Evalue("*", "Hide this information to not make it too easy for attackers", dontCategory, serverHeader);
        Evalue referrerStrictOrigin = new Evalue("strict-origin", "Only set refrerer link for HTTPS connections", doCategory, referrerHeader);
        Evalue referrerUnsafeUrl = new Evalue("unsafe-url", "Always set referer url", recommendationCategory, referrerHeader);

        //Save data
        categoryRepository.save(doCategory);
        categoryRepository.save(dontCategory);
        categoryRepository.save(recommendationCategory);

        profileRepository.save(browserProfile);
        profileRepository.save(mobileProfile);

        valueRepository.save(cspDefaultsrc);
        valueRepository.save(xframeDeny);
        valueRepository.save(xframeSameorigin);
        valueRepository.save(xframeAllowfrom);
        valueRepository.save(serverAny);
        valueRepository.save(referrerStrictOrigin);
        valueRepository.save(referrerUnsafeUrl);
    }
}
