package com.capgemini.rna.services;

import com.capgemini.rna.models.Codon;
import com.capgemini.rna.models.Gen;
import com.capgemini.rna.models.exceptions.AParserHandledException;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@Scope("singleton")
@Log
public class StoreService {

    private HashMap<String, ArrayList<Gen>> computedGens = new HashMap<>();
    private HashMap<String, Gen> currentGen = new HashMap<>();
    private HashMap<String, ArrayList<AParserHandledException>> exceptions = new HashMap<>();
    private HashMap<String, String> missingChars = new HashMap<>();

    public void addCodon(Codon codon, String id) {
        this.checkCurrentGenExists(id);
        this.currentGen.get(id).addCodon(codon);
    }

    public Gen getCurrentGen(String id) {
        this.checkCurrentGenExists(id);
        return this.currentGen.get(id);
    }

    public void initNewGen(String id) {
        this.currentGen.put(id, new Gen());
    }

    private void checkCurrentGenExists(String id) {
        if(!this.currentGen.containsKey(id)){
            this.initNewGen(id);
        }
    }

    // COMPUTED GENS

    private void checkComputedGroupExists(String id) {
        if (!this.computedGens.containsKey(id)) {
            this.initNewComputedGroup(id);
        }
    }

    public void initNewComputedGroup(String id) {
        this.computedGens.put(id, new ArrayList<>());
    }

    public void saveAsComputed(String id) {
        this.checkComputedGroupExists(id);
        this.computedGens.get(id).add(this.currentGen.get(id));
    }

    public ArrayList<Gen> getComputed(String id) {
        this.checkComputedGroupExists(id);
        return this.computedGens.get(id);
    }

    // EXCEPTIONS

    private void checkExceptionGroupExists(String id) {
        if (!this.exceptions.containsKey(id)) {
            this.initNewExceptionsGroup(id);
        }
    }

    public void initNewExceptionsGroup(String id) {
        this.exceptions.put(id, new ArrayList<>());
    }

    public void saveException(AParserHandledException exception, String id) {
        this.checkExceptionGroupExists(id);
        this.exceptions.get(id).add(exception);
    }

    public ArrayList<AParserHandledException> getExceptions(String id) {
        this.checkExceptionGroupExists(id);
        return this.exceptions.get(id);
    }

    private void checkMissingCharsGroupExists(String id) {
        if(!this.missingChars.containsKey(id)) {
            this.cleanMissingChars(id);
        }
    }

    public String getMissingChars(String id) {
        this.checkMissingCharsGroupExists(id);
        return this.missingChars.get(id);
    }

    public void cleanMissingChars(String id) {
        this.missingChars.put(id, "");
    }

    public void setMissingChars(String substring, String id) {
         this.missingChars.put(id, substring);
    }
}
