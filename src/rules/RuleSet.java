package rules;

import java.util.*;
import parsing.*;

public class RuleSet {
	
	// private fields (lists)
	private final HashMap<Pos, List<BinarySyntacticRule>> birules_;
	private final HashMap<Pos, UnarySyntacticRule> unrules_;
	private final HashMap<Pos, List<AgreementRule>> agrules_;
	private final HashMap<Pos, List<SemanticRule>> semrules_;

	public RuleSet(HashMap<Pos, List<BinarySyntacticRule>> birules, HashMap<Pos, UnarySyntacticRule> unrules,
			HashMap<Pos, List<AgreementRule>> agrules, HashMap<Pos, List<SemanticRule>> semrules) {
		birules_ = birules;
		unrules_ = unrules;
		agrules_ = agrules;
		semrules_ = semrules;
	}
	
	// GETTERS
	public HashMap<Pos, List<BinarySyntacticRule>> getBiRules() {
		return birules_;
	}
	
	public HashMap<Pos, UnarySyntacticRule> getUnRules() {
		return unrules_;
	}
	
	public HashMap<Pos, List<AgreementRule>> getAgRules() {
		return agrules_;
	}
	
	public HashMap<Pos, List<SemanticRule>> getSemRules() {
		return semrules_;
	}
	
}
