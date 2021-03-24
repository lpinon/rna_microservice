package com.capgemini.rna.tests.services;

import com.capgemini.rna.app.MainApplication;
import com.capgemini.rna.services.ParserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = MainApplication.class)
public class ParserServiceTests {

    @Autowired
    ParserService parserService;

    @Test
    public void whenReceiveAResultString_returnRNAGenes() {
        parserService.parseRNAMultilineString(exampleString, "a");
        parserService.parseRNAMultilineString(test3String, "a");
        parserService.parseRNAMultilineString(test2String, "a");
        parserService.parseRNAMultilineString(test1String, "a");
    }



    private static String exampleString = ">NM_0002\n" +
            "augugcgag gacugcuga \n" +
            ">NM_0003 \n" +
            "augugcgaguag";

    private static String test3String = ">NM_001309024 1\n" +
            "cugacaugagcaaggagaagcagugcaggccggguccccgggugcccaag\n" +
            "ugcucccgcugccggaaccacggcuugaagaccccgcugaaaggccacaa\n" +
            "gcgcuucugccgcuggaaagacugccagugcuccaaaugcgagcagaucg\n" +
            "ugguccgccagagagucauggcggcgcaggucgccgauaggaggcagcag\n" +
            "gcucaagaggaggagcuugggauuuguaguccagaggccuuguccggccc\n" +
            "ugaagugguggugaagaaugaagccggagccgacugucucuucuccaugg\n" +
            "agggacgauccggcacgcuagccaucccccccaaccccaauccucugucu\n" +
            "gccgcagggagcuacucugcuucaucguccagcccaucggcugcggcccg\n" +
            "uguuuacggcgaggaagcgucugaccgccugcuggaaaccuccuacuaca\n" +
            "acuucuaccagccuuccagauauuccuccuauuauggaaaccuguacaac\n" +
            "uaccagcaguaccagaugccucccuccgacggccaccuguccggccacag\n" +
            "caugcccucucaguaccgcacgcacuccuucuaccccgggaccaccuacc\n" +
            "ugccccagggccugggcucgccggggccgccguacuucagccuggaggac\n" +
            "aaugacgacgcagccgccuccuucuuccccagcagccucaccuccaccca\n" +
            "ugacuccacccugaccuaccgcuccaucagcucacuagugaacgacggcg\n" +
            "ucaaggcagaguuugagagcggcggccagccgcccgucuucccggccgac\n" +
            "uccaugagcagugaaaccaaaugaggagaagcugcgaggcguuggaugga\n" +
            "ugacgucugcugcagguuucugacuuuggaaccacauuuuaugucuauuu\n" +
            "uuaguucaaguaaaauucaauccuauuuuggaacaaguagacagaaaauu\n" +
            "cagacaaaugaaaacuucuuuagaaucaguagauguuggucacaaugauc\n" +
            "aaaaagcugaauuuaaaacucuuuucuuuauaaacuguuaaaaaacaaag\n" +
            "uucauuuucuagagaugaaagugaaaaaucaagaauaaaaaaugagaugc\n" +
            "aaaaaaaaaaaaaaa\n" +
            ">NM_001309009 2\n" +
            "cgugugcaggcggaguuaagcgcgcacagaagaaaugcuggggagcgcgg\n" +
            "gcacaggugugagugagcgcgcagagaccgaccggauucaucagaggcaa\n" +
            "augaaagaguaauuuaucagacaaagguuuuugugagguuuaucuguuca\n" +
            "gcucuguauauaaaagguuacagucuagauucuauuaacgucacaguuua\n" +
            "gggaaugaggauuuuggggguuuugagcgucauuuucuucgguaugaaca\n" +
            "gcgcaacggcugucacgcauucgcugcgguaccugcacacggcagcuuca\n" +
            "ggaaucccagcauucccagaguuugugacggugguccugguggacggaca\n" +
            "gcccuucucuuacuaugacagcagcaucaaaagggagcuucccaggcaga\n" +
            "ccuggacgguccaaaccgaagacccgaauuucugggagagacgcacuaag\n" +
            "agcuccauuguugacgaacaagucuucaaaaguaaaguggagaugauuaa\n" +
            "accaagcuuuaaccagacaggagguguucacauuauucagcugauguacg\n" +
            "gcugugagugggaugaugagacuggaagcaucaagggcugguggcaguuu\n" +
            "gguuauaauggagaagaccucauggcuuuggaccugaagacgaacacggg\n" +
            "gacggcacagacuccagcugccaccuuccauaaacacaaaaaggacaaag\n" +
            "auaaaggcugggccauacgaaugaacaccuaucugucccagggguguccu\n" +
            "gaucaucuuaagaaguaugugagcuaugggaagagcacauugcugagaac\n" +
            "agagacuccaucaguguuucuccuccagaagaauuuccuguccccgguca\n" +
            "gaugucacgcuacaggcuuuuaccccaacagagcugugauguucuggagg\n" +
            "aaagauggaaaggagauaaccgaaggaguggagaccggagagauccuccc\n" +
            "aaacaaugacgggaccuuucagaugagcacugaucugaaacuuuuauuag\n" +
            "acacaacagaaaagaagagguacgaauguguuuuucagcuuucuggugca\n" +
            "aaugaaagcaucaucaccaaacuggagcagucaaaaaucaagaccaaugg\n" +
            "agcaaaccccuaugaagugaccaucuccguuguuauugcagucauuauuu\n" +
            "uugcugcugucauugcugacuggcucauuguuuauaaaggcagaaaagcu\n" +
            "gcagaucacuugaacguaaagaagcaaaugcaucuuugagucuggaugca\n" +
            "aaugaaacucugcacucaguuuucaagugaagucugcacugaaaaggcaa\n" +
            "cucuaauaauaucauggauaaacuauguguuuauaacaaauuaucugauu\n" +
            "uaaucugguagaugugucagaaauuuacaauguugcaauuguuugauuuu\n" +
            "aaagagaaauuuauuugccauuuuucuuuagcacaauaaaugagucucau\n" +
            "augauuaauuuuuguuuccaaacuggaguuugguguugaauauugucaaa\n" +
            "ggaaccaaaa\n" +
            ">NM_001104817 1\n" +
            "acccaggaaaauguggaaagcaccaugcuugacuuugcuccugcuggugg\n" +
            "uuuugaugaacaggguggagugcaacgauggccaucccucuuucuacgga\n" +
            "gugaaacuuuguggaagagaauucauucgagccgucaucuucaccugugg\n" +
            "aggcucgcgcuggaggagaagugugggagacucagaggagacguuugauc\n" +
            "cguggaaaacaagucccaucucucagcucaacucggaucaggauccagaa\n" +
            "aagucccaagcauggaaagaccaaauucuugacguugcuucaguggcugc\n" +
            "ugguuucagccgcucugcucgcucuccgguuucagaugaaguccuggagg\n" +
            "cucuucgaaguguggacaggaaaggacgggacguugugcugggacugucc\n" +
            "aacgccugcugcaaguggggaugcagcaagagugagaucagcucucuaug\n" +
            "cugaaccugagaac\n";

    private static String test2String = ">NM_001170833 1\n" +
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