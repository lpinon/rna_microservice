package com.capgemini.rna.tests.services;

import com.capgemini.rna.app.MainApplication;
import com.capgemini.rna.services.ParserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"test"})
@SpringBootTest(classes = MainApplication.class)
public class ParserServiceTests {

    @Autowired
    ParserService parserService;

    @Test
    public void whenReceiveAResultString_returnRNAGenes() {
        parserService.parseRNAMultilineString(test1String);
    }

    private static String test1String = ">NM_001170833 1\n" +
            "auggccuuucgcuccagcuugguggaaaaacaagaaacguuuaacuguuu\n" +
            "cgugaugagugacagagagaaaccaugugacaccaacgcguguccaacaa\n" +
            "ucgggcugaguaaagcggugucggugucucugggucuggauucgcugucg\n" +
            "ucuccgcuccacagccagugccccagcagcgcuuuugcagaccccgccga\n" +
            "cagcggaaaucaugguuuccgaggagugucagaguugggaacgccuggaa\n" +
            "augugaacucggaaggaaauggacuuguccuagcccgcagcagcccggca\n" +
            "cgggacaacuuuagagaagugugcgcuaauauucggcaggugagcugcgu\n" +
            "ggaucuguucagaucggggggaauggacugcgcgcagucuguuacgcgcg\n" +
            "gaucggucauauccagauucaucugcgaggaucccagcauguucgugaac\n" +
            "ccggcagcagagcucuccgcgacuucccaggucaaugaggucgcgccuuu\n" +
            "aaaaccauauucugcuuaucccgcuaauccagaccuguacagggagaacc\n" +
            "agggcggauggucaucauggugcgcaaacgagcgcauuuacagcgagcga\n" +
            "cccgagccccacagaggcgggucugacggacaucauuucuccugcaaaua\n" +
            "ucgcagcugugggccaacuccauucggguccagacaggaaugcaacugug\n" +
            "ugugguacaguagaggugcgcaaagcgggaaagguggcucacaagcagcg\n" +
            "auggcacaggguuacggccaaguggaaaguuacccagacgcgacucccca\n" +
            "agggcaaaacacuuuuuccaccaucaagaccgaaccuucaguuugggugg\n" +
            "acugcggcgaccgaacuuucaggcaugaggauuuguuuccaggagucuac\n" +
            "cuguccgaccgcagagugugccagguguguggugacgaugcuucaggcug\n" +
            "ucacuauggagcagucaccugcggcagcugcaaaguguucuuuaaaaggg\n" +
            "ccgcugcagguaagcagaaccaccugugugccagccggaacgacugcacc\n" +
            "aucgacaagcugaggaggaagaacugcgccucgugccguuugaagaggug\n" +
            "cuucaugucggggaugagccugaaaggccggaggcugaagggagcuggac\n" +
            "agacgaggaacggagaggaggagcagcagcccacugccugggggccuggg\n" +
            "gaaagggaagagcgaaagaaagauguuaucccggagccuggaaacauggc\n" +
            "uuccacggcucaagggccucaaggcuugccaccagggauccccccgacuc\n" +
            "ugcgcuccugcuuaucccuccugagcauccugcagucuauugaacccacu\n" +
            "cugguuaaugcgggacaugaccccgcccagccagacagccccucguccuu\n" +
            "gcuaaccagccucaacgagcucggagagaggcagcugguaaccguggugc\n" +
            "gcugggccaaggcaauuccagguuuccgugaucuccacguggaugaucag\n" +
            "augucagugauucagcugucguggaugggggugaugguguuggcguuagg\n" +
            "guggagauccuacacucucaccaacugcuccaugcucuacuuugcuccag\n" +
            "aucugguuuuuaacgaccagcggaugcaaaucuccaguauguacgaacac\n" +
            "ugcgugaggaugaagcuccucgcccaacgcuuccaccggcuggaggugac\n" +
            "cgaggaggaguuccucugcaugaaggcccucguccucuucagcaucuugc\n" +
            "caguagagggccugaagagccagcgcuguuuugaugaacugcggaccccc\n" +
            "uacaucaaggagcuggaucggcuggccagccaccgcggggagaccacccg\n" +
            "aacacagaggcuguuucagcucacggagcugcuggacuaccuccagucgg\n" +
            "uugugaggaaacugcaccaguucaccuaugaucucuucauccaagcucag\n" +
            "ucccugcagacgcgugucaacuuccccgagaugaucucggagauugucag\n" +
            "uguucaugugcccaaaauccucucaggcauggucaagcccauccuuuucc\n" +
            "acaacacagccuag";
}